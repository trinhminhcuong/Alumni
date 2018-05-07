package com.example.cuong.alumni;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cuong.alumni.Adapter.JobAdapter;
import com.example.cuong.alumni.Adapter.UserAdapter;
import com.example.cuong.alumni.DataModel.Job;
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

public class Jobs extends AppCompatActivity {

    ListView lv11;
    ArrayAdapter<Job> jobAdapter;
    List<Job> jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        lv11=findViewById(R.id.lv11);
        new ReadJobs().execute(ip+"?j=a");

    }


    private class ReadJobs extends AsyncTask<String,Void,String> {
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
            jobs=new ArrayList<>();
            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String idjob=activity.getString("id");
                    String jobname=activity.getString("jobname");
                    String company=activity.getString("company");
                    String info=activity.getString("info");
                    jobs.add(new Job(idjob,jobname,company,info));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jobAdapter=new JobAdapter(Jobs.this,R.layout.job_adapter,jobs);
            lv11.setAdapter(jobAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }
}
