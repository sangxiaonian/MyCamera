package mycamera.ping.com.mycamera.shexiang;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

import mycamera.ping.com.mycamera.R;

public class Move extends Activity implements OnClickListener {
	private Button btn_record, btn_video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		btn_record = (Button) findViewById(R.id.btn_record);
		btn_video = (Button) findViewById(R.id.btn_video);

		btn_record.setOnClickListener(this);
		btn_video.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_record:
			intent = new Intent(Move.this, ControllerActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_video:
			intent = new Intent(Move.this, VideoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
