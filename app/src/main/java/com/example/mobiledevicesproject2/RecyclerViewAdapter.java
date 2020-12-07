package com.example.mobiledevicesproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DummyViewHolder> {

    ArrayList<Item_Instrument> instrumentList;
    Context dummyContext;

    public  RecyclerViewAdapter(ArrayList<Item_Instrument> dummyList, Context dummyContext) {
        this.instrumentList = dummyList;
        this.dummyContext = dummyContext;
    }

    @NonNull
    @Override
    public DummyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dummy, parent, false);
        return new DummyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DummyViewHolder holder, int position) {
        Item_Instrument instrument = instrumentList.get(position);

        holder.instrumentImage.setImageResource(instrument.getInstrumentImage());
        holder.instrumentName.setText(instrument.getName());
        holder.instrumentPrice.setText(instrument.getPrice());
        holder.instrumentId.setText(instrument.getId());
    }

    @Override
    public int getItemCount() {
        return instrumentList.size();
    }

    class DummyViewHolder extends RecyclerView.ViewHolder {

        ImageView instrumentImage;
        TextView instrumentName, instrumentPrice, instrumentId;

        public DummyViewHolder(@NonNull View itemView) {
            super(itemView);

            instrumentImage = itemView.findViewById(R.id.instImage);
            instrumentName = itemView.findViewById(R.id.instNameTextView);
            instrumentPrice = itemView.findViewById(R.id.instPriceTextView);
            instrumentId = itemView.findViewById(R.id.instIDTextView);

        }
    }
}