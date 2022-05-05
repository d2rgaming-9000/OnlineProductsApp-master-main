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

    EditText row_id, firstname_input, f2_input, email_input,pass_input, usrID_input;
    Button btnInsert, btnView, btnUpdate, btnDelete;

    String id, type, specifier, ranges, qty;


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

        //clicks on insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(UsrData.this);
                myDB.addUsrs(firstname_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        pass_input.getText().toString().trim(),
                        Integer.valueOf(usrID_input.getText().toString().trim())
                );
            }
        });

        //clicks on View
        //to view all data of items

        Button button1 = (Button) findViewById(R.id.btnView);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UsrData.this, userView.class);
                startActivity(i);
            }
        });
        
        //(sol 4) Updating data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(UsrData.this);
                boolean isUpdate = myDB.updateUsrData(row_id.toString(),
                        firstname_input.getText().toString(),
                        email_input.getText().toString(),
                        pass_input.getText().toString(),
                        Integer.valueOf(usrID_input.getText().toString())

                );
            }
        });
    }
}