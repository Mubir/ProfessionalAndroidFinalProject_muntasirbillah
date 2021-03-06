package edu.galileo.android.myphotogallery.searchphoto;

/**
 * Created by mubir on 8/19/17.
 */
public class SearchInteractorImpl implements SearchInteractor {
    SearchRepository repository;

    public SearchInteractorImpl(SearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doSearchPhotos(String request) {
        repository.getPhotos(request);
    }
}
