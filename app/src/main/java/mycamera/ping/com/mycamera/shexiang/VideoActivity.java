package mycamera.ping.com.mycamera.shexiang;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaDataSource;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import mycamera.ping.com.mycamera.JLog;
import mycamera.ping.com.mycamera.R;
import mycamera.ping.com.mycamera.bean.Config;

public class VideoActivity extends Activity {
	private SurfaceView sv_view;
	private boolean isRecording;
	private MediaRecorder mediaRecorder;
	private Intent intent;
	private String path;

	private Button bt_start,bt_stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		intent = getIntent();
		path = intent.getStringExtra("path");


		sv_view = (SurfaceView) findViewById(R.id.sv_view);
		bt_start = (Button) findViewById(R.id.bt_start);
		bt_stop = (Button) findViewById(R.id.bt_stop);

		bt_start.setVisibility(View.VISIBLE);
	bt_stop.setVisibility(View.GONE);
		bt_start.setOnClickListener(click);
		bt_stop.setOnClickListener(click);

		// 声明Surface不维护自己的缓冲区，针对Android3.0以下设备支持
		sv_view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	private View.OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.bt_start:
					start();
					break;
				case R.id.bt_stop:
					stop();
					break;
				default:
					break;
			}
		}
	};

	protected void start() {

		bt_start.setVisibility(View.GONE);
		bt_stop.setVisibility(View.VISIBLE);

		JLog.i("-------开始录制------");
		File file1 = new File(Config.videDis);
		if (!file1.exists()||file1.isFile()){
			file1.mkdirs();
			JLog.i("创建文件夹");
		}
		try {
			File file = new File(path);
			if (file.exists()) {
				// 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
				file.delete();
			}

			mediaRecorder = new MediaRecorder();
			mediaRecorder.reset();
			// 设置音频录入源
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 设置视频图像的录入源
			mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			// 设置录入媒体的输出格式
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			// 设置音频的编码格式
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			// 设置视频的编码格式
			mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
			// 设置视频的采样率，每秒4帧
			mediaRecorder.setVideoFrameRate(4);


			JLog.i("--------180--------");
			// 设置录制视频文件的输出路径
			mediaRecorder.setOutputFile(file.getAbsolutePath());
			// 设置捕获视频图像的预览界面
			mediaRecorder.setPreviewDisplay(sv_view.getHolder().getSurface());

			mediaRecorder.setOnErrorListener(new OnErrorListener() {

				@Override
				public void onError(MediaRecorder mr, int what, int extra) {
					// 发生错误，停止录制
					mediaRecorder.stop();
					mediaRecorder.release();
					mediaRecorder = null;
					isRecording=false;
					bt_stop.setVisibility(View.GONE);
					bt_start.setVisibility(View.VISIBLE);
					Toast.makeText(VideoActivity.this, "录制出错", Toast.LENGTH_SHORT).show();
				}
			});

			// 准备、开始
			mediaRecorder.prepare();
			mediaRecorder.start();
			isRecording = true;
			Toast.makeText(VideoActivity.this, "开始录像", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void stop() {
		bt_stop.setVisibility(View.GONE);
		bt_start.setVisibility(View.VISIBLE);
		if (isRecording) {
			// 如果正在录制，停止并释放资源
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
			isRecording=false;
			Toast.makeText(VideoActivity.this, "停止录像，并保存文件", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this,ControllerActivity.class));
			finish();
		}
	}

	@Override
	protected void onDestroy() {
		if (isRecording) {
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
		}
		super.onDestroy();
	}

}
