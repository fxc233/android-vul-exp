package com.example.launchanywhere;

import android.content.Intent;
import android.content.ComponentName;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.accounts.AccountManager;
import android.accounts.Account;


public class MainActivity extends AppCompatActivity {

    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.launch_any_where).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.settings",
                    "com.android.settings.accounts.AddAccountSettings"));
            intent.setAction(Intent.ACTION_RUN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String[] authTypes = {"com.example.launchanywhere.type"};
            intent.putExtra("account_types", authTypes);
            intent.putExtra("authTypes", authTypes);
            startActivity(intent);
        });

    }

}