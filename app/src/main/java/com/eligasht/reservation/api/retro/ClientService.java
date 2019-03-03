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
import com.eligasht.reservation.models.model.pack.call.PurchaseRequestModel;
import com.eligasht.reservation.models.model.pack.response.CountryListRes;
import com.eligasht.reservation.models.model.pack.response.ResponseSearchPackage;
import com.eligasht.reservation.models.model.pack.response.PurchaseRes;
import com.eligasht.service.model.newModel.auth.response.ResponseAuth;
import com.eligasht.service.model.newModel.login.loginUser.request.RequestLoginUser;
import com.eligasht.service.model.newModel.login.loginUser.response.ResponseLoginUser;
import com.eligasht.service.model.newModel.xpackage.packageCity.response.ResponseGetPackageCity;
import com.eligasht.service.model.newModel.xpackage.searchPack.request.RequestSearchPackage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by elham.bonyani on 2/28/2017.
 */

public interface  ClientService {


  /*  @POST("Common/StaticDataService.svc/Login")
    Call<LoginRes> Login(@Body LoginRequestModel loginRequestModel);*/
    @POST("api/UserAutentication/LoginUser")
    Call<ResponseLoginUser> Login(@Body RequestLoginUser loginRequestModel);

    @POST("api/Common/StaticDataService.svc/WebUserUpdateProfile")
    Call<WebUserUpdateProfileRes> updateProfile(@Body RegisterRequestModel registerRequestModel);

    @POST("api/Common/StaticDataService.svc/EmailContract")
    Call<EmailContractRes> emailContractProfile(@Body EmailContractRequestModel emailContractRequestModel);

    @POST("api/Common/StaticDataService.svc/WebUserChangePassword")
    Call<WebUserChangePasswordRes> changePasswordProfile(@Body ChangePasswordRequestModel changePasswordRequestModel);

    @POST("api/Common/StaticDataService.svc/WebUserRegister")
    Call<WebUserRegisterRes> Register(@Body RegisterRequestModel registerRequestModel);

    @POST("api/Common/StaticDataService.svc/WebUserRememberPassword")
    Call<WebUserRememberPasswordRes> ResetPassword(@Body ResetPassRequestModel registerRequestModel);

    @POST("api/Insurance/InsuranceService.svc/ShowInsurance")
    Call<InsuranceRes> showInsurance(@Body InsuranceRequestModel insuranceRequestModel);
    //"/Common/StaticDataService.svc/GetCountryAjax"GetCountryAjaxWithCulture
    @POST("api/Common/StaticDataService.svc/GetCountryAjax")
    Call<CountryListRes> getCountryListResult(@Body CountryRequestModel countryRequestModel);

    //@POST("Common/StaticDataService.svc/GetPackageRoutes")
    @POST("api/LoadAndFillAPI/GetPackageCities")
    Call<List<ResponseGetPackageCity>> getCityListResult(@Body CityRequestModel cityRequestModel);

    @POST("api/PackageServiceAPI/Search")
    Call<ResponseSearchPackage> getPackageListResult(@Body RequestSearchPackage packageRequestModel);

    @POST("api/Package/PackageService.svc/PurchaseFlightService")
    Call<PurchaseRes> getPurchaseResult(@Body PurchaseRequestModel purchaseRequestModel);

    @FormUrlEncoded
    @POST("token")
    Call<ResponseAuth> getAuthResult( @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password);


}
