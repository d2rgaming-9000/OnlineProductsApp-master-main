package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import java.util.Random;

public class RegistrationActivity extends AppCompatActivity {
    private Button register;
    EditText usrname_input, lastname_input, email_input, pass_input, cpass_input;
    DBHelper DB;

    String user_id, usrname, lastname, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usrname_input = findViewById(R.id.InputUsername);
        email_input = findViewById(R.id.InputEmail);
        pass_input = findViewById(R.id.InputPassword);
        cpass_input = findViewById(R.id.InputComformPassword);

        final int sum = 1;

        DB = new DBHelper(this);
        register = (Button) findViewById(R.id.SignUpButton);

        TextView btn = findViewById(R.id.AlreadyhaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        //when click register

        //clicks on signup
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(RegistrationActivity.this);
                usrname = usrname_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                pass = pass_input.getText().toString().trim();
                pass = cpass_input.getText().toString().trim();

                myDB.registerUsrs(usrname, email, pass);
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });

    }
}