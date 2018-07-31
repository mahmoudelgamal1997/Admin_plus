package com.example2017.android.admin_plus;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delete extends AppCompatActivity {
  private   DatabaseReference db ,code;
  private   Spinner spinner;

    MediaPlayer alert;

    //to save code from clear
    //we put one child as least
    private int minmumChildreninCode=1;
    private String shop_selected;
    private int preventRepeatDelete =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Firebase.setAndroidContext(this);

        db = FirebaseDatabase.getInstance().getReference().child("CodeValue");
        code = FirebaseDatabase.getInstance().getReference().child("codes");

        spinner = (Spinner) findViewById(R.id.spinner2);
        alert = MediaPlayer.create(this, R.raw.alert);


        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.text_style,
                db
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(R.id.textView_style);
                textView.setText(model);
                shop_selected = model;

            }

        };
        spinner.setAdapter(firebaseListAdapter);






    }


    public void okDelete(View v) {

        if (preventRepeatDelete == 0) {
            Toast.makeText(getApplicationContext(), shop_selected, Toast.LENGTH_LONG).show();


            code.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.getChildrenCount() > minmumChildreninCode) {

                        String key = dataSnapshot.getKey();
                        code.child(key).child(shop_selected).removeValue();
                        db.child(shop_selected).removeValue();

                    }
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
            });


            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();


            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alert.start();
                        }
                    },
                    30000
            );

        } else {

            preventRepeatDelete++;
            Toast.makeText(getApplicationContext(), "اقفل البرنامج وافتحه تاني عشان تمسح دا امان اكتر ", Toast.LENGTH_LONG).show();

        }
    }
}