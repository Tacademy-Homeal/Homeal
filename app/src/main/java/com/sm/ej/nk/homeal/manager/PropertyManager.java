package com.sm.ej.nk.homeal.manager;

import android.content.SharedPreferences;

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


        private static final String FIELD_EMAIL = "email";
        public void setEmail(String email) {

        }


        private static final String FIELD_PASSWORD = "password";
        public void setPassword(String password) {

        }


        public static final String FIELD_FACEBOOK_ID = "facebookid";
        public void setFacebookId(String facebookId) {

        }

        private boolean isLogin = false;
        public void setLogin(boolean login) {
            isLogin = login;
        }
        public boolean isLogin() {
            return isLogin;
        }

        public void setUser() {

        }


        private static final String FIELD_REGISTRATION_ID = "regid";
        public void setRegistrationToken(String token) {

        }

}

