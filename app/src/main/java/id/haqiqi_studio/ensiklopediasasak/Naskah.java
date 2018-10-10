package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarNaskah;

public class Naskah extends AppCompatActivity {
    Toolbar toolbar;
    PDFView viewer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naskah);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Arsip Naskah");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarNaskah.class));
            }
        });

        String path = getIntent().getStringExtra("path");

        viewer = (PDFView) findViewById(R.id.pdfviewer);
        viewer.fromAsset(path).load();


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
