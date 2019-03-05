package com.example.jose.ejerciciolistview;


import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getAppContext() {
        return instance;
    }
}
