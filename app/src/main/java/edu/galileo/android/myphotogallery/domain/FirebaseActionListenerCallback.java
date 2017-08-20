package edu.galileo.android.myphotogallery.domain;

/**
 * Created by mubir on 8/19/17.
 */
public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(String error);
}
