package edu.galileo.android.myphotogallery.galleryfragment.adapter;

import edu.galileo.android.myphotogallery.database.ImageGallery;

/**
 * Created by mubir on 8/19/17.
 */
public interface OnImageItemClickListener {
    void onImageClick(ImageGallery image);
    void onDeleteClick(ImageGallery image);
}
