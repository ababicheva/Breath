package anastasiia.babicheva.breath.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import anastasiia.babicheva.breath.R;
import anastasiia.babicheva.breath.utils.Constants;
import anastasiia.babicheva.breath.utils.Preset;
import anastasiia.babicheva.breath.utils.SettingsUtils;

public class SettingsDialog extends Dialog {

    private SettingsChangeListener listener;

    private RadioGroup radioGroup;

    public SettingsDialog(@NonNull Context context, @NonNull SettingsChangeListener listener) {
        super(context, R.style.Theme_SettingsDialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_overlay);
        getWindow().getAttributes().windowAnimations = R.style.Theme_SettingsDialog;

        radioGroup = findViewById(R.id.rg_gradients);
        SeekBar inhaleSeekBar = findViewById(R.id.seekBar_inhale);
        SeekBar exhaleSeekBar = findViewById(R.id.seekBar_exhale);
        SeekBar holdSeekBar = findViewById(R.id.seekBar_hold);
        Button closeButton = findViewById(R.id.btn_close);

        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        inhaleSeekBar.setOnSeekBarChangeListener(inhaleSeekBarChangeListener);
        exhaleSeekBar.setOnSeekBarChangeListener(exhaleSeekBarChangeListener);
        holdSeekBar.setOnSeekBarChangeListener(holdSeekBarChangeListener);
        closeButton.setOnClickListener(closeBtnClickListener);

        ((RadioButton) radioGroup.getChildAt(SettingsUtils.getSelectedPreset())).setChecked(true);
        inhaleSeekBar.setProgress(SettingsUtils.getSelectedInhaleDuration() / Constants.MILLISECOND);
        exhaleSeekBar.setProgress(SettingsUtils.getSelectedExhaleDuration() / Constants.MILLISECOND);
        holdSeekBar.setProgress(SettingsUtils.getSelectedHoldDuration() / Constants.MILLISECOND);
    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            Preset selectedPreset;
            switch (checkedId) {
                case R.id.rb_1:
                    selectedPreset = Preset.WARM_FLAME;
                    break;
                case R.id.rb_2:
                    selectedPreset = Preset.NIGHT_FADE;
                    break;
                case R.id.rb_3:
                    selectedPreset = Preset.WINTER_NEVA;
                    break;
                case R.id.rb_4:
                    selectedPreset = Preset.MORNING_SALAD;
                    break;
                case R.id.rb_5:
                    selectedPreset = Preset.SOFT_GRASS;
                    break;
                default:
                    selectedPreset = Preset.WARM_FLAME;
                    break;
            }
            SettingsUtils.saveSelectedPreset(selectedPreset.ordinal());
            listener.onPresetChanged(selectedPreset.getResId());
        }
    };

    private SeekBar.OnSeekBarChangeListener inhaleSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            listener.onInhaleValueChanged(progress != 0 ? progress * Constants.MILLISECOND : Constants.MILLISECOND);
            SettingsUtils.saveSelectedInhaleDuration(progress != 0 ? progress * Constants.MILLISECOND :
                    Constants.MILLISECOND);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private SeekBar.OnSeekBarChangeListener exhaleSeekBarChangeListener = new SeekBar
            .OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            listener.onExhaleValueChanged(progress != 0 ? progress * Constants.MILLISECOND : Constants.MILLISECOND);
            SettingsUtils.saveSelectedExhaleDuration(progress != 0 ? progress * Constants.MILLISECOND :
                    Constants.MILLISECOND);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private SeekBar.OnSeekBarChangeListener holdSeekBarChangeListener = new SeekBar
            .OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            listener.onHoldValueChanged(progress * Constants.MILLISECOND);
            SettingsUtils.saveSelectedHoldDuration(progress * Constants.MILLISECOND);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private View.OnClickListener closeBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    public interface SettingsChangeListener {
        void onPresetChanged(int backgroundResId);
        void onInhaleValueChanged(int duration);
        void onExhaleValueChanged(int duration);
        void onHoldValueChanged(int duration);
    }

}
