package com.example.cuong.alumni;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cuong.alumni.DataModel.Network;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.example.cuong.alumni.Network.ip;

public class SignIn extends AppCompatActivity {

    private Button btnSignIn02;
    private EditText edUserName02;
    private EditText edPassword02;
    private static String result = "";
    private TextView tv02;
    public static String username = "";
    public static String password = "";

    private final String name = "?name=";
    private final String pass = "&pass=";
    private final String ip02 ="http://192.168.0.110/api/final";
    //192.168.0.101
    //"http://192.168.30.180/api/final"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn02 = findViewById(R.id.btnSignIn02);
        edUserName02 = findViewById(R.id.edUserName02);
        edPassword02 = findViewById(R.id.edPassword02);
        tv02 = findViewById(R.id.tv02);

        btnSignIn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edUserName02.length() == 0 || edPassword02.length() == 0) {
                    Toast.makeText(SignIn.this, "Xin mời nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String url = ip + name + edUserName02.getText().toString().trim()+pass+edPassword02.getText().toString().trim();
                    new Check().execute(url);
                }
            }
        });

    }

    private class Check extends AsyncTask<String, Void, String> {
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
            result=s.charAt(0)+"";
            if(result.equals("t")==true)
            {
                username=edUserName02.getText().toString();
                password=edPassword02.getText().toString();
                Intent intent021=new Intent(SignIn.this,Menu.class);
                startActivity(intent021);
            }
            else {
                Toast.makeText(SignIn.this,"Tài khoản không đúng", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}

