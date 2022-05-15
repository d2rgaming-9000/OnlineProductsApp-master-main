package com.rajendra.onlineproductsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class checkout extends AppCompatActivity{

    RecyclerView ItemRecycler;
    TextView items;
    int[] CartArry = new int[]{0};
    int Cart;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.checkout);

            Intent intent = getIntent();
            String count = intent.getStringExtra("items");
            ItemRecycler = findViewById(R.id.cartItemsRecyclerView);
            items = findViewById(R.id.textView);
            Cart = Integer.parseInt(Cart + count);
            CartArry = new int[]{Cart};
            items.setText("You have "+ Arrays.toString(CartArry) +" items in your cart.");

        }
}
