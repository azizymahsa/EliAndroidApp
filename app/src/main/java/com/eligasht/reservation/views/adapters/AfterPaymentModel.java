package com.eligasht.reservation.views.adapters;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class AfterPaymentModel {
      int ActionStep;
      String MsgTextFa;

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
