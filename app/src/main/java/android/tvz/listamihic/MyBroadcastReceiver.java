package android.tvz.listamihic;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("broadcast", "Broadcast recieved!");
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity) {
                Toast.makeText(context, context.getResources().getString(R.string.noConnectivity), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.connected), Toast.LENGTH_LONG).show();
            }
        }
    }
}
