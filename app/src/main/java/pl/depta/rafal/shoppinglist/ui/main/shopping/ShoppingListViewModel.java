package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.ui.base.BaseViewModel;

public class ShoppingListViewModel extends BaseViewModel {

    private Type type = Type.SHOPPING;
    private LiveData<List<FullShopping>> mFullShoppingList;

    public void setType(Type type) {
        this.type = type;
    }

    public LiveData<List<FullShopping>> getFullShoppingList() {
        return mFullShoppingList;
    }

    public ShoppingListViewModel(DataManager dataManager, Application application) {
        super(dataManager, application);
    }

    public void initShoppingList() {
        switch (type) {
            case SHOPPING:
                mFullShoppingList = getDataManager().getNoArchivedShoppingList();
                break;
            case ARCHIVED:
                mFullShoppingList = getDataManager().getArchivedShoppingList();
                break;
            default:
                mFullShoppingList = getDataManager().getNoArchivedShoppingList();
                break;
        }
    }
}
