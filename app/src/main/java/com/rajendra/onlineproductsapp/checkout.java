package com.rajendra.onlineproductsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class checkout extends AppCompatActivity{


    Integer qty, Cart;
    String specifier, count;
    DBHelper myDB;
    Button Quantity;
    RecyclerView ItemRecycler;
    TextView items, item_specifier, btnPurchase;
    int[] CartArry = new int[]{0};

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.checkout);

            Intent intent = getIntent();
            count = intent.getStringExtra("items");
            ItemRecycler = findViewById(R.id.cartItemsRecyclerView);
            items = findViewById(R.id.quantity);
           // Cart = Integer.parseInt(Cart + count);
            //CartArry = new int[]{Cart};

            //sets number of items selected (quantity)
            items.setText(count +" items in your cart.");
            //sets item specifiers
        item_specifier = findViewById(R.id.item_specifier_ui);

        btnPurchase = findViewById(R.id.placeYourOrder);

        //when click purchase
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updates remainder of qauntity in DB of item
                DBHelper myDB = new DBHelper(checkout.this);
                try {
                    specifier = "JPN#34536";
                    qty = 1;

                    myDB.updatePurch(specifier, qty);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
