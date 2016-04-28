package mycamera.ping.com.mycamera.bean;

import android.os.Environment;

import java.io.File;

/**
 *  Created by ping on 2016/4/28.
 */
public class Config {
    public static String mediaStorageDir = new

            File(Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),

            "MyCameraApp/pic").getAbsolutePath();

    public static String video = new

            File(Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),

            "MyCameraApp/vido").getAbsolutePath()+"/vido.mp4";

    public static String videodown = new

            File(Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),

            "MyCameraApp/vido").getAbsolutePath()+"/vido_down.mp4";

    public static String videDis = new

            File(Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),

            "MyCameraApp/vido").getAbsolutePath();
}
