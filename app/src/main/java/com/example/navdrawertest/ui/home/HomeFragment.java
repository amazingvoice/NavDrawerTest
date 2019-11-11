package com.example.navdrawertest.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class HomeFragment extends Fragment implements PhotoSection.ClickListener {

    private static final String TAG = "HomeFragment";
    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();

        final LoadPhotos loadPhotos = new LoadPhotos(getActivity());
        for(String album : loadPhotos.getAlbumList()) {
            sectionedAdapter.addSection(
                    new PhotoSection(album, loadPhotos.execute(album), getContext()));
        }

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedAdapter.getSectionItemViewType(position)
                        == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
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