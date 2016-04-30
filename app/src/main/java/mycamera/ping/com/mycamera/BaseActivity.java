package mycamera.ping.com.mycamera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class BaseActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void clear(){
        JLog.i("----------clear-----------");
        if (timerTask!=null){
            timerTask.cancel();
        }
        if (timer!=null){
            timer.cancel();
        }

        timer=null;
        timerTask=null;
    }

    //初始化时间
    public void initTimer() {

        clear();
        JLog.i("---------initTimer--------");
        if (timer==null){
            timer=new Timer();
        }

        if (timerTask==null){
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(BaseActivity.this,ADActivity.class));
                }
            };
        }
        timer.schedule(timerTask,5000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTimer();
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        initTimer();
        JLog.i("-----------onKeyDown-----");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        JLog.i("-----------onTouchEvent-----");
        return super.onTouchEvent(event);
    }
}
