package pl.depta.rafal.shoppinglist.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import pl.depta.rafal.shoppinglist.BR;
import pl.depta.rafal.shoppinglist.R;
import pl.depta.rafal.shoppinglist.databinding.ActivityMainBinding;
import pl.depta.rafal.shoppinglist.ui.base.BaseActivity;
import pl.depta.rafal.shoppinglist.ui.main.details.DetailsActivity;
import pl.depta.rafal.shoppinglist.ui.main.shopping.ShoppingListFragment;
import pl.depta.rafal.shoppinglist.ui.main.shopping.Type;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private MainViewModel mMainViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();

        setUp();
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setUp() {
        mBinding.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mBinding.toolbar);
        loadFragment(ShoppingListFragment.newInstance(Type.SHOPPING));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_list:
                mBinding.toolbar.setTitle(getString(R.string.app_name));
                fragment = ShoppingListFragment.newInstance(Type.SHOPPING);
                loadFragment(fragment);
                return true;
            case R.id.navigation_list_archived:
                mBinding.toolbar.setTitle(getString(R.string.archived));
                fragment = ShoppingListFragment.newInstance(Type.ARCHIVED);
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(mBinding.frameContainer.getId(), fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(this, DetailsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
