package pl.depta.rafal.shoppinglist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.depta.rafal.shoppinglist.data.db.pojo.FullShopping;
import pl.depta.rafal.shoppinglist.ui.main.shopping.ShoppingListAdapter;

public final class BindingUtils {


    @BindingAdapter("dateFormat")
    public static void setDate(TextView view, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String stringDate = sdf.format(date);
        view.setText(stringDate);
    }

}
