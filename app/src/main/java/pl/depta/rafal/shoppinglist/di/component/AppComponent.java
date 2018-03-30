package pl.depta.rafal.shoppinglist.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.depta.rafal.shoppinglist.App;
import pl.depta.rafal.shoppinglist.di.builder.ActivityBuilder;
import pl.depta.rafal.shoppinglist.di.module.AppModule;

@Singleton
@Component(modules =
        {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuilder.class
        })
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
