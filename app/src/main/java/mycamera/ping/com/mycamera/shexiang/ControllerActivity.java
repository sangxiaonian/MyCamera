package mycamera.ping.com.mycamera.shexiang;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import mycamera.ping.com.mycamera.BaseActivity;
import mycamera.ping.com.mycamera.JLog;
import mycamera.ping.com.mycamera.R;
import mycamera.ping.com.mycamera.bean.Config;

public class ControllerActivity extends BaseActivity implements OnClickListener {
    private VideoView vv_video, vv_video_down;
    private MediaController mController, mediaController_down;
    private LinearLayout ll_up,ll_down;

    private Button bt_new, bt_newdown, bt_play, btplay_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        vv_video = (VideoView) findViewById(R.id.vv_video);
        vv_video_down = (VideoView) findViewById(R.id.vv_video_down);

        bt_new = (Button) findViewById(R.id.bt_new);
        bt_newdown = (Button) findViewById(R.id.bt_new_down);

        bt_play = (Button) findViewById(R.id.bt_play);
        btplay_down = (Button) findViewById(R.id.bt_play_down);

        ll_up = (LinearLayout) findViewById(R.id.ll_up);
        ll_down= (LinearLayout) findViewById(R.id.ll_down);

        bt_new.setOnClickListener(this);
        bt_newdown.setOnClickListener(this);
        bt_play.setOnClickListener(this);
        btplay_down.setOnClickListener(this);

        // 实例化MediaController
        mController = new MediaController(this);
        mediaController_down = new MediaController(this);
    }

    @Override
    public void onClick(View view) {
        clear();
        switch (view.getId()) {
            case R.id.bt_play:

                play(vv_video, mController, Config.video);

                break;
            case R.id.bt_play_down:

                play(vv_video_down, mediaController_down, Config.videodown);
                break;
            case R.id.bt_new:
//                Intent intent = new Intent(ControllerActivity.this, VideoActivity.class);
//                intent.putExtra("path", Config.video);
//                startActivity(intent);
                start(Config.video);
                break;
            case R.id.bt_new_down:
//                Intent intentdown = new Intent(ControllerActivity.this, VideoActivity.class);
//                intentdown.putExtra("path", Config.videodown);
//                startActivity(intentdown);
                start(Config.videodown);
                break;
        }
    }

    private void play(VideoView vv_video, MediaController mController, String path) {
        File file = new File(path);


        if (!file.exists()) {
            Toast.makeText(this, "还没有视频，先录制一个吧", Toast.LENGTH_SHORT).show();
            return;
        }
        if (file.exists()) {
            if (Config.videodown.equals(path)){
                ll_down.setVisibility(View.GONE);

            }
            if (Config.video.equals(path)){
                ll_up.setVisibility(View.GONE);
            }

            JLog.i("存在" + file.getAbsolutePath());
            // 设置播放视频源的路径
            vv_video.setVideoPath(file.getAbsolutePath());
            // 为VideoView指定MediaController
            vv_video.setMediaController(mController);
            // 为MediaController指定控制的VideoView
            mController.setMediaPlayer(vv_video);

            vv_video.start();

            // 增加监听上一个和下一个的切换事件，默认这两个按钮是不显示的
//            mController.setPadding(0,0,0,getWindowManager().getDefaultDisplay().getHeight()/2);
            mController.setPrevNextListeners(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(ControllerActivity.this, "下一个", Toast.LENGTH_SHORT).show();
                }
            }, new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(ControllerActivity.this, "上一个", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void start(String path){
        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ll_down.setVisibility(View.VISIBLE);
        ll_up.setVisibility(View.VISIBLE);
    }
}


