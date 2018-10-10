package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarNaskah;
import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarVideo;

public class Video extends AppCompatActivity {
    Toolbar toolbar;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Video");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
            }
        });

        String path = getIntent().getStringExtra("path");


        video = findViewById(R.id.video);
        video.setVideoURI(Uri.parse(path));
        video.setMediaController(new MediaController(this));
        video.requestFocus();
        video.start();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
        }
        return super.onKeyDown(keycode, event);
    }
}
