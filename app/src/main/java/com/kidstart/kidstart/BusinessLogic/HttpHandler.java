package com.kidstart.kidstart.BusinessLogic;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * This class handle the http request
 * @author HuanZhang
 */
public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){

    }

    /**
     * Connection with the sever side
     * @param reqUrl the url which you want to connect to
     * @return a response whether it is connected
     */
    public String makeServiceCall(String reqUrl){
        String response = null;
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the respone
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException" + e.getMessage());
        } catch (ProtocolException e){
            Log.e(TAG, "ProtocolException" + e.getMessage());
        } catch (IOException e){
            Log.e(TAG, "IOException" + e.getMessage());
        } catch(Exception e){
            Log.e(TAG, "Exception" + e.getMessage());
        }
        return response;
    }

    /**
     * Convertion of bit to readable string format.
     * @param is the bit that transfering
     * @return the string convertion
     */
    private String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while((line = reader.readLine()) != null){
                sb.append(line).append(("\n"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}