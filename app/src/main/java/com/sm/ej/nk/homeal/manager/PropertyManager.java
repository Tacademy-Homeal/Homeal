package com.sm.ej.nk.homeal.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.data.User;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class PropertyManager {

    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(HomealApplication.getContext());
        mEditor = mPrefs.edit();
    }

    private static final String FIELD_EMAIL = "email";
    public void setEmail(String email) {
        mEditor.putString(FIELD_EMAIL, email);
        mEditor.commit();
    }
    public String getEmail() {
        return mPrefs.getString(FIELD_EMAIL,"");
    }

    private static final String FIELD_PASSWORD = "password";
    public void setPassword(String password) {
        mEditor.putString(FIELD_PASSWORD, password);
        mEditor.commit();
    }
    public String getPassword() {
        return mPrefs.getString(FIELD_PASSWORD, "");
    }


    public static final String FIELD_FACEBOOK_ID = "facebookid";
    public void setFacebookId(String facebookId) {
        mEditor.putString(FIELD_FACEBOOK_ID, facebookId);
        mEditor.commit();
    }
    public String getFacebookId() {
        return mPrefs.getString(FIELD_FACEBOOK_ID, "");
    }

    private boolean isLogin = false;
    public void setLogin(boolean login) {
        isLogin = login;
    }
    public boolean isLogin() {
        return isLogin;
    }

    private User user = null;
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    private static final String FIELD_REGISTRATION_ID = "regid";
    public void setRegistrationId(String token) {
        mEditor.putString(FIELD_REGISTRATION_ID, token);
        mEditor.commit();
    }
    public String getRegistartionId(){
        return mPrefs.getString(FIELD_REGISTRATION_ID, "");
    }
}

