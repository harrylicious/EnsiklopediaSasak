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
import android.widget.Toast;

import java.util.ArrayList;

import id.haqiqi_studio.ensiklopediasasak.TentangSasak;
import id.haqiqi_studio.ensiklopediasasak.DBHelper;
import id.haqiqi_studio.ensiklopediasasak.Pengembangan;
import id.haqiqi_studio.ensiklopediasasak.Home;
import id.haqiqi_studio.ensiklopediasasak.Kamus;
import id.haqiqi_studio.ensiklopediasasak.Model.ModelNaskah;
import id.haqiqi_studio.ensiklopediasasak.Naskah;
import id.haqiqi_studio.ensiklopediasasak.Adapter.NaskahAdapter;
import id.haqiqi_studio.ensiklopediasasak.R;

public class DaftarNaskah extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;
    DBHelper mydb;
    Toolbar toolbar;
    TextView translation;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_naskah);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Daftar Naskah");
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


    }

    void showData() {
        final ArrayList<ModelNaskah> naskah = new ArrayList<>();
        naskah.add(new ModelNaskah("Naskah Adat Kawin","Lalu Malik Hidayat",14, "naskah_adat_kawin.pdf"));
        naskah.add(new ModelNaskah("Naskah Sasak Halus","Lalu Malik Hidayat",8, "naskah_sasak_halus.pdf"));

        final NaskahAdapter adapter = new NaskahAdapter(naskah, getApplicationContext());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), naskah.get(position).getPath(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), Naskah.class);
                i.putExtra("path", naskah.get(position).getPath());
                startActivity(i);
            }
        });
    }

    void init() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        list = (ListView) findViewById(R.id.list);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Kamus.class));
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
