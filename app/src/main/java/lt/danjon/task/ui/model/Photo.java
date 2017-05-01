package lt.danjon.task.ui.model;

import java.io.Serializable;

// TODO: 30/04/2017 use parcelable for better performance
public class Photo implements Serializable{
    public final String url;
    public final String title;

    public Photo(String url, String title) {
        this.url = url;
        this.title = title;
    }
}
