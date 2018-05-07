package com.example.cuong.alumni;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button btnUsers04;
    private Button btnFeeds04;
    private Button btnAboutme04;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnUsers04=findViewById(R.id.btnUsers04);
        btnFeeds04=findViewById(R.id.btnFeeds04);
        btnAboutme04=findViewById(R.id.btnAboutMe04);


        btnAboutme04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent04=new Intent(Menu.this,AboutMe.class);
                startActivity(intent04);
            }
        });

        btnUsers04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent041=new Intent(Menu.this,Users.class);
                startActivity(intent041);
            }
        });

        btnFeeds04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog04=new Dialog(Menu.this);
                dialog04.setContentView(R.layout.feeds);
                Button btnTopic10=dialog04.findViewById(R.id.btnTopic10);
                Button btnJob10=dialog04.findViewById(R.id.btnJob10);

                btnTopic10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent042=new Intent(Menu.this,Topics.class);
                        startActivity(intent042);
                    }
                });

                btnJob10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent043=new Intent(Menu.this,Jobs.class);
                        startActivity(intent043);
                    }
                });

                dialog04.show();


            }
        });



    }
}
