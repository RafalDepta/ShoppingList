package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;


public class DiffCallback extends DiffUtil.Callback {
    private List<FullShopping> oldList;
    private List<FullShopping> newList;

    DiffCallback(List<FullShopping> oldList, List<FullShopping> newList) {
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
        FullShopping fullShopping = newList.get(newItemPosition);
        FullShopping old;
        if (oldItemPosition >= newList.size()) {
            return false;
        } else {
            old = oldList.get(oldItemPosition);
            return fullShopping.getId() == old.getId()
                    && fullShopping.getDate().equals(old.getDate())
                    && fullShopping.getModifyDate().equals(old.getModifyDate())
                    && fullShopping.getName().equals(old.getName());
        }
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        FullShopping oldItem = oldList.get(oldItemPosition);
        FullShopping newItem = newList.get(newItemPosition);

        if (oldItem.getModifyDate() != newItem.getModifyDate())
            return newItem;
        else
            return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
