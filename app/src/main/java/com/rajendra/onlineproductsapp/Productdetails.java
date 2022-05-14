package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Productdetails extends AppCompatActivity {

    int count = 0;
    TextView change;;
    ImageView addition;
    ImageView remove;
    Button addcart;
    Intent openSetPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        addition = findViewById(R.id.addition);
        remove = findViewById(R.id.remove);
        change = findViewById(R.id.qtychange);
        addcart = findViewById(R.id.addCart);

        //Shown image is equivalant to the ID of the image in the DB

        // Values of price and quantity shown equivalant to product ID in DB

        //Shown description is generic
        // (manually it would be assosciated to each product ID in the DB)


        //when user adds or removes item change number of counts shown
        addition.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                count = count +1;
                    change.setText(""+count);
            } });

        //REMOVES
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                if (count <= 0 ) count = 0;

                else
                count = count - 1;
                change.setText(""+count);
            }
        });

        //When adds to cart
        addcart.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                if (count <= 0 )
                    return;

                //the else statement adds the count of products
                //to the cart, that will ONLY update the values in the DB
                //when the user finalises purchase at the purchase page
            else
                openSetPin = new Intent(Productdetails.this, checkout.class);
                openSetPin = new Intent(Productdetails.this, MainActivity.class);

                String countasstring = String.valueOf(count);
                openSetPin.putExtra("items", countasstring);
                startActivity(openSetPin);
            }
        });
    }
}
