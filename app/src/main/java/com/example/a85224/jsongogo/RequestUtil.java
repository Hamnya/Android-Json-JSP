package com.example.a85224.jsongogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85224 on 2017-05-10.
 */

public class RequestUtil {


    private SharedPreferences mPreferences;

    public RequestUtil(Context mContext) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    /**
     *
     */
    public String UserInfo(String name) throws Exception {

        URL url = new URL("http:///*********************************************");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content_Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();

        String param = "name=" + name;
        OutputStream opstrm = connection.getOutputStream();
        opstrm.write(param.getBytes());
        opstrm.flush();
        opstrm.close();

        int responseCode = connection.getResponseCode();
        Log.d("RequestUtil", "결과값 : "+responseCode);

        String buffer = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
   //     List<String> list = new ArrayList<String>();
        String result = "";

        while ((buffer = in.readLine()) != null) {
            result += buffer;
        }

        in.close();
        Log.d("***pointInfo***", result);
        return result;
    }
}
