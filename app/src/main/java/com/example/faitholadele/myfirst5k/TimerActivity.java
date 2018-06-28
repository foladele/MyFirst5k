package com.example.faitholadele.myfirst5k;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    //set up window properties
    DisplayMetrics displayMetrics;
    private int width;
    private int height;

    //Control buttons
    private Button start;
    private Button stop;

    int weekReps = 0;
    int weekTempReps = 0;

    //TextViews
    TextView timerTextView;
    TextView updateState;

    //times we want for week 1
    private static long START_RUN_WEEK = 0;
    private static long START_WALK_WEEK = 0;

    //timer declarations
    private CountDownTimer runCountDownTimer; //to begin running
    private CountDownTimer walkCountDownTimer; //to begin walking

    // some boolean to check states
    private boolean isRunning;
    private boolean isWalking;
    private boolean isCountingDown;

    private long timeLeftToRun;
    private long timeLeftToWalk;


    //Text to speech
    TextToSpeech workOutUpdates;

    //Vibrate
//    Vibrator vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //getExtras
        int savedRunTime = getIntent().getIntExtra("RUNTIME", 0);
        int saveWalkTime = getIntent().getIntExtra("WALKTIME", 0);
        int Reps = getIntent().getIntExtra("REPS", 0);

        weekReps = Reps;
        weekTempReps = Reps;

        START_RUN_WEEK = savedRunTime;
        START_WALK_WEEK = saveWalkTime;

        timeLeftToRun = savedRunTime;
        timeLeftToWalk = saveWalkTime;

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width * 1.0), (int)(height * 0.5));

        isRunning = false;
        isWalking = false;
        isCountingDown = false;

        start = (Button) findViewById(R.id.beginWorkout);
        stop = (Button) findViewById(R.id.stopWorkout);

        timerTextView = (TextView)findViewById(R.id.CountTimer);
        updateState = (TextView)findViewById(R.id.updates);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isWalking == false & isCountingDown == false)
                {
                    startRunTimer();
                }
                else if(isWalking == true & isCountingDown == false)
                {
                    startWalkTimer();
                }

                else if(isRunning )
                {
                    pauseRunTimer();

                }
                else if(isWalking)
                {
                    pauseWalkTimer();
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(vibrate != null)
//                    vibrate.cancel();
                finish();
            }
        });

        runUpdates();

        workOutUpdates = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                {
                    workOutUpdates.setLanguage(Locale.US);
                }
            }
        });

//        vibrate = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    }

    private void pauseRunTimer() {

        runCountDownTimer.cancel();
        isCountingDown = false;
        start.setText("START");
    }
    private void pauseWalkTimer() {

        walkCountDownTimer.cancel();
        isCountingDown = false;
        start.setText("START");
    }
    private void startRunTimer() {

        isRunning = true;
        isCountingDown = true;
        start.setText("PAUSE");
        speak("Begin Run");
//        vibrate.vibrate(300);
        updateState.setText("Running...");

        runCountDownTimer = new CountDownTimer(timeLeftToRun, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftToRun = millisUntilFinished;
                runUpdates();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                if( weekTempReps == 0)
                {
                    updateState.setText("WorkOut Completed.");
                    speak("WorkOut Completed");
//                        vibrate.vibrate(300);
                }
                else
                {
                    startWalkTimer();
                }

            }
        }.start();
    }

    // Text To speech method
    private void speak(String word) {

        workOutUpdates.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void startWalkTimer() {

        if(isRunning == false)
        {
            isWalking = true;
            updateState.setText("Walking...");
            speak("Begin Walk");
//            vibrate.vibrate(300);
            isCountingDown = true;
            start.setText("PAUSE");
            walkCountDownTimer = new CountDownTimer(timeLeftToWalk, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftToWalk = millisUntilFinished;
                    walkUpdates();
                }

                @Override
                public void onFinish() {
                    isWalking = false;
                    resetCountDownTimer();
                    isCountingDown = false;
                    if(weekReps != 0)
                    {
                        startRunTimer();
                        weekReps = weekReps - 1;
                    }
                    else if(weekReps == 0)
                    {
                        weekReps = weekTempReps;
                        updateState.setText("WorkOut Completed.");
                        speak("WorkOut Completed");
//                        vibrate.vibrate(300);
                    }
                }
            }.start();
        }
    }

    private void resetCountDownTimer() {
        timeLeftToWalk = START_WALK_WEEK;
        timeLeftToRun = START_RUN_WEEK;
        walkUpdates();
        runUpdates();
        start.setText("START");
    }

    private void walkUpdates() {

        int minutes = (int) (timeLeftToWalk / 1000) / 60;
        int seconds = (int) (timeLeftToWalk / 1000) % 60;

        String currentCount = String.format("%02d:%02d:%02d", 00, minutes, seconds);
        timerTextView.setText(currentCount);

    }

    private void runUpdates() {
        int minutes = (int) (timeLeftToRun / 1000) / 60;
        int seconds = (int) (timeLeftToRun / 1000) % 60;

        String currentCount = String.format("%02d:%02d:%02d", 00, minutes, seconds);
        timerTextView.setText(currentCount);
    }

    @Override
    protected void onDestroy() {
//        if(vibrate != null)
//            vibrate.cancel();
        if(workOutUpdates != null)
        {
            workOutUpdates.stop();
            workOutUpdates.shutdown();

        }

        super.onDestroy();
    }
}
