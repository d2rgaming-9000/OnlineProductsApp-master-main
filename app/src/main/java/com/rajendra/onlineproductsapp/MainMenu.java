package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //clicks on picture to go back
        Button button1 = (Button)findViewById(R.id.back2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
