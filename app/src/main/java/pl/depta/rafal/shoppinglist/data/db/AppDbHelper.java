package pl.depta.rafal.shoppinglist.data.db;


import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;


public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


    @Override
    public LiveData<List<FullShopping>> getNoArchivedShoppingList() {
        return mAppDatabase.shoppingListDao().getNoArchivedList();
    }

    @Override
    public LiveData<List<FullShopping>> getArchivedShoppingList() {
        return mAppDatabase.shoppingListDao().getArchivedList();
    }

    @Override
    public LiveData<List<ShoppingItem>> getShoppingListByListId(long listId) {
        return mAppDatabase.shoppingItemDao().getItemByListId(listId);
    }

    @Override
    public Flowable<FullShopping> getFullShoppingListById(long id) {
        return Flowable.fromCallable(() -> mAppDatabase.shoppingListDao().getFullShoppingListById(id));
    }

    @Override
    public Flowable<Long> insertShoppingList(ShoppingList shoppingList) {
        return Flowable.fromCallable(() -> mAppDatabase.shoppingListDao().insertShoppingList(shoppingList));
    }

    @Override
    public Flowable<Long> insertShoppingItem(ShoppingItem shoppingItem) {
        return Flowable.fromCallable(() -> mAppDatabase.shoppingItemDao().insertShoppingItem(shoppingItem));
    }

    @Override
    public Flowable<Integer> deleteShoppingItem(ShoppingItem shoppingItem) {
        return Flowable.fromCallable(() -> mAppDatabase.shoppingItemDao().deleteShoppingItem(shoppingItem));
    }

    @Override
    public Flowable<Integer> updateShoppingList(FullShopping mFullShopping) {
        return Flowable.fromCallable(()->mAppDatabase.shoppingListDao().updateShoppingList(mFullShopping));
    }

    @Override
    public Flowable<Integer> updateShoppingItem(ShoppingItem shoppingItem) {
        return Flowable.fromCallable(()->mAppDatabase.shoppingItemDao().updateShoppingItem(shoppingItem));
    }
}
