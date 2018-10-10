package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import java.net.URI;

import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarNaskah;

public class Percakapan extends AppCompatActivity {
    WebView viewer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percakapan);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contoh Percakapan");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        viewer = findViewById(R.id.web);

        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.getSettings().setDomStorageEnabled(true);
        viewer.getSettings().setSupportZoom(true);
        viewer.loadUrl("file:///android_asset/Percakapan.html");

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home.class));
        super.onBackPressed();
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
        return super.onKeyDown(keycode, event);
    }
}
