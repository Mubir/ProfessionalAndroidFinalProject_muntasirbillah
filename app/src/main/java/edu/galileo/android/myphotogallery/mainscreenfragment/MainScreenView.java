package edu.galileo.android.myphotogallery.mainscreenfragment;

import edu.galileo.android.myphotogallery.mainscreenfragment.entities.MyImage;

/**
 * Created by mubir on 8/19/17.
 */
public interface MainScreenView {
    void showImage();
    void hideImage();
    void showProgress();
    void hideProgress();

    void onError(String message);
    void setPhoto(MyImage image);
    void setMessage(String message);
}
