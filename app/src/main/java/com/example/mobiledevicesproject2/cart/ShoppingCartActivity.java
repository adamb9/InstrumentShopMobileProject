//Adam Baldwin
//R00176025
//SDH3A

package com.example.mobiledevicesproject2.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledevicesproject2.DBHelper;
import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.payment.PaymentActivity;
import com.example.mobiledevicesproject2.R;
import com.example.mobiledevicesproject2.home.MainActivity;
import com.example.mobiledevicesproject2.login.LoginActivity;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements CartAdapter.ListItemClickListener {
    private DBHelper mDatabase;
    private int totalPrice = 0;
    ArrayList<Item_Instrument> instrumentList;
    RecyclerView recyclerView;
    CartAdapter mAdapter;
    TextView cartTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        final FrameLayout frameLayout = findViewById(R.id.frame_layout_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mDatabase = new DBHelper(this);
        instrumentList = mDatabase.getAllCartItems();

        //Loading and populating recyclerview from sqlite database
        if(instrumentList.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter = new CartAdapter(instrumentList, this, this);
            recyclerView.setAdapter(mAdapter);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "There are no items in your cart", Toast.LENGTH_LONG).show();
        }


        //Displaying current price of items in cart
        for(int i=0;i<instrumentList.size();i++){
            int itemPrice = Integer.parseInt(instrumentList.get(i).getPrice());
            totalPrice += itemPrice;
            System.out.println(totalPrice);
        }


        Button continueShopping = findViewById(R.id.continueShopping);
        Button payNow = findViewById(R.id.payNow);
        Button clearCart = findViewById(R.id.clearCart);
        cartTotal = findViewById(R.id.cartTotal);


        cartTotal.setText(String.valueOf(totalPrice));

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
                startActivity(pay);
            }
        });

        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Clear cart button
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ShoppingCartActivity.this);
                db.clearCart();
                instrumentList.clear();
                mAdapter.notifyDataSetChanged();
                totalPrice = 0;
                cartTotal.setText(String.valueOf(totalPrice));

            }
        });
    }

    //Remove button for individual item in cart
    @Override
    public void onListItemClick(int position) {
        //Item removed from cart table in database
        DBHelper db = new DBHelper(this);
        String id = instrumentList.get(position).getId();
        db.DeleteItem(id);
        //Cart total price updated
        int itemPrice = Integer.parseInt(instrumentList.get(position).getPrice());
        totalPrice -= itemPrice;
        cartTotal.setText(String.valueOf(totalPrice));
        //Recyclerview and adapter remove item and update
        instrumentList.remove(position);
        recyclerView.removeViewAt(position);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, instrumentList.size());
        mAdapter.notifyDataSetChanged();
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                Intent login = new Intent(ShoppingCartActivity.this, LoginActivity.class);
                startActivity(login);
                return true;
            case R.id.action_basket:
                return true;
            case R.id.action_home:
                Intent home = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}