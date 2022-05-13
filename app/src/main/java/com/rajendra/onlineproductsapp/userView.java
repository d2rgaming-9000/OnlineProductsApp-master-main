package com.rajendra.onlineproductsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajendra.onlineproductsapp.adapter.CustomAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductAdapter;
import com.rajendra.onlineproductsapp.adapter.UserAdapter;

import java.util.ArrayList;

public class userView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ProductAdapter productAdapter;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;

    ArrayList<String> usrid, usrf1, usrf2, usremail, usrpass;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usr_view);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userView.this, ItemsActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBHelper(userView.this);
        usrid = new ArrayList<>();
        usrf1 = new ArrayList<>();
        usrf2 = new ArrayList<>();
        usremail = new ArrayList<>();
        usrpass = new ArrayList<>();

        storeDataInArrays();

        userAdapter = new UserAdapter(userView.this,this,
                usrid, usrf1,
                usrf2, usremail, usrpass);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(userView.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readUsrData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                usrid.add(cursor.getString(0));
                usrf1.add(cursor.getString(1));
                usrf2.add(cursor.getString(2));
                usremail.add(cursor.getString(3));
                usrpass.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}