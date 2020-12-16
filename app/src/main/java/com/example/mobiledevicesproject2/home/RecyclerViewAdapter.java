//Adam Baldwin
//R00176025
//SDH3A

package com.example.mobiledevicesproject2.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.R;

import java.util.ArrayList;

//RecyclerView adapter for the main activity
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DummyViewHolder> {

    final private ListItemClickListener mOnClickListener;
    ArrayList<Item_Instrument> instrumentList;
    Context context;

    public interface ListItemClickListener {
        void onListItemClick(int position);
    }


    public  RecyclerViewAdapter(ArrayList<Item_Instrument> dummyList, Context context, ListItemClickListener onClickListener) {
        this.instrumentList = dummyList;
        this.context = context;
        this.mOnClickListener = onClickListener;
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

    class DummyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView instrumentImage;
        TextView instrumentName, instrumentPrice, instrumentId;
        Button cartButton;

        public DummyViewHolder(@NonNull View itemView) {
            super(itemView);

            instrumentImage = itemView.findViewById(R.id.instImage);
            instrumentName = itemView.findViewById(R.id.instNameTextView);
            instrumentPrice = itemView.findViewById(R.id.instPriceTextView);
            instrumentId = itemView.findViewById(R.id.instIDTextView);
            cartButton = itemView.findViewById(R.id.cartButton);
            cartButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}