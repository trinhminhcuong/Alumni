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

import static com.example.cuong.alumni.Network.ip;
import static com.example.cuong.alumni.SignIn.password;
import static com.example.cuong.alumni.SignIn.username;

public class ModPassword extends AppCompatActivity {

    private EditText edOldPassword06;
    private EditText edNewPassword06;
    private EditText edNewPassword061;
    private Button btnOk06;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_password);

        edOldPassword06=findViewById(R.id.edOldPassword06);
        edNewPassword06=findViewById(R.id.edNewPassword06);
        edNewPassword061=findViewById(R.id.edNewPassword061);
        btnOk06=findViewById(R.id.btnOk06);
        final String pass=password;
        //Toast.makeText(ModPassword.this,pass,Toast.LENGTH_SHORT).show();
        btnOk06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edOldPassword06.length()==0||edNewPassword06.length()==0||edNewPassword061.length()==0){
                    Toast.makeText(ModPassword.this,"Xin mời nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else if(edOldPassword06.getText().toString().equals(pass)==false){
                    Toast.makeText(ModPassword.this,"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                }
                else if(edNewPassword06.getText().toString().equals(edNewPassword061.getText().toString())==false){
                    Toast.makeText(ModPassword.this,"Mật khẩu mới không khớp",Toast.LENGTH_SHORT).show();
                }
                else {
                    new Mod().execute(ip+"?username="+username+"&password="+edNewPassword06.getText().toString());
                }
            }
        });


    }


    private class Mod extends AsyncTask<String, Void, String> {
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
                Toast.makeText(ModPassword.this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();
                password=edNewPassword06.getText().toString();
            }
            else Toast.makeText(ModPassword.this,"Thất bại, mời làm lại",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}

