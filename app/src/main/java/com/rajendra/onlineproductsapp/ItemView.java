package com.rajendra.onlineproductsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
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

import java.util.ArrayList;

public class ItemView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ProductAdapter productAdapter;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;

    ArrayList<String> product_id, product_type, product_specifier, product_ranges, prod_qty, prod_img;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemView.this, ItemsActivity.class);
                startActivity(intent); } });


        myDB = new DBHelper(ItemView.this);
        product_id = new ArrayList<>();
        product_type = new ArrayList<>();
        product_specifier = new ArrayList<>();
        product_ranges = new ArrayList<>();
        prod_qty = new ArrayList<>();
//        prod_img = new ArrayList<>();


        storeDataInArrays();

        customAdapter = new CustomAdapter(ItemView.this,this,
                product_id, product_type,
                product_specifier, product_ranges, prod_qty);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemView.this));

    }
/*
    Constraints btn = findViewById(R.id.views);
    btn.setOnClickListener(
    {
        Intent i = new Intent(ItemView.this, userView.class);
        startActivity(i);
    });
    btn.OnClickListener(new View.OnClickListener() {
        public void onClick() {
            Intent i = new Intent(UsrData.this, userView.class);
            startActivity(i);
        }
    });
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //stores data in arrays
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_type.add(cursor.getString(1));
                product_specifier.add(cursor.getString(2));
                product_ranges.add(cursor.getString(3));
                prod_qty.add(cursor.getString(4));
               // prod_img.add(cursor.getString(5));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    // function not yet implemented
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(ItemView.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ItemView.this, ItemsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
