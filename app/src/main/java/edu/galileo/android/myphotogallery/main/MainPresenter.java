package edu.galileo.android.myphotogallery.main;

import android.net.Uri;

/**
 * Created by mubir on 8/19/17.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void upPhotos(String image);
    void logout();

    void onEventMainThread(MainEvent event);
}
