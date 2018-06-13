package anastasiia.babicheva.breath;


import android.app.Application;
import android.content.Context;

import anastasiia.babicheva.breath.utils.BreathePreferences;

public class BreatheApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BreathePreferences.init(getApplicationContext());
    }

}
