package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

public class Admin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_board);


        //clicks on userdetail
        Button button1 = (Button)findViewById(R.id.usrdtlbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, UsrData.class);
                startActivity(i);
            }
        });

        //clicks on products/ order details
        Button button2 = (Button)findViewById(R.id.OrderD);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, ItemsActivity.class);
                startActivity(i);
            }
        });
    }
}
