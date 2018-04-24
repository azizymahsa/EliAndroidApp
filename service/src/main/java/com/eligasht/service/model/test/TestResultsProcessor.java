package com.eligasht.service.model.test;

import android.util.Log;

import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import junit.framework.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Ahmad.nemati on 4/23/2018.
 */
public class TestResultsProcessor {
    private static final String FilePath = "sdcard/data.json";
    private static final String readMe = "sdcard/README.md";
    private File data;
    private File markDown;


    public TestResultsProcessor() {
        data = new File(FilePath);
        markDown = new File(readMe);
    }

    private MarkDownGenerator readJson() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (reader != null)
            return new Gson().fromJson(reader, MarkDownGenerator.class);
        else
            return null;

    }



    private void saveFile(String data, File file) {
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            try {
                try {
                    stream.write(data.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void checkResults(int durationTime) {
        MarkDownGenerator markDownGenerator = readJson();
        markDownGenerator.setDur(durationTime);
        List<Response<?>> responseList = SingletonResponse.getInstance().getResponseList();
        markDownGenerator.setTotalRun(markDownGenerator.getTotalRun() + 1);


        for (Response<?> response : responseList) {
            String url = response.raw().request().url().toString().replace("http://mobilews.eligasht.com/LightServices/Rest/", "");
            Log.e("Url", url + "\n");
            for (int i = 0; i < markDownGenerator.getHeaderTestServices().size(); i++) {
                for (int j = 0; j < markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().size(); j++) {
                    if (markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j).getName().equals(url)) {
                        markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j)
                                .setTotalCall(markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j).getTotalCall() + 1);

                        markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j)
                                .setStatusCode(response.code());


                        markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j)
                                .setMessage(response.message());

                        long tx = response.raw().networkResponse().sentRequestAtMillis();
                        long rx = response.raw().networkResponse().receivedResponseAtMillis();
                        int sec = (int) ((rx - tx) / 1000);
                        markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j)
                                .setTryTime(String.valueOf(sec == 0 ? "1" : sec));

                        int size = (int) (response.raw().body().contentLength() / 1000);
                        markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j)
                                .setSize(size == 0 ? "1" : String.valueOf(size));

                        if (response.isSuccessful()) {
                            markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j).setClose(true);
                        } else {
                            markDownGenerator.getHeaderTestServices().get(i).getServiceTestModel().get(j).setClose(false);
                        }
                    }


                }

            }
        }

        saveFile(new Gson().toJson(markDownGenerator), data);
        saveFile(markDownGenerator.generate(), markDown);
    }

    public void analyse(Response<?> response, ServiceTestModel serviceTestModel) {


    }


}
