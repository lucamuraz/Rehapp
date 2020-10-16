package com.example.rehapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rehapp.Fragment.CalendarFragment;
import com.example.rehapp.Fragment.HomeFragment;
import com.example.rehapp.Fragment.NotifyFragment;
import com.example.rehapp.Fragment.ReportFragment;
import com.example.rehapp.R;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_AUTO);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));
        toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.rehapp);
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
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_calendar:
                            openFragment(CalendarFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_report:
                            openFragment(ReportFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notify:
                            openFragment(NotifyFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
}