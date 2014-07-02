package rs.dcsw.syncadapterdemo.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticationService extends Service {

    private AccountAuthenticator mAccountAuthenticator;

    @Override
    public void onCreate() {
        mAccountAuthenticator = new AccountAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAccountAuthenticator.getIBinder();
    }
}
