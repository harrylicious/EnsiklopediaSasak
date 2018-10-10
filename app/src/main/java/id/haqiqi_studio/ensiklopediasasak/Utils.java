package id.haqiqi_studio.ensiklopediasasak;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Gentong on 26/03/2018.
 */

public class Utils extends AppCompatActivity {

    public void composeEmail(String address, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendToWA(String no) {
        Uri uri = Uri.parse("smsto:" + no);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }

    public void sendChoicer(String link) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Download Ensiklopedia Sasak : " + link);
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "Bagikan:"));
    }

    public void openBrowser(String path) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        startActivity(browserIntent);
    }
}
