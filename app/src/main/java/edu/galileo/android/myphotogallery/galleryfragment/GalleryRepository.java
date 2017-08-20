package edu.galileo.android.myphotogallery.galleryfragment;

import edu.galileo.android.myphotogallery.database.ImageGallery;
/**
 * Created by mubir on 8/19/17.
 */
public interface GalleryRepository {
    void getPhotos();
    void deletePhoto(ImageGallery image);
}
