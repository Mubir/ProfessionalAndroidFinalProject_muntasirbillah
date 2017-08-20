package edu.galileo.android.myphotogallery.lib;

/**
 * Created by mubir on 8/19/17.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
