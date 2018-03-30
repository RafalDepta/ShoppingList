package pl.depta.rafal.shoppinglist;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import pl.depta.rafal.shoppinglist.di.component.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
