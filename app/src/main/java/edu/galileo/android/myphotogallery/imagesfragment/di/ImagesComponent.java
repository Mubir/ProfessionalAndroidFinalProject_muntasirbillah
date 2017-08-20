package edu.galileo.android.myphotogallery.imagesfragment.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.myphotogallery.api.di.ApiModule;
import edu.galileo.android.myphotogallery.di.MyPhotoGalleryAppModule;
import edu.galileo.android.myphotogallery.imagesfragment.ImageFragment;
import edu.galileo.android.myphotogallery.lib.di.LibsModule;

/**
 * Created by mubir on 8/19/17.
 */
@Singleton
@Component(modules={ImageModule.class, LibsModule.class,ApiModule.class, MyPhotoGalleryAppModule.class})
public interface ImagesComponent {
    void inject(ImageFragment fragment);
}
