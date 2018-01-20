package com.reserv.myapplicationeli.api.retro;

import com.reserv.myapplicationeli.models.model.insurance.call.InsuranceRequestModel;
import com.reserv.myapplicationeli.models.model.insurance.response.InsuranceRes;
import com.reserv.myapplicationeli.models.model.login.call.LoginRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.RegisterRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.ResetPassRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.TWebUserLogin;
import com.reserv.myapplicationeli.models.model.pack.call.CityRequestModel;
import com.reserv.myapplicationeli.models.model.pack.call.CountryRequestModel;
import com.reserv.myapplicationeli.models.model.pack.call.PackageRequestModel;
import com.reserv.myapplicationeli.models.model.pack.call.PurchaseRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.CityListRes;
import com.reserv.myapplicationeli.models.model.pack.response.CountryListRes;
import com.reserv.myapplicationeli.models.model.pack.response.PackageListRes;
import com.reserv.myapplicationeli.models.model.pack.response.PurchaseRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by elham.bonyani on 2/28/2017.
 */

public interface ClientService {


    @POST("Common/StaticDataService.svc/Login")
    Call<TWebUserLogin> Login(@Body LoginRequestModel loginRequestModel);


    @POST("Common/StaticDataService.svc/WebUserRegister")
    Call<TWebUserLogin> Register(@Body RegisterRequestModel registerRequestModel);

    @POST("Common/StaticDataService.svc/WebUserRememberPassword")
    Call<TWebUserLogin> ResetPassword(@Body ResetPassRequestModel registerRequestModel);

    @POST("Insurance/InsuranceService.svc/ShowInsurance")
    Call<InsuranceRes> showInsurance(@Body InsuranceRequestModel insuranceRequestModel);

    @POST("Common/StaticDataService.svc/GetCountryAjax")
    Call<CountryListRes> getCountryListResult(@Body CountryRequestModel countryRequestModel);

    @POST("Common/StaticDataService.svc/GetPackageRoutes")
    Call<CityListRes> getCityListResult(@Body CityRequestModel cityRequestModel);

    @POST("Package/PackageService.svc/SearchXPackage")
    Call<PackageListRes> getPackageListResult(@Body PackageRequestModel packageRequestModel);

    @POST("Package/PackageService.svc/PurchaseFlight")
    Call<PurchaseRes> getPurchaseResult(@Body PurchaseRequestModel purchaseRequestModel);


}
