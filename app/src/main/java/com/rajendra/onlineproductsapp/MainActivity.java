package com.rajendra.onlineproductsapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajendra.onlineproductsapp.adapter.CustomAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductCategoryAdapter;
import com.rajendra.onlineproductsapp.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ProductAdapter productAdapter;
    ImageView empty_imageview;
    TextView no_data;
    DBHelper myDB;

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    TextView username;
    Button Cart;
    Intent putToCart;
    String itemcnt, name;

    ArrayList<String> product_id, product_type, product_specifier, product_ranges, prod_qty, prod_img;
    CustomAdapter customAdapter;
    private Button button;


    @Override
    //The recycler controllers..
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        Intent intent1 = getIntent();
        Intent intent2 = getIntent();

        myDB = new DBHelper(MainActivity.this);
        product_id = new ArrayList<>();
        product_type = new ArrayList<>();
        product_specifier = new ArrayList<>();
        product_ranges = new ArrayList<>();
        prod_qty = new ArrayList<>();
//        prod_img = new ArrayList<>();

        //
        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this,
                product_id, product_type,
                product_specifier, product_ranges, prod_qty, prod_img);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //gets user name from login
        name = intent1.getStringExtra("name_key");
        username = findViewById(R.id.username);

        itemcnt = intent2.getStringExtra("items");
        Cart = findViewById(R.id.CartBtn);

        if (itemcnt == null) {
            Cart.setText("Your cart is empty.");
        } else if (itemcnt != null) {
            Cart.setText("You have " + itemcnt + " items.");
        } else {
            Cart.setText("View Cart");
        }

        //when user logs in show name of user in main menu
        if (name != null)
            username.setText("Hello, " + name + " !");
        else
            username.setText("Select more items or checkout.              ");

        TextView nameTXT = findViewById(R.id.nameTextView);

        //Button to redirect user to pages with button declaration
        //clicks on logout
        Button button1 = (Button) findViewById(R.id.MainBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });


        //clicks on info page
        Button button2 = (Button) findViewById(R.id.infoBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Info_page.class);
                startActivity(i);

            }
        });

        //clicks on view cart
        try {
            Button button3 = (Button) findViewById(R.id.CartBtn);
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    putToCart = new Intent(MainActivity.this, checkout.class);
                    putToCart.putExtra("items", itemcnt);
                    startActivity(putToCart);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //contact us API


        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "All Products"));
        productCategoryList.add(new ProductCategory(4, "Limited Editions"));
        productCategoryList.add(new ProductCategory(5, "Fitness"));
        productCategoryList.add(new ProductCategory(6, "Smart Watches"));
        productCategoryList.add(new ProductCategory(7, "Smart Phones"));

        //      setProductRecycler(productCategoryList);
        // each recycler, optional solution for recyclers
        /*
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(3, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(3, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));

        setProdItemRecycler(productsList);
        */
    }


    /*
    //category not implemented optional feature
        private void setProductRecycler(List<ProductCategory> productCategoryList) {

            productCatRecycler = findViewById(R.id.cat_recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            productCatRecycler.setLayoutManager(layoutManager);
            productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
            productCatRecycler.setAdapter(productCategoryAdapter);
        }
    */
    /*
    private void setProdItemRecycler(List<Products> productsList) {

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        ProductAdapter productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);
    }
    */




    //Method to store data in arrays and refresh page
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
}