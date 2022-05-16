package com.rajendra.onlineproductsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper{

	//other solution 2
	/*static final String DATABASE_NAME ="ProductDB.DB";
	static final int DATABASE_VERSION = 1;
	
	static final String DATABASE_TABLE = "USERS";
	static final String USER_ID = "_ID";
	static final String USER_NAME = "user_name";
	static final String USER_EMAIL = "email";
	static final String USER_PASSWORD = "pass";

	private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE +"( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + "EMAIL, " + USER_EMAIL + "TEXT NOT NULL, " + USER_PASSWORD + " );";
*/
	//SOLUTION 3
    private Context context;
    private static final String DATABASE_NAME = "Ecommerce.db";
    private static final int DATABASE_VERSION = 1;

    //table for product stocks
    public static final String TABLE_NAME1 = "my_stocks";
    private static final String COLUMN_ID = "product_id";
    private static final String COLUMN_TYPE = "product_type";
    private static final String COLUMN_SPECIFIER = "product_specifier";
    private static final String COLUMN_RANGE = "product_range";
    private static final String COLUMN_QTY = "prod_qty";
    private static final String COLUMN_IMG = "prod_img";



    //table for user details
    public static final String TABLE_NAME2 = "users";
    private static final String COLUMN_ID2 = "user_id";
    private static final String COLUMN_F1 = "first_name";
    private static final String COLUMN_F2 = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    //for admin uses
    private static final String COLUMN_ADMIN = "admin";

    //other solution 1 )
    DBHelper(Context context)
    {
        super (context, DATABASE_NAME, null, 1/* sol 1 "Userdata.db", null, 1 */);
   this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    //solution 2
    //DB.execSQL(CREATE_DB_QUERY);

    //sol 1
       // DB.execSQL("create Table Userdetails(usrname TEXT primary key, email TEXT, pass TEXT)");

    //sol 3 for items and products
        String query =
                "CREATE TABLE " +  TABLE_NAME1 +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TYPE + " TEXT, " +
                COLUMN_SPECIFIER + " TEXT, "
                + COLUMN_RANGE + " TEXT, " + COLUMN_QTY + " INTEGER, " + COLUMN_IMG + " BLOB );";
        DB.execSQL(query);

        //Insert default DATA to items and products
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty) VALUES (5, 'JPN-LMTD Watch', 'JPN#2593', '$299', 1, R.Drawable.prod)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty, prod_img) VALUES (2, 'Samsung Watch', 'SMG#2293', '$799', 2,R.Drawbale.prod1)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty) VALUES (3, 'Hauwei Watch', 'HWUI#2393', '$699', 4, R.Drawable.prod2)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty) VALUES (4, 'iPhone 14', 'APPL#3093', '$499', 5, R.Drawable.prod3)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty, prod_img) VALUES (1, 'Apple Watch', 'APPL#2193', '$399', 6, R.Drawable.prod4)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty) VALUES (6, 'Hauwei Pro', 'HWUI#2690', '$699', 4, R.Drawable.prod5)");
        DB.execSQL("INSERT INTO " + TABLE_NAME1 + "(product_id, product_type, product_specifier, product_range, prod_qty) VALUES (7, 'Galaxy Watch Pro', 'GLX#2793', '$699', 3, R.Drawable.prod6)");


        //for users and admins
        String usrquery =
                "CREATE TABLE " +  TABLE_NAME2 +
                        " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_F1 + " TEXT, " +
                        COLUMN_F2 + " TEXT, "
                        + COLUMN_EMAIL + " TEXT, " + COLUMN_PASS + " TEXT, " + COLUMN_ADMIN + " TEXT);";
        DB.execSQL(usrquery);
        //isnert users and admin
        DB.execSQL("INSERT INTO " + TABLE_NAME2 + "(user_id, first_name, last_name, email, pass, admin) VALUES (1, 'James', 'Conney', 'JamesConney@admin', 123, 'ADMIN')");
        DB.execSQL("INSERT INTO " + TABLE_NAME2 + "(user_id, first_name, last_name, email, pass) VALUES (41739, 'Salah', 'Saleh', '41739@aum.com', 000)");
        DB.execSQL("INSERT INTO " + TABLE_NAME2 + "(user_id, first_name, last_name, email, pass) VALUES (2000, '12', '12', 'Tester', 12)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        //sol 1
        //db.execSQL (" DROP TABLE IF EXISTS " + DATABASE_TABLE);

        //SOL 2
        //DB.execSQL("drop Table if exists Userdetails");

        //sol 3
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(DB);

        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(DB);
    }




    //sol 3 continuance (items and products)
    void addItems(String type, String specifier, String range, int qty ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (COLUMN_TYPE, type );
        cv.put (COLUMN_SPECIFIER,specifier );
        cv.put (COLUMN_RANGE, range );
        cv.put (COLUMN_QTY, qty );

        long result = db.insert(TABLE_NAME1, null, cv);
        if (result == -1 )
        {
            Toast.makeText(context, "failed to insert items", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "items successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    //for users
    Boolean addUsrs(String first_name, String email, String pass, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (COLUMN_F1, first_name );
        cv.put (COLUMN_EMAIL, email );
        cv.put (COLUMN_PASS, pass );
        cv.put (COLUMN_ID2,user_id);

        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1 )
        {
            Toast.makeText(context, "Failed to insert user", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "User successfully updated", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    //for admin users
    Boolean addAdmin(String admin, String email, String pass, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (COLUMN_ADMIN, admin );
        cv.put (COLUMN_EMAIL, email );
        cv.put (COLUMN_PASS, pass );
        cv.put (COLUMN_ID2,user_id);

        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1 )
        {
            Toast.makeText(context, "Failed to insert admin", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Admin successfully updated", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    //for user registers
    Boolean registerUsrs(String first_name,String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (COLUMN_F1, first_name );
        cv.put (COLUMN_EMAIL, email );
        cv.put (COLUMN_PASS, pass );

        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1 )
        {
            Toast.makeText(context, "Failed to create user account.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Account successfully created !", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

//part of sol1 (user data)
    /*public boolean insertuserdata(String usrname, String email, String pass)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usrname", usrname);
        contentValues.put("email", email);
        contentValues.put("pass", pass);

            long result = DB.update("Userdetails", contentValues, "usrname=?", new String[]{usrname});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
    }*/
/*
    public boolean updateuserdata(String usrname, String email, String pass)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usrname", usrname);
        contentValues.put("email", email);
        contentValues.put("pass", pass);

        //sol1
        Cursor cursor = DB.rawQuery("select * from Userdetails where name = ?", new String[]{usrname});

        if(cursor.getCount()>0) {

            long result = DB.update("Userdetails", contentValues, "usrname=?", new String[]{usrname});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else {return false;}
    }*/
/*
        public boolean deleteuserdata(String usrname)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("select * from Userdetails where name = ?", new String[]{usrname});

            if(cursor.getCount()>0) {

                long result = DB.delete("Userdetails", "usrname=?", new String[]{usrname});
                if (result == 1) {
                    return false;
                } else {
                    return true;
                }
            }else {return false;}
        }

        //sol1
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Userdetails", null);
        return cursor;
    }
*/
    //sol3
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //reads from users

    Cursor readUsrData(){
        String usrquery = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(usrquery, null);
        }
        return cursor;
    }

//update and delete section DB (sol3 part)
    public void updateData(String type, String specifier, String ranges, int qty){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(COLUMN_TYPE, type);
    cv.put(COLUMN_SPECIFIER, specifier);
    cv.put(COLUMN_RANGE, ranges);
    cv.put(COLUMN_QTY, qty);


    long result = db.update(TABLE_NAME1, cv, "product_specifier=?", new String[]{specifier});
    if(result == -1){
        Toast.makeText(context, "Failed, make sure the specifier is the same", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
    }
    }

    //Updates quantity of items when items are purchased
    public Boolean updatePurch(String specifier, int qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QTY, qty);

        long result = db.update(TABLE_NAME1, cv, "product_specifier=?", new String[]{specifier});
        if(result == -1){
            Toast.makeText(context, "Failed, items are either out of stock or product is reserved.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Purchased Successfully!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    //update for user
    public boolean updateUsrData(String first_name, String email, String pass, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_F1, first_name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASS, pass);
        cv.put(COLUMN_ID2, user_id);

        //shows the info of the table for users
        long result = db.update(TABLE_NAME2, cv, "user_id=?", new String[]{String.valueOf(user_id)});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //update for admins
    public boolean updateAdmin(String admin, String email, String pass, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ADMIN, admin);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASS, pass);
        cv.put(COLUMN_ID2, user_id);

        //shows the info of the table for users
        long result = db.update(TABLE_NAME2, cv, "user_id=?", new String[]{String.valueOf(user_id)});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //deletes one row off table product
    void deleteOneRow(String specifier){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME1, "product_specifier=?", new String[]{specifier});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    //deletes one row off table users or admins using indentifier
    void deleteUsrRow(String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, "user_id=?", new String[]{user_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    //deletes all rows off products
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1);
    }

    //deletes for  all users
    void deleteUsrData(Integer user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2);
    }

    //user login
    public Boolean checkuserdata(String username, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE first_name = ? AND email = ?", new String[] {username, email});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    public Boolean checkuserNmPass(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE first_name = ?  and pass = ?", new String[] {username,password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    public Boolean checkuserMailPass(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?  and pass = ?", new String[] { email,password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    public Boolean checkadmin(String admin, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE admin = ? AND pass = ?", new String[] {admin, password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    public Boolean checkadminmail(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND pass = ?", new String[] {email, password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    //user forgotten password
    /*
    public Boolean updatePass(String first_name, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASS, pass);

        //shows the info of the table for users
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE pass=? AND email=email AND first_name=first_name", new String[]{first_name, email, pass});
        if(cursor.getCount()>0){
            Toast.makeText(context, "Updated password Successfully !", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(context, "Failed to update password.", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }*/


}
