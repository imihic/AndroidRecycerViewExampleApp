package android.tvz.listamihic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();

    private ArrayList<LanguageItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver, filter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                final Dialog dialog = new Dialog(this);
                // Dohvat layouta iz XML-a
                dialog.setContentView(R.layout.custom_dialog_layout);

                TextView textView = (TextView) dialog.findViewById(R.id.text);
                textView.setText(R.string.dialogTitle);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // Ako je gumb kliknut, posalji broadcast i zatvori dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction("CUSTOM_INTENT");
                        sendBroadcast(intent);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new LanguageItem(R.drawable.ic_c__, "C++", "http://www.w3schools.com/cpp/"));
        mExampleList.add(new LanguageItem(R.drawable.ic_java, "Java", "http://www.w3schools.com/java/"));
        mExampleList.add(new LanguageItem(R.drawable.ic_kotlin_icon, "Kotlin", "http://www.w3schools.com/kotlin/"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, android.tvz.listamihic.DetailActivity.class);
                intent.putExtra("Example Item", mExampleList.get(position));

                startActivity(intent);
            }
        });
    }
}