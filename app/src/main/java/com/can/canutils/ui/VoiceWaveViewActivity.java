package com.can.canutils.ui;

import android.os.Bundle;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;
import com.can.mvp.views.VoiceWaveView;

/**
 * Created by CAN on 18-11-27.
 */

public class VoiceWaveViewActivity extends BaseActivity {

    @BindView(id = R.id.view_wave)
    private VoiceWaveView waveView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_voice_wave_view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        waveView.startPlayWave();
    }

    @Override
    protected void onStop() {
        super.onStop();
        waveView.stopPlayWave();
    }

    @Override
    protected void onPause() {
        super.onPause();
        waveView.pausePlayWave();
    }
}
