package pl.depta.rafal.shoppinglist.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import pl.depta.rafal.shoppinglist.App;
import pl.depta.rafal.shoppinglist.annotation.ApplicationContext;
import pl.depta.rafal.shoppinglist.annotation.DatabaseInfo;
import pl.depta.rafal.shoppinglist.data.AppDataManager;
import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.data.db.AppDatabase;
import pl.depta.rafal.shoppinglist.data.db.AppDbHelper;
import pl.depta.rafal.shoppinglist.data.db.DbHelper;
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

    @Binds
    @Singleton
    abstract DataManager provideDataManager(AppDataManager appDataManager);

    @Binds
    @Singleton
    abstract DbHelper provideDbHelper(AppDbHelper appDbHelper);

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(@DatabaseInfo String dbName, @ApplicationContext Application context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .build();
    }

}
