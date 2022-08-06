package com.mutairibassam.emergencydevice;

import android.app.Application;
import com.firebase.client.Firebase;

public class FireApp extends Application {

    /* FireApp class allow the application to access the firebase instead of adding it
    in every class

    */

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
