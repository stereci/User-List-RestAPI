package com.example.mia.userlistrestapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRest {

    public static String Post(String postUrl, String jsonData, int timeOut)
    {
        HttpURLConnection httpcon;
        URL url;
        //String url = null;
        String result = null;
        try
        {
            //Connect

            url = new URL(postUrl);
            httpcon = (HttpURLConnection) (url.openConnection());
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestMethod("POST");
            httpcon.setConnectTimeout(timeOut * 1000);
            httpcon.setReadTimeout(timeOut * 1000);
            httpcon.connect();

            //Write
            OutputStream os = httpcon.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonData);
            writer.close();
            os.close();
            //Read
            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            br.close();
            result = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

}
