package com.example.launchanywhere;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.accounts.AbstractAccountAuthenticator;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.content.ComponentName;
import android.util.Log;


public class MyAccountService extends Service {

    private MyAccountService.AccountAuthenticator mAuthenticator;

    private MyAccountService.AccountAuthenticator getAuthenticator() {
        if (mAuthenticator == null)
            mAuthenticator = new MyAccountService.AccountAuthenticator(this);
        return mAuthenticator;
    }

    public MyAccountService() {
    }

    @Override
    public void onCreate() {
        mAuthenticator = new MyAccountService.AccountAuthenticator(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MyAccountService.AccountAuthenticator.class.getName(), "onBind");
        return getAuthenticator().getIBinder();
    }

    static class AccountAuthenticator extends AbstractAccountAuthenticator {

        public AccountAuthenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            return null;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
            Log.d("ADD Account", "success");

            Intent intent = new Intent();
            // 系统的重新设置密码页面，跳过前面的原有密码验证

            intent.setComponent(new ComponentName("com.android.settings",
                    "com.android.settings.ChooseLockPassword"));
            intent.setAction(Intent.ACTION_RUN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            final Bundle bundle = new Bundle();
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
            return bundle;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
            return null;
        }
    }
}