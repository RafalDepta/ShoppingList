package pl.depta.rafal.shoppinglist.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.depta.rafal.shoppinglist.annotation.PerActivity;
import pl.depta.rafal.shoppinglist.ui.main.MainActivity;
import pl.depta.rafal.shoppinglist.ui.main.MainModule;
import pl.depta.rafal.shoppinglist.ui.main.details.DetailsActivity;
import pl.depta.rafal.shoppinglist.ui.main.details.DetailsModule;
import pl.depta.rafal.shoppinglist.ui.main.shopping.ShoppingListFragmentProvider;

@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainModule.class, ShoppingListFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {DetailsModule.class})
    abstract DetailsActivity bindDetailsActivity();

}
