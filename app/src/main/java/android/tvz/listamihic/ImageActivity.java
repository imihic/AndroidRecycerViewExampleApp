package android.tvz.listamihic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        LanguageItem exampleItem = intent.getParcelableExtra("Example Item");

        int imageRes = exampleItem.getImageResource();

        ImageView imageView = findViewById(R.id.image_activity2);
        imageView.setImageResource(imageRes);

    }
}
