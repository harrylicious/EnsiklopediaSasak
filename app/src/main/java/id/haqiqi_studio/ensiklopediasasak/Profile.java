package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {
    ImageView fb, twitter, linkedln, yt;
    Utils util = new Utils();

    void openBrowser(String path) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        startActivity(browserIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fb = findViewById(R.id.fb);
        twitter = findViewById(R.id.twitter);
        linkedln = findViewById(R.id.linkedln);
        yt = findViewById(R.id.youtube);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(getString(R.string.fb));
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(getString(R.string.tw));
            }
        });

        linkedln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(getString(R.string.ln));
            }
        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(getString(R.string.yt));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Kontak.class));
            }
        });
    }

}
