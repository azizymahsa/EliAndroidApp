package com.eligasht.reservation.views.activities;

import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.schibsted.spain.parallaxlayerlayout.AnimatedTranslationUpdater;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;
import com.squareup.seismic.ShakeDetector;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.squareup.seismic.ShakeDetector.SENSITIVITY_HARD;

/**
 * Created by Ahmad.nemati on 5/9/2018.
 */
public class ShakeActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private ParallaxLayerLayout parallaxLayout;
    private SensorTranslationUpdater translationUpdater;
    private LottieAnimationView lottieAnimationView;
    MediaPlayer mp;
    MediaPlayer sucPlayer;
    KonfettiView konfettiView;
    ShakeDetector hs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ovni);
        konfettiView = findViewById(R.id.konfettiView);
        parallaxLayout = findViewById(R.id.parallax);
        lottieAnimationView = findViewById(R.id.gif);
        initShakeDetect();
        initSound();

    }

    @Override
    protected void onResume() {
        super.onResume();
        translationUpdater.registerSensorManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        translationUpdater.unregisterSensorManager();
    }

    @Override
    public void hearShake() {
        if (!mp.isPlaying())
            mp.start();
        if (!lottieAnimationView.isAnimating())
            lottieAnimationView.playAnimation();
        createVibrate();

    }

    private void createVibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(250);
        }
    }

    private void showKnofetti() {
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

    private void initShakeDetect() {
        translationUpdater = new SensorTranslationUpdater(this);
        SensorManager hardSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        hs = new ShakeDetector(this);

        hs.setSensitivity(SENSITIVITY_HARD);
        hs.start(hardSensor);
    }

    private void initSound() {
        mp = MediaPlayer.create(this, R.raw.shakesound);
        sucPlayer = MediaPlayer.create(this, R.raw.sfx);
        mp.setOnCompletionListener(mp -> {
            if (!sucPlayer.isPlaying())
                sucPlayer.start();
        });

        sucPlayer.setOnCompletionListener(mp -> showKnofetti());
        parallaxLayout.setOnClickListener(v -> translationUpdater.reset());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hs.stop();
    }
}
