package com.example2017.android.admin;

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

import java.util.ArrayList;

public class TestRetrieve extends AppCompatActivity {
DatabaseReference medo,codes;
    Spinner spinner;
    DataSnapshot sec;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrieve);
        Firebase.setAndroidContext(this);


        medo= FirebaseDatabase.getInstance().getReference().child("test");

       codes=FirebaseDatabase.getInstance().getReference().child("codes");






        spinner=(Spinner)findViewById(R.id.spinner3);


        FirebaseListAdapter<com.example2017.android.admin.City> firebaseListAdapter=new FirebaseListAdapter<com.example2017.android.admin.City>(

                this,
                com.example2017.android.admin.City.class,
                android.R.layout.simple_spinner_item,
                medo

        ) {




            @Override
            protected void populateView(View v, final com.example2017.android.admin.City model, int position) {

                TextView textView =(TextView)v.findViewById(android.R.id.text1);
                textView.setText(model.getTitle());

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),model.getTitle().toString(),Toast.LENGTH_LONG).show();


                    }
                });

            }
        };

    spinner.setAdapter(firebaseListAdapter);



 medo.addChildEventListener(new ChildEventListener() {
     @Override
     public void onChildAdded(DataSnapshot dataSnapshot, String s) {



         if (dataSnapshot.hasChild("title")){
             System.out.println("yes");
         }else
             System.out.println("no");

     }
     @Override
     public void onChildChanged(DataSnapshot dataSnapshot, String s) {
         for (DataSnapshot ds : dataSnapshot.getChildren()) {
             for (DataSnapshot ds2 : ds.getChildren()) {
                 for (DataSnapshot ds3 : ds2.getChildren()){
                     System.out.println(ds3);
                     com.example2017.android.admin.City c = ds3.getValue(com.example2017.android.admin.City.class);
                     System.out.println(c.getTitle());


     }}}}

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





}


  static  public void Search(String city,ChildEventListener listener){

        FirebaseDatabase.getInstance().getReference("codes").addChildEventListener( listener);
    }


public void but(View v)
{

com.example2017.android.admin.CodeValue c=new com.example2017.android.admin.CodeValue();
    c.setPerson1("ahmed");
    c.setPerson2("mahmoud");
    c.setPerson3("hamdy");
    c.setPerson4("bassam");
    c.setPerson5("sako");
    codes.child("11111").setValue(c);

}


}