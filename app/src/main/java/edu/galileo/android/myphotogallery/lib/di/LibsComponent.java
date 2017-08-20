package edu.galileo.android.myphotogallery.lib.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.myphotogallery.di.MyPhotoGalleryAppModule;

/**
 * Created by mubir on 8/19/17.
 */
@Singleton
@Component(modules ={LibsModule.class, MyPhotoGalleryAppModule.class})
public interface LibsComponent {
}
