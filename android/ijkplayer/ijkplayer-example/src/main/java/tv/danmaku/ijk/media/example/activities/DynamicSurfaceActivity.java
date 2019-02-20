package tv.danmaku.ijk.media.example.activities;

import android.content.Intent;
import android.media.MediaCodec;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

public class DynamicSurfaceActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout container1;
    private FrameLayout container2;
    private IjkVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_surface);

        container1 = (FrameLayout) findViewById(R.id.container1);
        container2 = (FrameLayout) findViewById(R.id.container2);
        videoView = (IjkVideoView) findViewById(R.id.videoView);
        videoView.setHudView((TableLayout) findViewById(R.id.hudView));

        container1.setOnClickListener(this);
        container2.setOnClickListener(this);

        videoView.setVideoPath("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");

        MediaCodec mediaCodec = null;
        //mediaCodec.setOutputSurface(new Surface(null));
        //MediaCodec.INFO_TRY_AGAIN_LATER


    }

    @Override
    public void onClick(View view) {
        if (videoView.getParent() != view) {
            ((ViewGroup) videoView.getParent()).removeView(videoView);
            ((FrameLayout) view).addView(videoView,0, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
}
