package edu.galileo.android.myphotogallery.galleryfragment;

import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public interface GalleryPresenter {
    void onCreate();
    void onDestroy();
    void getPhotos();
    void deletePhoto(ImageGallery image);

    void onEventMainThread(GalleryEvent event);
}
