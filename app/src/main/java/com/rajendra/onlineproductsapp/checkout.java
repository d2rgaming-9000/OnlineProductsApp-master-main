package com.rajendra.onlineproductsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class checkout extends AppCompatActivity{


    Integer qty, Cart = 0;
    String specifier, count, stringCnt;
    DBHelper myDB = new DBHelper(checkout.this);
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
            stringCnt = count;
            try {
                Cart = Cart + Integer.valueOf(stringCnt);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        ItemRecycler = findViewById(R.id.cartItemsRecyclerView);
            items = findViewById(R.id.quantity);
            //CartArry = new int[]{Cart};

            //sets number of items selected (quantity)
            items.setText(Cart +" items in your cart.");
            //sets item specifiers
        item_specifier = findViewById(R.id.item_specifier_ui);

        btnPurchase = findViewById(R.id.placeYourOrder);

        //when click purchase
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updates remainder of qauntity in DB of item
                try {
                    specifier = "JPN#34536";
                    qty = qty;
                    qty = qty - Cart;
                    Boolean checkPurch = myDB.updatePurch(specifier, qty);

                    if(checkPurch == true)
                    {
                        Toast.makeText(checkout.this, "Successfully purchased!", Toast.LENGTH_SHORT).show();
                    }
                    else if (checkPurch == false)
                    {
                        Toast.makeText(checkout.this, "Failed to purchase",Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
