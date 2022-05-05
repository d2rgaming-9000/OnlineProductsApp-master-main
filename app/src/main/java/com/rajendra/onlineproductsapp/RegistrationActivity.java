package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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

public class RegistrationActivity extends AppCompatActivity {
    private Button register;
    EditText usrname, email, pass;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usrname = findViewById(R.id.InputUsername);
        email = findViewById(R.id.InputEmail);
        pass = findViewById(R.id.InputPassword);

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrnameTXT = usrname.getText().toString();
                String lastnameTXT = usrname.getText().toString();
                String emailTXT = email.getText().toString();
                Integer passTXT = Integer.valueOf(pass.getText().toString());

                Boolean checkinsertdata = DB.addUsrs (usrnameTXT, lastnameTXT, emailTXT, passTXT);
                if (checkinsertdata == true) {
                    Toast.makeText(RegistrationActivity.this, "Account Registered", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegistrationActivity.this, "Entry Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}