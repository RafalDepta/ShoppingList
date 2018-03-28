package pl.depta.rafal.shoppinglist.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import pl.depta.rafal.shoppinglist.App;
import pl.depta.rafal.shoppinglist.annotation.ApplicationContext;
import pl.depta.rafal.shoppinglist.annotation.DatabaseInfo;
import pl.depta.rafal.shoppinglist.utils.AppConstants;

@Module
public abstract class AppModule {

    @Binds
    abstract Application bindApplication(App app);

    @Binds
    @ApplicationContext
    @Singleton
    abstract Application provideContext(Application application);

    @Provides
    @DatabaseInfo
    @Singleton
    static String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }


}
