package com.example.mobiledevicesproject2.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.R;
import com.example.mobiledevicesproject2.home.RecyclerViewAdapter;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements CartAdapter.ListItemClickListener {
    private DBHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        final FrameLayout frameLayout = findViewById(R.id.frame_layout_cart);

        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mDatabase = new DBHelper(this);
        ArrayList<Item_Instrument> instrumentList = mDatabase.getAllCartItems();
        if(instrumentList.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            CartAdapter mAdapter = new CartAdapter(instrumentList, this, this);
            recyclerView.setAdapter(mAdapter);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "There are no items in your cart", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onListItemClick(int position) {

    }
}