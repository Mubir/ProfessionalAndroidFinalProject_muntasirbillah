package edu.galileo.android.myphotogallery.mainscreenfragment;

import edu.galileo.android.myphotogallery.mainscreenfragment.entities.MyImage;

/**
 * Created by mubir on 8/19/17.
 */
public class MainScreenEvent {
    public static final int onReadPhoto = 1;
    public static final int onPhotoError = 2;
    public static final int onPhotoEmpty = 3;

    private int type;
    private String message;
    private MyImage image;

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

    public MyImage getImage() {
        return image;
    }

    public void setImage(MyImage image) {
        this.image = image;
    }
}
