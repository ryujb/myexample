package com.pushplant.android.timesheet;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import static android.text.TextUtils.substring;

public class Main2Activity extends FragmentActivity implements MonthlyFragment.OnHeadlineSelectedListener {


    private ArrayList<itemList2> mListData = new ArrayList<itemList2>();

    DBHelper mydb;

    private static final String TAG = MConfig.TAG;
    private static final String NAME = "Main2Activity";
    private final String CLASS = NAME + "@" + Integer.toHexString(hashCode());

    private MonthlyFragment mf;

    public TextView thisMonthTv;

    private  itemList2 itemsData;

    public RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    //    getActionBar().set
      // setSupportActionBar(toolbar);

      //  setSupportActionBar(toolbar);


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


         recyclerView = (RecyclerView) findViewById(R.id.recyclerview);



//        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

//        layoutManager.set
  //      recyclerView.setLayoutManager(layoutManager);

          recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

          mydb = new DBHelper(getApplicationContext());


//        Button addButton = (Button) findViewById(R.id.main_add_bt);
//        Button monthButton = (Button) findViewById(R.id.main_monthly_bt);
//        Button weekButton = (Button) findViewById(R.id.main_weekly_bt);
//        Button dayButton = (Button) findViewById(R.id.main_daily_bt);
        thisMonthTv = (TextView) findViewById(R.id.this_month_tv);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         //itemsData  = new itemsData();

//        MyAdapter mAdapter = new MyAdapter(itemsData);
  //      recyclerView.setAdapter(mAdapter);
    //    recyclerView.setItemAnimator(new DefaultItemAnimator());

         mf = (MonthlyFragment) getFragmentManager().findFragmentById(R.id.monthly);



        mf.setOnMonthChangeListener(new MonthlyFragment.OnMonthChangeListener() {

            @Override
            public void onChange(int year, int month) {
                HLog.d(TAG, CLASS, "onChange " + year + "." + month);
                thisMonthTv.setText(year + "." + (month + 1));
            }
        });






/*
        findViewById(R.id.button5).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //여기에 이벤트를 적어주세요
//                        MonthlyFragment  mf1 = (MonthlyFragment) getSupportFragmentManager().findFragmentById(R.id.monthly);
//                        mf1.setdate(2015,1);


                        //생각하는날보다 한달 낮게 넣어야함 얘를들어 2015.02 월을 보고싶다면 2015.1 을 하단에 넣어줘야함

                        android.app.FragmentManager mf1 = getFragmentManager();
                        android.app.FragmentTransaction fmt = mf1.beginTransaction();
                        fmt.replace(R.id.monthly,new MonthlyFragment().newInstance(2015,0));
                        fmt.commit();









                    }
                }
        );

*/




    }



    public void setText(int inYear,int inMonth){
        //생각하는날보다 한달 낮게 넣어야함 얘를들어 2015.02 월을 보고싶다면 2015.1 을 하단에 넣어줘야함
        //그래서 하단에서 + 1을 해줌
        thisMonthTv.setText(inYear + "." + (inMonth + 1));
    }



    public void setList(int inYear,int inMonth,int inDay){


        String uuid = "11111";

        String ls_year = String.valueOf(inYear);
        String ls_month = doubleString(inMonth + 1);
        String ls_day = doubleString(inDay);

        String ls_arg = ls_year + ls_month + ls_day;

        Cursor c = mydb.get_day_report(uuid,ls_arg);


        String ls_indt;
        String ls_outdt;
        String ls_seq;
        String ls_WORKTIME;
        String ls_context ;

        String ls_arg1;
        String ls_arg2;
        String ls_arg3;
        String ls_arg4;



        mListData.clear();

        while (c.moveToNext()) {



            ls_indt =  c.getString(c.getColumnIndex("IN_DT"));


            Log.d("test -2222 -   ",ls_indt);

            ls_outdt =  c.getString(c.getColumnIndex("OUT_DT"));
            ls_seq = String.valueOf( c.getInt(c.getColumnIndex("SEQ")));
            ls_WORKTIME =  String.valueOf( c.getInt(c.getColumnIndex("WORKTIME")));





            ls_arg1 = "";
            if((ls_indt.length()==14) & (ls_indt != null)) {
//                ls_arg1 = "출근[" + substring(ls_indt, 0, 4) + "-" + substring(ls_indt, 4, 6) + "-" + substring(ls_indt, 6, 8) + " " + substring(ls_indt, 8,10) + ":" + substring(ls_indt, 10, 12) + ":" + substring(ls_indt, 12, 14) + "]";

                ls_arg1 = " " + substring(ls_indt, 0, 4) + "-" + substring(ls_indt, 4, 6) + "-" + substring(ls_indt, 6, 8) + " " + substring(ls_indt, 8,10) + ":" + substring(ls_indt, 10, 12) + ":" + substring(ls_indt, 12, 14) + " ";


            }else{
//                ls_arg1 = "출근[" +ls_indt + "]";
                ls_arg1 = "" +ls_indt + "";
            }

            ls_arg2 = "";
            if(ls_outdt != null) {
                if ((ls_outdt.length() == 14) & (ls_outdt != null)) {
//                    ls_arg2 = "퇴근[" + substring(ls_outdt, 0, 4) + "-" + substring(ls_outdt, 4, 6) + "-" + substring(ls_outdt, 6, 8) + " " + substring(ls_outdt, 8, 10) + ":" + substring(ls_outdt, 10, 12) + ":" + substring(ls_outdt, 12, 14) + "]";

                    ls_arg2 = " " + substring(ls_outdt, 0, 4) + "-" + substring(ls_outdt, 4, 6) + "-" + substring(ls_outdt, 6, 8) + " " + substring(ls_outdt, 8, 10) + ":" + substring(ls_outdt, 10, 12) + ":" + substring(ls_outdt, 12, 14) + " ";
                } else {
//                    ls_arg1 = "퇴근[" + ls_outdt + "]";

                    ls_arg1 = " " + ls_outdt + " ";
                }
            }

            ls_arg3 = "";
            if(ls_WORKTIME != null) {
                ls_arg3 = "" + ls_WORKTIME + " hr";
            }else{
                ls_arg3 = "0 시간";
            }

            itemList2 l_Cheeses2 = null;

            l_Cheeses2 = new itemList2("",ls_arg1,ls_arg2,ls_seq,ls_arg3);
            mListData.add(l_Cheeses2);


        }

        MyAdapter mAdapter = new MyAdapter(getApplicationContext(),mListData);
              recyclerView.setAdapter(mAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());




   //     Toast.makeText(getApplicationContext(),inYear + "." + inMonth + "." + inDay,Toast.LENGTH_LONG).show();
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
