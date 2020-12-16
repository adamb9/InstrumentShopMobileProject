//Adam Baldwin
//R00176025
//SDH3A

package com.example.mobiledevicesproject2.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mobiledevicesproject2.R;
import com.example.mobiledevicesproject2.cart.ShoppingCartActivity;
import com.example.mobiledevicesproject2.home.MainActivity;
import com.example.mobiledevicesproject2.login.LoginActivity;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(PaymentActivity.this, ConfirmationActivity.class);
                startActivity(pay);
            }
        });
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                Intent login = new Intent(PaymentActivity.this, LoginActivity.class);
                startActivity(login);
                return true;
            case R.id.action_basket:
                Intent cart = new Intent(PaymentActivity.this, ShoppingCartActivity.class);
                startActivity(cart);
                return true;
            case R.id.action_home:
                Intent home = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}