package pl.depta.rafal.shoppinglist.ui.main.details;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.depta.rafal.shoppinglist.R;
import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.data.db.entity.ShoppingItem;
import pl.depta.rafal.shoppinglist.databinding.ItemShoppingBinding;
import pl.depta.rafal.shoppinglist.ui.main.details.DiffCallback;

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ShoppingItemViewHolder> {

    @Inject
    DataManager mDataManager;
    private List<ShoppingItem> mShoppingItems;

    public DetailsListAdapter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public List<ShoppingItem> getShoppingItems() {
        return mShoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        if (mShoppingItems == null) {
            notifyItemRangeInserted(0, shoppingItems.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(mShoppingItems, shoppingItems));
            result.dispatchUpdatesTo(this);
        }
        this.mShoppingItems = shoppingItems;
    }

    @NonNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoppingBinding itemShoppingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_shopping, parent, false);
        return new ShoppingItemViewHolder(itemShoppingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingItemViewHolder holder, int position) {
        holder.onBind(mShoppingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mShoppingItems == null ? 0 : mShoppingItems.size();
    }


    class ShoppingItemViewHolder extends RecyclerView.ViewHolder {
        ItemShoppingBinding binding;

        ShoppingItemViewHolder(ItemShoppingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(ShoppingItem shoppingItem) {
            binding.deleteItem.setOnClickListener(v ->
                    mDataManager.deleteShoppingItem(shoppingItem)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe());
            binding.name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    shoppingItem.setName(s.toString());

                    mDataManager.updateShoppingItem(shoppingItem)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe();
                }
            });

            binding.itemCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
                shoppingItem.setChecked(isChecked);
                mDataManager.updateShoppingItem(shoppingItem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            });
            binding.setItem(shoppingItem);
            binding.executePendingBindings();
        }


    }
}
