package cyla.juan.icab.app;

import android.app.Application;


/**
 * Created by chen on 16-4-24.
 */
public class ICAB extends Application{

    static {
        System.loadLibrary("cylacloudjni");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
