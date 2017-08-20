package edu.galileo.android.myphotogallery.main;
/**
 * Created by mubir on 8/19/17.
 */
public interface MainView {
    void showProgress();
    void hideProgress();

    void photoError(String error);
    void photoSuccess();
}
