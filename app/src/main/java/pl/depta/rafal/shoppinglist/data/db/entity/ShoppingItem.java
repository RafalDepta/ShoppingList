package pl.depta.rafal.shoppinglist.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = ShoppingList.class,
                parentColumns = "id",
                childColumns = "shoppingListId",
                onDelete = ForeignKey.CASCADE
        )
}, indices = {
        @Index("shoppingListId")
})
public class ShoppingItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";
    private boolean isChecked = false;
    private long shoppingListId;

    public ShoppingItem() {
    }

    public ShoppingItem(long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
