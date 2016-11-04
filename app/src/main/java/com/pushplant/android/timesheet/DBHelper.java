package com.pushplant.android.timesheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Timesheet.db";
    //public static final String CONTACTS_TABLE_NAME = "contacts";
    //public static final String CONTACTS_COLUMN_ID = "id";
    //public static final String CONTACTS_COLUMN_NAME = "name";
    //public static final String CONTACTS_COLUMN_EMAIL = "email";
    //public static final String CONTACTS_COLUMN_STREET = "street";
    //public static final String CONTACTS_COLUMN_CITY = "place";
    //public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub




        db.execSQL(
        "CREATE TABLE TIMESHEET(\n" +
                                "USER_ID text not null,\n" +
                                "USER_NM text not null,\n" +
                                "SEQ INTEGER PRIMARY KEY   AUTOINCREMENT,\n" +
                                "IN_DAY text not null,\n" +
                                "IN_DT text not null,\n" +
                                "OUT_DT text ,\n" +
                                "WORKTIME int, \n" +
                                " reg_dt DATETIME DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')) not null\n" +
                                ")"

        );

        db.execSQL(
                "CREATE TABLE USER(\n" +
                        "USER_ID text not null,\n" +
                        "USER_NM text not null\n" +
                        ")"

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS TIMESHEET");

        db.execSQL("DROP TABLE IF EXISTS USER");


        onCreate(db);
    }


    public boolean insert_intime (String in_USER_ID ,String in_USER_NM ,String in_IN_DAY,String in_IN_DT)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_ID"                  ,in_USER_ID        );
        contentValues.put("USER_NM"                  ,in_USER_NM        );
        contentValues.put("IN_DAY"                      ,in_IN_DAY            );
        contentValues.put("IN_DT"                      ,in_IN_DT            );

        db.insert("TIMESHEET", null, contentValues);
        return true;

    }


    public boolean update_outtime (String in_USER_ID ,String in_USER_NM ,String in_OUT_DT,int in_SEQ)
    {
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();

//        contentValues.put("OUT_DT"                  ,in_OUT_DT        );


        db.execSQL("UPDATE TIMESHEET SET OUT_DT = '"+ in_OUT_DT +  "' " + " WHERE SEQ = " + String.valueOf(in_SEQ) + " ;");

        //"WORKTIME =  (strftime('%Y%Md%H%M%S',OUT_DT) - strftime('%Y%Md%H%M%S',IN_DT)) / 60 / 60 "  +
        //    "WORKTIME = round(Cast (( JulianDay(datetime(substr(out_dt,1,4)|| '-' || substr(out_dt,5,2)|| '-' || substr(out_dt,7,2) || ' ' || substr(out_dt,9,2)|| ':' || substr(out_dt,11,2)|| ':' || substr(out_dt,13,2))) -  JulianDay(datetime(substr(in_dt,1,4)|| '-' || substr(in_dt,5,2)|| '-' || substr(in_dt,7,2) || ' ' || substr(in_dt,9,2)|| ':' || substr(in_dt,11,2)|| ':' || substr(in_dt,13,2))) ) * 24   As Float),2)  "  +



        db.execSQL("UPDATE TIMESHEET SET WORKTIME = round(Cast (( JulianDay(datetime(substr(out_dt,1,4)|| '-' || substr(out_dt,5,2)|| '-' || substr(out_dt,7,2) || ' ' || substr(out_dt,9,2)|| ':' || substr(out_dt,11,2)|| ':' || substr(out_dt,13,2))) -  JulianDay(datetime(substr(in_dt,1,4)|| '-' || substr(in_dt,5,2)|| '-' || substr(in_dt,7,2) || ' ' || substr(in_dt,9,2)|| ':' || substr(in_dt,11,2)|| ':' || substr(in_dt,13,2))) ) * 24   As Float),2)   " +
                " WHERE SEQ = " + String.valueOf(in_SEQ) + " ;");

        //"WORKTIME =  (strftime('%Y%Md%H%M%S',OUT_DT) - strftime('%Y%Md%H%M%S',IN_DT)) / 60 / 60 "  +
//                "WORKTIME = round(Cast (( JulianDay(datetime(substr(out_dt,1,4)|| '-' || substr(out_dt,5,2)|| '-' || substr(out_dt,7,2) || ' ' || substr(out_dt,9,2)|| ':' || substr(out_dt,11,2)|| ':' || substr(out_dt,13,2))) -  JulianDay(datetime(substr(in_dt,1,4)|| '-' || substr(in_dt,5,2)|| '-' || substr(in_dt,7,2) || ' ' || substr(in_dt,9,2)|| ':' || substr(in_dt,11,2)|| ':' || substr(in_dt,13,2))) ) * 24   As Float),2)  "  +


        //db.update("TIMESHEET", contentValues, "SEQ" + "=?", new String[]{String.valueOf(in_SEQ)});
        return true;
    }

    public Cursor get_worktime(String in_user_id,String in_date){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select sum(WORKTIME) from TIMESHEET where user_id '" + in_user_id + "' \n" +
                "and IN_DAY ='" + in_date + "'" ;

        Cursor cursor = db.rawQuery(sql,null );

        return cursor;

    }


    public Cursor get_day_report(String in_user_id,String in_date){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select  seq,in_dt ,OUT_DT,WORKTIME  from TIMESHEET where user_id =  '" + in_user_id + "' \n" +
                "and IN_DAY ='" + in_date + "'" ;

        Log.d("test - 11111111111111111111111 -   ",sql);

        Cursor cursor = db.rawQuery(sql,null );



        return cursor;

    }

    public int get_seq_t(){

        Cursor res = null;
        int result_value = 0 ;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            res = db.rawQuery("select max(SEQ) max_id from TIMESHEET", null);

            if (res.getCount() > 0) {

                res.moveToFirst();
                result_value =  Integer.parseInt(res.getString(0));
                //res.getInt(res.getColumnIndex("seq_id"));
            } else {
                result_value = 0;
            }



        }catch(Exception e){
            System.out.println(e.getMessage());
            result_value = -1;
        }finally {
            res.close();
            return result_value;

        }

    }


    public Cursor AllRows() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.query("TIMESHEET", null, null, null, null, null, null);

    }

}


