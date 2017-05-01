package lt.danjon.task.data;

import lt.danjon.task.data.model.PhotosResponse;
import rx.Observable;

public interface Photos {

    Observable<PhotosResponse.Photos> load(int page, int perPage);
}
