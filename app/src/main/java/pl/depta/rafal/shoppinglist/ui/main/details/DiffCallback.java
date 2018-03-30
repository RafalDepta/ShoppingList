package pl.depta.rafal.shoppinglist.ui.main.details;

import android.support.v7.util.DiffUtil;

import java.util.List;

import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;


public class DiffCallback extends DiffUtil.Callback {
    private List<ShoppingItem> oldList;
    private List<ShoppingItem> newList;

    DiffCallback(List<ShoppingItem> oldList, List<ShoppingItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() ==
                newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ShoppingItem shoppingItem = newList.get(newItemPosition);
        ShoppingItem old;
        if (oldItemPosition >= newList.size()) {
            return false;
        } else {
            old = oldList.get(oldItemPosition);
            return shoppingItem.getId() == old.getId()
                    && shoppingItem.getName().equals(old.getName());
        }
    }
}
