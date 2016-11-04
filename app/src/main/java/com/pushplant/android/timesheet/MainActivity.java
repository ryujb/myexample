package com.pushplant.android.timesheet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import static android.text.TextUtils.substring;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    String uuid;

    public TS_Preference mts_preference ;
    public Button    mbutton;

    public ImageButton    mImageButton;
    public ImageView mImageView;

    public TextView mtextview;

    public Drawable tempImage;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        backPressCloseHandler = new BackPressCloseHandler(this);

        mydb = new DBHelper(this);

        mbutton = (Button)findViewById(R.id.button);
    //    mtextview = (TextView)findViewById(R.id.textView);
        mImageView  = (ImageView)findViewById(R.id.imageView2);


        mImageButton     = (ImageButton)findViewById(R.id.imageButton);

        uuid = "11111";
         mts_preference = new TS_Preference(this);

        String status  = mts_preference.getValue("status","out");

        if(    status.equals("in")) {
//            mtextview.setText("출근상태");

//            mbutton.setText("퇴근처리");

        //    Drawable tempImage = getResources().getDrawable(R.drawable.noun_21484_cc);

            mImageButton.setImageResource(R.drawable.man_out_office);
            mImageView.setImageResource(R.drawable.in_office);

        }else{
//            mtextview.setText("퇴근상태");
//            mbutton.setText("출근처리");

//            Drawable tempImage = getResources().getDrawable(R.drawable.noun_671676_cc);

            mImageButton.setImageResource(R.drawable.man_in_office);
            mImageView.setImageResource(R.drawable.out_office);

        }




        //uuid = GetDevicesUUID(this);



    //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
   //     fab.setOnClickListener(new View.OnClickListener() {
   ////         @Override
    //        public void onClick(View view) {
    //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    //                    .setAction("Action", null).show();
      //      }
      //  });



        findViewById(R.id.imageButton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //여기에 이벤트를 적어주세요

                        String in_status  = mts_preference.getValue("status","out");

                        if(    in_status.equals("in")) {
                            f_out_work(uuid);
//                            mtextview.setText("퇴근상태");
                         //   mbutton.setText("출근처리");


                            mImageButton.setImageResource(R.drawable.man_in_office);
                            mImageView.setImageResource(R.drawable.out_office);



                        }else{
                            f_in_work(uuid);
//                            mtextview.setText("출근상태");
                       //     mbutton.setText("퇴근처리");



                            mImageButton.setImageResource(R.drawable.man_out_office);
                            mImageView.setImageResource(R.drawable.in_office);

                        }



                    }
                }
        );


        findViewById(R.id.imageButton2).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //여기에 이벤트를 적어주세요

                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);

                        startActivity(intent);

                    }
                }
        );


/*        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/
    }


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//     //      super.onBackPressed();
            backPressCloseHandler.onBackPressed();

//        }
    }


    /*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    */
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    public void f_in_work (String user_id) {


       String  status  = mts_preference.getValue("status","out");

        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));



        if(    status.equals("out")){
            mydb.insert_intime(uuid,"류재봉",substring(today,0,8),today);

            mts_preference.put("status","in");

            Toast.makeText(getApplicationContext(),"출근 " + today ,Toast.LENGTH_LONG ).show();

        }else {
            Toast.makeText(getApplicationContext(),"출근 상태틀림" + status + "-" +  today ,Toast.LENGTH_LONG ).show();
        }

    }

    public void f_out_work (String user_id) {


        String status  = mts_preference.getValue("status","out");


        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));



        if(status.equals("in")){

            int li_max_seq = mydb.get_seq_t();

            mydb.update_outtime(uuid,"류재봉",today,li_max_seq);

            mts_preference.put("status","out");

            Toast.makeText(getApplicationContext(),"퇴근 " + today ,Toast.LENGTH_LONG ).show();

        }else{
            Toast.makeText(getApplicationContext(),"퇴근 상태틀림" + status + "-" +  today ,Toast.LENGTH_LONG ).show();
        }

    }


    private String GetDevicesUUID(Context mContext){
        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }

    public void f_list_work_all () {


        Cursor all_cursor = mydb.AllRows();

        while (all_cursor.moveToNext()) {

            Log.d("TEST", "USER_ID=" + all_cursor.getString(all_cursor.getColumnIndex("USER_ID")));
            Log.d("TEST", "USER_NM=" + all_cursor.getString(all_cursor.getColumnIndex("USER_NM")));
            Log.d("TEST", "IN_DAY=" + all_cursor.getString(all_cursor.getColumnIndex("IN_DAY")));
            Log.d("TEST", "IN_DT=" + all_cursor.getString(all_cursor.getColumnIndex("IN_DT")));
            Log.d("TEST", "SEQ=" + all_cursor.getInt(all_cursor.getColumnIndex("SEQ")));

            Log.d("TEST", "WORKTIME=" + all_cursor.getInt(all_cursor.getColumnIndex("WORKTIME")));

            Log.d("TEST", "OUT_DT=" + all_cursor.getString(all_cursor.getColumnIndex("OUT_DT")));
            Log.d("TEST", "reg_dt=" + all_cursor.getString(all_cursor.getColumnIndex("reg_dt")));


            // c의 int가져와라 ( c의 컬럼 중 id) 인 것의 형태이다.
            /*
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));
            Log.i("db", "id: " + _id + ", name : " + name + ", age : " + age
                    + ", address : " + address);
            */

        }
    }


    protected String doubleString(int value) {

        String temp;

        if(value < 10){
            temp = "0"+ String.valueOf(value);

        }else {
            temp = String.valueOf(value);
        }
        return temp;
    }

}
