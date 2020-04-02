package com.example.remotewife;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class СreationOf_URL {
    private static final String THE_BORED_API = "https://www.boredapi.com/api/activity" ;
    
    public static URL generateURL(){
        Uri crateUri = Uri.parse( THE_BORED_API )
                .buildUpon()
                .build();
        URL url = null;
        try {
             url = new URL(crateUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        return url;
    }
    
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in ;
        try{
            in = urlConnection.getInputStream();
            
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
                
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
