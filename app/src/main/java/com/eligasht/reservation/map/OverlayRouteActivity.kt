package com.eligasht.reservation.map

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SwitchCompat
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CompoundButton
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView

import com.airbnb.lottie.LottieAnimationView
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.amalbit.trail.RouteOverlayView
import com.amalbit.trail.TrailSupportMapFragment
import com.eligasht.R
import com.eligasht.reservation.base.BaseActivity
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity
import com.eligasht.reservation.views.ui.SingletonContext
import com.eligasht.reservation.views.ui.dialog.LocationAlertDialog
import com.eligasht.reservation.views.ui.dialog.ResultGiftDialog
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PatternItem
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.SphericalUtil
import com.jaeger.library.StatusBarUtil

import java.util.ArrayList
import java.util.Arrays

import mehdi.sakout.fancybuttons.FancyButton
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape

import com.eligasht.reservation.base.GlobalApplication.getActivity
import nl.dionsegijn.konfetti.models.Size

class OverlayRouteActivity : BaseActivity(), OnMapReadyCallback, LocationAlertDialog.OnDialogClick, GoogleMap.OnCameraIdleListener {
    override fun needTerminate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mMap: GoogleMap? = null
    private var mapStyle: MapStyleOptions? = null
    private lateinit var btnBack: FancyButton
    private lateinit var locationAlertDialog: LocationAlertDialog
    internal var arrivalDialog: LocationAlertDialog? = null
    private lateinit var animation_view: LottieAnimationView
    private lateinit var tvStart: FancyButton
    internal var test = 0
    private lateinit var fm: android.support.v4.app.FragmentManager
    internal var markerView: View? = null
    internal var isShowAlert = false
    private var konfettiView: KonfettiView? = null
    private var sucPlayer: MediaPlayer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projection_route)
        fm = supportFragmentManager
        locationAlertDialog = LocationAlertDialog(this, "lottie/verify_phone.json", "پیغام", getString(R.string.locationAlert), "مکان یاب", "بازگشت", true, this)
        arrivalDialog = LocationAlertDialog(this, "lottie/location.json", "پیغام", "مبدا شما مشخص شد! لطفا هنگامی که به مقصد خود رسیدید دکمه رسیدم را فشار دهید.", "متوجه شدم!", "مکان یاب", false, this)

        Handler().postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                locationAlertDialog.show(fm, "location")

            }
        }, 800)
        btnBack = findViewById(R.id.btnBack)
        tvStart = findViewById(R.id.tvStart)
        konfettiView = findViewById(R.id.konfettiView)

        animation_view = findViewById(R.id.animation_view)
        btnBack.setCustomTextFont("fonts/icomoon.ttf")
        tvStart.setCustomTextFont(SingletonContext.getInstance().context.resources.getString(R.string.iran_sans_bold_ttf))
        btnBack.setText(getString(R.string.search_back_right))
        btnBack.setOnClickListener { finish() }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        tvStart.setOnClickListener {
            if (test == 1) {
                animation_view.visibility = View.VISIBLE
                animation_view.playAnimation()
                animation_view.setMaxFrame((animation_view.maxFrame - 7).toInt())
            } else if (test == 2) {
               initSound()


            } else if (test == 0) {
                isShowAlert = true








                test++
                mMap!!.addMarker(MarkerOptions().position(POINT_A)
                        .draggable(false).visible(true).title("Marker 1"))
                /*    .icon(BitmapDescriptorFactory
                            .fromBitmap(createDrawableFromView(
                                    OverlayRouteActivity.this,
                                    markerView)))*/
                mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(POINT_A, 4f))
                tvStart.setText("رسیدم!")
            }

            animation_view.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    animation_view.visibility = View.GONE
                    if (test == 1) {
                        test++
                        mMap!!.addMarker(MarkerOptions().position(POINT_B)
                                .draggable(false).visible(true).title("Marker 2"))
                        tvStart.setText("جایز تو بگیر!")
                        // drawElementsOnMap(POINT_A, POINT_B);
                        draw(POINT_A, POINT_B)
                    }
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
    }

    override fun onResume() {
        super.onResume()
        //        if (locationAlertDialog != null&&locationAlertDialog.isAdded()) {
        //            locationAlertDialog.dismiss();
        //        }
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        mMap!!.uiSettings.isZoomControlsEnabled = false
        mapStyle = MapStyleOptions.loadRawResourceStyle(applicationContext, R.raw.mapstyle)
        mMap!!.setMapStyle(mapStyle)
        mMap!!.setOnCameraIdleListener(this)
        //     markerView = LayoutInflater.from(this).inflate(R.layout.map_marker, null);


        // Add a marker in Sydney and move the camera
        /*  mMap.addMarker(new MarkerOptions().position(POINT_A)
                .draggable(false).visible(true).title("Marker 1"));
        mMap.addMarker(new MarkerOptions().position(POINT_B)
                .draggable(false).visible(true).title("Marker 2"));
        mMap.addMarker(new MarkerOptions().position(POINT_C)
                .draggable(false).visible(true).title("Marker 3"));
        mMap.addMarker(new MarkerOptions().position(POINT_D)
                .draggable(false).visible(true).title("Marker 4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(POINT_A, 3));
        drawElementsOnMap(POINT_A, POINT_D);
        drawElementsOnMap(POINT_A, POINT_C);
        drawElementsOnMap(POINT_A, POINT_B);*/
        mMap!!.setOnMapClickListener { point -> Log.e("onMapClick", LatLng(point.latitude, point.longitude).toString() + "") }
        // showCurvedPolyline(POINT_A,POINT_B, 0.5);
        val bounds = LatLngBounds(POINT_A, POINT_B)
        //  Log.e("center1", bounds.getCenter()+"");
        Log.e("center", midPoint(POINT_A.latitude, POINT_A.longitude, POINT_B.latitude, POINT_B.longitude).toString() + "")


    }


    fun draw(latLng1: LatLng, latLng2: LatLng) {


        val marker1 = mMap!!.addMarker(MarkerOptions().position(latLng1).title("Start"))
        val marker2 = mMap!!.addMarker(MarkerOptions().position(latLng2).title("End"))

        //   List<PatternItem> pattern = Arrays.<PatternItem>asList(new Dash(300), new Gap(20));
        val popt = PolylineOptions().add(latLng1).add(latLng2)
                .width(5f).color(ContextCompat.getColor(this, R.color.app_base_color))
                .geodesic(true)
        mMap!!.addPolyline(popt)

        val builder = LatLngBounds.Builder()

        builder.include(marker1.position)
        builder.include(marker2.position)

        val bounds = builder.build()
        val padding = 150 // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mMap!!.moveCamera(cu)
        mMap!!.animateCamera(cu)


    }

    private fun gmapsCubicBezier(p1: LatLng, p2: LatLng, pA: LatLng, pB: LatLng) {
        //Polyline options
        val options = PolylineOptions()
        var curveLatLng: LatLng? = null
        var t = 0.0
        while (t < 1.01) {
            // P = (1−t)3P1 + 3(1−t)2tP2 +3(1−t)t2P3 + t3P4; for 4 points
            val arcX = ((1 - t) * (1 - t) * (1 - t) * p1.latitude
                    + 3.0 * (1 - t) * (1 - t) * t * pA.latitude
                    + 3.0 * (1 - t) * t * t * pB.latitude
                    + t * t * t * p2.latitude)
            val arcY = ((1 - t) * (1 - t) * (1 - t) * p1.longitude
                    + 3.0 * (1 - t) * (1 - t) * t * pA.longitude
                    + 3.0 * (1 - t) * t * t * pB.longitude
                    + t * t * t * p2.longitude)
            curveLatLng = LatLng(arcX, arcY)
            options.add(curveLatLng)


            //Draw polyline
            mMap!!.addPolyline(options.width(5f).color(ContextCompat.getColor(this, R.color.app_base_color)).geodesic(false))
            t += 0.01
        }
        //      mMap.addMarker(new MarkerOptions().position(curveLatLng));
    }

    private fun drawElementsOnMap(init: LatLng, end: LatLng) {

        /*    mMap.addMarker(new MarkerOptions().position(init));
        mMap.addMarker(new MarkerOptions().position(end));
*/
        mMap!!.addMarker(MarkerOptions().position(end))
        val bc = LatLngBounds.Builder()
        bc.include(init)
        bc.include(end)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 100))
        /*
        PolylineOptions line = new PolylineOptions();
        line.add(init);
        line.add(end);
        mMap.addPolyline(line);*/
        val distanceBetween = SphericalUtil.computeDistanceBetween(init, end)
        val lineHeadingInit = SphericalUtil.computeHeading(init, end)
        //   double lineHeadingEnd = bearing(end, init)
        val lineHeading1: Double
        val lineHeading2: Double
        if (lineHeadingInit < 0) {
            lineHeading1 = lineHeadingInit + 45
            lineHeading2 = lineHeadingInit + 135
        } else {
            lineHeading1 = lineHeadingInit + -45
            lineHeading2 = lineHeadingInit + -135
        }
        val pA = SphericalUtil.computeOffset(init, distanceBetween / 2.5, lineHeading1)
        val pB = SphericalUtil.computeOffset(end, distanceBetween / 2.5, lineHeading2)
        // Beizer curves with 4 points
        gmapsCubicBezier(init, end, pA, pB)
    }


    override fun btnOk(tag: String) {
        if (tag == "location") {
            val gpsOptionsIntent = Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(gpsOptionsIntent)
        }


    }

    override fun btnCancel(tag: String) {
        if (tag == "location") {
            finish()
        }
    }

    override fun onCameraIdle() {
        if (isShowAlert && arrivalDialog != null && !arrivalDialog!!.isAdded) {
            isShowAlert = false
            arrivalDialog!!.show(fm, "arrival")
        }

    }

    companion object {
        private val POINT_A = LatLng(35.737670, 51.412907)
        private val POINT_B = LatLng(41.015137, 28.97953)
        private val POINT_C = LatLng(41.716667, 44.7833)
        private val POINT_D = LatLng(30.315879761591844, 32.72402286529541)


        fun midPoint(lat1: Double, lon1: Double, lat2: Double, lon2: Double): LatLng {
            var lat1 = lat1
            var lon1 = lon1
            var lat2 = lat2
            val dLon = Math.toRadians(lon2 - lon1)
            //convert to radians
            lat1 = Math.toRadians(lat1)
            lat2 = Math.toRadians(lat2)
            lon1 = Math.toRadians(lon1)
            val Bx = Math.cos(lat2) * Math.cos(dLon)
            val By = Math.cos(lat2) * Math.sin(dLon)
            val lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By))
            val lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx)
            return LatLng(Math.toDegrees(lat3), Math.toDegrees(lon3))
        }


        fun createDrawableFromView(context: Activity, view: View): Bitmap {
            val displayMetrics = DisplayMetrics()
            context.windowManager.defaultDisplay
                    .getMetrics(displayMetrics)
            view.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT)
            view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
            view.layout(0, 0, displayMetrics.widthPixels,
                    displayMetrics.heightPixels)
            view.buildDrawingCache()
            val bitmap = Bitmap.createBitmap(view.measuredWidth,
                    view.measuredHeight, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(bitmap)
            view.draw(canvas)

            return bitmap
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
                val resultGiftDialog = ResultGiftDialog.newInstance(this@OverlayRouteActivity)
                resultGiftDialog.show(fm, "dialog")          }
            catch (e: Exception) {
            }



        }, 2500)
    }

    private fun initSound() {
        sucPlayer = MediaPlayer.create(this, R.raw.sfx)
        if (!sucPlayer!!.isPlaying)
            sucPlayer!!.start()

        showKnofetti()



    }
}
