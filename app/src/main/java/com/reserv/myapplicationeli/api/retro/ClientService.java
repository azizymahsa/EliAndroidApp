package com.reserv.myapplicationeli.api.retro;

import com.reserv.myapplicationeli.models.model.insurance.call.InsuranceRequestModel;
import com.reserv.myapplicationeli.models.model.insurance.response.InsuranceRes;
import com.reserv.myapplicationeli.models.model.login.call.ChangePasswordReq;
import com.reserv.myapplicationeli.models.model.login.call.ChangePasswordRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.EmailContractReq;
import com.reserv.myapplicationeli.models.model.login.call.EmailContractRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.LoginRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.RegisterRequestModel;
import com.reserv.myapplicationeli.models.model.login.call.ResetPassRequestModel;
import com.reserv.myapplicationeli.models.model.login.response.EmailContractRes;
import com.reserv.myapplicationeli.models.model.login.response.LoginRes;
import com.reserv.myapplicationeli.models.model.login.response.EmailContractResult;
import com.reserv.myapplicationeli.models.model.login.response.WebUserChangePasswordRes;
import com.reserv.myapplicationeli.models.model.login.response.WebUserRegisterRes;
import com.reserv.myapplicationeli.models.model.login.response.WebUserRememberPasswordRes;
import com.reserv.myapplicationeli.models.model.login.response.WebUserUpdateProfileRes;
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

    @POST("Common/StaticDataService.svc/GetCountryAjax")
    Call<CountryListRes> getCountryListResult(@Body CountryRequestModel countryRequestModel);

    @POST("Common/StaticDataService.svc/GetPackageRoutes")
    Call<CityListRes> getCityListResult(@Body CityRequestModel cityRequestModel);

    @POST("Package/PackageService.svc/SearchXPackage")
    Call<PackageListRes> getPackageListResult(@Body PackageRequestModel packageRequestModel);

    @POST("Package/PackageService.svc/PurchaseFlight")
    Call<PurchaseRes> getPurchaseResult(@Body PurchaseRequestModel purchaseRequestModel);


}
