package com.eligasht.reservation.api.retro;

import com.eligasht.reservation.models.model.insurance.call.InsuranceRequestModel;
import com.eligasht.reservation.models.model.insurance.response.InsuranceRes;
import com.eligasht.reservation.models.model.login.call.ChangePasswordRequestModel;
import com.eligasht.reservation.models.model.login.call.EmailContractRequestModel;
import com.eligasht.reservation.models.model.login.call.LoginRequestModel;
import com.eligasht.reservation.models.model.login.call.RegisterRequestModel;
import com.eligasht.reservation.models.model.login.call.ResetPassRequestModel;
import com.eligasht.reservation.models.model.login.response.EmailContractRes;
import com.eligasht.reservation.models.model.login.response.LoginRes;
import com.eligasht.reservation.models.model.login.response.WebUserChangePasswordRes;
import com.eligasht.reservation.models.model.login.response.WebUserRegisterRes;
import com.eligasht.reservation.models.model.login.response.WebUserRememberPasswordRes;
import com.eligasht.reservation.models.model.login.response.WebUserUpdateProfileRes;
import com.eligasht.reservation.models.model.pack.call.CityRequestModel;
import com.eligasht.reservation.models.model.pack.call.CountryRequestModel;
import com.eligasht.reservation.models.model.pack.call.PackageRequestModel;
import com.eligasht.reservation.models.model.pack.call.PurchaseRequestModel;
import com.eligasht.reservation.models.model.pack.response.CityListRes;
import com.eligasht.reservation.models.model.pack.response.CountryListRes;
import com.eligasht.reservation.models.model.pack.response.PackageListRes;
import com.eligasht.reservation.models.model.pack.response.PurchaseRes;
import com.eligasht.service.model.newModel.xpackage.packageCity.response.ResponseGetPackageCity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by elham.bonyani on 2/28/2017.
 */

public interface  ClientService {


    @POST("Common/StaticDataService.svc/Login")
    Call<LoginRes> Login(@Body LoginRequestModel loginRequestModel);

    @POST("Common/StaticDataService.svc/WebUserUpdateProfile")
    Call<WebUserUpdateProfileRes> updateProfile(@Body RegisterRequestModel registerRequestModel);

    @POST("Common/StaticDataService.svc/EmailContract")
    Call<EmailContractRes> emailContractProfile(@Body EmailContractRequestModel emailContractRequestModel);

    @POST("Common/StaticDataService.svc/WebUserChangePassword")
    Call<WebUserChangePasswordRes> changePasswordProfile(@Body ChangePasswordRequestModel changePasswordRequestModel);

    @POST("Common/StaticDataService.svc/WebUserRegister")
    Call<WebUserRegisterRes> Register(@Body RegisterRequestModel registerRequestModel);

    @POST("Common/StaticDataService.svc/WebUserRememberPassword")
    Call<WebUserRememberPasswordRes> ResetPassword(@Body ResetPassRequestModel registerRequestModel);

    @POST("Insurance/InsuranceService.svc/ShowInsurance")
    Call<InsuranceRes> showInsurance(@Body InsuranceRequestModel insuranceRequestModel);
    //"/Common/StaticDataService.svc/GetCountryAjax"GetCountryAjaxWithCulture
    @POST("Common/StaticDataService.svc/GetCountryAjax")
    Call<CountryListRes> getCountryListResult(@Body CountryRequestModel countryRequestModel);

    //@POST("Common/StaticDataService.svc/GetPackageRoutes")
    @POST("LoadAndFillAPI/GetPackageCities")
    Call<List<ResponseGetPackageCity>> getCityListResult(@Body CityRequestModel cityRequestModel);

    @POST("Package/PackageService.svc/SearchXPackage")
    Call<PackageListRes> getPackageListResult(@Body PackageRequestModel packageRequestModel);

    @POST("Package/PackageService.svc/PurchaseFlightService")
    Call<PurchaseRes> getPurchaseResult(@Body PurchaseRequestModel purchaseRequestModel);


}
