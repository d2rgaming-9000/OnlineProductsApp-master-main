package com.rajendra.onlineproductsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class checkout extends AppCompatActivity{

    RecyclerView ItemRecycler;
    TextView items;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.checkout);

            Intent intent = getIntent();
            String count = intent.getStringExtra("items");
            //ItemRecycler = findViewById(R.id.cartItemsRecyclerView);
            items = findViewById(R.id.textView);

            items.setText("You have "+count+" in your cart.");

        }
}
