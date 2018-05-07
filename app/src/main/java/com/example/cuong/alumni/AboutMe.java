package com.example.cuong.alumni;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cuong.alumni.SignIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.cuong.alumni.DataModel.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.cuong.alumni.Network.ip;
import static com.example.cuong.alumni.SignIn.password;
import static com.example.cuong.alumni.SignIn.username;

public class AboutMe extends AppCompatActivity {

    private TextView tvName05;
    private TextView tvDob05;
    private TextView tvJob05;
    private TextView tvYear05;
    private Button btnModPass05;
    private Button btnModInfo05;
    private String check05="";
    private User user;
    public static int request;

    //private String ip05="http://192.168.30.180/api/final";
    //private String ip05="http://172.20.10.11/api/final";
    //http://192.168.0.110/api/final



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me2);

        tvName05=findViewById(R.id.tvName05);
        tvDob05=findViewById(R.id.tvDob05);
        tvJob05=findViewById(R.id.tvJob05);
        tvYear05=findViewById(R.id.tvYear05);
        btnModInfo05=findViewById(R.id.btnModInfo05);
        btnModPass05=findViewById(R.id.btnModPass05);

        String a=username;
        tvName05.setText(a);

        new Get().execute(ip+"?n="+tvName05.getText().toString()); //Lấy username gán vào trước



        btnModInfo05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent052=new Intent(AboutMe.this,ModInfo.class);
                startActivity(intent052);
            }
        });

        btnModPass05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent051=new Intent(AboutMe.this,ModPassword.class);
                startActivity(intent051);
            }
        });
    }



    private class Get extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder(); //gan du lieu doc
            try {
                URL url=new URL(strings[0]);
                //URLConnection urlConnection=url.openConnection(); //mo connection
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                // lay du lieu tu url connection

                httpURLConnection.setRequestMethod("GET");
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
           // Toast.makeText(AboutMe.this,s,Toast.LENGTH_SHORT).show();
            if (s.length()<10)
            {
                request=0;
                Toast.makeText(AboutMe.this,"Chưa có thông tin, xin mời cập nhật thông tin lên hệ thống",Toast.LENGTH_SHORT).show();
            }
            else {
                request=1;
                
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.d("Creat JSONObject","Done");
                    String u=jsonObject.getString("username");
                    String d=jsonObject.getString("dob");
                    String j=jsonObject.getString("job");
                    String sy=jsonObject.getString("startyear");
                    String ey=jsonObject.getString("endyear");
                    Log.d("Creat JSONObject","Done");
                    tvDob05.setText(d);
                    tvJob05.setText(j);
                    tvYear05.setText(sy+" - " +ey);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
