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

        initTimer();

    }

    //初始化时间
    private void initTimer() {
        clear();
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

    private void clear(){
        if (timer!=null){
            timer.cancel();
        }
        if (timerTask!=null){
            timerTask.cancel();
        }
        timer=null;
        timerTask=null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        clear();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initTimer();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        clear();
        initTimer();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        initTimer();
        return super.onTouchEvent(event);
    }
}
