package lt.danjon.task;

import android.app.Application;

public class TestApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .backendModule(new BackendModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
