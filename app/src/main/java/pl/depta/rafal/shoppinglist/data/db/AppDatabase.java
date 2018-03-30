package pl.depta.rafal.shoppinglist.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.depta.rafal.shoppinglist.data.db.dao.ShoppingItemDao;
import pl.depta.rafal.shoppinglist.data.db.dao.ShoppingListDao;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;


@Database(entities =
        {
                ShoppingList.class, ShoppingItem.class
        },
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ShoppingItemDao shoppingItemDao();

    public abstract ShoppingListDao shoppingListDao();

}
