package com.athompson.permissions_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class permission_info extends AppCompatActivity {
    private TextView textView;
    private PackageManager packageManager;
    private List<String> permissions;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String appName = "";

        if (extras != null) {
            appName = extras.getString("appName");
            // and get whatever type user account id is
        }
        setContentView(R.layout.permission_info);
        StringBuilder builder = new StringBuilder();
        textView = findViewById(R.id.text_view);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(appName, PackageManager.GET_PERMISSIONS);
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                for (String permission : permissions) {
                    //Log.d("Permission: ", permission);
                    builder.append("• " +permission + "\n");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        textView.setText(builder.toString());
    }
}

