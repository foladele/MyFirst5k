package com.example.faitholadele.myfirst5k;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //    private Button week1;
    private Button week1;
    private Button week2;
    private Button week3;
    private Button week4;
    private Button week5;
    private Button week6;
    private Button week7;
    private Button week8;
    private Button week9;
    private Button week10;
    private Button week11;
    private Button week12;
    private Button Test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.ab_layout);



        week1 = (Button) findViewById(R.id.week1);
        week1.setOnClickListener(this);
        week2 = (Button) findViewById(R.id.week2);
        week2.setOnClickListener(this);
        week3 = (Button) findViewById(R.id.week3);
        week3.setOnClickListener(this);
        week4 = (Button) findViewById(R.id.week4);
        week4.setOnClickListener(this);
        week5 = (Button) findViewById(R.id.week5);
        week5.setOnClickListener(this);
        week6 = (Button) findViewById(R.id.week6);
        week6.setOnClickListener(this);
        week7 = (Button) findViewById(R.id.week7);
        week7.setOnClickListener(this);
        week8 = (Button) findViewById(R.id.week8);
        week8.setOnClickListener(this);
        week9 = (Button) findViewById(R.id.week9);
        week9.setOnClickListener(this);
        week10 = (Button) findViewById(R.id.week10);
        week10.setOnClickListener(this);
        week11 = (Button) findViewById(R.id.week11);
        week11.setOnClickListener(this);
        week12 = (Button) findViewById(R.id.week12);
        week12.setOnClickListener(this);

        Test = (Button) findViewById(R.id.Test);
        Test.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == week1)
        {
            newActivity(30000, 180000, 5);
//            newActivity(6000, 6000, 5);

        }
        else if(v == week2)
        {
            newActivity(60000, 180000, 4);

        }
        else if(v == week3)
        {
            newActivity(90000, 180000, 4);

        }
        else if(v == week4)
        {
            newActivity(120000, 120000, 4);

        }
        else if(v == week5)
        {
            newActivity(180000, 120000, 3);

        }
        else if(v == week6)
        {
            newActivity(240000, 120000, 3);

        }
        else if(v == week7)
        {
            newActivity(360000, 120000, 2);

        }
        else if(v == week8)
        {
            newActivity(480000, 60000, 2);

        }
        else if(v == week9)
        {
            newActivity(540000, 60000, 2);

        }
        else if(v == week10)
        {
            newActivity(600000, 60000, 2);

        }
        else if(v == week11)
        {
            newActivity(900000, 60000, 1);

        }
        else if(v == week12)
        {
            newActivity(((900000) * 2), 0, 0);

        }
        else if(v == Test)
        {
            newActivity(8000, 10000, 1);
        }
        else
        {
            //custome?
            Log.i("CUSTOME", "Doing Great Things!!!!");
        }
    }

    private void newActivity(int runTime, int walkTime, int Reps){
            //disables Touch
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Intent intent = new Intent(getBaseContext(), TimerActivity.class);
        intent.putExtra("RUNTIME", runTime);
        intent.putExtra("WALKTIME", walkTime);
        intent.putExtra("REPS", Reps);
        startActivityForResult(intent, 999);
//        startActivity(new Intent(MainActivity.this, TimerActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 999)
        {
            if(resultCode == 999)
            {
                //enables Touch
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        }
    }

}
