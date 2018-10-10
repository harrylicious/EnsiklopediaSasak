package id.haqiqi_studio.ensiklopediasasak;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarKata;
import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarNaskah;
import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarVideo;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SliderView sliderView;
    SliderPagerAdapter mAdapter;

    private SliderIndicator mIndicator;
    private LinearLayout mLinearLayout;
    DrawerLayout drawer;
    NavigationView navigationView;
    CardView kosakata, chat, lagu, video, gambar, naskah, tentang_sasak, tentang_app;
    FoldingCell fc;
    Utils util = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);



        kosakata = findViewById(R.id.kosakata_card);
        chat = findViewById(R.id.chat_card);
        lagu = findViewById(R.id.lagu_card);
        naskah = findViewById(R.id.naskah_card);

        tentang_sasak = findViewById(R.id.tentang_sasak);
        tentang_app = findViewById(R.id.tentang_app);

        tentang_sasak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TentangSasak.class));
            }
        });

        tentang_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        kosakata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Kamus.class));
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Percakapan.class));
            }
        });

        lagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
            }
        });


        naskah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarNaskah.class));
            }
        });


        setupSlider();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendChoicer(getString(R.string.share_app

































































































































               ));
            }
        });
    }

    public void sendChoicer(String link) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Download Ensiklopedia Sasak : " + link);
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "Bagikan:"));
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://eksotisjogja.com/wp-content/uploads/2016/10/14719049_660490354108973_144083615771262976_n.jpg"));
        fragments.add(FragmentSlider.newInstance("http://infoincara.com/wp-content/uploads/2016/10/wisata-raja-ampat.jpg"));
        fragments.add(FragmentSlider.newInstance("https://s3-ap-southeast-1.amazonaws.com/traveloka/imageResource/2017/08/18/1503054965597-2129c242d33053b60520c5202b70bd21.jpeg"));
        fragments.add(FragmentSlider.newInstance("https://cdn.rentalmobilbali.net/wp-content/uploads/2016/05/10-tempat-wisata-favorit-wisatawan-indonesia-di-bali.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getApplicationContext(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), Home.class));
        } else if (id == R.id.nav_kamus) {
            startActivity(new Intent(getApplicationContext(), Kamus.class));
        } else if (id == R.id.nav_percakapan) {
            startActivity(new Intent(getApplicationContext(), Percakapan.class));
        } else if (id == R.id.nav_video) {
            startActivity(new Intent(getApplicationContext(), DaftarVideo.class));
        } else if (id == R.id.nav_naskah) {
            startActivity(new Intent(getApplicationContext(), Naskah.class));
        } else if (id == R.id.nav_develop) {
            startActivity(new Intent(getApplicationContext(), Pengembangan.class));
        } else if (id == R.id.nav_list) {
            startActivity(new Intent(getApplicationContext(), DaftarKata.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(getApplicationContext(), TentangSasak.class));
        } else if (id == R.id.nav_share) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            System.exit(1);
        }
        return super.onKeyDown(keycode, event);
    }

}
