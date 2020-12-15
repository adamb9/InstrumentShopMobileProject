package com.example.mobiledevicesproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";

    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ADDRESS = "address";

    private static final String TABLE_Cart = "cart";
    private static final String KEY_NAME = "name";
    private static final String KEY_CODE = "code";
    private static final String KEY_PRICE = "price";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_ADDRESS + " TEXT" + ")";

        String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_Cart + "("
                + KEY_CODE + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " int" + ")";
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }

    //Create new user
    public void addUser(String username, String password, String address) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USERNAME, username);
        cValues.put(KEY_PASSWORD, password);
        cValues.put(KEY_ADDRESS, address);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> getUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT username, password FROM " + TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_USERNAME, KEY_PASSWORD},
                KEY_ID + "=?", new String[]{String.valueOf(userid)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("username", cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            user.put("password", cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            userList.add(user);
        }
        return userList;
    }

    public ArrayList<HashMap<String, String>> getUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, password FROM " + TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_ID, KEY_PASSWORD},
                KEY_USERNAME + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("password", cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            userList.add(user);
        }
        return userList;
    }

    // Update User Details
    public int updateUserDetails(String address, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_ADDRESS, address);
        int count = db.update(TABLE_Users, cVals, KEY_ID + " = ?", new String[]
                {String.valueOf(id)});
        return count;
    }

    // Delete User Details
    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID + " = ?", new String[]{String.valueOf(userid)});
        db.close();
    }


    //Create new cart item
    public void addCartItem(String id, String name, int price) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_CODE, id);
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PRICE, price);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Cart, null, cValues);
        db.close();
    }

    // Get Item Details based on an item code
    public ArrayList<HashMap<String, String>> getItemByID(int itemid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();
        String query = "SELECT name, price FROM " + TABLE_Cart;
        Cursor cursor = db.query(TABLE_Cart, new String[]{KEY_NAME, KEY_PRICE},
                KEY_CODE + "=?", new String[]{String.valueOf(itemid)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> item = new HashMap<>();
            item.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            item.put("price", cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
            itemList.add(item);
        }
        return itemList;
    }

    public ArrayList<HashMap<String, String>> getItemByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();
        String query = "SELECT code, price FROM " + TABLE_Cart;
        Cursor cursor = db.query(TABLE_Cart, new String[]{KEY_CODE, KEY_PRICE},
                KEY_NAME + "=?", new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> item = new HashMap<>();
            item.put("code", cursor.getString(cursor.getColumnIndex(KEY_CODE)));
            item.put("price", cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
            itemList.add(item);
        }
        return itemList;
    }

    // Update Cart Item Details
    public int updateUserDetails(int price, int itemid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_PRICE, price);
        int count = db.update(TABLE_Cart, cVals, KEY_CODE + " = ?", new String[]
                {String.valueOf(itemid)});
        return count;
    }

    // Delete Cart Item Details
    public void DeleteItem(int itemid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Cart, KEY_CODE + " = ?", new String[]{String.valueOf(itemid)});
        db.close();
    }

    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from cart");
        db.close();
    }

    public ArrayList<Item_Instrument> getAllCartItems() {
        String sql = "select * from " + TABLE_Cart;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Item_Instrument> cartItems = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String price = cursor.getString(2);
                cartItems.add(new Item_Instrument(name, price, id));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }

}
