package com.example.cuong.alumni;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuong.alumni.Adapter.CommentAdapter;
import com.example.cuong.alumni.Adapter.TopicAdapter;
import com.example.cuong.alumni.DataModel.Comment;
import com.example.cuong.alumni.DataModel.Topic;

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
import static com.example.cuong.alumni.SignIn.username;
import static com.example.cuong.alumni.Topics.idtopic;
import static com.example.cuong.alumni.Topics.topics;

public class TopicComment extends AppCompatActivity {

    private EditText edComment14;
    private TextView tvOwner14;
    private TextView tvTopicName14;
    private Button btnComment14;
    private ListView lv14;
    private ArrayAdapter<Comment> commentAdapter;
    private List<Comment> comments;
    private Button btnReadComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_comment);

        edComment14=findViewById(R.id.edComment14);
        tvOwner14=findViewById(R.id.tvOwner14);
        tvTopicName14=findViewById(R.id.tvTopicName14);
        btnComment14=findViewById(R.id.btnComment14);
        lv14=findViewById(R.id.lv14);

        btnReadComment=findViewById(R.id.btnReadComment);

        for(Topic topic:topics){
            if(topic.getIdtopic().equals(idtopic)==true){
                tvOwner14.setText("Người đăng: "+topic.getUser());
                tvTopicName14.setText("Chủ đề: "+ topic.getContent().replace("-"," "));
            }
        }

        btnComment14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edComment14.length()==0){
                    Toast.makeText(TopicComment.this,"Mời nhập comment",Toast.LENGTH_SHORT).show();
                }
                else {
                    //http://172.20.10.11/api/final?com==)))&topicid=3&usercomment=nguyenthienkim
                    String com=edComment14.getText().toString().replace(" ","-");
                    String url=ip+"?com="+com+"&topicid="+idtopic+"&usercomment="+username;
                    new InsertComment().execute(url);
                }
            }
        });

        btnReadComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comments=new ArrayList<>();
                new ReadComments().execute("http://192.168.30.115/api/final?c=c");
            }
        });
    }

    private class ReadComments extends AsyncTask<String,Void,String> {
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
           // Toast.makeText(TopicComment.this,s,Toast.LENGTH_SHORT).show();

            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String idcomment=activity.getString("idcoment");
                    String com=activity.getString("com");
                    String topicid=activity.getString("topicid");
                    String usercomment=activity.getString("usercomment");
                    if(topicid.equals(idtopic)==true){
                    comments.add(new Comment(idcomment,com,topicid,usercomment));}
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            commentAdapter=new CommentAdapter(TopicComment.this,R.layout.adapter_comment,comments);
            lv14.setAdapter(commentAdapter);



        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }

    private class InsertComment extends AsyncTask<String, Void, String> {
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
            String a = s.charAt(0) + "";
            //Toast.makeText(TopicComment.this, s, Toast.LENGTH_SHORT).show();
            if(a.equals("t")){
                Toast.makeText(TopicComment.this,"Comment thành công",Toast.LENGTH_SHORT).show();

            }
            else Toast.makeText(TopicComment.this,"Thất bại, mời làm lại",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
