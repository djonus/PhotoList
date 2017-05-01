package lt.danjon.task.data;

public class FlickrServiceFactory {

    public static FlickrService create() {
        return new FlickrServiceMock();
    }
}
