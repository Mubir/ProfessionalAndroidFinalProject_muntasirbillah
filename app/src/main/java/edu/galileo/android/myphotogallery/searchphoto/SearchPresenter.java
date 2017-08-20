package edu.galileo.android.myphotogallery.searchphoto;

/**
 * Created by mubir on 8/19/17.
 */
public interface SearchPresenter {

    void onShow();
    void onDestroy();
    void onSearchPhotos(String request);

    void onEventMainThread(SearchEvent event);

}
