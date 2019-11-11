package com.example.navdrawertest.ui.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

final class ItemViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    // final TextView tvItem;
    // final TextView tvSubItem;

    ItemViewHolder(@NonNull final View view) {
        super(view);

        rootView = view;
        // tvItem = view.findViewById(R.id.tvItem);
        // tvSubItem = view.findViewById(R.id.tvSubItem);
    }
}