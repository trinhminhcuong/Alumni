package com.example.cuong.alumni;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.cuong.alumni.Adapter.UserAdapter;
import com.example.cuong.alumni.Network;

import com.example.cuong.alumni.DataModel.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.cuong.alumni.Network.ip;

public class Users extends AppCompatActivity {

    private EditText edFindUser09;
    private Button btnOk09;
    private ListView lv09;
    private List<User> users;
    private List<User> users2;
    private ArrayAdapter<User> userAdapter;
    private ArrayAdapter<User> userAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        edFindUser09=findViewById(R.id.edFindUser09);
        btnOk09=findViewById(R.id.btnOk09);
        lv09=findViewById(R.id.lv09);

        new ReadUsers().execute(ip);

        btnOk09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users2=new ArrayList<>();
                if(edFindUser09.length()==0){
                    Toast.makeText(Users.this,"Mời nhập username cần tìm",Toast.LENGTH_SHORT).show();
                }
                else {
                    for(User user:users){
                        if(user.getUserName().equals(edFindUser09.getText().toString()))
                            users2.add(user);
                    }
                    if(users2.size()!=0){
                        userAdapter2=new UserAdapter(Users.this,R.layout.adapter_user,users2);
                        Log.d("new adapter","done");
                        lv09.setAdapter(userAdapter2);
                    }
                    else
                        Toast.makeText(Users.this,"Không có username cần tìm",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class ReadUsers extends AsyncTask<String,Void,String> {
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
            users=new ArrayList<>();

            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String dob=activity.getString("dob");
                    String endyear=activity.getString("endyear");
                    String job=activity.getString("job");
                    String startyear=activity.getString("startyear");
                    String username=activity.getString("username");
                    users.add(new User(username,dob,job,startyear,endyear));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            userAdapter=new UserAdapter(Users.this,R.layout.adapter_user,users);
            lv09.setAdapter(userAdapter);


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }




}
