package com.example.trucksharingmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {

        super(context, "truck_sharing_db", null, 11);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT ,FULLNAME TEXT, USERNAME TEXT, PASSWORD TEXT,PHONENUMBER TEXT)";
        String CREATE_TRUCK_TABLE = "CREATE TABLE TRUCKS(TRUCKID INTEGER PRIMARY KEY AUTOINCREMENT ,TRUCKNAME TEXT, TRUCKDESCRIPTION TEXT)";
        String CREATE_ORDER_TABLE = "CREATE TABLE ORDERS(ORDERID INTEGER PRIMARY KEY AUTOINCREMENT ,SENDERNAME TEXT,RECEIVERNAME TEXT, PICKUPTIME TEXT,DROPOFFTIME TEXT,WEIGHT TEXT,HEIGHT TEXT,WIDTH TEXT,LENGHT TEXT,QUANTITY TEXT,TYPE TEXT)";


        String ROW1 = "INSERT INTO TRUCKS (TRUCKNAME,TRUCKDESCRIPTION) VALUES ('Bandito','El Bandito was a Chevy S-10 monster truck owned by Don King out of Missouri. The truck was a former Terry Woodcock truck, then became El Bandito in 2004 and ran until 2012 when the truck became Bucked Up under Straight Up Racing');";
        String ROW2 = "INSERT INTO TRUCKS (TRUCKNAME,TRUCKDESCRIPTION) VALUES ('Rambo','Rambo was a series of Chevrolet monster trucks owned by Bill Weaver Jr. out of Hastings, New York. It was best known for winning 2 Thunder Nationals championships');";
        String ROW3 = "INSERT INTO TRUCKS (TRUCKNAME,TRUCKDESCRIPTION) VALUES ('Chief','Big Chief was a Ford Raptor monster truck owned and driven by Chris Trussell since 2015. It was formerly the Bars Leaks Eliminator');";



        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_TRUCK_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
        sqLiteDatabase.execSQL(ROW1);
        sqLiteDatabase.execSQL(ROW2);
        sqLiteDatabase.execSQL(ROW3);


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        String DROP_TRUCK_TABLE = "DROP TABLE IF EXISTS TRUCKS";
        String DROP_ORDER_TABLE = "DROP TABLE IF EXISTS ORDERS";

        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(DROP_TRUCK_TABLE);
        sqLiteDatabase.execSQL(DROP_ORDER_TABLE);

        onCreate(sqLiteDatabase);
    }
    public long insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("FULLNAME",user.getFullname());
        contentValues.put("USERNAME",user.getUsername());
        contentValues.put("PASSWORD",user.getPassword());
        contentValues.put("PHONENUMBER",user.getPhoneNumber());

        long newRow = db.insert("USERS",null,contentValues);
        db.close();
        return newRow;
    }

    public boolean fetchUser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"userid"}, "USERNAME=? and PASSWORD=?",new String[]{username, password}, null, null, null);
        int rowsCount = cursor.getCount();
        db.close();

        if (rowsCount > 0)
            return  true;
        else
            return false;
    }
    public long insertTruck(Truck truck)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("TRUCKNAME",truck.getTruckname());
        contentValues.put("TRUCKDESCRIPTION",truck.getDescription());

        long newRow = db.insert("TRUCKS",null,contentValues);
        db.close();
        return newRow;
    }
    public List<Truck> fetchAllTrucks (){
        List<Truck> truckList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM TRUCKS";
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                String truckname = cursor.getString(1);
                String truckdescription = cursor.getString(2);
                Truck truck = new Truck(truckname,truckdescription);
                truckList.add(truck);

            } while (cursor.moveToNext());

        }

        return truckList;
    }
    public long insertOrder(Order order)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("SENDERNAME",order.getSenderName());
        contentValues.put("RECEIVERNAME",order.getReceiverName());
        contentValues.put("PICKUPTIME",order.getPickupTime());
        contentValues.put("DROPOFFTIME",order.getDropoffTime());
        contentValues.put("WEIGHT",order.getWeight());
        contentValues.put("HEIGHT",order.getHeight());
        contentValues.put("WIDTH",order.getWidth());
        contentValues.put("LENGHT",order.getLength());
        contentValues.put("QUANTITY",order.getQuantity());
        contentValues.put("TYPE",order.getType());
        long newRow = db.insert("ORDERS",null,contentValues);
        db.close();
        return newRow;
    }
    public List<Order> fetchAllOrders (String username){
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM ORDERS WHERE SENDERNAME = '"+username.trim()+"'" ;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                String sendername = cursor.getString(1);
                String receivername = cursor.getString(2);
                String pickuptime = cursor.getString(3);
                String dropofftime = cursor.getString(4);
                String weight = cursor.getString(5);
                String height = cursor.getString(6);
                String width = cursor.getString(7);
                String length = cursor.getString(8);
                String quantity = cursor.getString(9);
                String type = cursor.getString(10);

                Order order = new Order(sendername,receivername,pickuptime,dropofftime,weight,height,width,length,quantity,type);
                orderList.add(order);

            } while (cursor.moveToNext());

        }

        return orderList;
    }
}
