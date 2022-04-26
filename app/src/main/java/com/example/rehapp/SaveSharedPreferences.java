package com.example.rehapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class SaveSharedPreferences{

    static final String PREF_USER_NAME= "name";
    static final String PREF_USER_PSW= "password";
    static final String PREF_USER_EDSS= "EDSS";
    static final String PREF_USER= "user";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    public static void setUserPassword(Context ctx, String userPassword) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PSW, userPassword);
        editor.apply();
    }

    public static void setUserEdss(Context ctx, String userEdss) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EDSS, userEdss);
        editor.apply();
    }

    public static void setUser(Context ctx, String user) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER, user);
        editor.apply();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUser(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER, "");
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PSW, "");
    }

    public static String getUserEdss(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EDSS, "");
    }

    public static void clearData(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.apply();
    }

    public static List<String> getSystems(Context ctx){
        List<String> result= new ArrayList<>();
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("balance", false)){
            result.add("balance");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("core", false)){
            result.add("core");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("artiSuperiori", false)){
            result.add("artiSuperiori");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("artiInferiori", false)){
            result.add("artiInferiori");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("respirazione", false)){
            result.add("respirazione");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("stretching", false)){
            result.add("stretching");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("torace", false)){
            result.add("torace");
        }
        if(PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean("dorso", false)){
            result.add("dorso");
        }
        return result;
    }

    public static void setSystems(Context ctx, String listSys){
        String[] list= listSys.split(" ");
        for (String s: list) {
            PreferenceManager.getDefaultSharedPreferences(ctx).edit()
                    .putBoolean(s, true).apply();
        }
    }

}