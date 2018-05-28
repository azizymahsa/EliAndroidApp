package com.eligasht.reservation.views.activities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

import com.airbnb.lottie.LottieAnimationView
import com.eligasht.R
import com.eligasht.reservation.base.BaseActivity
import com.eligasht.reservation.views.ui.dialog.ResultGiftDialog
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog
import com.schibsted.spain.parallaxlayerlayout.AnimatedTranslationUpdater
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater
import com.squareup.seismic.ShakeDetector

import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

import com.squareup.seismic.ShakeDetector.SENSITIVITY_HARD
import com.squareup.seismic.ShakeDetector.SENSITIVITY_LIGHT
import mehdi.sakout.fancybuttons.FancyButton

@SuppressLint("MissingSuperCall")
class ShakeActivity : BaseActivity(), ShakeDetector.Listener {

    private var parallaxLayout: ParallaxLayerLayout? = null
    private var translationUpdater: SensorTranslationUpdater? = null
    private var lottieAnimationView: LottieAnimationView? = null
    private var mp: MediaPlayer? = null
    private var sucPlayer: MediaPlayer? = null
    private var konfettiView: KonfettiView? = null
    private var resultGiftDialog: ResultGiftDialog? = null
    private var hs: ShakeDetector? = null
    private var btnBack: FancyButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ovni)
        konfettiView = findViewById(R.id.konfettiView)
        parallaxLayout = findViewById(R.id.parallax)
        lottieAnimationView = findViewById(R.id.gif)

        btnBack = findViewById(R.id.btnBack) as FancyButton
        //btnBack = findViewById(R.id.btnBack)

         btnBack!!.setCustomTextFont("fonts/icomoon.ttf")
         btnBack!!.setText(this.getString(R.string.search_back_right))

        btnBack!!.setOnClickListener {
          //  Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            this.finish()
        }
        initShakeDetect()
        initSound()
    }

    override fun onResume() {
        super.onResume()
        translationUpdater!!.registerSensorManager()
    }

    override fun onPause() {
        super.onPause()
        translationUpdater!!.unregisterSensorManager()
    }
    override fun hearShake() {
        if (!mp!!.isPlaying)
            mp!!.start()
        if (!lottieAnimationView!!.isAnimating)
            lottieAnimationView!!.playAnimation()
        createVibrate()
    }

    private fun createVibrate() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(250)
        }
    }

    private fun showKnofetti() {
        konfettiView!!.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(12, 5f))
                .setPosition(-50f, konfettiView!!.width + 50f, -50f, -50f)
                .streamFor(200, 5000L)

        Handler().postDelayed({
            val fm = supportFragmentManager
            try {
                resultGiftDialog = ResultGiftDialog.newInstance(this)
                resultGiftDialog!!.show(fm,"dialog")            }
            catch (e: Exception) {
            }

        }, 2500)
    }

    private fun initShakeDetect() {
        translationUpdater = SensorTranslationUpdater(this)
        val hardSensor = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        hs = ShakeDetector(this)

        hs!!.setSensitivity(SENSITIVITY_HARD)
        hs!!.start(hardSensor)
        parallaxLayout!!.setTranslationUpdater(translationUpdater)
    }

    private fun initSound() {
        mp = MediaPlayer.create(this, R.raw.shakesound)
        sucPlayer = MediaPlayer.create(this, R.raw.sfx)
        mp!!.setOnCompletionListener { mp ->
            if (!sucPlayer!!.isPlaying)
                sucPlayer!!.start()

            showKnofetti()
        }


        parallaxLayout!!.setOnClickListener { translationUpdater!!.reset() }
    }

    override fun onDestroy() {
        super.onDestroy()
        hs!!.stop()
    }
}