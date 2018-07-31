package com.example2017.android.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EldalelSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eldalel_setting);
    }

    public void City(View v){

        Intent intent = new Intent(this, com.example2017.android.admin.City_Post.class);
        startActivity(intent);

    }
    public void cat(View v){

        Intent intent = new Intent(this,Catorgy_Post.class);
        startActivity(intent);

    }


    public void shop(View v){
        Intent intent = new Intent(this,Shop.class);
        startActivity(intent);

    }



}
