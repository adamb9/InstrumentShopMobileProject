package com.example.mobiledevicesproject2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DummyFragment extends Fragment {
    int dummyColor;
    RecyclerViewAdapter dummyAdapter;
    ArrayList<Item_Instrument> instrumentObjects;

    public DummyFragment(ArrayList<Item_Instrument> instrumentObjects) {
        this.instrumentObjects = instrumentObjects;
    }

    @SuppressLint("ValidFragment")
    public DummyFragment(int color) {
        this.dummyColor = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);

        final FrameLayout frameLayout = view.findViewById(R.id.frame_layout_dummy);
        frameLayout.setBackgroundColor(dummyColor);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_dummy);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        dummyAdapter = new RecyclerViewAdapter(instrumentObjects, getContext());
        recyclerView.setAdapter(dummyAdapter);

        return view;
    }
}