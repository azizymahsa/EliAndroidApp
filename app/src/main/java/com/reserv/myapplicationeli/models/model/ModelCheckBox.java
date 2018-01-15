package com.reserv.myapplicationeli.models.model;

/**
 * Created by Mahsa.azizi on 1/9/2018.
 */

public class ModelCheckBox {
    String name;
    boolean isCheck; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    public ModelCheckBox(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
