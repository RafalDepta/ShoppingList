package pl.depta.rafal.shoppinglist.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import javax.inject.Inject;

import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ShoppingItemDao {

    @Insert(onConflict = REPLACE)
    Long insertShoppingItem(ShoppingItem shoppingItem);

    @Insert(onConflict = REPLACE)
    void insertShoppingItems(List<ShoppingItem> shoppingItems);

    @Update(onConflict = REPLACE)
    int updateShoppingItem(ShoppingItem shoppingItem);

    @Delete
    int deleteShoppingItem(ShoppingItem shoppingItem);

    @Query("SELECT * FROM ShoppingItem WHERE shoppingListId=:listId")
    LiveData<List<ShoppingItem>> getItemByListId(long listId);
}
