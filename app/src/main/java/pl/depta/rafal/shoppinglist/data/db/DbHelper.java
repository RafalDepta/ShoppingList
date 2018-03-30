package pl.depta.rafal.shoppinglist.data.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Flowable;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;

public interface DbHelper {

    LiveData<List<FullShopping>> getNoArchivedShoppingList();

    LiveData<List<FullShopping>> getArchivedShoppingList();

    LiveData<List<ShoppingItem>> getShoppingListByListId(long listId);

    Flowable<FullShopping> getFullShoppingListById(long id);

    Flowable<Long> insertShoppingList(ShoppingList shoppingList);

    Flowable<Long> insertShoppingItem(ShoppingItem shoppingItem);

    Flowable<Integer> deleteShoppingItem(ShoppingItem shoppingItem);

    Flowable<Integer> updateShoppingList(FullShopping mFullShopping);

    Flowable<Integer> updateShoppingItem(ShoppingItem shoppingItem);
}
