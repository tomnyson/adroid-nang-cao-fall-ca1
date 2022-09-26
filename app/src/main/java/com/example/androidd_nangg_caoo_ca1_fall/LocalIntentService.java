package com.example.androidd_nangg_caoo_ca1_fall;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LocalIntentService extends IntentService {
    public LocalIntentService() {
        super("local service");
    }

    // TODO: Customize helper method
    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this, "Intent service đã call", Toast.LENGTH_SHORT).show();
    }

}
