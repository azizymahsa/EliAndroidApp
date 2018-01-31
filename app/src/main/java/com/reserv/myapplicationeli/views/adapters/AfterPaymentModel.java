package com.reserv.myapplicationeli.views.adapters;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class AfterPaymentModel {
    public final int ActionStep;
    public final String MsgTextFa;

    public AfterPaymentModel(int actionStep, String msgTextFa) {
        ActionStep = actionStep;
        MsgTextFa = msgTextFa;
    }

    public int getActionStep() {
        return ActionStep;
    }

    public String getMsgTextFa() {
        return MsgTextFa;
    }
}
