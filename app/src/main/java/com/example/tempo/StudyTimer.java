package com.example.tempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class StudyTimer extends AppCompatActivity {
    TextView studyQ1;
    TextView studyQ2;
    TextView studyQ3;
    EditText studyA1;
    EditText studyA2;
    EditText studyA3;
    Button startStudyingButton;
    TextView whichSession;
    TextView time;
    Button pauseButton;
    Button resumeButton;
    boolean pause = false;
    long pauseTime;

    private View.OnClickListener pauseListener = new View.OnClickListener() {
        public void onClick(View view) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_timer);

        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(pauseListener);

        studyQ1 = findViewById(R.id.studyQ1);
        studyQ2 = findViewById(R.id.studyQ2);
        studyQ3 = findViewById(R.id.studyQ3);
        studyA1 = findViewById(R.id.studyA1);
        studyA2 = findViewById(R.id.studyA2);
        studyA3 = findViewById(R.id.studyA3);
        startStudyingButton = findViewById(R.id.startStudyingButton);
        whichSession = findViewById(R.id.whichSession);
        time = findViewById(R.id.time);
        resumeButton = findViewById(R.id.resumeButton);

        whichSession.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        resumeButton.setVisibility(View.INVISIBLE);

    }

    public void studyTimer(View view) {
        Intent intent = new Intent(view.getContext(), StudyTimer.class);
        startActivity(intent);
    }

    public void startStudying() {
        int answer1 = Integer.parseInt(studyA1.getText().toString());
        int answer2 = Integer.parseInt(studyA2.getText().toString());
        int answer3 = Integer.parseInt(studyA3.getText().toString());

        if (answer1 != 0 && answer2 != 0 && answer3 != 0) {
            studyQ1.setVisibility(View.INVISIBLE);
            studyQ2.setVisibility(View.INVISIBLE);
            studyQ3.setVisibility(View.INVISIBLE);
            studyA1.setVisibility(View.INVISIBLE);
            studyA2.setVisibility(View.INVISIBLE);
            studyA3.setVisibility(View.INVISIBLE);
            startStudyingButton.setVisibility(View.INVISIBLE);

            whichSession.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            resumeButton.setVisibility(View.VISIBLE);

            long saveMin;
            answer1 = answer1 * 60 * 1000;
            answer2 = answer2 * 60 * 1000;

            CountDownTimer timer;
            int session = 0;


            for (int i = 0; i < answer3; i++) {
                if (session == 0) {
                    whichSession.setText("Study Session");
                    timer = new CountDownTimer(answer1, 1000) {
                        long min;
                        long sec;

                        public void onTick(long millisUntilFinished) {
                            min = millisUntilFinished / 1000 / 60;
                            sec = millisUntilFinished % (1000 * 60);
                            time.setText(min + " minutes, " + sec + " seconds remaining.");
                        }

                        public void onFinish() {
                        }
                    };
                    timer.start();
                    session++;
                } else {
                    whichSession.setText("Break");
                    timer = new CountDownTimer(answer2, 1000) {
                        public void onTick(long millisUntilFinished) {
                            time.setText((millisUntilFinished / 1000 / 60) + " minutes, " + (millisUntilFinished % (1000 * 60)) + " seconds remaining.");
                        }

                        public void onFinish() {
                        }
                    };
                    timer.start();
                    session -= 1;
                }
                whichSession.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.INVISIBLE);
                resumeButton.setVisibility(View.INVISIBLE);

                studyQ1.setVisibility(View.VISIBLE);
                studyQ2.setVisibility(View.VISIBLE);
                studyQ3.setVisibility(View.VISIBLE);
                studyA1.setVisibility(View.VISIBLE);
                studyA2.setVisibility(View.VISIBLE);
                studyA3.setVisibility(View.VISIBLE);
                startStudyingButton.setVisibility(View.VISIBLE);
            }
        }else{
            whichSession.setVisibility(View.VISIBLE);
            whichSession.setText("Enter a number greater than 0 for each.");
        }
    }


    public void resume(CountDownTimer timer) {
        pause = false;
        timer.start();
    }

    public void pause(CountDownTimer timer) {
        pause = true;
        timer.cancel();
    }
}
