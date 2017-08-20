package edu.galileo.android.myphotogallery.galleryfragment;

import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public class GalleryInteractorImpl implements GalleryInteractor {
    GalleryRepository repository;

    public GalleryInteractorImpl(GalleryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doGetPhotos() {
        repository.getPhotos();
    }

    @Override
    public void doDeletePhoto(ImageGallery image) {
        repository.deletePhoto(image);
    }
}
