package mycamera.ping.com.mycamera;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ADActivity extends AppCompatActivity {

    private int[]ids={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    private ImageSwitcher imageSwitcher;


    private int index;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index++;
            if (index>ids.length-1){
                index=0;
            }
            imageSwitcher.setImageResource(ids[index]);
            handler.sendEmptyMessageDelayed(0,5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.img);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ADActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        imageSwitcher.setImageResource(ids[index]);
        handler.sendEmptyMessageDelayed(0,5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(new Intent(this,MainActivity.class));
        return super.onTouchEvent(event);
    }
}
