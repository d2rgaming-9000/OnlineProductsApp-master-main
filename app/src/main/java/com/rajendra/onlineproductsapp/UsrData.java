package com.rajendra.onlineproductsapp;
import static com.rajendra.onlineproductsapp.DBHelper.TABLE_NAME2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UsrData extends AppCompatActivity {

    EditText firstname_input, email_input,pass_input, usrID_input;
    Button btnInsert, btnUpdate, btnDelete;

    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        firstname_input =  findViewById(R.id.Usrname);
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
                DBHelper myDB = new DBHelper(UsrData.this);
                myDB.addUsrs(firstname_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        pass_input.getText().toString().trim(),
                        usrID_input.getText().toString().trim()
                );
            }
        });

        //clicks on View
        //to view all data of USERS

        Button button1 = (Button) findViewById(R.id.btnView);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UsrData.this, userView.class);
                startActivity(i);
            }
        });
        
        //(sol 4) Updating USERS
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(UsrData.this);
                boolean isUpdate = myDB.updateUsrData(
                        firstname_input.getText().toString(),
                        email_input.getText().toString(),
                        pass_input.getText().toString(),
                        usrID_input.getText().toString());
            }
        });

        //sol Delete USERS
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(UsrData.this);
                user_id = usrID_input.getText().toString().trim();
                myDB.deleteUsrRow(user_id);
            }
        });
    }

}
