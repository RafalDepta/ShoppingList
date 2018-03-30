package pl.depta.rafal.shoppinglist.ui.main.details;

import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;

public interface OnDetailsItemClickListener {

    void onItemClick(ShoppingItem entity);
}
