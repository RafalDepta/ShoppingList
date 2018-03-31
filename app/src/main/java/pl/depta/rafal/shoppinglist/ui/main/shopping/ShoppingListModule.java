package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pl.depta.rafal.shoppinglist.ViewModelProviderFactory;
import pl.depta.rafal.shoppinglist.annotation.ApplicationContext;
import pl.depta.rafal.shoppinglist.annotation.PerFragment;
import pl.depta.rafal.shoppinglist.data.DataManager;

@Module
public class ShoppingListModule {

    @Provides
    @PerFragment
    ShoppingListViewModel shoppingListViewModel(DataManager dataManager, Application application) {
        return new ShoppingListViewModel(dataManager, application);
    }

    @Provides
    @PerFragment
    ShoppingListAdapter provideAdapter(DataManager dataManager){
        return new ShoppingListAdapter(dataManager);
    }

    @Provides
    @PerFragment
    @Named("ShoppingVF")
    ViewModelProvider.Factory provideShoppingListViewModel(ShoppingListViewModel shoppingListViewModel) {
        return new ViewModelProviderFactory<>(shoppingListViewModel);
    }
}
