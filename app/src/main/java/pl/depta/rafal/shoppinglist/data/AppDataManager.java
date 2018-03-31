package pl.depta.rafal.shoppinglist.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import pl.depta.rafal.shoppinglist.data.db.DbHelper;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;


public class AppDataManager implements DataManager {

    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    @Override
    public LiveData<List<FullShopping>> getNoArchivedShoppingList() {
        return mDbHelper.getNoArchivedShoppingList();
    }

    @Override
    public LiveData<List<FullShopping>> getArchivedShoppingList() {
        return mDbHelper.getArchivedShoppingList();
    }

    @Override
    public LiveData<List<ShoppingItem>> getShoppingListByListId(long listId) {
        return mDbHelper.getShoppingListByListId(listId);
    }

    @Override
    public Flowable<FullShopping> getFullShoppingListById(long id) {
        return mDbHelper.getFullShoppingListById(id);
    }

    @Override
    public Flowable<Long> insertShoppingList(ShoppingList shoppingList) {
        return mDbHelper.insertShoppingList(shoppingList);
    }

    @Override
    public Flowable<Long> insertShoppingItem(ShoppingItem shoppingItem) {
        return mDbHelper.insertShoppingItem(shoppingItem);
    }

    @Override
    public Flowable<Integer> deleteShoppingItem(ShoppingItem shoppingItem) {
        return mDbHelper.deleteShoppingItem(shoppingItem);
    }

    @Override
    public Flowable<Integer> updateShoppingList(FullShopping mFullShopping) {
        return mDbHelper.updateShoppingList(mFullShopping);
    }

    @Override
    public Flowable<Integer> updateShoppingItem(ShoppingItem shoppingItem) {
        return mDbHelper.updateShoppingItem(shoppingItem);
    }

 @Override
    public Completable insertShoppingItems(List<ShoppingItem> shoppingItems) {
        return mDbHelper.insertShoppingItems(shoppingItems);
    }
}
