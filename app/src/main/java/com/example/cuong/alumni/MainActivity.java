package com.example.cuong.alumni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private Button btnSignIn01;
    private Button btnSignUp01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn01=findViewById(R.id.btnSignIn01);
        btnSignUp01=findViewById(R.id.btnSignUp01);

        btnSignUp01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent011=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent011);
            }
        });

        btnSignIn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent012=new Intent(MainActivity.this,SignIn.class);
                startActivity(intent012);
            }
        });


    }
}
