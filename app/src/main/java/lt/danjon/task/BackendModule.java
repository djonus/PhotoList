package lt.danjon.task;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lt.danjon.task.data.FlickrService;
import lt.danjon.task.data.FlickrServiceFactory;
import lt.danjon.task.data.Photos;
import lt.danjon.task.data.PhotosImpl;
import lt.danjon.task.ui.ListLoader;
import lt.danjon.task.ui.catalog.PhotosListLoader;
import lt.danjon.task.ui.model.Photo;

@Module
public class BackendModule {

    @Provides
    @Singleton
    FlickrService providesService() {
        return FlickrServiceFactory.create();
    }

    @Provides
    @Singleton
    Photos providesPhotos(FlickrService service) {
        return new PhotosImpl(service);
    }

    @Provides
    @Singleton
    ListLoader<Photo> providesPhotosLoader(Photos photos) {
        return new PhotosListLoader(photos);
    }
}
