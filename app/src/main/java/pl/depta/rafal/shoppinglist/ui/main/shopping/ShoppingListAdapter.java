package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.arch.persistence.room.Insert;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.depta.rafal.shoppinglist.R;
import pl.depta.rafal.shoppinglist.data.DataManager;
import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.databinding.ItemShoppingListBinding;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.FullShoppingViewHolder> {

    @Inject
    DataManager mDataManager;

    private List<FullShopping> mFullShoppingList;
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public void setFullShoppingList(List<FullShopping> fullShoppingList) {
        if (mFullShoppingList == null) {
            notifyItemRangeInserted(0, fullShoppingList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(mFullShoppingList, fullShoppingList));
            result.dispatchUpdatesTo(this);
        }
        mFullShoppingList = fullShoppingList;
    }

    public ShoppingListAdapter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @NonNull
    @Override
    public FullShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoppingListBinding itemShoppingListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_shopping_list, parent, false);
        return new FullShoppingViewHolder(itemShoppingListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FullShoppingViewHolder holder, int position) {
        holder.onBind(mFullShoppingList.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull FullShoppingViewHolder holder, int position, List<Object> payloads) {
        if (payloads != null && !payloads.isEmpty()) {
            holder.onBind((FullShopping) payloads.get(0));
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mFullShoppingList == null ? 0 : mFullShoppingList.size();
    }

    public void archiveItem(int position) {
        FullShopping fullShopping = mFullShoppingList.get(position);
        fullShopping.setArchived(true);
        mDataManager.updateShoppingList(fullShopping)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    class FullShoppingViewHolder extends RecyclerView.ViewHolder {
        ItemShoppingListBinding mBinding;

        FullShoppingViewHolder(ItemShoppingListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            binding.getRoot().setOnClickListener(view -> mListener.onItemClick(binding.getFullShopping()));
        }

        void onBind(FullShopping fullShopping) {
            mBinding.setFullShopping(fullShopping);
            mBinding.executePendingBindings();
        }

    }
}
