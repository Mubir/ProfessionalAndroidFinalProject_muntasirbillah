package edu.galileo.android.myphotogallery.detailfragment.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.myphotogallery.detailfragment.DetailFragment;
import edu.galileo.android.myphotogallery.di.MyPhotoGalleryAppModule;

/**
 * Created by mubir on 8/19/17.
 */
@Singleton
@Component(modules={DetailModule.class, MyPhotoGalleryAppModule.class})
public interface DetailComponent {
    void inject(DetailFragment fragment);
}
