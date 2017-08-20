package edu.galileo.android.myphotogallery.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by mubir on 8/19/17.
 */
@Database(name = GalleryDatabase.NAME,version = GalleryDatabase.VERSION)
public class GalleryDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Gallery";
}
