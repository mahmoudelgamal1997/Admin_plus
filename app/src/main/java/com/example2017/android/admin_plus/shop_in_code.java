package com.example2017.android.admin_plus;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class shop_in_code extends AppCompatActivity {
    DatabaseReference shop,codevalue,ShopVisitors;
    ProgressDialog progressDialog;
    EditText marketname,value;
    int i;
    MediaPlayer alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_code);
        Firebase.setAndroidContext(this);

        marketname=(EditText)findViewById(R.id.editText);
        value=(EditText)findViewById(R.id.editText2);

        alert = MediaPlayer.create(this, R.raw.alert);

        shop= FirebaseDatabase.getInstance().getReference().child("codes");
        codevalue=FirebaseDatabase.getInstance().getReference().child("CodeValue");


        Adding_many_codes a=new Adding_many_codes();
        a.add(this,shop);



        progressDialog=new ProgressDialog(this);
    }



    public void add_all(String code,String ShopName,String value)
    {
        //this function add one value to all codes in the same time
        shop.child(code).child(ShopName).setValue(value);

        // we also add value to codevalue in database
        codevalue.child(ShopName).setValue(ShopName);
    }



    public void clear(View v){
        marketname.getText().clear();
        value.getText().clear();

    }

    //on click to button
    public void add(View v){

        //certain that fields not empty
        if(!TextUtils.isEmpty(marketname.getText().toString().trim())  &&  !TextUtils.isEmpty(value.getText().toString().trim()))
        {
            shop.addChildEventListener(new ChildEventListener()
            {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //to get the key of first node
                    String key = dataSnapshot.getKey();
                    add_all(key.toString(), marketname.getText().toString().trim(), value.getText().toString().trim());


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            }

            );

        } else {

            if(marketname.getText().toString().trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Market Name is Empty",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Value Name is Empty",Toast.LENGTH_LONG).show();
            }
        }


        // adding data to all code take 23 seconds
        //we made alert after 35 second
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                    alert.start();
                    }
                },
               30000
        );
    }
}



