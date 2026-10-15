package com.rythm_zone.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseSignUpAuthController {
    public static final String API_KEY="AIzaSyC0OR9WXC34mfYp5JbNLI8K4exdiN1504k";

    public boolean signUpWithEmailAnsPassword(String email,String Password){
       
        try{
            URL url=new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="+API_KEY);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            
            String payload=String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",email,Password);

            OutputStream os=null;
            os=conn.getOutputStream();
            os.write(payload.getBytes());
            int responese=conn.getResponseCode();
            if(responese== 200){
                //login sucessfull!!
                return true;
            }else{
                //Login failed
                try(BufferedReader br=new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                    String line;
                    while((line=br.readLine())!=null){
                        System.out.println(line);
                    }
                }
                return false;
            }   
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }    
}

