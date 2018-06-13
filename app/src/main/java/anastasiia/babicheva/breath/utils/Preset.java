package anastasiia.babicheva.breath.utils;


import anastasiia.babicheva.breath.R;

public enum Preset {

    WARM_FLAME(0, R.drawable.bg_circle_preset_warm_flame),
    NIGHT_FADE(1, R.drawable.bg_circle_preset_night_fade),
    WINTER_NEVA(2, R.drawable.bg_circle_preset_winter_neva),
    MORNING_SALAD(3, R.drawable.bg_circle_preset_morning_salad),
    SOFT_GRASS(4, R.drawable.bg_circle_preset_soft_grass);

    private final int settingsPosition;
    private final int resId;

    Preset(int settingsPosition, int resId) {
        this.settingsPosition = settingsPosition;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

}
