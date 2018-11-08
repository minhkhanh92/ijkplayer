package tv.danmaku.ijk.media.example.activities;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

public class TestActivity2 extends AppCompatActivity {
    private float volume = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

//        VideoView videoView1 = (VideoView) findViewById(R.id.videoView1);
//        VideoView videoView2 = (VideoView) findViewById(R.id.videoView2);
//        VideoView videoView3 = (VideoView) findViewById(R.id.videoView3);
//        VideoView videoView4 = (VideoView) findViewById(R.id.videoView4);
//
//        String fileName = "android.resource://"+  getPackageName() + "/raw/test2";
//        videoView1.setVideoURI(Uri.parse(fileName));
//        videoView2.setVideoURI(Uri.parse(fileName));
//        videoView3.setVideoURI(Uri.parse(fileName));
//        videoView4.setVideoURI(Uri.parse(fileName));
//
//        videoView1.start();
//        videoView2.start();
//        videoView3.start();
//        videoView4.start();

        LinearLayout listView = (LinearLayout) findViewById(R.id.listView);

        List<String> videos = new ArrayList<>();

        File videoFile = new File(Environment.getExternalStorageDirectory() + "/test/test.mp4");
        for (int i = 0; i < 5; i++) {
            videos.add(videoFile.getPath());
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        for (final String video : videos) {
            View videoItem = inflater.inflate(R.layout.item_video, listView, false);
            final IjkVideoView videoView = (IjkVideoView) videoItem.findViewById(R.id.videoView);
            TableLayout hubView = (TableLayout) videoItem.findViewById(R.id.hudView);
            Button btnStart = (Button) videoItem.findViewById(R.id.btnStart);

            videoView.setHudView(hubView);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.setVideoPath(video);
                    videoView.start();
                }
            });
            listView.addView(videoItem, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        }

        Button btnDown = (Button) findViewById(R.id.btnDown);
        Button btnUp = (Button) findViewById(R.id.btnUp);
        TextView tvVolume = (TextView) findViewById(R.id.tvVolume);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume -= 0.05f;
                volumeChange();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume += 0.05f;
                volumeChange();
            }
        });

        tvVolume.setText(String.valueOf(volume));
    }

    private void volumeChange() {
        ((TextView) findViewById(R.id.tvVolume)).setText(String.valueOf(volume));
        IjkVideoView.setVolume(volume);
    }
}
