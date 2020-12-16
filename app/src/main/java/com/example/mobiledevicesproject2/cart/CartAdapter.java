//Adam Baldwin
//R00176025
//SDH3A


//RecyclerView adapter for the shopping cart activity
package com.example.mobiledevicesproject2.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.R;
import com.example.mobiledevicesproject2.home.RecyclerViewAdapter;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.DummyViewHolder> {

    final private CartAdapter.ListItemClickListener mOnClickListener;
    ArrayList<Item_Instrument> instrumentList;
    Context dummyContext;
    private DBHelper mDatabase;

    public interface ListItemClickListener {
        void onListItemClick(int position);
    }


    public  CartAdapter(ArrayList<Item_Instrument> dummyList, Context dummyContext, CartAdapter.ListItemClickListener onClickListener) {
        this.instrumentList = dummyList;
        this.dummyContext = dummyContext;
        this.mOnClickListener = onClickListener;
        mDatabase = new DBHelper(dummyContext);
    }

    @NonNull
    @Override
    public CartAdapter.DummyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false);
        return new CartAdapter.DummyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.DummyViewHolder holder, int position) {
        Item_Instrument instrument = instrumentList.get(position);

        holder.instrumentName.setText(instrument.getName());
        holder.instrumentPrice.setText(instrument.getPrice());
        holder.instrumentId.setText(instrument.getId());
    }

    @Override
    public int getItemCount() {
        return instrumentList.size();
    }

    class DummyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView instrumentName, instrumentPrice, instrumentId;
        Button cartButton;

        public DummyViewHolder(@NonNull View itemView) {
            super(itemView);

            instrumentName = itemView.findViewById(R.id.cartItemName);
            instrumentPrice = itemView.findViewById(R.id.cartItemPrice);
            instrumentId = itemView.findViewById(R.id.cartItemID);
            cartButton = itemView.findViewById(R.id.cartRemoveButton);
            cartButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}
