package com.athompson.permissions_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {
    SearchView searchView;
    ArrayAdapter<String> adapter;
    public List<ApplicationInfo> getInstalledApps() {
        PackageManager packageManager = getPackageManager();
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = findViewById(R.id.search_view);
        ListView listView = findViewById(R.id.list_view);
        ArrayList<String> appNames = new ArrayList<>();
        ArrayList<String> appClassNames = new ArrayList<>();
        List<ApplicationInfo> apps = getInstalledApps();
        for (ApplicationInfo app : apps) {
            appNames.add(getPackageManager().getApplicationLabel(app).toString());
            appClassNames.add(app.taskAffinity);

        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent appInfo = new Intent(MainActivity.this, permission_info.class);
                //appInfo.putExtra("appName", listView.getItemAtPosition(i).toString()); //Optional parameters
                appInfo.putExtra("appName", appClassNames.get(i)); //Optional parameters
                Log.d("appName", appClassNames.get(i));
                startActivity(appInfo);
            }
        });
        searchView.setOnQueryTextListener(this);


 ;

    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.getFilter().filter(newText);
        return false;
    }

}