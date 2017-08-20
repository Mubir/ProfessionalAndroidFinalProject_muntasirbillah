package edu.galileo.android.myphotogallery.galleryfragment.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.myphotogallery.di.MyPhotoGalleryAppModule;
import edu.galileo.android.myphotogallery.galleryfragment.GalleryFragment;
import edu.galileo.android.myphotogallery.lib.di.LibsModule;

/**
 * Created by mubir on 8/19/17.
 */
@Singleton
@Component(modules={GalleryModule.class, LibsModule.class, MyPhotoGalleryAppModule.class})
public interface GalleryComponent {
    void inject(GalleryFragment fragment);
}
