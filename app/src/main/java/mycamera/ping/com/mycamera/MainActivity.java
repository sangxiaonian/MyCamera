package mycamera.ping.com.mycamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import mycamera.ping.com.mycamera.camera.CustomCamera;
import mycamera.ping.com.mycamera.shexiang.ControllerActivity;
import mycamera.ping.com.mycamera.shexiang.Move;

public class MainActivity extends BaseActivity {

    private static int CAMERA_CODE1 = 1;
    private static int CAMERA_CODE2 = 2;
    private String mFilePath = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tempPath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = tempPath + "/" + "test1.png";
    }


    public void camera3(View view) {
        startActivity(new Intent(this, CustomCamera.class));
    }

    public void camera1(View view) {

        final Intent intent = new Intent(this, ControllerActivity.class);
        startActivity(intent);
    }


}