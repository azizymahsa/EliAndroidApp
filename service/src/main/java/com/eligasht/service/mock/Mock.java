package com.eligasht.service.mock;

import android.support.annotation.RawRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ahmad.nemati on 4/11/2018.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface  Mock {
    String jsonName();

    Class response();
}



