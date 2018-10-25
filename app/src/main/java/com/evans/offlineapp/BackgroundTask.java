package com.evans.offlineapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,String,String> {
    Context context;
    public String Token;
    public ProgressDialog progressDialog;

    public BackgroundTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String method = strings[0];
        String email = strings[1];
        String password = strings[2];
        String login="https://evansmwendaem.000webhostapp.com/login.php";

        if(method.equals("login")){
            //method to recover user password using email
            //Toast.makeText(context, "gekki", Toast.LENGTH_SHORT).show();
            try {
                URL url = new URL(login);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+ URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();//flush the bufferedwriter
                bufferedWriter.close();
                os.close();//close the output stream
                InputStream is = httpURLConnection.getInputStream();
                //String result="mail sent";
                //*** trial 1
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                if(result==null){
                    result="null";
                }
                bufferedReader.close();
                is.close();
                return result;
            } catch (UnsupportedEncodingException e) {
           } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPreExecute() {
        //before task executes
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Signing in....");
        progressDialog.setTitle("TrackIt");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    protected void onPostExecute(String result) {
        //upon completion
        String lsuccess="login_success";
        String lfailed="login_failed";
        boolean islmatch=result.contentEquals(lsuccess);
        boolean islfailed=result.contentEquals(lfailed);
        if(islmatch){
            //result->login_success
            Intent intent = new Intent(context,Homepage.class);
            context.startActivity(intent);
        }else if(islfailed){
            //incorrect password
             String errorMsg="Incorrect username/password combination";
             Toast.makeText(context,errorMsg, Toast.LENGTH_SHORT).show();

        }else{
            //another problem occurred ...check the internet
            String errorMsg="Error, something went wrong..check your internet and try again";
            Toast.makeText(context,errorMsg, Toast.LENGTH_SHORT).show();

        }
        progressDialog.dismiss();
    }
}