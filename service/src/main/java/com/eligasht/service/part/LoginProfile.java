package com.eligasht.service.part;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.login.request.LoginRequestModel;
import com.eligasht.service.model.login.response.LoginResponse;
import com.eligasht.service.model.newModel.login.forgetPassword.request.RequestForgetPassword;
import com.eligasht.service.model.newModel.login.forgetPasswordActivation.request.RequestForgetPasswordActivation;
import com.eligasht.service.model.newModel.login.forgetPasswordChangePassword.request.RequestForgetPasswordChangePassword;
import com.eligasht.service.model.newModel.login.reSendActivation.request.RequestReSendActivation;
import com.eligasht.service.model.newModel.login.registerActivation.request.RequestRegisterActivation;
import com.eligasht.service.model.newModel.login.registerUser.request.RequestRegisterUser;
import com.eligasht.service.model.newModel.login.registerUser.response.ResponseWebUserLogin;

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

    //*************NEW******************************************************************
    public void GetRegisterUser(OnServiceStatus<ResponseWebUserLogin> listener, RequestRegisterUser requestRegisterUser) {
        start(getServiceGenerator().createService().responseWebUserLogin(requestRegisterUser), listener);
    }
    public void GetRegisterActivation(OnServiceStatus<ResponseWebUserLogin> listener, RequestRegisterActivation requestRegisterActivation) {
        start(getServiceGenerator().createService().responseWebUserActivation(requestRegisterActivation), listener);
    }
    public void GetReSendActivation(OnServiceStatus<ResponseWebUserLogin> listener, RequestReSendActivation requestReSendActivation) {
        start(getServiceGenerator().createService().responseWebUserResend(requestReSendActivation), listener);
    }

    public void GetForgetPassword(OnServiceStatus<ResponseWebUserLogin> listener, RequestForgetPassword requestForgetPassword) {
        start(getServiceGenerator().createService().responseForgetPassword(requestForgetPassword), listener);
    }
    public void GetForgetPasswordActivation(OnServiceStatus<ResponseWebUserLogin> listener, RequestForgetPasswordActivation requestForgetPasswordActivation) {
        start(getServiceGenerator().createService().responseForgetPasswordActivation(requestForgetPasswordActivation), listener);
    }
    public void GetForgetPasswordChangePassword(OnServiceStatus<ResponseWebUserLogin> listener, RequestForgetPasswordChangePassword requestForgetPasswordChangePassword) {
        start(getServiceGenerator().createService().responseForgetPasswordChangePassword(requestForgetPasswordChangePassword), listener);
    }


}
