package pl.depta.rafal.shoppinglist.ui.main.details;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Intent;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingList;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.ui.base.BaseViewModel;

public class DetailsViewModel extends BaseViewModel {

    private FullShopping mFullShopping;
    private LiveData<List<ShoppingItem>> mShoppingItems;

    private Callbacks mCallbacks;

    public LiveData<List<ShoppingItem>> getShoppingItems() {
        return mShoppingItems;
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    public long getListId() {
        return mFullShopping.getId();
    }

    public DetailsViewModel(DataManager dataManager, Application application) {
        super(dataManager, application);
    }

    public void insertEmptyShoppingItem() {
        ShoppingItem shoppingItem = new ShoppingItem(mFullShopping.getId());
        getCompositeDisposable().add(
                getDataManager().insertShoppingItem(new ShoppingItem(mFullShopping.getId()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());
    }

    public void getIntent(Intent intent) {
        long id = intent.getLongExtra(DetailsActivity.LIST_ID, -1);

        if (id != -1) {
            getFullShopping(id);
        } else {
            createFullShopping();
        }
    }

    private void createFullShopping() {
        ShoppingList shoppingItems = new ShoppingList(new Date());
        getCompositeDisposable().add(
                getDataManager()
                        .insertShoppingList(shoppingItems)
                        .flatMap(listId -> getDataManager().getFullShoppingListById(listId))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fullShopping -> {
                            mFullShopping = fullShopping;
                            mShoppingItems = getDataManager().getShoppingListByListId(mFullShopping == null ? -1 : mFullShopping.getId());
                            mCallbacks.onNewList(mFullShopping);
                        }));
    }

    private void getFullShopping(long id) {
        getCompositeDisposable().add(
                getDataManager()
                        .getFullShoppingListById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fullShopping -> {
                            mFullShopping = fullShopping;
                            mShoppingItems = getDataManager().getShoppingListByListId(mFullShopping == null ? -1 : mFullShopping.getId());
                            mCallbacks.onNewList(mFullShopping);
                        }));
    }

    public void saveName(String s) {
        mFullShopping.setName(s);
        mFullShopping.setModifyDate(new Date());
        getCompositeDisposable().add(
                getDataManager()
                        .updateShoppingList(mFullShopping)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());

    }

    public void save(List<ShoppingItem> shoppingItems) {
        getCompositeDisposable().add(
                getDataManager()
                        .insertShoppingItems(shoppingItems)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());

    }
}
