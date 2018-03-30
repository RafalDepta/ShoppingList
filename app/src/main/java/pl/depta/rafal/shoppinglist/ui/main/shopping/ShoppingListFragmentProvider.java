package pl.depta.rafal.shoppinglist.ui.main.shopping;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.depta.rafal.shoppinglist.annotation.PerFragment;


@Module
@PerFragment
public abstract class ShoppingListFragmentProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = ShoppingListModule.class)
    abstract ShoppingListFragment provideShoppingListFragmentFactory();

}
