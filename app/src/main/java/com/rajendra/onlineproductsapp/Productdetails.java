package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Productdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        Button addcart = findViewById(R.id.addCart);
        ImageView addition = findViewById(R.id.addition);
        ImageView remove = findViewById(R.id.remove);
        final TextView change = findViewById(R.id.qtychange);
        final int[] count = {1};

        //Shown image is equivalant to the ID of the image in the DB

        // Values of price and quantity shown equivalant to product ID in DB

        //Shown description is generic
        // (manually it would be assosciated to each product ID in the DB)


        //when user adds or removes item change number of counts shown
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]++;
                change.setText(count[0]);

            } });
    }
}
