package com.rajendra.onlineproductsapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajendra.onlineproductsapp.adapter.CustomAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductAdapter;
import com.rajendra.onlineproductsapp.adapter.ProductCategoryAdapter;
import com.rajendra.onlineproductsapp.model.ProductCategory;
import com.rajendra.onlineproductsapp.model.Products;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler, recyclerView;
    TextView username;
    Button Cart;
    Intent putToCart;
    String itemcnt, name;
    private Button button;


    @Override
    //The recycler controllers..
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent1 = getIntent();
        Intent intent2 = getIntent();

        name = intent1.getStringExtra("name_key");
        username = findViewById(R.id.username);

        itemcnt = intent2.getStringExtra("items");
        Cart = findViewById(R.id.CartBtn);

        if (itemcnt == null)
        {Cart.setText("Your cart is empty.");}
        else if (itemcnt != null){
            Cart.setText("You have " + itemcnt + " items.");
        }
        else
        {
            Cart.setText("View Cart");
        }

        //when user logs in show name of user in main menu

            username.setText("Hello, "+name+" !");

        TextView nameTXT = findViewById(R.id.nameTextView);
	
    //Button to redirect user to pages with button declaration
        //clicks on logout
        Button button1 = (Button)findViewById(R.id.MainBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });


        //clicks on info page
        Button button2 = (Button)findViewById(R.id.infoBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Info_page.class);
                startActivity(i);

            }
        });

        //clicks on view cart
        Button button3 = (Button)findViewById(R.id.CartBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                putToCart = new Intent(MainActivity.this, checkout.class);
                putToCart.putExtra("items", itemcnt);
                startActivity(putToCart);
            }
        });

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
// each recycler
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(3, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));
        productsList.add(new Products(2, "New Special Edition Watch", "40 GB", "$ 250.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese LMTD EDITION Smart Watch", "35 GB", "$ 267.00", R.drawable.prod2));
        productsList.add(new Products(3, "Fitness Watch", "8 GB", "$ 170.00", R.drawable.prod3));

        setProdItemRecycler(productsList);
    }
/*
    private void setProductRecycler(List<ProductCategory> productCategoryList) {

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);
    }
*/
    private void setProdItemRecycler(List<Products> productsList) {

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        ProductAdapter productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);
    }

}
