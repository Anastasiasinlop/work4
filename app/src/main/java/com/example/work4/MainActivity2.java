package com.example.work4; //Your package name

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private TextView timeView;
    private Button startButton;
    private Button stopButton;
    private long startTime = 0;
    private Handler handler = new Handler();
    private Runnable updateTimerThread;
    private boolean isRunning = false;
    private long timeInMilliseconds = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        timeView = findViewById(R.id.time_view);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);


        stopButton.setEnabled(false);


        View mainView = findViewById(R.id.main); // Get the main view
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        updateTimerThread = new Runnable() {
            public void run() {
                timeInMilliseconds = System.currentTimeMillis() - startTime;
                updateTimeView();
                handler.postDelayed(this, 1000);
            }
        };
    }  public void onClickStart(View view) {
        startTime = System.currentTimeMillis();
        handler.postDelayed(updateTimerThread, 0);
        isRunning = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

    }


    public void onClickStop(View view) {
        handler.removeCallbacks(updateTimerThread);
        isRunning = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    public void onClickReset(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateTimeView() {
        int seconds = (int) (timeInMilliseconds / 1000) % 60;
        int minutes = (int) ((timeInMilliseconds / (1000 * 60)) % 60);
        int hours = (int) ((timeInMilliseconds / (1000 * 60 * 60)) % 24);

        String time = String.format("%d:%02d:%02d", hours, minutes, seconds);
        timeView.setText(time);
    }
}