package lt.danjon.task.data;

import lt.danjon.task.data.model.PhotosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    Call<PhotosResponse> recent(@Query("api_key") String apiKey,
                                @Query("page") int page,
                                @Query("per_page") int perPage);
}
