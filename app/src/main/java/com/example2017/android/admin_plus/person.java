package com.example2017.android.admin_plus;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class person extends AppCompatActivity {
    DatabaseReference UtilityFirebase;
    AlertDialog.Builder alert;
    EditText p1,p2,p3,p4,p5,p6,code,adress;
    MediaPlayer mediaPlayer,finishAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Firebase.setAndroidContext(this);

         alert=new AlertDialog.Builder(this);


        mediaPlayer = MediaPlayer.create(this, R.raw.notify);
        finishAlert = MediaPlayer.create(this, R.raw.alert);



        UtilityFirebase= FirebaseDatabase.getInstance().getReference().child("codes");

        p1=(EditText)findViewById(R.id.person1);
        p2=(EditText)findViewById(R.id.person2);
        p3=(EditText)findViewById(R.id.person3);
        p4=(EditText)findViewById(R.id.person4);
        p5=(EditText)findViewById(R.id.person5);
        p6=(EditText)findViewById(R.id.person6);
        code=(EditText)findViewById(R.id.code);
        adress=(EditText)findViewById(R.id.person7);



    }




    public void clear(android.view.View v)
    {
        p1.getText().clear();
        p2.getText().clear();
        p3.getText().clear();
        p4.getText().clear();
        p5.getText().clear();
        p6.getText().clear();
        code.getText().clear();
        adress.getText().clear();
        finishAlert.stop();
    }


    public void per(android.view.View v){

        UtilityFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.hasChild(code.getText().toString().toLowerCase().trim())){

                    if(!TextUtils.isEmpty(code.getText().toString().toLowerCase().trim())) {

                        add_person_names(code.getText().toString().toLowerCase().trim(),
                                p1.getText().toString().trim(),
                                p2.getText().toString().trim(),
                                p3.getText().toString().trim(),
                                p4.getText().toString().trim(),
                                p5.getText().toString().trim(),
                                p6.getText().toString().trim(),
                                adress.getText().toString().trim()
                                );


                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                           finishAlert.start();

                    }else{

                        Toast.makeText(getApplicationContext(),"You should add code",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    mediaPlayer.start();

                    alert.setMessage("This code isn't Available")
                    .setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog a=alert.create();
                    a.show();


            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }





     public void add_person_names(String code,String a1,String a2,String a3,String a4,String a5,String a6,String adress)
    {


        UtilityFirebase.child(code).child("person1").setValue(a1);
        UtilityFirebase.child(code).child("person2").setValue(a2);
        UtilityFirebase.child(code).child("person3").setValue(a3);
        UtilityFirebase.child(code).child("person4").setValue(a4);
        UtilityFirebase.child(code).child("person5").setValue(a5);
        UtilityFirebase.child(code).child("phone").setValue(a6);
        UtilityFirebase.child(code).child("adress").setValue(adress);



}}
