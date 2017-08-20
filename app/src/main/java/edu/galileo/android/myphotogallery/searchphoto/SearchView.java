package edu.galileo.android.myphotogallery.searchphoto;

import java.util.List;

import edu.galileo.android.myphotogallery.api.entities.FlickrItem;

/**
 * Created by mubir on 8/19/17.
 */
public interface SearchView {
    void hideInput();
    void showInput();
    void hideProgress();
    void showProgress();

    void onError(String error);
    void onEmpty(String message);
    void setContent(List<FlickrItem> list);
}
