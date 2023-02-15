package com.athompson.permissions_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<ApplicationInfo> getInstalledApps() {
        PackageManager packageManager = getPackageManager();
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);
        ArrayList<String> appNames = new ArrayList<>();
        List<ApplicationInfo> apps = getInstalledApps();
        for (ApplicationInfo app : apps) {
            appNames.add(getPackageManager().getApplicationLabel(app).toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appNames);
        listView.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, permission_info.class);
                startActivity(intent);
            }
        });
    }
}