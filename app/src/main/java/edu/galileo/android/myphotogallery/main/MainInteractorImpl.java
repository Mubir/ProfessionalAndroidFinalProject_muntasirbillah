package edu.galileo.android.myphotogallery.main;

import android.net.Uri;

/**
 * Created by mubir on 8/19/17.
 */
public class MainInteractorImpl implements MainInteractor {
    private MainRepository repository;

    public MainInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doUpPhotos(String image) {
        repository.upPhoto(image);
    }

    @Override
    public void doLogOut() {
        repository.logout();
    }
}
