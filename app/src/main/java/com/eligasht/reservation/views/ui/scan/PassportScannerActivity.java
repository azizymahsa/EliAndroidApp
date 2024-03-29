package com.eligasht.reservation.views.ui.scan;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eligasht.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
/*
import at.nineyards.anyline.AnylineDebugListener;
import at.nineyards.anyline.camera.CameraController;
import at.nineyards.anyline.camera.CameraOpenListener;
import at.nineyards.anyline.core.RunFailure;
import at.nineyards.anyline.core.exception_error_codes;
import at.nineyards.anyline.modules.AnylineBaseModuleView;
import at.nineyards.anyline.modules.mrz.Identification;
import at.nineyards.anyline.modules.mrz.MrzResult;
import at.nineyards.anyline.modules.mrz.MrzResultListener;
import at.nineyards.anyline.modules.mrz.MrzScanView;*/


/**
 * Example Activity for the Anyline-MRZ-Module.
 */
public class PassportScannerActivity {/*extends ScanActivity implements CameraOpenListener, AnylineDebugListener {

    private static final String TAG = PassportScannerActivity.class.getSimpleName();
    private MrzScanView mrzScanView;
    private static Toast notificationToast;


 //   private ScanViewResultActivity scanViewResultActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main2, (ViewGroup) findViewById(R.id.scan_view_placeholder));

        mrzScanView = (MrzScanView) findViewById(R.id.mrz_view);


        // add a camera open listener that will be called when the camera is opened or an error occurred
        //  this is optional (if not set a RuntimeException will be thrown if an error occurs)
        mrzScanView.setCameraOpenListener(this);
        // the view can be configured via a json file in the assets, and this config is set here
        // (alternatively it can be configured via xml, see the Energy Example for that)
        mrzScanView.setConfigFromAsset("mrz_view_config.json");

        // initialize Anyline with the license key and a Listener that is called if a result is found
        mrzScanView.initAnyline(getString(R.string.anyline_license_key), new MrzResultListener() {

            @Override
            public void onResult(MrzResult mrzResult) {
                // This is called when a result is found.
                // The Identification includes all the data read from the MRZ
                // as scanned and the given image shows the scanned ID/Passport

                Identification identification = mrzResult.getResult();
                identification.toJSONObject();

                String path = setupImagePath(mrzResult.getCutoutImage());


                startScanResultIntent("", getIdentificationResult(identification), path);
              //  setupScanProcessView(ScanMrzActivity.this, mrzResult, getScanModule());
            }
        });

        mrzScanView.setDebugListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

    }
    @Override
    protected AnylineBaseModuleView getScanView() {
        return mrzScanView;
    }

    @Override
    public Rect getCutoutRect() {
        return mrzScanView.getCutoutRect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start the actual scanning
        mrzScanView.startScanning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stop the scanning
        mrzScanView.cancelScanning();
        //release the camera (must be called in onPause, because there are situations where
        // it cannot be auto-detected that the camera should be released)
        mrzScanView.releaseCameraInBackground();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCameraOpened(CameraController cameraController, int width, int height) {
        //the camera is opened async and this is called when the opening is finished
        Log.d(TAG, "Camera opened successfully. Frame resolution " + width + " x " + height);
    }

    @Override
    public void onCameraError(Exception e) {
        //This is called if the camera could not be opened.
        // (e.g. If there is no camera or the permission is denied)
        // This is useful to present an alternative way to enter the required data if no camera exists.
        throw new RuntimeException(e);
    }

    @Override
    protected ScanModuleEnum.ScanModule getScanModule() {
        return ScanModuleEnum.ScanModule.MRZ;
    }

    public LinkedHashMap<String, String> getIdentificationResult(Identification identification) {

        LinkedHashMap<String, String> identificationResult = new LinkedHashMap<>();

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
       // DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(getApplicationContext());
        identificationResult.put(getResources().getString(R.string.mrz_document_type) , (identification.getDocumentType() == null || identification.getDocumentType().isEmpty()) ?  getResources().getString(R.string.not_available) : identification.getDocumentType());
        identificationResult.put(getResources().getString(R.string.mrz_country_code), (identification.getNationalityCountryCode() == null || identification.getNationalityCountryCode().isEmpty()) ? getResources().getString(R.string.not_available) : identification.getNationalityCountryCode());
        identificationResult.put(getResources().getString(R.string.mrz_document_number), (identification.getDocumentNumber() == null || identification.getDocumentNumber().isEmpty()) ? getResources().getString(R.string.not_available) : identification.getDocumentNumber());
        identificationResult.put(getResources().getString(R.string.mrz_sur_names),(identification.getSurNames() == null || identification.getSurNames().isEmpty()) ? getResources().getString(R.string.not_available) : identification.getSurNames());
        identificationResult.put(getResources().getString(R.string.mrz_given_names),(identification.getGivenNames() == null || identification.getGivenNames().isEmpty()) ? getResources().getString(R.string.not_available) : identification.getGivenNames());

        ////////////////////

        if(identification.getExpirationDateObject() == null){
            if(identification.getExpirationDate() != null && (!identification.getExpirationDate().isEmpty())){
               identificationResult.put(getResources().getString(R.string.mrz_expiration_date), getResources().getString(R.string.not_valid));
              //  identificationResult.put(getResources().getString(R.string.mrz_expiration_date),  "1/3/21");

            }else{
                identificationResult.put(getResources().getString(R.string.mrz_expiration_date), getResources().getString(R.string.not_available));
            }
        }else{
            identificationResult.put(getResources().getString(R.string.mrz_expiration_date), dateFormat.format(identification.getExpirationDateObject()));
        }
        /////////////////////////
        if(identification.getDayOfBirthObject() == null){
            if(identification.getDayOfBirth() != null && (!identification.getDayOfBirth().isEmpty())){
                identificationResult.put(getResources().getString(R.string.mrz_date_of_birthday), getResources().getString(R.string.not_valid));
               // identificationResult.put(getResources().getString(R.string.mrz_date_of_birthday), "11/8/10");
            }else{
                identificationResult.put(getResources().getString(R.string.mrz_date_of_birthday), getResources().getString(R.string.not_available));
            }
        }else{
            identificationResult.put(getResources().getString(R.string.mrz_date_of_birthday), dateFormat.format(identification.getDayOfBirthObject()));
        }
        identificationResult.put(getResources().getString(R.string.mrz_sex), (identification.getSex() == null || identification.getSex().isEmpty()) ?  getResources().getString(R.string.not_available) : identification.getSex());
        return identificationResult;
    }


    @Override
    public void onDebug(String s, Object o) {
    }

    @Override
    public void onRunSkipped(RunFailure runFailure) {

        if(runFailure!=null && runFailure.errorCode() == exception_error_codes.PointsOutOfCutout.swigValue()){
            showToast(runFailure.getMessage());
            System.out.println("aici sunt");
        }
    }

    private void showToast(String st) {
        try {
            notificationToast.getView().isShown();
            notificationToast.setText(st);
        } catch (Exception e) {
            notificationToast = Toast.makeText(this, st, Toast.LENGTH_SHORT);
        }
        notificationToast.show();
    }
    protected void startScanResultIntent(String scanMode, HashMap<String, String> scanResult, String path){
        JSONObject obj=new JSONObject(scanResult);
        // String path = setupImagePath(anylineOcrResult.getCutoutImage());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",obj.toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
       *//* Intent i = new Intent(getBaseContext(), ScanViewResultActivity.class);
        i.putExtra(Constant.SCAN_MODULE, scanMode);
        i.putExtra(Constant.SCAN_RESULT_DATA, scanResult);
        i.putExtra(Constant.SCAN_FULL_PICTURE_PATH, path);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.fade_out);
        startActivity(i);*//*
    }
*/
}
