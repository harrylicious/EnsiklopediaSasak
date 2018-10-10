package id.haqiqi_studio.ensiklopediasasak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.florent37.materialtextfield.MaterialTextField;

public class Kontak extends AppCompatActivity {
    Utils util = new Utils();
    Button btn;
    MaterialTextField msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        btn = findViewById(R.id.dialog_btn_send);
        msg = findViewById(R.id.dialog_txt_msg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.composeEmail("Harrysunaryo03@gmail.com", msg.getEditText().toString());
            }
        });
    }
}
