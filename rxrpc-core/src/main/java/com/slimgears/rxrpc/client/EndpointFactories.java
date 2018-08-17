/**
 *
 */
package com.slimgears.rxrpc.client;

import io.reactivex.Single;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Future;

public class EndpointFactories {
    public static RxClient.EndpointFactory constructorFactory() {
        return new RxClient.EndpointFactory() {
            @Override
            public <T> T create(Class<T> clientClass, Single<RxClient.Session> session) {
                try {
                    return clientClass.getConstructor(Single.class).newInstance(session);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
