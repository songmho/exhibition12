package com.example.saerom.exhibition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button st;
    LinearLayout [] lin = new LinearLayout[9];
    TextView tv;
    boolean isAsc=true;

    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        st = (Button)findViewById(R.id.st);
        lin[0] = (LinearLayout)findViewById(R.id.l_1);
        lin[1] = (LinearLayout)findViewById(R.id.l_2);
        lin[2] = (LinearLayout)findViewById(R.id.l_3);
        lin[3] = (LinearLayout)findViewById(R.id.l_4);
        lin[4] = (LinearLayout)findViewById(R.id.l_5);
        lin[5] = (LinearLayout)findViewById(R.id.l_6);
        lin[6] = (LinearLayout)findViewById(R.id.l_7);
        lin[7] = (LinearLayout)findViewById(R.id.l_8);
        lin[8] = (LinearLayout)findViewById(R.id.l_9);
        st.setOnTouchListener(new View.OnTouchListener() {
            int i=5;
            boolean check=true;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN){
                    //버튼 눌렀을 떄 효과
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {


                                            if(isAsc){
                                                lin[i].setBackgroundColor(0xff000000);
                                                i--;
                                                if(i<=0)
                                                    isAsc=false;
                                            }
                                            else{
                                                lin[i].setBackgroundColor(0xffFFFFFF);
                                                i++;
                                                if(i>=8)
                                                    isAsc=true;
                                            }

                                        }
                                    });
                                }
                            }.start();



                        }
                    };
                    timer.schedule(timerTask,1000,300);
                }
                else if(action==MotionEvent.ACTION_UP){
                    //버튼에서 손땠을떄
                    timer.cancel();
                    if(i==5){
                        tv.setText("맞췄습니다.");
                    }
                    else tv.setText("틀렸습니다.");

                    i=5;
                }

                return false;
            }
        });
    }
}
