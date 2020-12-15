package com.example.mobiledevicesproject2.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.R;

import java.util.ArrayList;

public class DummyFragment extends Fragment implements RecyclerViewAdapter.ListItemClickListener {
    int dummyColor;
    RecyclerViewAdapter dummyAdapter;
    ArrayList<Item_Instrument> instrumentObjects;

    public DummyFragment(ArrayList<Item_Instrument> instrumentObjects) {
        this.instrumentObjects = instrumentObjects;
    }

    @SuppressLint("ValidFragment")
    public DummyFragment(int instrument) {
        this.dummyColor = instrument;
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


        dummyAdapter = new RecyclerViewAdapter(instrumentObjects, getContext(), this);
        recyclerView.setAdapter(dummyAdapter);

        return view;
    }

    @Override
    public void onListItemClick(int position) {
        Toast.makeText(getContext(), instrumentObjects.get(position).getName(), Toast.LENGTH_SHORT).show();
        DBHelper db = new DBHelper(getContext());
        int price = Integer.parseInt(instrumentObjects.get(position).getPrice().substring(1));
        String name = instrumentObjects.get(position).getName();
        String id = instrumentObjects.get(position).getId();
        db.addCartItem(id, name, price);


    }
}