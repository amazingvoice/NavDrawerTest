package com.example.navdrawertest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navdrawertest.R;
import com.example.navdrawertest.ui.gallery.DataAdapter;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class HomeFragment extends Fragment implements PhotoSection.ClickListener {

    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadPhotos loadMoviesUseCase = new LoadPhotos();
        // TODO: implement method execute() to get the image list for a specific month
        sectionedAdapter.addSection(new PhotoSection("October",
                loadMoviesUseCase.execute(), this));
        sectionedAdapter.addSection(new PhotoSection("November",
                loadMoviesUseCase.execute(), this));

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionedAdapter);

        return view;
    }

    @Override
    public void onHeaderRootViewClicked(@NonNull final String sectionTitle, @NonNull final PhotoSection section) {
        Toast.makeText(
                getContext(),
                String.format(
                        "Clicked on more button from the header of Section %s",
                        sectionTitle
                ),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onItemRootViewClicked(@NonNull final String sectionTitle, final int itemAdapterPosition) {
        Toast.makeText(
                getContext(),
                String.format(
                        "Clicked on position #%s of Section %s",
                        sectionedAdapter.getPositionInSection(itemAdapterPosition),
                        sectionTitle
                ),
                Toast.LENGTH_SHORT
        ).show();
    }
}