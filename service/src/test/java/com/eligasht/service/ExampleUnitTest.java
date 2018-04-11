package com.eligasht.service;

import android.util.Log;

import com.example.type.TypeResolver;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Tester<String> comparator =new Tester<String>() {

        } ;
        Class<?> typeArg = TypeResolver.resolveRawArgument(Tester.class, comparator.getClass());

        System.out.printf(typeArg.getName());

    }

    public interface Tester<T> {}


}