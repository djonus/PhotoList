package lt.danjon.task.ui;

import java.util.List;

import rx.Observable;

public interface ListLoader<T> {

    /**
     * Load fresh data
     */
    void refresh();

    /**
     * Load more items
     * @return true if there will be more items
     */
    boolean more();

    /**
     * Observe latest data updates
     * @return Observable that emits list updates
     */
    Observable<List<T>> observe();

    /**
     * Get list items loaded since last refresh
     * @return list of items
     */
    List<T> list();
}
