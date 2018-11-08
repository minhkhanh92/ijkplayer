package tv.danmaku.ijk.media.example.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

public class TestActivity extends AppCompatActivity {

    IjkVideoView videoView1;
    IjkVideoView videoView2;
    IjkVideoView videoView3;

    Button btnStart1;
    Button btnStart2;
    Button btnStart3;

    SettingsContentObserver settingsContentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        videoView1 = (IjkVideoView) findViewById(R.id.videoView1);
        TableLayout hubView = (TableLayout) findViewById(R.id.hudView1);
        videoView1.setHudView(hubView);

        btnStart1 = (Button) findViewById(R.id.btnStart1);
        btnStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnStart1.getText().toString().equals("Start")) {
                    stopVideo1();
                } else {
                    startVideo1();
                }
            }
        });

        videoView2 = (IjkVideoView) findViewById(R.id.videoView2);
        hubView = (TableLayout) findViewById(R.id.hudView2);
        videoView2.setHudView(hubView);

        btnStart2 = (Button) findViewById(R.id.btnStart2);
        btnStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnStart2.getText().toString().equals("Start")) {
                    stopVideo2();
                } else {
                    startVideo2();
                }
            }
        });

        videoView3 = (IjkVideoView) findViewById(R.id.videoView3);
        hubView = (TableLayout) findViewById(R.id.hudView3);
        videoView3.setHudView(hubView);

        btnStart3 = (Button) findViewById(R.id.btnStart3);
        btnStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btnStart3.getText().toString().equals("Start")) {
                    stopVideo3();
                } else {
                    startVideo3();
                }
            }
        });

        settingsContentObserver = new SettingsContentObserver( new Handler() );
        this.getApplicationContext().getContentResolver().registerContentObserver(
                android.provider.Settings.System.CONTENT_URI, true,
                settingsContentObserver );

        checkMaxVolume();

        //copyAssets();
    }

    private void startVideo1() {
        //videoView1.setVideoPath("rtsp://stream.veriksystems.com:443/live/89ffdbc423274f1993037d3d581e999d?role=user&userUUID=4c845cf472ba409fae08f09fb1fb200d&token=cK2hnRANpmFZ2IBTlHFL0Qz0sHgw2PGyUFQbEUbaBJm7jB3RMJqgMyAWw2VnDp1o&hubUUID=2c3fe4191a894aa485f73f7971e4c460");
        //videoView1.setVideoPath("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");

        File video = new File(Environment.getExternalStorageDirectory() + "/test/test.mp4");
        videoView1.setVideoPath(video.getPath());

        videoView1.start();
        btnStart1.setText("Stop");
    }

    private void stopVideo1() {
        videoView1.release(true);
        btnStart1.setText("Start");
    }

    private void startVideo2() {
        //videoView2.setVideoPath("rtsp://stream.veriksystems.com:443/live/91fcbd08bdb34e5bad2d28a3fc4f430e?role=user&userUUID=4c845cf472ba409fae08f09fb1fb200d&token=cK2hnRANpmFZ2IBTlHFL0Qz0sHgw2PGyUFQbEUbaBJm7jB3RMJqgMyAWw2VnDp1o&hubUUID=2c3fe4191a894aa485f73f7971e4c460");
        //videoView2.setVideoPath("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");

        //String fileName = "android.resource://"+  getPackageName() + "/raw/test1";
        //videoView2.setVideoURI(Uri.parse(fileName));

        File video = new File(Environment.getExternalStorageDirectory() + "/test/test.mp4");
        videoView2.setVideoPath(video.getPath());

        videoView2.start();
        btnStart2.setText("Stop");
    }

    private void stopVideo2() {
        videoView2.release(true);
        btnStart2.setText("Start");
    }

    private void startVideo3() {
        //videoView3.setVideoPath("rtsp://stream.veriksystems.com:443/live/91fcbd08bdb34e5bad2d28a3fc4f430e?role=user&userUUID=4c845cf472ba409fae08f09fb1fb200d&token=cK2hnRANpmFZ2IBTlHFL0Qz0sHgw2PGyUFQbEUbaBJm7jB3RMJqgMyAWw2VnDp1o&hubUUID=2c3fe4191a894aa485f73f7971e4c460");
        //videoView3.setVideoPath("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");

        File video = new File(Environment.getExternalStorageDirectory() + "/test/test.mp4");
        videoView3.setVideoPath(video.getPath());
        videoView3.start();
        btnStart3.setText("Stop");    }

    private void stopVideo3() {
        videoView3.release(true);
        btnStart3.setText("Start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopVideo1();
        stopVideo2();
        stopVideo3();

        this.getApplicationContext().getContentResolver().unregisterContentObserver(settingsContentObserver);
    }

    private void checkMaxVolume() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume >= audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
            //audio.setStreamVolume(AudioManager.STREAM_MUSIC, audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC) - 1, 0);
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(Environment.getExternalStorageDirectory() + "/test", filename);
                if (!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdirs();
                }
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public class SettingsContentObserver extends ContentObserver {

        public SettingsContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            checkMaxVolume();
        }
    }
}
