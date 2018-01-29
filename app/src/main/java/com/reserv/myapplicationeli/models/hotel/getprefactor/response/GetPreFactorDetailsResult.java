package com.reserv.myapplicationeli.models.hotel.getprefactor.response;



import com.reserv.myapplicationeli.models.model.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class GetPreFactorDetailsResult {
    public final List<Errors> errors;
    public final PreFactor PreFactor;

    public GetPreFactorDetailsResult(List<Errors> errors, com.reserv.myapplicationeli.models.hotel.getprefactor.response.PreFactor preFactor) {
        this.errors = errors;
        PreFactor = preFactor;
    }
}
