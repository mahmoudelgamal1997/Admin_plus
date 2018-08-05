package com.example2017.android.admin_plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        DatabaseReference offer= FirebaseDatabase.getInstance().getReference().child("offer");
        offer.child("name").setValue("na");

    }





    public void start(View v){

        Intent intent = new Intent(this,OfferCard.class);
        startActivity(intent);

    }
    public void eldalel(View v){

        Intent intent = new Intent(this,EldalelSetting.class);
        startActivity(intent);

    }




    public void test(View v){

        Intent intent = new Intent(this,TestRetrieve.class);
        startActivity(intent);

    }



}
