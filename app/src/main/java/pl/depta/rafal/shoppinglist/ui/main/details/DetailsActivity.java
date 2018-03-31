package pl.depta.rafal.shoppinglist.ui.main.details;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import javax.inject.Inject;

import pl.depta.rafal.shoppinglist.BR;
import pl.depta.rafal.shoppinglist.R;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.databinding.ActivityDetailsBinding;
import pl.depta.rafal.shoppinglist.ui.base.BaseActivity;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> implements Callbacks {

    public static final String LIST_ID = "list_id";

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    DetailsListAdapter mAdapter;
    private DetailsViewModel mViewModel;
    private ActivityDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setCallbacks(this);
        setUp();
        mViewModel.getIntent(getIntent());
    }

    private void setUp() {
        setSupportActionBar(mBinding.toolbar);
        mBinding.detailsList.setAdapter(mAdapter);
        mBinding.btnAddNew.setOnClickListener(v -> mAdapter.insertNewItem(mViewModel.getListId()));

        mBinding.detailsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.saveName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onDestroy() {

        mViewModel.save(mAdapter.getShoppingItems());

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public DetailsViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailsViewModel.class);
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void onNewList(FullShopping fullShopping) {
        if(fullShopping.isArchived()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }

        mBinding.setName(fullShopping.getName());
        mBinding.setIsArchived(fullShopping.isArchived());
        mAdapter.setIsArchived(fullShopping.isArchived());
        mAdapter.setShoppingItems(fullShopping.shoppingItems);
    }
}
