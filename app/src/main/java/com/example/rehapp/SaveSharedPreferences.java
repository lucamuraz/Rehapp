package com.example.rehapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreferences{

    static final String PREF_USER_NAME= "name";
    static final String PREF_USER_SURNAME= "surname";
    static final String PREF_USER_EMAIL= "email";
    static final String PREF_USER_PSW= "password";
    static final String PREF_USER_EDSS= "edss";

    static final String ACTIVITY_TYPE="type";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    public static void setUserSurname(Context ctx, String userSurname) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_SURNAME, userSurname);
        editor.apply();
    }

    public static void setUserEmail(Context ctx, String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, userEmail);
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

    public static void setActivityType(Context ctx, String type) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(ACTIVITY_TYPE, type);
        editor.apply();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserSurname(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_SURNAME, "");
    }

    public static String getUserEmail(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PSW, "");
    }

    public static String getUserEdss(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EDSS, "");
    }

    public static String getActivityType(Context ctx) {
        return getSharedPreferences(ctx).getString(ACTIVITY_TYPE, "");
    }

    public static void clearData(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.apply();
    }
}