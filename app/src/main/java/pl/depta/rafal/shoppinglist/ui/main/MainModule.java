package pl.depta.rafal.shoppinglist.ui.main;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import pl.depta.rafal.shoppinglist.ViewModelProviderFactory;
import pl.depta.rafal.shoppinglist.annotation.ApplicationContext;
import pl.depta.rafal.shoppinglist.annotation.PerActivity;
import pl.depta.rafal.shoppinglist.data.DataManager;

@Module
public class MainModule {

    @Provides
    @PerActivity
    MainViewModel provideMainViewModel(DataManager dataManager, @ApplicationContext Application application) {
        return new MainViewModel(dataManager, application);
    }

    @Provides
    @PerActivity
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

}
