package pl.depta.rafal.shoppinglist.ui.main;

import android.app.Application;

import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel{


    public MainViewModel(DataManager dataManager, Application application) {
        super(dataManager, application);
    }
}
