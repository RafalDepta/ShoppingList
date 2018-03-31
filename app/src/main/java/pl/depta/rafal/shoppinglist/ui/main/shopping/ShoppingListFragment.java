package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import pl.depta.rafal.shoppinglist.R;
import pl.depta.rafal.shoppinglist.BR;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.databinding.FragmentShoppingListBinding;
import pl.depta.rafal.shoppinglist.ui.base.BaseFragment;
import pl.depta.rafal.shoppinglist.ui.main.details.DetailsActivity;

public class ShoppingListFragment extends BaseFragment<FragmentShoppingListBinding, ShoppingListViewModel> implements OnItemClickListener {

    private static final String TYPE = "type";
    @Inject
    @Named("ShoppingVF")
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    ShoppingListAdapter mAdapter;

    private FragmentShoppingListBinding mBinding;
    private ShoppingListViewModel mViewModel;

    public static ShoppingListFragment newInstance(Type type) {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        args.putSerializable(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();

        Type type;
        if (getArguments() != null) {
            type = (Type) getArguments().get(TYPE);
            mViewModel.setType(type);
            mViewModel.initShoppingList();
        }
        setUp();
        subscribeLiveData();
        initSwipe();
    }

    private void setUp() {
        mAdapter.setListener(this);
        mBinding.shoppingList.setAdapter(mAdapter);
    }

    private void subscribeLiveData() {
        mViewModel.getFullShoppingList().observe(this, fullShoppingList ->
                mAdapter.setFullShoppingList(fullShoppingList));
    }

    private void initSwipe() {
        SwipeToArchiveCallback swipeToDeleteCallback = new SwipeToArchiveCallback(getContext()) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mAdapter.archiveItem(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(mBinding.shoppingList);
    }

    @Override
    public ShoppingListViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ShoppingListViewModel.class);
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping_list;
    }

    @Override
    public void onItemClick(FullShopping entity) {
        Intent intent = new Intent(getBaseActivity(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.LIST_ID, entity.getId());
        startActivity(intent);
    }
}
