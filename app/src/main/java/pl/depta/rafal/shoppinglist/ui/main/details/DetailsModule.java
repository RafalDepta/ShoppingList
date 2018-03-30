package pl.depta.rafal.shoppinglist.ui.main.details;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import pl.depta.rafal.shoppinglist.ViewModelProviderFactory;
import pl.depta.rafal.shoppinglist.annotation.ApplicationContext;
import pl.depta.rafal.shoppinglist.annotation.PerActivity;
import pl.depta.rafal.shoppinglist.annotation.PerFragment;
import pl.depta.rafal.shoppinglist.data.DataManager;

@Module
public class DetailsModule {

    @Provides
    @PerActivity
    DetailsViewModel provideMainViewModel(DataManager dataManager, @ApplicationContext Application application) {
        return new DetailsViewModel(dataManager, application);
    }

    @Provides
    @PerActivity
    DetailsListAdapter provideDetailsAdapter(DataManager dataManager){
        return new DetailsListAdapter(dataManager);
    }

    @Provides
    @PerActivity
    ViewModelProvider.Factory mainViewModelProvider(DetailsViewModel detailsViewModel) {
        return new ViewModelProviderFactory<>(detailsViewModel);
    }

}
