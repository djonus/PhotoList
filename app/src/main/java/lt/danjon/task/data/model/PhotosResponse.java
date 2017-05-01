package lt.danjon.task.data.model;

import java.util.List;

public class PhotosResponse {

    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public static class Photos {
        private int page;
        private int pages;
        private int perpage;
        private int total;
        private List<FlickrPhoto> photo;

        public int getPage() {
            return page;
        }

        public int getPages() {
            return pages;
        }

        public int getPerpage() {
            return perpage;
        }

        public int getTotal() {
            return total;
        }

        public List<FlickrPhoto> list() {
            return photo;
        }
    }
}
