package com.reserv.myapplicationeli.tools;


import java.util.ArrayList;
import java.util.List;


public class StreamList {

    public interface Function<Arg, Result> {
        Result apply(Arg arg);
    }

    public static  <Source, Result> List<Result> convertAll(List<Source> source, Function<Source, Result> projection) {
        ArrayList<Result> results = new ArrayList<Result>();
        for (Source element : source) {
            if(!results.contains(projection.apply(element))){
                results.add(projection.apply(element));
            }
        }
        return results;
    }

}
