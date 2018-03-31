package pl.depta.rafal.shoppinglist.ui.main.shopping;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import pl.depta.rafal.shoppinglist.R;


abstract class SwipeToArchiveCallback extends ItemTouchHelper.SimpleCallback {

    private Drawable deleteIcon;
    private int intrinsicWidth;
    private int intrinsicHeight;
    private ColorDrawable background;
    private int backgroundColor;

    SwipeToArchiveCallback(Context context) {
        super(0, ItemTouchHelper.LEFT);

        deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_archive_white);
        backgroundColor = ContextCompat.getColor(context, R.color.color_archive_list_item);
        intrinsicWidth = deleteIcon.getIntrinsicWidth();
        intrinsicHeight = deleteIcon.getIntrinsicHeight();
        background = new ColorDrawable();
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getBottom() - itemView.getTop();

        background.setColor(backgroundColor);
        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(c);

        int archiveIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int archiveIconMargin = (itemHeight - intrinsicHeight) / 2;
        int archiveIconLeft = itemView.getRight() - archiveIconMargin - intrinsicWidth;
        int archiveIconRight = itemView.getRight() - archiveIconMargin;
        int archiveIconBottom = archiveIconTop + intrinsicHeight;

        deleteIcon.setBounds(archiveIconLeft, archiveIconTop, archiveIconRight, archiveIconBottom);
        deleteIcon.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}
