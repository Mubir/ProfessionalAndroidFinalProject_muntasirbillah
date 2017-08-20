package edu.galileo.android.myphotogallery.mainscreenfragment;

/**
 * Created by mubir on 8/19/17.
 */
public interface MainScreenPresenter {
    void onCreate();
    void onDestroy();
    void onSubscribe();
    void unSubscribe();

    void onEventMainThread(MainScreenEvent event);
}
