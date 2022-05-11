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

    EditText admin, email_input,pass_input, usrID_input;
    Button btnInsert, btnUpdate, btnDelete;

    String user_id;

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

        //clicks on admins
        Button button4 = (Button)findViewById(R.id.adminD);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.admin_acc);

                admin =  findViewById(R.id.Usrname);
                email_input =  findViewById(R.id.Email);
                pass_input = findViewById(R.id.Pass);
                usrID_input = findViewById(R.id.usrid_txt);


                btnInsert = findViewById(R.id.btnInsert);
                btnUpdate = findViewById(R.id.btnUpdate);
                btnDelete = findViewById(R.id.btnDelete);

                //clicks on insert
                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelper myDB = new DBHelper(Admin.this);
                        myDB.addAdmin(admin.getText().toString().trim(),
                                email_input.getText().toString().trim(),
                                pass_input.getText().toString().trim(),
                                usrID_input.getText().toString().trim()
                        );
                    }
                });

                //clicks on View
                //to view all data of USERS and admins

                Button button1 = (Button) findViewById(R.id.btnView);
                button1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(Admin.this, userView.class);
                        startActivity(i);
                    }
                });

                // Updating admin
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelper myDB = new DBHelper(Admin.this);
                        boolean isUpdate = myDB.updateAdmin(
                                admin.getText().toString(),
                                email_input.getText().toString(),
                                pass_input.getText().toString(),
                                usrID_input.getText().toString());
                    }
                });

                //sol Delete admins or users
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelper myDB = new DBHelper(Admin.this);
                        user_id = usrID_input.getText().toString().trim();
                        myDB.deleteUsrRow(user_id);
                    }
                });
            }
        });

        //delete all users and admins


        //clicks on logout
        Button button3 = (Button)findViewById(R.id.logout);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
