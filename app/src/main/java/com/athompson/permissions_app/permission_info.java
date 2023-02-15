package com.athompson.permissions_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        packageManager = getPackageManager();

        List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);

        permissions = new ArrayList<>();

        for (PackageInfo packageInfo : packageList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {
                    for (String permission : requestedPermissions) {
                        permissions.add(permission);
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String permission : permissions) {
            builder.append(permission + "\n");
        }

        textView.setText(builder.toString());
    }
}

