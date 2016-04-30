package mycamera.ping.com.mycamera.show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import mycamera.ping.com.mycamera.BaseActivity;
import mycamera.ping.com.mycamera.JLog;
import mycamera.ping.com.mycamera.R;
import mycamera.ping.com.mycamera.bean.Config;

public class ShowActivity extends BaseActivity {

    private ImageView img;
    private RecyclerView mRecyclerView;
    private File[]files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        img = (ImageView) findViewById(R.id.show_img);
        mRecyclerView = (RecyclerView) findViewById(R.id.show_recy);
        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);

        File file =new File(Config.mediaStorageDir);
        if (file.exists()&&file.isDirectory()){
            files=file.listFiles();
        }
        JLog.i(file.length());
        mRecyclerView.setAdapter(new MyAdapter());
    }





    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ShowHolder(LayoutInflater.from(ShowActivity.this).inflate(R.layout.show_item,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ShowHolder showHolder = (ShowHolder) holder;
            Glide.with(ShowActivity.this)
                    .load(files[showHolder.getPosition()])
                    .centerCrop()
                    .into(showHolder.img_item);

            showHolder.img_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Glide.with(ShowActivity.this)
                            .load(files[showHolder.getPosition()])
                            .centerCrop()
                            .into(img);
                    initTimer();
                }
            });
        }

        @Override
        public int getItemCount() {
            return files.length;
        }
    }

    class ShowHolder extends RecyclerView.ViewHolder{
        public ImageView img_item;
        public ShowHolder(View itemView) {
            super(itemView);
            img_item = (ImageView) itemView.findViewById(R.id.imag_item);
        }
    }
}
