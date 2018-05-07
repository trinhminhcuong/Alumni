package com.example.cuong.alumni;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.cuong.alumni.AboutMe.request;
import static com.example.cuong.alumni.Network.ip;
import static com.example.cuong.alumni.SignIn.password;
import static com.example.cuong.alumni.SignIn.username;

public class ModInfo extends AppCompatActivity {

    private EditText edDob07;
    private EditText edJob07;
    private EditText edSYear07;
    private EditText edEYear07;
    private Button btnOk07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_info);
        edDob07=findViewById(R.id.edDob07);
        edJob07=findViewById(R.id.edJob07);
        edSYear07=findViewById(R.id.edSYear07);
        edEYear07=findViewById(R.id.edEY07);
        btnOk07=findViewById(R.id.btnOk07);

        btnOk07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edDob07.length()==0||edJob07.length()==0||edSYear07.length()==0||edEYear07.length()==0){
                    Toast.makeText(ModInfo.this,"Xin mời nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    String u=ip+"?name="+username
                            +"&dob="+edDob07.getText().toString()+
                            "&job="+edJob07.getText().toString()+
                            "&syear="+edSYear07.getText().toString()+
                            "&eyear="+edEYear07.getText().toString();
                    String u2=ip+"?name="+username
                            +"&dob="+edDob07.getText().toString()+
                            "&job="+edJob07.getText().toString()+
                            "&startyear="+edSYear07.getText().toString()+
                            "&endyear="+edEYear07.getText().toString();
                    if(request==0){
                        new InsertInfos().execute(u2);
                    }
                    else
                    {new ModInfos().execute(u);}
                }
            }
        });

    }

    private class ModInfos extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String a=s.charAt(0)+"";
            if(a.equals("t")){
                Toast.makeText(ModInfo.this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(ModInfo.this,"Thất bại, mời làm lại",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

    private class InsertInfos extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String a=s.charAt(0)+"";
            if(a.equals("t")){
                Toast.makeText(ModInfo.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(ModInfo.this,"Thất bại, mời làm lại",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
