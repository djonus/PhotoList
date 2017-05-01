package lt.danjon.task.data;

import java.io.IOException;

import lt.danjon.task.data.model.PhotosResponse;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

public class PhotosImpl implements Photos {

    // TODO: 29/04/2017 hide api key
    private static final String API_KEY = "your_api_key";

    private final FlickrService flickr;

    public PhotosImpl(FlickrService flickr) {
        this.flickr = flickr;
    }

    @Override
    public Observable<PhotosResponse.Photos> load(int page, int perPage) {
        return execute(flickr.recent(API_KEY, page, perPage))
                .map(queryResponse -> queryResponse.getPhotos())
                .subscribeOn(Schedulers.io());
    }

    private <T> Observable<T> execute(Call<T> call) {
        return Observable.create(subscriber -> {
            try {
                Response<T> result = call.execute();

                if (result.isSuccessful()) {
                    // TODO: 29/04/2017 Add response validation
                    subscriber.onNext(result.body());
                } else {
                    // TODO: 08/04/2017 Handle failures
                    subscriber.onError(new RuntimeException("Error: " + result.code()));
                }
            } catch (IOException e) {
                subscriber.onError(unexpectedError(e));
            }
        });
    }

    private Throwable unexpectedError(Throwable throwable) {
        // TODO: 29/04/2017 track
        throwable.printStackTrace();
        return throwable;
    }
}
