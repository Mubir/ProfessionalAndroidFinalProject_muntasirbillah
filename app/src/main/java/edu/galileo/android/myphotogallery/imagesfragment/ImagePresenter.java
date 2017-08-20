package edu.galileo.android.myphotogallery.imagesfragment;

import java.util.List;

import edu.galileo.android.myphotogallery.api.entities.FlickrItem;
import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public interface ImagePresenter {
    void onShow();
    void onDestroy();
    void setRepo(List<FlickrItem> list);
    void nextImage();
    void saveImage(ImageGallery image);

    void onEventMainThread(ImageEvent event);
}
