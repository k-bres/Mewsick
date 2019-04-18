// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.0
//
// <auto-generated>
//
// Generated from file `Streaming.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.example.mewsick.Middleware;

public interface Streaming extends com.zeroc.Ice.Object
{
    boolean ajouter(String titre, String artiste, String album, String localisation, com.zeroc.Ice.Current current);

    boolean supprimer(String titre, String artiste, com.zeroc.Ice.Current current);

    Morceau[] rechercher(String titre, String artiste, String album, com.zeroc.Ice.Current current);

    Morceau[] afficherMorceaux(com.zeroc.Ice.Current current);

    void jouer(String titre, String artiste, com.zeroc.Ice.Current current);

    void stop(com.zeroc.Ice.Current current);

    int abonnement(com.zeroc.Ice.Current current);

    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::Middleware::Streaming"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Middleware::Streaming";
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_ajouter(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_titre;
        String iceP_artiste;
        String iceP_album;
        String iceP_localisation;
        iceP_titre = istr.readString();
        iceP_artiste = istr.readString();
        iceP_album = istr.readString();
        iceP_localisation = istr.readString();
        inS.endReadParams();
        boolean ret = obj.ajouter(iceP_titre, iceP_artiste, iceP_album, iceP_localisation, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeBool(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_supprimer(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_titre;
        String iceP_artiste;
        iceP_titre = istr.readString();
        iceP_artiste = istr.readString();
        inS.endReadParams();
        boolean ret = obj.supprimer(iceP_titre, iceP_artiste, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeBool(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_rechercher(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_titre;
        String iceP_artiste;
        String iceP_album;
        iceP_titre = istr.readString();
        iceP_artiste = istr.readString();
        iceP_album = istr.readString();
        inS.endReadParams();
        Morceau[] ret = obj.rechercher(iceP_titre, iceP_artiste, iceP_album, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        MorceauxHelper.write(ostr, ret);
        ostr.writePendingValues();
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_afficherMorceaux(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        Morceau[] ret = obj.afficherMorceaux(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        MorceauxHelper.write(ostr, ret);
        ostr.writePendingValues();
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_jouer(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_titre;
        String iceP_artiste;
        iceP_titre = istr.readString();
        iceP_artiste = istr.readString();
        inS.endReadParams();
        obj.jouer(iceP_titre, iceP_artiste, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_stop(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        obj.stop(current);
        return inS.setResult(inS.writeEmptyParams());
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_abonnement(Streaming obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        int ret = obj.abonnement(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    final static String[] _iceOps =
    {
        "abonnement",
        "afficherMorceaux",
        "ajouter",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "jouer",
        "rechercher",
        "stop",
        "supprimer"
    };

    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_abonnement(this, in, current);
            }
            case 1:
            {
                return _iceD_afficherMorceaux(this, in, current);
            }
            case 2:
            {
                return _iceD_ajouter(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 7:
            {
                return _iceD_jouer(this, in, current);
            }
            case 8:
            {
                return _iceD_rechercher(this, in, current);
            }
            case 9:
            {
                return _iceD_stop(this, in, current);
            }
            case 10:
            {
                return _iceD_supprimer(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}