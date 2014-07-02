package rs.dcsw.syncadapterdemo;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import rs.dcsw.syncadapterdemo.sync.AccountAuthenticator;
import rs.dcsw.syncadapterdemo.sync.SyncContentProvider;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestSyncAutomatically();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestSyncAutomatically() {
        final Account account = new Account(AccountAuthenticator.ACCOUNT_NAME,
                AccountAuthenticator.ACCOUNT_TYPE);
        ContentResolver.setSyncAutomatically(account, SyncContentProvider.AUTHORITY, true);
    }

    private void onDemandSync() {
        final Account account = new Account(AccountAuthenticator.ACCOUNT_NAME,
                AccountAuthenticator.ACCOUNT_TYPE);
        ContentResolver.requestSync(account, SyncContentProvider.AUTHORITY, null);
    }

    private void requestPeriodicSync() {
        final Account account = new Account(AccountAuthenticator.ACCOUNT_NAME,
                AccountAuthenticator.ACCOUNT_TYPE);
        long interval = 60 * 60; // one hour
        ContentResolver.addPeriodicSync(account, SyncContentProvider.AUTHORITY, null, interval);
    }
}
