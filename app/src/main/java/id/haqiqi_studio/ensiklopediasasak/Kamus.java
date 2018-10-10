package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import id.haqiqi_studio.ensiklopediasasak.Anim.AnimationClasses;
import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarKata;
import id.haqiqi_studio.ensiklopediasasak.Daftar.DaftarVideo;
import id.haqiqi_studio.ensiklopediasasak.Model.ModelKata;

public class Kamus extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<ModelKata> dataModels;
    AnimationClasses anim;
    AdUtils click = new AdUtils();
    ListView list;
    ImageView imageView;
    DBHelper mydb;
    EditText txtSearch;
    TextView txtNotFound;
    FloatingActionButton fab;
    LinearLayout main;
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.indo_sasak);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        MobileAds.initialize(this, getString(R.string.banner1));


        AdView adView = (AdView) findViewById(R.id.ads1);

        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        init();
        setVisiblityList();

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setVisiblityList();
                filter();
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSearch.setText(null);
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        dataModels = new ArrayList<>();

        mydb = new DBHelper(getApplicationContext());

        if (mydb.getCount() <= 0) {
            mydb.truncateWord();
            saveAll();
        }

        list.setAdapter(mydb.getAllWords(getApplicationContext()));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mydb.getPosition(position, getApplicationContext(), view);

            }
        });


    }

    //region Inisialisasi Variabel
    void init() {
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        main = findViewById(R.id.main_card);
        list = findViewById(R.id.list);
        txtSearch = findViewById(R.id.txtSearch);
        txtNotFound = findViewById(R.id.txtNotFound);
        main.setVisibility(View.VISIBLE);
        clear = findViewById(R.id.imgClear);
    }
    //endregion

    //region Sharing
    void share() {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Download Ensiklopedia Sasak : " + getString(R.string.share_app));
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "Bagikan Ke:"));
    }
    //endregion

    void saveData() {
        if (mydb.insertWord("Test", "Tes", "Kata Kerja", "1")) {
            Toast.makeText(getApplicationContext(), "done",
                    Toast.LENGTH_SHORT).show();
            showData();
        } else {
            Toast.makeText(getApplicationContext(), "not done",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //region Bulk Insert
    void saveAll() {

        mydb.bulkInsert("Sugeng Reuh", "Selamat Datang", "");
        mydb.bulkInsert("Tampiasih", "Terima Kasih", "");
        mydb.bulkInsert("Ding", "Di", "");
        mydb.bulkInsert("Bijan", "Anak", "");
        mydb.bulkInsert("Side", "Anda", "");

        mydb.bulkInsert("Alan", "Permisi", "");
        mydb.bulkInsert("Nurgehe", "Permisi", "");
        mydb.bulkInsert("Alas cokor", "Sendal", "");
        mydb.bulkInsert("Ampure", "Maaf", "");
        mydb.bulkInsert("Ampurayan", "Muda", "");
        mydb.bulkInsert("Anom", "Muda", "");
        mydb.bulkInsert("Antos", "Tunggu", "");
        mydb.bulkInsert("Antuk", "Oleh", "");
        mydb.bulkInsert("Arep", "Depan/mau", "");
        mydb.bulkInsert("Aturang", "Berikan", "");
        mydb.bulkInsert("Awinan", "Sebabnya", "");
        mydb.bulkInsert("Base krame", "Budibahasa", "");
        mydb.bulkInsert("Basen", "Perkataan", "");
        mydb.bulkInsert("Bebaos", "Sedang Bicara", "");
        mydb.bulkInsert("Bejangkep", "Menikah", "");
        mydb.bulkInsert("Bejeneng", "Berupa", "");
        mydb.bulkInsert("Bekarye", "Bekerja,pesta  (begawe)", "");
        mydb.bulkInsert("Bekesedi", "Buang air besar", "");
        mydb.bulkInsert("Belamak", "Bertikar", "");
        mydb.bulkInsert("Belemer", "Buang air kecil", "");
        mydb.bulkInsert("Beparas", "Bercukur", "");
        mydb.bulkInsert("Besermin", "Menangis", "");
        mydb.bulkInsert("Bijesanak", "Sanak Saudara", "");
        mydb.bulkInsert("Bije", "Anak", "");
        mydb.bulkInsert("Bini", "Perempuan", "");
        mydb.bulkInsert("Bosang", "Perut", "");
        mydb.bulkInsert("Calung", "Kacamata", "");
        mydb.bulkInsert("Cokor", "Kaki", "");
        mydb.bulkInsert("Dane", "Tuan", "");
        mydb.bulkInsert("Dastar", "Sapuk", "");
        mydb.bulkInsert("Dine", "Hari", "");
        mydb.bulkInsert("Doe", "Milik", "");
        mydb.bulkInsert("Doeang", "Yang memiliki", "");
        mydb.bulkInsert("Dumeteng", "Kepada", "");
        mydb.bulkInsert("Empu", "Ibu Jari", "");
        mydb.bulkInsert("Gading", "Tangan", "");
        mydb.bulkInsert("Gadingan", "Ambil", "");
        mydb.bulkInsert("Gedeng", "Rumah", "");
        mydb.bulkInsert("Hendaweganpisan", "Mohon Kiranya", "");
        mydb.bulkInsert("ican", "Beri", "");
        mydb.bulkInsert("ican", "Kasih", "");
        mydb.bulkInsert("Icanin", "Berikan", "");

        mydb.bulkInsert("Iling", "Ingat", "");
        mydb.bulkInsert("Imbuhan", "Tambahan", "");
        mydb.bulkInsert("Inggih", "Ya", "");
        mydb.bulkInsert("Iring", "Ikut", "");
        mydb.bulkInsert("Jangantade", "Lauk Pau", "");
        mydb.bulkInsert("Darang", "Lauk Pau", "");
        mydb.bulkInsert("Jate", "Rambut", "");
        mydb.bulkInsert("Jeneng", "Rupa", "");
        mydb.bulkInsert("Kainulung", "Kain Hitam", "");
        mydb.bulkInsert("Kakiq", "Kakek", "");
        mydb.bulkInsert("Kalihkayun", "Tersinggung", "");
        mydb.bulkInsert("Kampuh", "Selimut", "");
        mydb.bulkInsert("Kapetek", "Dikuburkan", "");
        mydb.bulkInsert("Karne", "Telinga", "");
        mydb.bulkInsert("Kayun", "Suka", "");
        mydb.bulkInsert("Suke", "Suka", "");
        mydb.bulkInsert("Kekirangan", "Kekurangan", "");
        mydb.bulkInsert("Kemos", "Tersenyum", "");
        mydb.bulkInsert("Kepanggih", "Bertemu", "");
        mydb.bulkInsert("Kepaten", "Kematian", "");
        mydb.bulkInsert("Kesengan", "Disuruh", "");
        mydb.bulkInsert("Kiat", "Tertawa", "");
        mydb.bulkInsert("Kinyam", "Sehat", "");
        mydb.bulkInsert("Kiwe", "Kiri", "");
        mydb.bulkInsert("Kuace", "Baju", "");
        mydb.bulkInsert("Kiwe", "Kiri", "");
        mydb.bulkInsert("Kiwe", "Kiri", "");
        mydb.bulkInsert("Kiwe", "Kiri", "");

        mydb.bulkInsert("Kuri", "Gerbang", "");
        mydb.bulkInsert("Laki", "Lelaki", "");
        mydb.bulkInsert("Lamak", "Tikar", "");
        mydb.bulkInsert("Lanjaran", "Rokok", "");
        mydb.bulkInsert("Lati", "Lidah", "");
        mydb.bulkInsert("Layah", "Lidah", "");
        mydb.bulkInsert("Layon", "Mayit", "");
        mydb.bulkInsert("Lingsir", "Tua", "");
        mydb.bulkInsert("Luaran", "Selesai", "");
        mydb.bulkInsert("Lumbar", "Pergi", "");
        mydb.bulkInsert("Margi", "Pergi", "");
        mydb.bulkInsert("Malih", "Lagi", "");
        mydb.bulkInsert("Mangkin", "Sekarang", "");
        mydb.bulkInsert("Mantuk", "Pulang", "");
        mydb.bulkInsert("Maring", "Kepada", "");
        mydb.bulkInsert("Maturpewikan", "Permakluman", "");
        mydb.bulkInsert("Matur", "Memberitahu", "");
        mydb.bulkInsert("Mawinan", "Olehsebab", "");
        mydb.bulkInsert("Mecacap", "Bekeja", "");
        mydb.bulkInsert("Mecunduk", "Bertemu", "");
        mydb.bulkInsert("Medahar", "Makan", "");
        mydb.bulkInsert("Majengan", "Makan", "");
        mydb.bulkInsert("Nade", "Makan", "");
        mydb.bulkInsert("Medahar", "Makan", "");


        mydb.bulkInsert("Meke", "Becermin", "");
        mydb.bulkInsert("Melinggih", "Duduk", "");
        mydb.bulkInsert("Melungguh", "Duduk", "");
        mydb.bulkInsert("Memaos", "Membicarakan", "");
        mydb.bulkInsert("Menawi", "Barangkali", "");
        mydb.bulkInsert("Meneng", "Diam", "");
        mydb.bulkInsert("Mengedengin", "Mendengarkan", "");
        mydb.bulkInsert("Menggah", "Marah", "");
        mydb.bulkInsert("Duka", "Marah", "");
        mydb.bulkInsert("Mensare", "Tidur", "");
        mydb.bulkInsert("Mentangi", "Bangun Tidur", "");
        mydb.bulkInsert("Merangkat", "Menikah", "");
        mydb.bulkInsert("Mejangkep", "Menikah", "");
        mydb.bulkInsert("Merengu", "Mendengarkan", "");
        mydb.bulkInsert("Mesingit", "Bersembunyi", "");
        mydb.bulkInsert("Mesiram", "Mandi", "");
        mydb.bulkInsert("Metaken", "Bertanya", "");
        mydb.bulkInsert("Mulut", "Sungap", "");
        mydb.bulkInsert("Munapaat", "Manfaat", "");
        mydb.bulkInsert("Munggah", "Naik keatas berugak/sholat", "");
        mydb.bulkInsert("Napi", "Apa", "");
        mydb.bulkInsert("Nenten man", "Belum", "");
        mydb.bulkInsert("Nenten/Boten", "Tidak", "");
        mydb.bulkInsert("Boten", "Tidak", "");
        mydb.bulkInsert("Ngadeg", "Berdiri", "");
        mydb.bulkInsert("Ngandike", "Mengatakan", "");
        mydb.bulkInsert("Ngantos", "Menunggu", "");
        mydb.bulkInsert("Ngaturang", "Memberikan", "");
        mydb.bulkInsert("Ngayahin", "Meladeni", "");
        mydb.bulkInsert("Ngelanjar", "Merokok", "");
        mydb.bulkInsert("Ngelunsur", "Meminta", "");
        mydb.bulkInsert("Ngemban", "Pembawa Amanat", "");
        mydb.bulkInsert("Ngeranjing", "Masuk", "");
        mydb.bulkInsert("Ngerencanin", "Merepotkan", "");
        mydb.bulkInsert("Ngewedang", "Merepotkan", "");
        mydb.bulkInsert("Ngerencanin", "Minum kopi", "");
        mydb.bulkInsert("Ngimbuh", "Tambah", "");
        mydb.bulkInsert("Ngiring", "Mengikuti", "");
        mydb.bulkInsert("Nike", "Itu", "");
        mydb.bulkInsert("Niki", "Ini", "");
        mydb.bulkInsert("Ninik", "Nenek", "");
        mydb.bulkInsert("Nyaluq", "Nyusul", "");
        mydb.bulkInsert("Nyandang", "Cukup", "");
        mydb.bulkInsert("Nyedah", "Makan Daun Sirih", "");
        mydb.bulkInsert("Nyupne", "Bermimpi", "");
        mydb.bulkInsert("Oleman", "Undangan", "");


        mydb.bulkInsert("Onang", "Berwenang", "");
        mydb.bulkInsert("Pacetan", "Teman Ngopi", "");
        mydb.bulkInsert("Pageran", "Gigi", "");
        mydb.bulkInsert("Pamit", "Mohondiri", "");
        mydb.bulkInsert("Pamitang", "Meminta", "");
        mydb.bulkInsert("Pangartike", "Pengertian", "");
        mydb.bulkInsert("Pangendike", "Ucapan", "");
        mydb.bulkInsert("Paosan", "Bacaan", "");
        mydb.bulkInsert("Parek", "Menemui", "");
        mydb.bulkInsert("Pasengan", "Nama", "");
        mydb.bulkInsert("Pecandangan", "Penginang", "");
        mydb.bulkInsert("Pelabuan", "Tempat Daun Sirih", "");
        mydb.bulkInsert("Pecawisan", "Pelocok Daun Sirih", "");
        mydb.bulkInsert("Pejarupan", "Muka", "");
        mydb.bulkInsert("Pekayunan", "Kemauan", "");
        mydb.bulkInsert("Pelinggih Senamian", "Kalian semua", "");
        mydb.bulkInsert("Pelinggih/pelungguh", "Anda", "");
        mydb.bulkInsert("Pemaos", "Pembaca", "");
        mydb.bulkInsert("Pendikayang", "Suruh", "");
        mydb.bulkInsert("Penjenengan", "Saselepan (Keris)", "");
        mydb.bulkInsert("Penyerminan", "Mata", "");
        mydb.bulkInsert("Penyingakin", "Mata", "");
        mydb.bulkInsert("Pepaosan", "Tempat Membaca", "");
        mydb.bulkInsert("Peragayan", "Kemauan", "");
        mydb.bulkInsert("Rage", "Tubuh", "");
        mydb.bulkInsert("Pesarean", "Tempat Tidur", "");
        mydb.bulkInsert("Petitis", "Kending", "");
        mydb.bulkInsert("Puad", "Pusar", "");
        mydb.bulkInsert("Pulihtuturan", "Dapatcerita", "");
        mydb.bulkInsert("Pulih", "Dapat", "");
        mydb.bulkInsert("Punggalan", "Leher", "");
        mydb.bulkInsert("Jongge", "Leher", "");
        mydb.bulkInsert("Pedek", "Leher", "");
        mydb.bulkInsert("Pungkur", "Belakang/Dekat", "");
        mydb.bulkInsert("Pungkuran", "Belakangan", "");
        mydb.bulkInsert("Puput", "Selesai/Tamat", "");
        mydb.bulkInsert("Puri", "Istana", "");
        mydb.bulkInsert("Raris", "Lanjutkan", "");
        mydb.bulkInsert("Gelis", "Cepat", "");
        mydb.bulkInsert("Rawis", "Kumis/Jenggot", "");
        mydb.bulkInsert("Rawuh", "Datang", "");
        mydb.bulkInsert("Ring", "Di", "");
        mydb.bulkInsert("Sakinghendi", "Darimana", "");
        mydb.bulkInsert("Sampunnapi", "Bagaimana", "");
        mydb.bulkInsert("Sampun", "Sudah/telah", "");
        mydb.bulkInsert("Sampunang", "Tidakperlu/jangan", "");
        mydb.bulkInsert("Sanak", "Saudara", "");
        mydb.bulkInsert("Sareng(bareng)", "Dengan (bersama)", "");


        mydb.bulkInsert("Sasih", "Bulan", "");
        mydb.bulkInsert("Sebiniq/rabi", "Istri", "");
        mydb.bulkInsert("Sedah/kinang", "Daunsirih", "");
        mydb.bulkInsert("Sede/ninggal", "Meninggal", "");
        mydb.bulkInsert("Selakiq", "Suami", "");
        mydb.bulkInsert("Semugik", "Susu", "");
        mydb.bulkInsert("Serminang", "Melihat", "");
        mydb.bulkInsert("Silaq/daweg", "Ayo,mari", "");
        mydb.bulkInsert("Simpang", "Mampir", "");
        mydb.bulkInsert("Simpuh", "Bersila", "");
        mydb.bulkInsert("Singit", "Sembunyi", "");
        mydb.bulkInsert("Sipaq", "Pundak", "");
        mydb.bulkInsert("Siratmaye", "Alis", "");
        mydb.bulkInsert("Suar", "Lapar", "");
        mydb.bulkInsert("Suargi/melekat", "Almarhum", "");
        mydb.bulkInsert("Sumuran", "Hidung", "");
        mydb.bulkInsert("Sungkan/serdeng", "Sakit", "");
        mydb.bulkInsert("Tampek", "Kain", "");
        mydb.bulkInsert("Tampi", "Menerima", "");
        mydb.bulkInsert("Teantosin", "Ditungguin", "");
        mydb.bulkInsert("Tebaosang", "Sedang dibicarakan", "");
        mydb.bulkInsert("Tebaosin", "Dibicarakan", "");
        mydb.bulkInsert("Tegamel/tegading", "Dipegang (dikuasai)", "");
        mydb.bulkInsert("Temargiang", "Diberlakukan/ dilaksanakan", "");
        mydb.bulkInsert("Tendes", "Kepala", "");
        mydb.bulkInsert("Tendikayang", "Disuruh", "");
        mydb.bulkInsert("Tengen", "Kanan", "");
        mydb.bulkInsert("Teparekin", "Ditemui", "");
        mydb.bulkInsert("Tepejeneng", "Dirupakan (dibentuk)", "");
        mydb.bulkInsert("Tepetek", "Tekubur", "");
        mydb.bulkInsert("Tertiptapsile", "Sopan Santun", "");
        mydb.bulkInsert("Tiang/dewek", "Saya, aku", "");
        mydb.bulkInsert("Tititate", "Aturan", "");
        mydb.bulkInsert("Tunas", "Minta", "");
        mydb.bulkInsert("Ulung", "Hitam", "");
        mydb.bulkInsert("Upakcara", "Upacara", "");
        mydb.bulkInsert("Urip", "Hidup", "");
        mydb.bulkInsert("Utawi", "Atau", "");
        mydb.bulkInsert("Wareg", "Kenyang", "");
        mydb.bulkInsert("Warsa", "Warsa", "");
        mydb.bulkInsert("Wenten", "Ada", "");
        mydb.bulkInsert("Wonten", "Ada", "");
        mydb.bulkInsert("Wikan", "Tahu", "");
        mydb.bulkInsert("Wikanang", "Mengetahui", "");

        mydb.bulkInsert("Sekek", "Satu", "");
        mydb.bulkInsert("Saq", "Satu", "");
        mydb.bulkInsert("Sopoq", "Satu", "Halus");
        mydb.bulkInsert("Due", "Dua", "");
        mydb.bulkInsert("Telu", "Tiga", "");
        mydb.bulkInsert("Empat", "Empat", "");
        mydb.bulkInsert("Lime", "Lima", "");
        mydb.bulkInsert("Enem", "Enam", "");
        mydb.bulkInsert("Pituq", "Tujuh", "");
        mydb.bulkInsert("Baluq", "Delapan", "");
        mydb.bulkInsert("Siwaq", "Sembilan", "");
        mydb.bulkInsert("Sepulu", "Sepuluh", "");
        mydb.bulkInsert("Solas", "Sebelas", "");
        mydb.bulkInsert("Due Olas", "Dua Belas", "");
        mydb.bulkInsert("Dua Pulu", "Dua Puluh", "");

        mydb.bulkInsert("Telung Dase", "Tigas Puluh", "");
        mydb.bulkInsert("Satus", "Seratus", "");
        mydb.bulkInsert("Sataq", "Dua Ratus", "");
        mydb.bulkInsert("Telung Atus", "Empat Ratus", "");
        mydb.bulkInsert("Atus", "Ratus", "");


        mydb.bulkInsert("Aoq","Ya","");
        mydb.bulkInsert("Endeq","Tidak","");
        mydb.bulkInsert("Maraq Ruen","Seperti","");
        mydb.bulkInsert("Ni/Ne/Ngene/Saq Ni/Yaq","ini (jauh & dekat)","");
        mydb.bulkInsert("No/Ngeno/Eto/Iku","itu (jauh)","");
        mydb.bulkInsert("Tiye","Itu (dekat banget)","");
        mydb.bulkInsert("Saq","Yang","");
        mydb.bulkInsert("Dateng/Rauh","Datang","");
        mydb.bulkInsert("Lalo/Mantuk","Pergi","");
        mydb.bulkInsert("Tetu Tie ?","Benarkah ?","");
        mydb.bulkInsert("Endekn Araq","Tidak ada apa apa","");
        mydb.bulkInsert("Araq","Ada","");
        mydb.bulkInsert("Dedare","Cewek","");
        mydb.bulkInsert("Terune/Bajang","Cowok","");
        mydb.bulkInsert("Tampi Asih","Terima kasih","");
        mydb.bulkInsert("Nyampah","Sarapan","");
        mydb.bulkInsert("Betengari","Sarapan jam 9 atau lebih","");
        mydb.bulkInsert("Semaiq Waye","Pas pasan","");
        mydb.bulkInsert("Tabeq","Permisi","");
        mydb.bulkInsert("Maaf/Ampure","Maaf","");

        mydb.bulkInsert("Aku Mesaq","Saya sendiri","");
        mydb.bulkInsert("Bareng/Kance","bersama","");
        mydb.bulkInsert("Musuh","Musuh","");
        mydb.bulkInsert("Nine","Wanita/gadis","");
        mydb.bulkInsert("Mame","Lelaki","");
        mydb.bulkInsert("Kadang Jari","Kerabat","");
        mydb.bulkInsert("Semeton Jari/Sanak","Saudara","");
        mydb.bulkInsert("Aku Epen","Punyaku","");
        mydb.bulkInsert("Ruan","Keponakan","");
        mydb.bulkInsert("Dengan Toaq/ Inaq Amaq","Orang tua","");
        mydb.bulkInsert("Besemeton","Bersaudara","");
        mydb.bulkInsert("Batur","Teman","");
        mydb.bulkInsert("Kakaq","Kakak","");
        mydb.bulkInsert("Ariq","Adik laki laki","");
        mydb.bulkInsert("Kakaq","Kakak gadis","");
        mydb.bulkInsert("Ariq","Adik gadis","");
        mydb.bulkInsert("Inaq","Ibu","");
        mydb.bulkInsert("Inaq","Mama","");
        mydb.bulkInsert("Amaq/Mamiq","Ayah/bapaq","");
        mydb.bulkInsert("Papuq","Kakek/nenek","");
        mydb.bulkInsert("Amaq Kake","Pakde","");
        mydb.bulkInsert("Tuaq/Saiq","Paman/bibi","");
        mydb.bulkInsert("Pisaq/Sampu","Misan/sepupu","");
        mydb.bulkInsert("Semeton Tereq","Saudara tiri","");
        mydb.bulkInsert("Beraye/Penyayang/Harem","Pacar","");
        mydb.bulkInsert("Beraye Tere'","Kekasih yang tak dianggap","");

        mydb.bulkInsert("Semame/Selaki'An","Suami","");
        mydb.bulkInsert("Senine/Sebini'An","Istri","");
        mydb.bulkInsert("Merariq/Merarik/Betikah","Menikah","");
        mydb.bulkInsert("Seyang/Seang","Cerai/Pisah","");
        mydb.bulkInsert("Ones","Mantan/ bekas","");
        mydb.bulkInsert("Tulak Malik","Balikan","");
        mydb.bulkInsert("Telang","Menghilang","");
        mydb.bulkInsert("Anak/Bije","Anak","");
        mydb.bulkInsert("Bai/Wai","Cucu","");
        mydb.bulkInsert("Tatiq","Anakku sayang","");
        mydb.bulkInsert("Sengake","Sulung (tertua)","");
        mydb.bulkInsert("Sengari","(Termuda)","");
        mydb.bulkInsert("","","");
        mydb.bulkInsert("Daye","Utara","");
        mydb.bulkInsert("  Daye Timuq","Timur laut","");
        mydb.bulkInsert("Timuq","Timur","");
        mydb.bulkInsert("Lauq Timuq","Tenggara","");
        mydb.bulkInsert("Lauq","Selatan","");
        mydb.bulkInsert("Lauq Bat","Barat daya","");
        mydb.bulkInsert("Bat","Barat","");
        mydb.bulkInsert("Daye Bat","Barat laut","");
        mydb.bulkInsert("Tengaq","Tengah","");
        mydb.bulkInsert("Atas","Atas","");
        mydb.bulkInsert("Bawaq","Bawah","");
        mydb.bulkInsert("Taek","Naik","");
        mydb.bulkInsert("Entun","Turun","");
        mydb.bulkInsert("Teriq","Jatuh","");
        mydb.bulkInsert("Tepeng/Lomboq","Lurus","");
        mydb.bulkInsert("Biluk","Belok","");
        mydb.bulkInsert("Julu","Depan","");
        mydb.bulkInsert("Mudi/Muri","Belakang","");
        mydb.bulkInsert("Surut","Ke belakang","");
        mydb.bulkInsert("Surut Mudi","Mundur","");
        mydb.bulkInsert("Tulak","Kembali","");
        mydb.bulkInsert("Sedi","Pinggir","");
        mydb.bulkInsert("Enteh","Ayo/yuk/mari","");
        mydb.bulkInsert("Badeq/Jage","Mungkin","");
        mydb.bulkInsert("Semapate Ule (Kb)/Nyempate Ule (Kk)","Sumpah serapah","");

        mydb.bulkInsert("Aku/Tiang","Aku/Saya","");
        mydb.bulkInsert("Side/Kamu/Pelungguh","Anda/kamu","");
        mydb.bulkInsert("Meq/***M","Anda/Kamu (laki)","");
        mydb.bulkInsert("Bi","Anda/Kamu (perempuan)","");
        mydb.bulkInsert("Nie","Dia (Lk & Pr)","");

        mydb.bulkInsert("Ite","Kita","");
        mydb.bulkInsert("Kami (Belom Ada)","Kami","");
        mydb.bulkInsert("Dengan-Dengan No","Mereka (orang orang itu)","");
        mydb.bulkInsert("Side Selapuq","Kalian","");

        mydb.bulkInsert("Cucuk/Elaq","Mulut/Lidah","");
        mydb.bulkInsert("Anak Elaq/Bijen Elaq","Anak lidah","");
        mydb.bulkInsert("Sampi-Kao","Sapi-kerbau","");
        mydb.bulkInsert("Beto'","Kontol","");
        mydb.bulkInsert("Telor","Biji","");

        mydb.bulkInsert("Ape/Napi","Apa","");
        mydb.bulkInsert("Araq Ape","Ada apa","");
        mydb.bulkInsert("Kembe'N","Kenapa","");
        mydb.bulkInsert("Kembe Endek","Kenapa tidak","");
        mydb.bulkInsert("Saq Embe","Yang Mana","");
        mydb.bulkInsert("Jok Embe/Lumbar","Kemana","");
        mydb.bulkInsert("To Embe","Dimana","");
        mydb.bulkInsert("Sae","Siapa","");
        mydb.bulkInsert("Sae Doang","Siapa saja","");
        mydb.bulkInsert("Sae Side","Siapa kamu","");
        mydb.bulkInsert("Sae Arande","Siapa namamu","");
        mydb.bulkInsert("Sae Aku","Siapa aku","");
        mydb.bulkInsert("Embe Lekan De","Dari mana","");
        mydb.bulkInsert("Piran","Kapan","");
        mydb.bulkInsert("Berembe","Bagaimana","");
        mydb.bulkInsert("Leman Laeq","Dari dulu","");
        mydb.bulkInsert("Apam","Kok","");
        mydb.bulkInsert("Embe Laiq/Embe Lumbarde","maukemana","");
        mydb.bulkInsert("Uwah Mangan/Wah Ngelor","udah makan","");
        mydb.bulkInsert("Berembe Kabar","gimana kabarnya","");
        mydb.bulkInsert("Embe Uwah Laiq","kamu sudah kemana","");
        mydb.bulkInsert("Leq To","Disana","");
        mydb.bulkInsert("Leq Te/ Leq Ti","Disini","");
        mydb.bulkInsert("Aloh (Baca : Aloeh)","Sana !","");
        mydb.bulkInsert("Ti Laiq","Sini","");
        mydb.bulkInsert("Maeh !","Sini'in","");

        mydb.bulkInsert("Dowe","KATA BENDA","");
        mydb.bulkInsert("Awuk Awuk","Abu","");
        mydb.bulkInsert("Aiq","Air","");
        mydb.bulkInsert("Pendet","Asap","");
        mydb.bulkInsert("Manok","Ayam","");
        mydb.bulkInsert("Kelambi/Tangkong","Baju","");
        mydb.bulkInsert("Ambu","Bau","");
        mydb.bulkInsert("Gumi","Bumi","");
        mydb.bulkInsert("Celane","Celana","");
        mydb.bulkInsert("Pawon","Dapur","");
        mydb.bulkInsert("Gansing","Gasing","");
        mydb.bulkInsert("Rintis","Gerimis","");
        mydb.bulkInsert("Leah (Baca : Leyah)","Halaman/Beranda","");
        mydb.bulkInsert("Dowe Karaq","Harta benda","");
        mydb.bulkInsert("Ujan","Hujan","");
        mydb.bulkInsert("Popo'An/Tatapan","Jemuran","");
        mydb.bulkInsert("Jeding","Kamar mandi","");
        mydb.bulkInsert("Bembeq","Kambing","");
        mydb.bulkInsert("Bale Langgaq","Kampung halaman","");
        mydb.bulkInsert("Kedekan","Mainan","");
        mydb.bulkInsert("Jelo","Matahari","");
        mydb.bulkInsert("Aran/Pasengan","Nama","");
        mydb.bulkInsert("Dengan/Tau (Paling Kasar)","Orang","");
        mydb.bulkInsert("Dengan Tiye","Orang itu","");
        mydb.bulkInsert("Begal","Penjahat","");
        mydb.bulkInsert("Jukung","Perahu/sampan","");
        mydb.bulkInsert("Lawang","Pintu","");
        mydb.bulkInsert("Puntiq","Pisang","");
        mydb.bulkInsert("Pisang","Pisang goreng","");
        mydb.bulkInsert("Ladik","Pisau","");
        mydb.bulkInsert("Gaman","Pisau Kecil","");
        mydb.bulkInsert("Bate'/Berang","Pisau besar (golok)","");
        mydb.bulkInsert("Turis","Pengunjung/Wisatawan","");
        mydb.bulkInsert("Bale","Rumah","");
        mydb.bulkInsert("Sampi","Sapi","");
        mydb.bulkInsert(" Uni","Suara","");
        mydb.bulkInsert("Lasah","Tempat Tidur","");
        mydb.bulkInsert("Penantan","Tempat jemuran","");
        mydb.bulkInsert("Tipah","Tikar","");
        mydb.bulkInsert("Bule","Turis asing","");
        mydb.bulkInsert("Bule (Bulat Letaq)","Turis Lokal","");

        mydb.bulkInsert("Bace","Baca","");
        mydb.bulkInsert("Gacek/Lecok","Bacok/tusuk","");
        mydb.bulkInsert("Towes/Tures","Bangun","");
        mydb.bulkInsert("Bayah/Mayah","Bayar","");
        mydb.bulkInsert("Upaq","Bayaran (upah)","");
        mydb.bulkInsert("Lampaq !","Berangkat !","");
        mydb.bulkInsert("Nganjeng","Berdiri","");
        mydb.bulkInsert("Engkah","Berhenti","");
        mydb.bulkInsert("Berembok","Bernafas","");
        mydb.bulkInsert("Bebao","Berteduh","");
        mydb.bulkInsert("Adeqn Bae","Biarkan saja","");
        mydb.bulkInsert("Muni/Beruni","Bicara","");
        mydb.bulkInsert("Begiras","Blusukan","");
        mydb.bulkInsert("Lekak/Ajaq/Berugung","Bohong","");
        mydb.bulkInsert("Bukaq","Buka","");
        mydb.bulkInsert("Boyaq","Cari","");
        mydb.bulkInsert("Siduk","Cium","");
        mydb.bulkInsert("Kemok","Cipok","");
        mydb.bulkInsert("Mopoq/Natap","Cuci (pakaian)","");
        mydb.bulkInsert("Beroas/Beronas","Cuci (piring)","");
        mydb.bulkInsert("Momot/Meco/Mekoq/Mendot/Meroq","Diam","");
        mydb.bulkInsert("Tekedek/Tekarye","Dimainkan","");
        mydb.bulkInsert("Tokol","Duduk","");
        mydb.bulkInsert("Onyaq Onyaq/Awas","Hati hati/awas","");
        mydb.bulkInsert("Betelah/Mentelah","Istirahat","");
        mydb.bulkInsert("Lampaq","Jalan","");
        mydb.bulkInsert("Ngenjek","Joget","");
        mydb.bulkInsert("Bedingklang/Keto Kete/Keliningan","Keliaran","");
        mydb.bulkInsert("Sugul","Keluar","");
        mydb.bulkInsert("Los/Langsung","Langsung","");
        mydb.bulkInsert("Mengkedek","Main","");
        mydb.bulkInsert("Berembo/Beruce","Main hujan","");
        mydb.bulkInsert("Mangan/Medaran","Makan","");
        mydb.bulkInsert("Mandiq/Endaus","Mandi","");
        mydb.bulkInsert("Tame","Masuk","");
        mydb.bulkInsert("  Ngenjeq","Menjulurkan lidah","");
        mydb.bulkInsert("Nyedi/Lalo","Minggat","");
        mydb.bulkInsert("Nginem","Minum","");
        mydb.bulkInsert("Memace","Membaca","");
        mydb.bulkInsert("Maaf/Ampure","Mohon Maaf","");
        mydb.bulkInsert("Madeq/Mareq","Nginap","");
        mydb.bulkInsert("Kapong","Peluk","");
        mydb.bulkInsert("Lalo","Pergi","");
        mydb.bulkInsert("Oleq","Pulang","");
        mydb.bulkInsert("Uwah/Sampun","Sudah","");
        mydb.bulkInsert("Bedait","Temu","");
        mydb.bulkInsert("Langkep","Tengkurap","");
        mydb.bulkInsert("Ngalaq","Terlentang","");
        mydb.bulkInsert("Tindoq/Pirem","Tidur","");
        mydb.bulkInsert("Begelaq","Tidur tiduran","");
        mydb.bulkInsert("Tunggang","Tunggang","");
        mydb.bulkInsert("Tulis","Tulis","");
        mydb.bulkInsert("Empet","Tutup","");

        mydb.bulkInsert("Araq/Aku Bedowe","Ada","");
        mydb.bulkInsert("Adeqne","Agar/supaya/biar","");
        mydb.bulkInsert("Solah","Bagus","");
        mydb.bulkInsert("Seneng","Bahagia","");
        mydb.bulkInsert("Lueq","Banyak","");
        mydb.bulkInsert("Aget","Beruntung","");
        mydb.bulkInsert("Beleq","Besar","");
        mydb.bulkInsert("Jamaq","Biasa (bagaikan)","");
        mydb.bulkInsert("Bedel/Bondol","Buncit","");
        mydb.bulkInsert("Inges","Cantik","");
        mydb.bulkInsert("Seneng","Ceria","");
        mydb.bulkInsert("Gratis","Gratis","");
        mydb.bulkInsert("Dagul","Kepala bundar","");
        mydb.bulkInsert("Tangkul","Kepala (dahi) lebar","");
        mydb.bulkInsert("Ketimbang","Daripada","");
        mydb.bulkInsert("Kance","Dengan","");
        mydb.bulkInsert("Endah/Dait","Juga","");
        mydb.bulkInsert("Kodeq/Kocet","Kecil","");
        mydb.bulkInsert("Lebak","Lebar","");
        mydb.bulkInsert("Galuh","Luas (area)/lapang","");
        mydb.bulkInsert("Mateng","Manis (rasa)","");
        mydb.bulkInsert("Solah","Manis (Rupa)","");
        mydb.bulkInsert("Cerubak","Mulut lebar (Dower)","");
        mydb.bulkInsert("Adeng Adeng","Pelan pelan","");
        mydb.bulkInsert("Peos (Baca : Peyos)","Peyang (kepalanya)","");
        mydb.bulkInsert("Beyang","Pirang","");
        mydb.bulkInsert("Dendeq","Rendah","");

        mydb.bulkInsert("Sede","Rusak","");
        mydb.bulkInsert("Gati","Sangat / banget","");
        mydb.bulkInsert("Gagah/Ganteng","Tampan","");
        mydb.bulkInsert("Ndek Kance","Tanpa","");
        mydb.bulkInsert("Paling Dendeq","Terendah","");
        mydb.bulkInsert("Tinggang","Tinggi","");

        mydb.bulkInsert("Eleq/Lekan","Alamat","");
        mydb.bulkInsert("Dendeq/Engkah/Endaq","Jangaaan","");
        mydb.bulkInsert("Kampus","Kampus","");
        mydb.bulkInsert("Pantai","Pantai","");

        mydb.bulkInsert("Eyaq","Akan","");
        mydb.bulkInsert("Se'Andai","Bila/jika","");
        mydb.bulkInsert("Karing","Hanya tinggal (menyisakan)","");
        mydb.bulkInsert("Nani/Mangkin","Sekarang","");
        mydb.bulkInsert("Baruq Gati","Tadi/baru saja","");
        mydb.bulkInsert("Jangken /Nyeken","Sedang","");
        mydb.bulkInsert("Jangken Saq","Pada saat/ketika","");
        mydb.bulkInsert("Jangken","Suatu saat (lalu/nanti)","");
        mydb.bulkInsert("Lemaq/Jemaq/Laun","Nanti","");
        mydb.bulkInsert("Uwah Liwat","Sudah lalu/lewat","");
        mydb.bulkInsert("Lemaq Aru","Besok","");
        mydb.bulkInsert("Uiq/Rubin","Kemarin","");
        mydb.bulkInsert("Tiwoq Jelo","Terbit pagi","");
        mydb.bulkInsert("Menah/Kelemaq","Pagi","");
        mydb.bulkInsert("Tengari","Dhuha","");
        mydb.bulkInsert("Galeng Galeng","Siang","");
        mydb.bulkInsert("Galeng Raraq","Siang menjelang sore","");
        mydb.bulkInsert("Bian","Sore","");
        mydb.bulkInsert("Serep Jelo","Magrib","");
        mydb.bulkInsert("Kelem","Malam","");
        mydb.bulkInsert("Tengaq Kelem","Tengah/larut malam","");
        mydb.bulkInsert("Parak Menah","Dini hari","");
        mydb.bulkInsert("Malik","Lagi","");
        mydb.bulkInsert("Lat","Lusa","");
        mydb.bulkInsert("Minggu Liwat","Minggu lalu","");
        mydb.bulkInsert("Minggu Lemaq","Minggu depan","");
        mydb.bulkInsert("Semendaq/Seberaq","Sebentar","");
        mydb.bulkInsert("Kereng/Mukur","Sering","");

        mydb.bulkInsert("Ulu","Kepala","");
        mydb.bulkInsert("Otak","Otak","");
        mydb.bulkInsert("Bulu","Rambut","");
        mydb.bulkInsert("Towak","Bahu","");
        mydb.bulkInsert("Kending/Telakar","Dahi","");
        mydb.bulkInsert("Penenteng/ Mate/Penyermin","Mata","");
        mydb.bulkInsert("Kentoq","Telinga","");
        mydb.bulkInsert("Dung / Irung","Hidung","");
        mydb.bulkInsert("Jelamer","Bibir","");
        mydb.bulkInsert("Biwih","Mulut","");
        mydb.bulkInsert("Gigi/Pageran","Gigi","");
        mydb.bulkInsert("Elaq","Lidah","");
        mydb.bulkInsert("Belong","Leher","");
        mydb.bulkInsert("Dade","Dada","");
        mydb.bulkInsert("  Susu","Payudara","");
        mydb.bulkInsert("Betek","Lengan","");
        mydb.bulkInsert("Kelelek","Ketiak","");
        mydb.bulkInsert("Ranggot","Jari","");
        mydb.bulkInsert("Tian","Perut","");
        mydb.bulkInsert("Sangkep","Pipi","");
        mydb.bulkInsert("Puset","Pusar","");
        mydb.bulkInsert("Bungkak/Bongkor","Punggung","");
        mydb.bulkInsert("Keng/ Keng","Pinggul","");
        mydb.bulkInsert("Buit/ Tombong","Pantat/Bokong","");
        mydb.bulkInsert("Tolang Bungkak/Tolang Bongkor","Tulang punggung","");
        mydb.bulkInsert("Pepeq/ Tele","Kemaluan wanita","");
        mydb.bulkInsert("Butoq/ Botoq/Loloq","Kemaluan pria","");
        mydb.bulkInsert("Meneq","Kencing","");
        mydb.bulkInsert("Nai","BAB","");
        mydb.bulkInsert("Meneq","BAK","");
        mydb.bulkInsert("Impugn","Paha","");
        mydb.bulkInsert("Jengku","Lutut/Dengkul ","");
        mydb.bulkInsert("Betis","Betis","");
        mydb.bulkInsert("Nae/Ceker","Kaki","");
        mydb.bulkInsert("Lampak","Telapak","");
        mydb.bulkInsert("Rambok","Uban","");

        mydb.bulkInsert("Aing ? Kan ? (Only On Praya /Middle Lombok)","KAN ?","");
        mydb.bulkInsert("Engkah/Sah/Jah/Endaq/Kendeq","Jangan","");

        mydb.bulkInsert("Bayoq (Only On Pujut/South Middle Of Lombok)","Jelek","");
        mydb.bulkInsert("Maiq (Seluruh Lombok Kecuali Pujut)","Enak","");
        mydb.bulkInsert("Meres (Pujut)","Enak","");
        mydb.bulkInsert("Sekediq/Sekejik","Sedikit","");
        mydb.bulkInsert("Separo","Sebagian","");

        mydb.bulkInsert("Jok To/Keto/Leto/To Laiq","Kesana","");
        mydb.bulkInsert("Jok Ti/Kete/Lete/Ti Laiq","Kemari","");
        mydb.bulkInsert("Meni/Merini/Meriaq","Begini","");
        mydb.bulkInsert("Meno/Mereto/Meriku","Begitu","");
        mydb.bulkInsert("Ngenjeq","Menjulurkan lidah","");
        mydb.bulkInsert("Ngenjek","Joget","");
        mydb.bulkInsert("Ngnjeq","Meniru suara sapi","");

    }
    //endregion

    //region Show Data
    void showData() {
        list.setAdapter(mydb.getAllWords(getApplicationContext()));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                //Intent intent = new Intent(getApplicationContext(), DisplayContact.class);

                //intent.putExtras(dataBundle);
                //startActivity(intent);
            }
        });
    }
    //endregion

    //region Filter Data
    void filter() {
        if (txtSearch.getHint().toString().matches(getString(R.string.indo))) {
            list.setAdapter(mydb.filterDataIndoSasak(txtSearch.getText().toString(), getApplicationContext(), txtNotFound));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    int id_To_Search = arg2 + 1;

                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("id", id_To_Search);

                    //Intent intent = new Intent(getApplicationContext(), *.class);

                    //intent.putExtras(dataBundle);
                    //startActivity(intent);
                }
            });
        } else {
            list.setAdapter(mydb.filterDataSasakIndo(txtSearch.getText().toString(), getApplicationContext(), txtNotFound));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    int id_To_Search = arg2 + 1;

                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("id", id_To_Search);

                    //Intent intent = new Intent(getApplicationContext(), *.class);

                    //intent.putExtras(dataBundle);
                    //startActivity(intent);
                }
            });
        }
    }
    //endregion

    //region Methods
    void setVisiblityList() {
        if (txtSearch.getText().toString().matches("")) {
            clear.setVisibility(View.INVISIBLE);
            main.setVisibility(View.VISIBLE);
            list.setVisibility(View.INVISIBLE);
        } else {
            clear.setVisibility(View.VISIBLE);
            main.setVisibility(View.INVISIBLE);
            list.setVisibility(View.VISIBLE);
        }
    }
    //endregion

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ImageView img = (ImageView) menu.findItem(R.id.action_convert).getActionView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String txt = txtSearch.getHint().toString();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_convert) {
            txtSearch.setText("");
            if (txt.matches(getString(R.string.indo))) {
                anim = new AnimationClasses();
                anim.setAnimationHyperToObject(txtSearch, getApplicationContext());
                txtSearch.setHint(getString(R.string.sasak));
                toolbar.setTitle(getString(R.string.sasak_indo));
            } else {
                txtSearch.setHint(getString(R.string.indo));
                toolbar.setTitle(getString(R.string.indo_sasak));
            }
        } else if (id == R.id.action_indo_sasak) {
            txtSearch.setHint(getString(R.string.indo));
            item.setIcon(getResources().getDrawable(R.drawable.ic_apply));
        } else {
            txtSearch.setHint(getString(R.string.sasak));
            item.setIcon(getResources().getDrawable(R.drawable.ic_apply));
        }

        return super.onOptionsItemSelected(item);
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
            share();
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
