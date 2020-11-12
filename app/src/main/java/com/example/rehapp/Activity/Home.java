package com.example.rehapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rehapp.AppManager;
import com.example.rehapp.Fragment.CalendarFragment;
import com.example.rehapp.Fragment.HomeFragment;
import com.example.rehapp.Fragment.NotifyFragment;
import com.example.rehapp.Fragment.ReportFragment;
import com.example.rehapp.R;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.SaveSharedPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigation);
        AppManager.getInstance().setBottomNavigationView(bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_AUTO);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            int tmp = extras.getInt("redirect");
            if(tmp==1){
                bottomNavigationView.setSelectedItemId(R.id.navigation_calendar);
                openFragment(CalendarFragment.newInstance());
            }else if(tmp==3){
                bottomNavigationView.setSelectedItemId(R.id.navigation_notify);
                openFragment(NotifyFragment.newInstance());
            }
        } else{
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            openFragment(HomeFragment.newInstance());
        }
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this,SettingsActivity.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.action_logout){
            SaveSharedPreferences.clearData(this);
            Intent i = new Intent(this, LogActivity.class);
            startActivity(i);
        }
        return true;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(HomeFragment.newInstance());
                            return true;
                        case R.id.navigation_calendar:
                            openFragment(CalendarFragment.newInstance());
                            return true;
                        case R.id.navigation_report:
                            openFragment(ReportFragment.newInstance());
                            return true;
                        case R.id.navigation_notify:
                            openFragment(NotifyFragment.newInstance());
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}