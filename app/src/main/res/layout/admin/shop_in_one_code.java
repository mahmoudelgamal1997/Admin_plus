package com.example2017.android.admin;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class shop_in_one_code extends AppCompatActivity {
    EditText codeText, marketText, marketValue;
    DatabaseReference codeReference;
    AlertDialog.Builder alert;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_one_code);
        Firebase.setAndroidContext(this);

        alert = new AlertDialog.Builder(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.notify);

        codeText = (EditText) findViewById(R.id.codeText);
        marketText = (EditText) findViewById(R.id.marketText);
        marketValue = (EditText) findViewById(R.id.marketValue);

        codeReference = FirebaseDatabase.getInstance().getReference().child("codes");

    }


    public void addtoOneCode(View v) {
        if (!TextUtils.isEmpty(marketText.getText().toString()) && !TextUtils.isEmpty(marketValue.getText().toString()) && !TextUtils.isEmpty(codeText.getText().toString())) {

            codeReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(codeText.getText().toString().toLowerCase().trim())) {

                        codeReference.child(codeText.getText().toString().toLowerCase().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChild(marketText.getText().toString().toLowerCase().trim())) {
                                    codeReference
                                            .child(codeText.getText().toString().toLowerCase().trim())
                                            .child(marketText.getText().toString().toLowerCase().trim())
                                            .setValue(marketValue.getText().toString().toLowerCase().trim());
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                                } else {


                                    mediaPlayer.start();
                                    alert.setMessage("هذا المحل غير متوفر ")
                                            .setCancelable(true)
                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });

                                    AlertDialog a = alert.create();
                                    a.show();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    } else {


                        mediaPlayer.start();
                        alert.setMessage("هذا الكود غير متوفر")
                                .setCancelable(true)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                        AlertDialog a = alert.create();
                        a.show();

                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else {
            Toast.makeText(getApplicationContext(),"ادخل البيانات كامله",Toast.LENGTH_LONG).show();
        }

    }

    public void clear_onecode(View v){
        codeText.getText().clear();
        marketText.getText().clear();
        marketValue.getText().clear();
    }


}