package com.example.rehapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;

import com.example.rehapp.AppManager;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    private final Context ctx=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getInstance().setCtx(ctx);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        Toolbar toolbar=findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Impostazioni");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_24px);
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        final int tmp = extras.getInt("redirect");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, Home.class);
                i.putExtra("redirect", tmp);
                startActivity(i);
                finish();
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        private Context ctx=AppManager.getInstance().getCtx();

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            getPreferenceScreen().findPreference("user").setSummary(SaveSharedPreferences.getUser(ctx));
//            Objects.requireNonNull(getPreferenceScreen().findPreference("user")).setSummary(SaveSharedPreferences.getUser(ctx));
//            Objects.requireNonNull(getPreferenceScreen().findPreference("name")).setSummary(SaveSharedPreferences.getUserName(ctx));
            getPreferenceScreen().findPreference("name").setSummary(SaveSharedPreferences.getUserName(ctx));
//            Objects.requireNonNull(getPreferenceScreen().findPreference("Licenses")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            getPreferenceScreen().findPreference("Licenses").setOnPreferenceClickListener(preference -> {
                Intent i = new Intent(ctx, LicenseActivity.class);
                startActivity(i);
                return true;
            });

//            Objects.requireNonNull(getPreferenceScreen().findPreference("Privacy policy")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
                getPreferenceScreen().findPreference("Privacy policy").setOnPreferenceClickListener(preference -> {
                    Intent i = new Intent(ctx, TermsActivity.class);
                    startActivity(i);
                    return true;
                });

//            Objects.requireNonNull(getPreferenceScreen().findPreference("Sistemi funzionali")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
                getPreferenceScreen().findPreference("Sistemi funzionali").setOnPreferenceClickListener(preference -> {
                    Intent i = new Intent(ctx, FunctionalActivity.class);
                    startActivity(i);
                    return true;
                });

//            Objects.requireNonNull(getPreferenceScreen().findPreference("EDSS")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                getPreferenceScreen().findPreference("EDSS").setOnPreferenceChangeListener((preference, newValue) -> {
                    SaveSharedPreferences.setUserEdss(ctx, (String) newValue);
                    DAO m=new DAO();
                    m.saveNewEDSS((String) newValue, SaveSharedPreferences.getUser(ctx));
                    return true;
                });

//            Objects.requireNonNull(getPreferenceScreen().findPreference("password")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                getPreferenceScreen().findPreference("password").setOnPreferenceChangeListener((preference, newValue) -> {
                    SaveSharedPreferences.setUserPassword(ctx, (String) newValue);
                    DAO m=new DAO();
                    m.saveNewPassword((String) newValue, SaveSharedPreferences.getUser(ctx));
                    return true;
                });

//            Objects.requireNonNull(getPreferenceScreen().findPreference("Contatti")).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                getPreferenceScreen().findPreference("Contatti").setOnPreferenceClickListener(preference -> {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "rehapp.unito@gmail.com" });
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(intent, ""));
                    return false;
                });
        }
    }
}