package edu.galileo.android.myphotogallery.imagesfragment;

import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public class ImageEvent {

    public static final int onSuccessBuilder = 1;
    public static final int onErrorBuilder = 2;
    public static final int onEndList = 3;
    public static final int onSaveImage = 4;

    private int type;
    private String message;
    private ImageGallery image;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ImageGallery getImage() {
        return image;
    }

    public void setImage(ImageGallery image) {
        this.image = image;
    }
}
