package com.example.mewsick;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jackandphantom.blurimage.BlurImage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity
{
	private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
	private boolean enEcoute = false, paused = false;

	private TextView artisteAlbum, morceau, tempsActuel, tempsTotal, texteCommande;
	private ImageView albumArt, enregistrer;
	private SeekBar progress;

	private int morceauActuel, duree, dureePassee;
	private String titre, artiste, album;
	private Bitmap image;

	private AudioManager audioManager = null;
	private MediaPlayer mp = null;
	private MediaMetadataRetriever mmr = null;
	private Handler durationHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pocket_sphinx);

		morceauActuel = R.raw.strawberry_girls_betelgeuse;
		durationHandler = new Handler();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		verifierPermissions();
		lierInterface();
		initMedia();
		charger(morceauActuel);

		enregistrer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!enEcoute)
					ecouter();
				else
					stop();
			}
		});
	}

	private void verifierPermissions()
	{
		int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);

		if (permissionCheck != PackageManager.PERMISSION_GRANTED)
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
	}

	private void lierInterface()
	{
		artisteAlbum = findViewById(R.id.artisteAlbum);
		morceau = findViewById(R.id.morceau);
		tempsActuel = findViewById(R.id.tempsActuel);
		tempsTotal = findViewById(R.id.tempsTotal);
		albumArt = findViewById(R.id.albumArt);
		enregistrer = findViewById(R.id.enregistrer);
		progress = findViewById(R.id.progress);
		texteCommande = findViewById(R.id.commande);
	}

	private void initMedia()
	{
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mmr = new MediaMetadataRetriever();
	}

	private void charger(int fichier)
	{
		if (mp != null)
			mp.reset();

		final AssetFileDescriptor afd = getResources().openRawResourceFd(fichier);

		mp = MediaPlayer.create(this, fichier);
		mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

		titre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
		artiste = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toUpperCase();
		album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toUpperCase();
		duree = mp.getDuration();

		byte[] data = mmr.getEmbeddedPicture();
		image = BitmapFactory.decodeByteArray(data, 0, data.length);

		artisteAlbum.setText(String.format("%s - %s", artiste, album));
		morceau.setText(titre);
		albumArt.setImageBitmap(image);
		tempsTotal.setText(String.format("%01d:%02d", (mp.getDuration() / 1000) / 60, (mp.getDuration() / 1000) % 60));

		progress.setMax(mp.getDuration());
		progress.setClickable(false);

		blurBG();
	}

	private void blurBG()
	{
		BlurImage.with(getApplicationContext())
				.load(image)
				.intensity(20)
				.Async(true)
				.into((ImageView) findViewById(R.id.backgroundImg));
	}

	private void stop()
	{
		enregistrer.setAlpha(1f);
		enEcoute = false;
	}

	private void ecouter()
	{
		if (mp.isPlaying())
		{
			pause();
			paused = true;
		}

		enregistrer.setAlpha(0.2f);
		enEcoute = true;

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Je vous écoute...");

		try
		{
			startActivityForResult(intent, 666);
		}
		catch (ActivityNotFoundException a)
		{
			Toast.makeText(getApplicationContext(),"Oups il manque un truc !", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		stop();

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 666 && resultCode == RESULT_OK && null != data)
		{
			ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			String commande = result.get(0);

			String rep = httpPostRequest("http://192.168.0.5:8080/server.php", commande);

			JSONParser parser = new JSONParser();
			JSONObject json;

			try
			{
				json = (JSONObject) parser.parse(rep);

				faireAction(json);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		else if (paused)
		{
			jouer();
			paused = false;
		}
	}

	private void jouer()
	{
		mp.start();
		progress.setProgress(mp.getCurrentPosition());
		durationHandler.postDelayed(updateSeekBarTime, 100);
	}

	private void pause()
	{
		mp.pause();
	}

	private void next()
	{
		if (morceauActuel == R.raw.strawberry_girls_betelgeuse)
			morceauActuel = R.raw.queen_dont_stop_me_now;
		else
			morceauActuel = R.raw.strawberry_girls_betelgeuse;

		charger(morceauActuel);
	}

	private void monterVolume()
	{
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
	}

	private void baisserVolume()
	{
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
	}

	private void partyMode()
	{
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
	}

	private void avancer()
	{
		if (mp.getCurrentPosition() + 10000 <= duree)
			mp.seekTo(mp.getCurrentPosition() + 10000);
		else
			mp.seekTo(duree);
	}

	private Runnable updateSeekBarTime = new Runnable()
	{
		public void run()
		{
			dureePassee = mp.getCurrentPosition();
			progress.setProgress(dureePassee);
			tempsActuel.setText(String.format("%01d:%02d", (mp.getCurrentPosition() / 1000) / 60, (mp.getCurrentPosition() / 1000) % 60));

			durationHandler.postDelayed(this, 100);
		}
	};

	/* https://stackoverflow.com/questions/38408121/how-to-send-http-post-request-from-android */
	public static String httpPostRequest(String url, String cmd)
	{
		String response = "";
		BufferedReader reader = null;
		HttpURLConnection conn = null;

		try
		{
			URL urlObj = new URL(url);

			conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

			String data = "&" + URLEncoder.encode("cmd", "UTF-8") + "=" + URLEncoder.encode(cmd, "UTF-8");

			wr.write(data);
			wr.flush();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null)
			{
				sb.append(line).append("\n");
			}

			response = sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();

				if (conn != null)
					conn.disconnect();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		return response;
	}

	private void faireAction(JSONObject json)
	{
		String action;

		if ((action = (String) json.get("cmd")) != null)
		{
			switch (action)
			{
				case "stop":
					break;

				case "play":
					jouer();
					break;

				case "next":
					next();
					jouer();
					break;

				case "move":
					if ((int) json.get("arg") == 10)
						avancer();
					else if ((int) json.get("arg") == -10)
						//reculer();

						if (paused)
							jouer();
					break;

				case "vol":
					if ((json.get("arg")).equals("+"))
						monterVolume();
					else if ((json.get("arg")).equals("-"))
						baisserVolume();
					else if ((json.get("arg")).equals("++"))
						partyMode();

					if (paused)
						jouer();
					break;

				default:
					Toast.makeText(getApplicationContext(),"Désolé je n'ai pas compris", Toast.LENGTH_SHORT).show();
					if (paused)
					{
						jouer();
						paused = false;
					}
					break;
			}

			texteCommande.setText(String.format("cmd : %s", action));
		}

	}
}