package com.rajendra.onlineproductsapp;
import static com.rajendra.onlineproductsapp.DBHelper.TABLE_NAME1;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;

public class ItemsActivity extends AppCompatActivity {

    EditText row_id, type_input, specifier_input, range_input, qty_input;
    Button btnInsert, btnView, btnUpdate, btnDelete;

    private TextView textViewStatus;
    private EditText editTextFileName;
    String id, type, specifier, ranges;
    Integer qty;
    ImageView img, prod_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsdata);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        type_input =  findViewById(R.id.type_input);
        specifier_input =  findViewById(R.id.specifier_input);
        range_input = findViewById(R.id.range_input);
        qty_input = findViewById(R.id.qty_input);

        prod_img = findViewById(R.id.imageView2);
        editTextFileName = findViewById(R.id.editTextTextFileName);
        textViewStatus = findViewById(R.id.textViewStatus);

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //clicks on insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(ItemsActivity.this);
                type = type_input.getText().toString().trim();
                specifier = specifier_input.getText().toString().trim();
                ranges = range_input.getText().toString().trim();
                qty = Integer.valueOf(qty_input.getText().toString().trim());

                //image
               /*
                String stringFilePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+editTextFileName.getText().toString()+".jpeg";
                Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                byte[] img = byteArrayOutputStream.toByteArray();
*/
                myDB.addItems(type,specifier,ranges,qty);
                textViewStatus.setText("Insert Successful");
            }
        });

        //clicks on View
        //to view all data of items
        Button btnView = (Button) findViewById(R.id.btnView);

        try {
            btnView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(ItemsActivity.this, ItemView.class);
                    startActivity(i);

                    try {
                        ImageView btn = findViewById(R.id.product_img_src);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(ItemsActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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