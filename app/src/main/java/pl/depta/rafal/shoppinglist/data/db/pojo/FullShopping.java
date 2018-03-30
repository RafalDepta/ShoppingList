package pl.depta.rafal.shoppinglist.data.db.pojo;

import android.arch.persistence.room.Relation;

import java.util.List;

import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;

public class FullShopping extends ShoppingList {
    @Relation(parentColumn = "id", entityColumn = "shoppingListId")
    public List<ShoppingItem> shoppingItems;
}
