package pl.depta.rafal.shoppinglist.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import pl.depta.rafal.shoppinglist.data.db.converter.DateConverter;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface ShoppingListDao {

    @Insert(onConflict = REPLACE)
    Long insertShoppingList(ShoppingList shoppingList);

    @Update(onConflict = REPLACE)
    int updateShoppingList(ShoppingList shoppingList);

    @Delete
    int deleteShoppingList(ShoppingList shoppingList);

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE isArchived=0 ORDER BY date")
    LiveData<List<FullShopping>> getNoArchivedList();

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE isArchived=1 ORDER BY date")
    LiveData<List<FullShopping>> getArchivedList();

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE id=:id ORDER BY date")
    FullShopping getFullShoppingListById(long id);
}
