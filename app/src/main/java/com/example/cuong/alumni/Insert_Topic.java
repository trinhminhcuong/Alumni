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

public class Insert_Topic extends AppCompatActivity {

    private EditText edContent13;
    private Button btnInsert13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__topic);

        edContent13=findViewById(R.id.edContent13);
        btnInsert13=findViewById(R.id.btnInsert13);

        btnInsert13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edContent13.length()==0){
                    Toast.makeText(Insert_Topic.this,"Xin mời nhập chủ đề",Toast.LENGTH_SHORT).show();
                }
                else {
                   // http://192.168.30.249/api/final?topicname=Logo&owner=trinhminhcuong&topicinfo=Các bạn thấy logo trường mới của mình sao???
                    String edit=edContent13.getText().toString().replace(" ","-").trim();
                    String url=ip+"?topicname=Logo&owner="
                            +username+"&topicinfo="+edit;
                    //Toast.makeText(Insert_Topic.this,url,Toast.LENGTH_LONG).show();
                    new InsertTop().execute(url);
                }
            }
        });
    }

    private class InsertTop extends AsyncTask<String, Void, String> {
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
            Toast.makeText(Insert_Topic.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            /*if(a.equals("t")){
                Toast.makeText(Insert_Topic.this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();

            }
            else Toast.makeText(Insert_Topic.this,"Thất bại, mời làm lại",Toast.LENGTH_SHORT).show();
        }*/
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}
