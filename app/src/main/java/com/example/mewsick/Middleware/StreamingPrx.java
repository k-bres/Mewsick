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

public interface StreamingPrx extends com.zeroc.Ice.ObjectPrx
{
    default boolean ajouter(int piste, String titre, String artiste, String album, String localisation)
    {
        return ajouter(piste, titre, artiste, album, localisation, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean ajouter(int piste, String titre, String artiste, String album, String localisation, java.util.Map<String, String> context)
    {
        return _iceI_ajouterAsync(piste, titre, artiste, album, localisation, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> ajouterAsync(int piste, String titre, String artiste, String album, String localisation)
    {
        return _iceI_ajouterAsync(piste, titre, artiste, album, localisation, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> ajouterAsync(int piste, String titre, String artiste, String album, String localisation, java.util.Map<String, String> context)
    {
        return _iceI_ajouterAsync(piste, titre, artiste, album, localisation, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_ajouterAsync(int iceP_piste, String iceP_titre, String iceP_artiste, String iceP_album, String iceP_localisation, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "ajouter", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_piste);
                     ostr.writeString(iceP_titre);
                     ostr.writeString(iceP_artiste);
                     ostr.writeString(iceP_album);
                     ostr.writeString(iceP_localisation);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default boolean supprimer(String titre, String artiste)
    {
        return supprimer(titre, artiste, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean supprimer(String titre, String artiste, java.util.Map<String, String> context)
    {
        return _iceI_supprimerAsync(titre, artiste, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> supprimerAsync(String titre, String artiste)
    {
        return _iceI_supprimerAsync(titre, artiste, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> supprimerAsync(String titre, String artiste, java.util.Map<String, String> context)
    {
        return _iceI_supprimerAsync(titre, artiste, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_supprimerAsync(String iceP_titre, String iceP_artiste, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "supprimer", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_titre);
                     ostr.writeString(iceP_artiste);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default Morceau[] rechercher(String titre, String artiste, String album)
    {
        return rechercher(titre, artiste, album, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default Morceau[] rechercher(String titre, String artiste, String album, java.util.Map<String, String> context)
    {
        return _iceI_rechercherAsync(titre, artiste, album, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Morceau[]> rechercherAsync(String titre, String artiste, String album)
    {
        return _iceI_rechercherAsync(titre, artiste, album, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Morceau[]> rechercherAsync(String titre, String artiste, String album, java.util.Map<String, String> context)
    {
        return _iceI_rechercherAsync(titre, artiste, album, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Morceau[]> _iceI_rechercherAsync(String iceP_titre, String iceP_artiste, String iceP_album, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Morceau[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "rechercher", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_titre);
                     ostr.writeString(iceP_artiste);
                     ostr.writeString(iceP_album);
                 }, istr -> {
                     Morceau[] ret;
                     ret = MorceauxHelper.read(istr);
                     istr.readPendingValues();
                     return ret;
                 });
        return f;
    }

    default Morceau[] getMorceaux()
    {
        return getMorceaux(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default Morceau[] getMorceaux(java.util.Map<String, String> context)
    {
        return _iceI_getMorceauxAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Morceau[]> getMorceauxAsync()
    {
        return _iceI_getMorceauxAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Morceau[]> getMorceauxAsync(java.util.Map<String, String> context)
    {
        return _iceI_getMorceauxAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Morceau[]> _iceI_getMorceauxAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Morceau[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getMorceaux", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     Morceau[] ret;
                     ret = MorceauxHelper.read(istr);
                     istr.readPendingValues();
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static StreamingPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static StreamingPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static StreamingPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static StreamingPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static StreamingPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static StreamingPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, StreamingPrx.class, _StreamingPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default StreamingPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (StreamingPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default StreamingPrx ice_adapterId(String newAdapterId)
    {
        return (StreamingPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default StreamingPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (StreamingPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default StreamingPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (StreamingPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default StreamingPrx ice_invocationTimeout(int newTimeout)
    {
        return (StreamingPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default StreamingPrx ice_connectionCached(boolean newCache)
    {
        return (StreamingPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default StreamingPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (StreamingPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default StreamingPrx ice_secure(boolean b)
    {
        return (StreamingPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default StreamingPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (StreamingPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default StreamingPrx ice_preferSecure(boolean b)
    {
        return (StreamingPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default StreamingPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (StreamingPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default StreamingPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (StreamingPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default StreamingPrx ice_collocationOptimized(boolean b)
    {
        return (StreamingPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default StreamingPrx ice_twoway()
    {
        return (StreamingPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default StreamingPrx ice_oneway()
    {
        return (StreamingPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default StreamingPrx ice_batchOneway()
    {
        return (StreamingPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default StreamingPrx ice_datagram()
    {
        return (StreamingPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default StreamingPrx ice_batchDatagram()
    {
        return (StreamingPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default StreamingPrx ice_compress(boolean co)
    {
        return (StreamingPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default StreamingPrx ice_timeout(int t)
    {
        return (StreamingPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default StreamingPrx ice_connectionId(String connectionId)
    {
        return (StreamingPrx)_ice_connectionId(connectionId);
    }

    static String ice_staticId()
    {
        return "::Middleware::Streaming";
    }
}
