package lt.danjon.task;

import javax.inject.Singleton;

import dagger.Component;
import lt.danjon.task.ui.catalog.CatalogActivity;

@Singleton
@Component(modules={BackendModule.class})
public interface AppComponent {

    void inject(CatalogActivity activity);
}
