package anastasiia.babicheva.breath.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class BreathePreferences {

    static final String SELECTED_PRESET_KEY = "selectedPreset";
    static final String SELECTED_INHALE_DURATION_KEY = "selectedInhaleDuration";
    static final String SELECTED_EXHALE_DURATION_KEY = "selectedExhaleDuration";
    static final String SELECTED_HOLD_DURATION_KEY = "selectedHoldDuration";

    private static final String BREATHE_PREFS = "BreathePreferences";

    private static BreathePreferences instance;

    private SharedPreferences prefs;

    private BreathePreferences(@NonNull Context context) {
        prefs = context.getSharedPreferences(BREATHE_PREFS, Context.MODE_PRIVATE);
    }

    public static void init(@NonNull Context context) {
        if (instance == null) {
            instance = new BreathePreferences(context);
        }
    }

    public static BreathePreferences getInstance() {
        if (instance == null) {
            Log.e(BreathePreferences.class.getSimpleName(), "Call init() first");
        }

        return instance;
    }

    public void putString(@NonNull String key, @NonNull String value) {
        prefs.edit().putString(key, value).apply();
    }

    public void putLong(@NonNull String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public void putInt(@NonNull String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public void putFloat(@NonNull String key, float value) {
        prefs.edit().putFloat(key, value).apply();
    }

    @Nullable
    public String getString(@NonNull String key) {
        return prefs.getString(key, null);
    }

    public long getLong(@NonNull String key) {
        return prefs.getLong(key, -1);
    }

    public int getInt(@NonNull String key) {
        return prefs.getInt(key, -1);
    }

    public float getFloat(@NonNull String key) {
        return prefs.getFloat(key, -1);
    }

    public void clearAll() {
        prefs.edit().clear().apply();
    }

}
