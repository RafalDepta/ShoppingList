package pl.depta.rafal.shoppinglist.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.depta.rafal.shoppinglist.App;

@Singleton
@Component(modules =
        {
                AndroidSupportInjectionModule.class,
        })
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
