package com.eligasht.service.part;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotelflight.request.HotelFlightRequest;
import com.eligasht.service.model.hotelflight.response.HotelFlightResponse;
import com.eligasht.service.model.login.request.LoginRequestModel;
import com.eligasht.service.model.login.response.LoginResponse;
/**
 * Created by Reza Nejati on 4/15/2018.
 */

public class LoginProfile extends BasePart {

    public LoginProfile(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }
    @Override
    protected BasePart getPart() {
        return this;
    }
    public void login(OnServiceStatus<LoginResponse> listener, LoginRequestModel req) {
        start(getServiceGenerator().createService().login(req), listener);
    }
}
