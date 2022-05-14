package com.rajendra.onlineproductsapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    EditText username, password, forgetpass, emailtxt;
    Button btnLogin, updtPass;
    DBHelper db;
    String user, pass, email, repass, admin;
    TextView fgtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.inputUsername);
        password = (EditText) findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.LoginButton);
        fgtPass =  findViewById(R.id.ForgetPassword);

        db = new DBHelper(this);

        //login to main activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user = username.getText().toString();
                email = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                        Boolean checkuser = db.checkuserNmPass(user, pass);
                        Boolean checkmail = db.checkuserMailPass(email, pass);
                        if(checkuser == true || checkmail == true){
                            Toast.makeText(LoginActivity.this, "Signed in successfully !", Toast.LENGTH_SHORT).show();

                            Intent openSetPin = new Intent(LoginActivity.this, MainActivity.class);
                            openSetPin.putExtra("name_key", user);

                            finish();
                        startActivity(openSetPin);}

                        else if(checkuser == false){
                            Toast.makeText(LoginActivity.this, "The credentials are invalid. Please enter correct credentials.",
                                    Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        //clicks on create account
        TextView btn1 = findViewById(R.id.CreateAccount);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });


        //clicks on about us
        TextView btn2 = findViewById(R.id.aboutUs);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.about_us);

                Button btn4 = findViewById(R.id.back);
                btn4.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                    }
                });
            }
        });

        //clicks on breif descrip
        TextView btn3 = findViewById(R.id.briefds);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { setContentView(R.layout.menu);

                Button btn4 = findViewById(R.id.back2);
                btn4.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                    }
                });
            }
        });

        //clicks on admin
        //admin logs in same as user with his own special credentials
        Button button2 = (Button)findViewById(R.id.AdminButton);

        button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pass = password.getText().toString();
                        admin = username.getText().toString();
                        email = username.getText().toString();
                        if(admin.equals("")||pass.equals(""))
                            Toast.makeText(LoginActivity.this, "Please enter admin identifiers", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean checkadmin = db.checkadmin(admin, pass);
                            Boolean checkadminmail = db.checkadminmail(email, pass);
                            if(checkadmin == true || checkadminmail == true){
                                Toast.makeText(LoginActivity.this, "Signed in as Admin successfully !", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, Admin.class);
                                startActivity(i);}

                            else if(checkadmin == false && checkadminmail == false){
                                Toast.makeText(LoginActivity.this, "Incorrect admin credits.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
        });

        //clicks on forget password
        //uncomment this line only when updatePass method in DBHelper is also uncommented
        /*
        fgtPass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //sets view to new layout
                setContentView(R.layout.forgetpassword);

                //calls methods for update
                updtPass = findViewById(R.id.btnUpdate);
                updtPass.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        DBHelper myDB = new DBHelper(LoginActivity.this);

                        user = username.getText().toString().trim();
                        email = emailtxt.getText().toString().trim();
                        pass = password.getText().toString().trim();
                        repass = password.getText().toString().trim();
                        Boolean checkpass = db.checkuserdata(user, email);
                        if(checkpass == true && pass.equals(repass)){
                            Toast.makeText(LoginActivity.this, "Pasword changed successfully !", Toast.LENGTH_SHORT).show();
                            myDB.updatePass(user, email, pass);

                            if(db.updatePass(user, email, pass) == true)
                            {
                            setContentView(R.layout.activity_login);}
                        }

                        else if(checkpass == false){
                            Toast.makeText(LoginActivity.this, "The credentials are invalid. Please enter proper credentials.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    };
                });
            };
        });*/
    }
}