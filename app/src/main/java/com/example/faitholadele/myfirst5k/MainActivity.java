package com.example.faitholadele.myfirst5k;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private Button week1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        
        week1 = (Button) findViewById(R.id.week1);

        week1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open timer window
                startActivity(new Intent(MainActivity.this, TimerActivity.class));

            }
        });
    }




    
}
