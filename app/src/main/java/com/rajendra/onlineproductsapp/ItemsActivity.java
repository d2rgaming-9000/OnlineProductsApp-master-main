package com.rajendra.onlineproductsapp;
import static com.rajendra.onlineproductsapp.DBHelper.TABLE_NAME1;

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

public class ItemsActivity extends AppCompatActivity {

    EditText row_id, type_input, specifier_input, range_input, qty_input;
    Button btnInsert, btnView, btnUpdate, btnDelete;

    String id;
    String type;
    String specifier;
    String ranges;
    Integer qty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsdata);

        type_input =  findViewById(R.id.type_input);
        specifier_input =  findViewById(R.id.specifier_input);
        range_input = findViewById(R.id.range_input);
        qty_input = findViewById(R.id.qty_input);


        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //clicks on insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(ItemsActivity.this);
                myDB.addItems(type_input.getText().toString().trim(),
                        specifier_input.getText().toString().trim(),
                        range_input.getText().toString().trim(),
                        Integer.valueOf(qty_input.getText().toString().trim())
                        );
            }
        });

        //clicks on View
        //to view all data of items
        Button btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ItemsActivity.this, ItemView.class);
                startActivity(i);
            }
        });

	//(sol 4) Updating data
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                   @Override
                public void onClick(View view) {
                    DBHelper myDB = new DBHelper(ItemsActivity.this);
                   //other solution
                  type = type_input.getText().toString().trim();
                  specifier = specifier_input.getText().toString().trim();
                  ranges = range_input.getText().toString().trim();
                  qty = Integer.valueOf(qty_input.getText().toString());
       		  myDB.updateData(type, specifier, ranges, qty);

       		  //This secondary solution checks for updated data, only uncomment this if it is a required option
              // and comment the above solution (sol 4 above)
       		  /* boolean isUpdate = myDB.updateData(
                            type_input.getText().toString(),
                            specifier_input.getText().toString(),
                            range_input.getText().toString(),
                            Integer.valueOf(qty_input.getText().toString())
                   ); */
                }
        });

            //sol Delete items
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(ItemsActivity.this);
                specifier = specifier_input.getText().toString().trim();
                myDB.deleteOneRow(specifier);
            }
        });
    }
}