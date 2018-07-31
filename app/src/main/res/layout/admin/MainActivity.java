package com.example2017.android.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  Firebase.setAndroidContext(this);


    }





    public void start(View v){

        Intent intent = new Intent(this, com.example2017.android.admin.OfferCard.class);
        startActivity(intent);

    }
    public void eldalel(View v){

        Intent intent = new Intent(this, com.example2017.android.admin.EldalelSetting.class);
        startActivity(intent);

    }




    public void test(View v){

        Intent intent = new Intent(this, com.example2017.android.admin.TestRetrieve.class);
        startActivity(intent);

    }



}
