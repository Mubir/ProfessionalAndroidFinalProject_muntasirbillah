package edu.galileo.android.myphotogallery.imagesfragment;

import edu.galileo.android.myphotogallery.api.entities.FlickrItem;
import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public interface ImagesView {
    void showImage();
    void hideImage();
    void showProgress();
    void hideProgress();

    void onSaveImage(String message);
    void onEndList(String message);
    void setImageItem(ImageGallery image);
}
