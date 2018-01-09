package com.reserv.myapplicationeli.api.retro;

import com.reserv.myapplicationeli.models.model.pack.call.CityRequestModel;
import com.reserv.myapplicationeli.models.model.pack.call.PackageRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.CityListRes;
import com.reserv.myapplicationeli.models.model.pack.response.PackageListRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by elham.bonyani on 2/28/2017.
 */

public interface ClientService {

    @POST("Common/StaticDataService.svc/GetPackageRoutes")
    Call<CityListRes> getCityListResult(@Body CityRequestModel cityRequestModel);

    @POST("Package/PackageService.svc/SearchXPackage")
    Call<PackageListRes> getPackageListResult(@Body PackageRequestModel packageRequestModel);
}
