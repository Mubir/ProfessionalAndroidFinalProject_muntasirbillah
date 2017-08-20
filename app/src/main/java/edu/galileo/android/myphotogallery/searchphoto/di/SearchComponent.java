package edu.galileo.android.myphotogallery.searchphoto.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.myphotogallery.api.di.ApiModule;
import edu.galileo.android.myphotogallery.di.MyPhotoGalleryAppModule;
import edu.galileo.android.myphotogallery.lib.di.LibsModule;
import edu.galileo.android.myphotogallery.searchphoto.SearchFragment;

/**
 * Created by mubir on 8/19/17.
 */
@Singleton
@Component(modules={SearchModule.class, LibsModule.class,ApiModule.class, MyPhotoGalleryAppModule.class})
public interface SearchComponent {
    void inject(SearchFragment fragment);
}
