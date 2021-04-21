package android.tvz.listamihic;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        LanguageItem exampleItem = intent.getParcelableExtra("Example Item");

        int imageRes = exampleItem.getImageResource();
        String line1 = exampleItem.getText1();
        String line2 = exampleItem.getText2();

        ImageView imageView = findViewById(R.id.image_activity2);
        imageView.setImageResource(imageRes);

        TextView textView1 = findViewById(R.id.text1_activity2);
        textView1.setText(line1);

        TextView textView2 = findViewById(R.id.text2_activity2);
        textView2.setText(line2);

        Button btnVisit = findViewById(R.id.button)
                ;
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        imageView.startAnimation(animFadeIn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, android.tvz.listamihic.ImageActivity.class);
                intent.putExtra("Example Item", exampleItem);
                startActivity(intent);
            }
        });

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicitni intent s odabirom aplikacije
                    System.out.println(line2);
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(line2));
                    intent.setType("href");
                    // Postavljanje naslova na odabir aplikacije
                    String title = getResources().getString(R.string.chooser_title);
                    // Kreiranje intenta za odabir aplikacije
                    Intent chooser = Intent.createChooser(i, title);
                    try {
                        startActivity(chooser);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), R.string.ActivityError, Toast.LENGTH_LONG);
                    }
            }
        });
    }
}
