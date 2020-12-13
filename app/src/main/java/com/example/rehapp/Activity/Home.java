package com.example.rehapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.AppManager;
import com.example.rehapp.Fragment.CalendarFragment;
import com.example.rehapp.Fragment.HomeFragment;
import com.example.rehapp.Fragment.NotifyFragment;
import com.example.rehapp.Fragment.ReportFragment;
import com.example.rehapp.R;
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
        assert extras != null;
        int tmp = extras.getInt("redirect");
        switch (tmp){
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                openFragment(HomeFragment.newInstance());
                break;
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.navigation_calendar);
                openFragment(CalendarFragment.newInstance());
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.navigation_report);
                openFragment(ReportFragment.newInstance());
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.navigation_notify);
                openFragment(NotifyFragment.newInstance());
                break;
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
            int n=bottomNavigationView.getSelectedItemId();
            int red=0;
            switch (n) {
                case R.id.navigation_home:
                    red=0;
                    break;
                case R.id.navigation_calendar:
                    red=1;
                    break;
                case R.id.navigation_report:
                    red=2;
                    break;
                case R.id.navigation_notify:
                    red=3;
                    break;
            }
            i.putExtra("redirect", red);
            startActivity(i);
            finish();
        }else if(item.getItemId() == R.id.action_logout){
            SaveSharedPreferences.clearData(this);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
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