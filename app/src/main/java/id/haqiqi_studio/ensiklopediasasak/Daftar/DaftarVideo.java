package id.haqiqi_studio.ensiklopediasasak.Daftar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import id.haqiqi_studio.ensiklopediasasak.TentangSasak;
import id.haqiqi_studio.ensiklopediasasak.Adapter.VideoAdapter;
import id.haqiqi_studio.ensiklopediasasak.DBHelper;
import id.haqiqi_studio.ensiklopediasasak.Pengembangan;
import id.haqiqi_studio.ensiklopediasasak.Home;
import id.haqiqi_studio.ensiklopediasasak.Kamus;
import id.haqiqi_studio.ensiklopediasasak.Model.ModelVideo;
import id.haqiqi_studio.ensiklopediasasak.R;
import id.haqiqi_studio.ensiklopediasasak.Video;

public class DaftarVideo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;
    DBHelper mydb;
    Toolbar toolbar;
    TextView translation;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_video);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Daftar Video");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        mydb = new DBHelper(getApplicationContext());

        init();
        showData();

        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


    }

    void showData() {
        final ArrayList<ModelVideo> video = new ArrayList<>();
        video.add(new ModelVideo("Senam Sasambo", "Video", "10:39", "http://lombokit.com/wp-content/uploads/2018/10/Tari-Senam-Musik-Sasambo-YouTube.flv"));
        video.add(new ModelVideo("Gugur Mayang", "Video", "06:51", "http://lombokit.com/wp-content/uploads/2018/10/Sasak-GUGUR-MAYANG-YouTube.mkv"));
        video.add(new ModelVideo("Inak Tegining Amaq Teganang", "Video", "02:40", "http://lombokit.com/wp-content/uploads/2018/10/Lagu-sasak-inak-tegining-amaq-teganang-cover-PD.-Bhayangkari-NTB-YouTube.mkv"));

        final VideoAdapter adapter = new VideoAdapter(video, getApplicationContext());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), Video.class);
                i.putExtra("path", video.get(position).getPath());
                startActivity(i);
            }
        });
    }

    void init() {
        list = findViewById(R.id.listWords);
        translation = findViewById(R.id.listTranslator);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home.class));
        super.onBackPressed();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), Kamus.class));
        } else if (id == R.id.nav_develop) {
            startActivity(new Intent(getApplicationContext(), Pengembangan.class));
        } else if (id == R.id.nav_list) {
            startActivity(new Intent(getApplicationContext(), DaftarKata.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(getApplicationContext(), TentangSasak.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(getApplicationContext(), Kamus.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
        return super.onKeyDown(keycode, event);
    }
}
