package mycamera.ping.com.mycamera.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import mycamera.ping.com.mycamera.BaseActivity;
import mycamera.ping.com.mycamera.R;
import mycamera.ping.com.mycamera.show.ShowActivity;

public class CameraResult extends BaseActivity {

    private String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_camera_result);
        picPath = getIntent().getStringExtra("picPath");
        ImageView imageView = (ImageView) findViewById(R.id.iv_camera_result);
        imageView.setImageBitmap(getBitmapFromPath(picPath));
    }

    private Bitmap getBitmapFromPath(String path) {
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try {
            fis = new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(fis);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }


    public void entry(View view){
        startActivity(new Intent(this, ShowActivity.class));
        finish();
    }

    public void deleted(View view){
        File file = new File(picPath);
        if (file.exists()&&file.isFile()){
            file.delete();
        }
        startActivity(new Intent(this,CustomCamera.class));
        finish();
    }
}


