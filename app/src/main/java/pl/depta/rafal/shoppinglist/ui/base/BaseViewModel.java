package pl.depta.rafal.shoppinglist.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;
import pl.depta.rafal.shoppinglist.data.DataManager;


public abstract class BaseViewModel<N> extends AndroidViewModel {

    private N mNavigator;
    private final DataManager mDataManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(DataManager dataManager, Application application) {
        super(application);
        this.mDataManager = dataManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
