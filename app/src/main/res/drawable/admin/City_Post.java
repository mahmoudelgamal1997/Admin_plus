package com.example2017.android.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class City_Post extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private Uri imageuri = null;
    private StorageReference s;
    EditText input;
    Bitmap resized;
    ImageButton imageButton;
    public static final int gallery_Intent = 2;
    ProgressDialog progressDialog;
   private MediaPlayer mediaPlayer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__post);

        Firebase.setAndroidContext(this);

        imageButton = (ImageButton) findViewById(R.id.image);
        s = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("City");
        input = (EditText) findViewById(R.id.desc);
        progressDialog = new ProgressDialog(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.notify);



    }

    public void select(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, gallery_Intent);


    }

    public void push(View v) {

        StartPosting();
        mediaPlayer.start();

    }


    public void StartPosting() {

        if (!TextUtils.isEmpty(input.getText().toString()) && imageuri != null) {
            progressDialog.setMessage("wait..");
            progressDialog.show();
            StorageReference filebath = s.child("photos").child(imageuri.getLastPathSegment());
            filebath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri down = taskSnapshot.getDownloadUrl();
                    DatabaseReference newpost = mdatabase.push();
                    newpost.child("title").setValue(input.getText().toString());
                    newpost.child("img").setValue(down.toString());
                    Toast.makeText(getApplicationContext(), "Post Succsesfull", Toast.LENGTH_LONG).show();
                    input.getText().clear();
                    progressDialog.dismiss();


                }
            });



        } else {

            if ((input.getText().toString().isEmpty()))

            {
                Toast.makeText(getApplicationContext(), "Description is Empty", Toast.LENGTH_LONG).show();
            }
            if (imageuri == null) {
                Toast.makeText(getApplicationContext(), "You should select Image", Toast.LENGTH_LONG).show();

            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == gallery_Intent && resultCode == RESULT_OK) {

            imageuri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);
                resized = Bitmap.createScaledBitmap(bitmap, 1000, 1020, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageButton.setImageBitmap(resized);

        }}



}