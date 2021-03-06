package com.example2017.android.admin_plus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;

public class Shop extends AppCompatActivity {
    private Spinner spinnerCity, spinnerCatorgy;
    private DatabaseReference catorgy_database, city_database, ShopVisitors,offers;
    private StorageReference Storage;
    int initialVisits = 0;
    private MediaPlayer mediaPlayer;
    ProgressDialog progressDialog;
    String city_selected, catorgy_selected;
    public static final int MultiImages = 1 ;
    public static final int SoloImages  = 2 ;
    public static final int OfferImages  = 3 ;
    private Uri imageuri  , imageuri_offer= null;
    ImageButton imageButton,ImageButton_offer;
    EditText editText_shop, editText_home, editText_home2, editText_home3, editText_mobile, editText_mobile2, editText_mobile3, editText_mobile4, editText_details, facebook, Instgram, Twitter;
    TextView txt_alert;
    CheckBox checkBox;
    boolean   check_offer=false;
    ArrayList<Uri> filepath = new ArrayList<>();
    GridView gridView;
    DatabaseReference post=null;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Firebase.setAndroidContext(this);
        city_database = FirebaseDatabase.getInstance().getReference().child("City");
        catorgy_database = FirebaseDatabase.getInstance().getReference().child("catorgy");
        Storage = FirebaseStorage.getInstance().getReference();
        offers=FirebaseDatabase.getInstance().getReference().child("offers");
        ShopVisitors = FirebaseDatabase.getInstance().getReference().child("ShopVisitors");
        mediaPlayer = MediaPlayer.create(this, R.raw.notify);

        gridView = (GridView) findViewById(R.id.gridview);
        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        spinnerCatorgy = (Spinner) findViewById(R.id.spinner_catorgy);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        ImageButton_offer=(ImageButton)findViewById(R.id.imageButton_offer);
        editText_shop = (EditText) findViewById(R.id.editText_shop);
        editText_mobile = (EditText) findViewById(R.id.editText_mobile);
        editText_mobile2 = (EditText) findViewById(R.id.editText_mobile2);
        editText_mobile3 = (EditText) findViewById(R.id.editText_mobile3);
        editText_mobile4 = (EditText) findViewById(R.id.editText_mobile4);
        editText_home = (EditText) findViewById(R.id.editText_home);
        editText_home2 = (EditText) findViewById(R.id.editText_home2);
        editText_home3 = (EditText) findViewById(R.id.editText_home3);
        facebook = (EditText) findViewById(R.id.editText_facebook);
        Instgram = (EditText) findViewById(R.id.editText_Instgram);
        Twitter = (EditText) findViewById(R.id.editText_Twitter);
        editText_details = (EditText) findViewById(R.id.editText_details);

        txt_alert=(TextView)findViewById(R.id.textView_alert);
        checkBox=(CheckBox)findViewById(R.id.check_offer);




        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_offer==false) {
                    check_offer = true;
                    txt_alert.setVisibility(View.VISIBLE);
                    ImageButton_offer.setVisibility(View.VISIBLE);
                  } else{

                    check_offer = false;
                    txt_alert.setVisibility(View.INVISIBLE);
                    ImageButton_offer.setVisibility(View.INVISIBLE);
                    }
            }
        });

        progressDialog = new ProgressDialog(this);


        FirebaseListAdapter<City> cityListAdapter = new FirebaseListAdapter<City>(
                this,
                City.class,
                R.layout.text_style,
                city_database
        ) {
            @Override
            protected void populateView(android.view.View v, City model, int position) {
                TextView txt = (TextView) v.findViewById(R.id.textView_style);
                txt.setText(model.getTitle());

                //taking select of user
                //and put it in variable
                city_selected = model.getTitle();

            }
        };
        spinnerCity.setAdapter(cityListAdapter);

        FirebaseListAdapter<Datainput> catorgyListAdapter = new FirebaseListAdapter<Datainput>(
                this,
                Datainput.class,
                R.layout.text_style,
                catorgy_database
        ) {
            @Override
            protected void populateView(android.view.View v, Datainput model, int position) {

                TextView textView = (TextView) v.findViewById(R.id.textView_style);
                textView.setText(model.getCatorgy_name());

                //taking select of user
                //and put it in variable
                catorgy_selected = model.getCatorgy_name();
            }
        };

        spinnerCatorgy.setAdapter(catorgyListAdapter);
    }


    public void upload(android.view.View view) {

        if (!TextUtils.isEmpty(editText_shop.getText().toString()) && imageuri != null) {
           post = catorgy_database.child(catorgy_selected).child(catorgy_selected).child(city_selected).child(editText_shop.getText().toString().toLowerCase().trim());

            progressDialog.setMessage("Wait..");
            progressDialog.show();
            progressDialog.setCancelable(false);
            StorageReference filebath = Storage.child("photos").child(imageuri.getLastPathSegment());
            filebath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri down = taskSnapshot.getDownloadUrl();

                    post.child("catorgy_image").setValue(down.toString());

                    UploadOffer(post);

                   if(check_offer)
                   {


                       DatabaseReference temp =offers.push();
                       UploadOffer(temp);
                       uploadOfferImage(temp);
                   }

                UploadingImages(filepath);



                    /*
                    post.child("catorgy_name").setValue(editText_shop.getText().toString());
                    post.child("catorgy_image").setValue(down.toString());


                    ShopVisitors.child(editText_shop.getText().toString()).child("ShopName").setValue(editText_shop.getText().toString());
                    ShopVisitors.child(editText_shop.getText().toString()).child("visits").setValue(String.valueOf(initialVisits));


                    post.child("shop_details").setValue(editText_details.getText().toString().toLowerCase().trim());


                    post.child("shop_mobile").setValue(editText_mobile.getText().toString().toLowerCase().trim());

                    post.child("shop_mobile2").setValue(editText_mobile2.getText().toString().toLowerCase().trim());
                    post.child("shop_mobile3").setValue(editText_mobile3.getText().toString().toLowerCase().trim());
                    post.child("shop_mobile4").setValue(editText_mobile4.getText().toString().toLowerCase().trim());


                    post.child("shop_home").setValue(editText_home.getText().toString().toLowerCase().trim());
                    post.child("shop_home2").setValue(editText_home2.getText().toString().toLowerCase().trim());
                    post.child("shop_home3").setValue(editText_home3.getText().toString().toLowerCase().trim());


                    post.child("Facebook").setValue(facebook.getText().toString().toLowerCase().trim());
                    post.child("Instgram").setValue(Instgram.getText().toString().toLowerCase().trim());
                    post.child("Twitter").setValue(Twitter.getText().toString().toLowerCase().trim());
*/



                    Toast.makeText(getApplicationContext(), "Post Succsesfull", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });

        } else {
            if (TextUtils.isEmpty(editText_shop.getText().toString())) {
                Toast.makeText(getApplication(), "Enter shop name", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Select an image", Toast.LENGTH_LONG).show();

            }

        }

        mediaPlayer.start();


    }

    public void MapOfshop(android.view.View v) {

        Intent intent=new Intent(Shop.this,MapsActivity.class);
        startActivity(intent);


    }


    public void select_shopimage(android.view.View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SoloImages);

    }

    public void select_shopimageOffer (android.view.View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OfferImages);

    }



    public void selectImagesOfshop(android.view.View v) {
        //to select multi images from gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);


    }


@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {

      switch (requestCode) {

        case SoloImages:
        imageuri = data.getData();
        imageButton.setImageURI(imageuri);

        Bitmap resized = null;
        try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);

        resized = Bitmap.createScaledBitmap(bitmap, 1000, 1020, true);


        } catch (IOException e) {
        e.printStackTrace();
        }

        imageButton.setImageBitmap(resized);

        break;


          case OfferImages:

              imageuri_offer = data.getData();
              ImageButton_offer.setImageURI(imageuri_offer);

              Bitmap resized2 = null;
              try {
                  Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri_offer);

                  resized2 = Bitmap.createScaledBitmap(bitmap, 1000, 1020, true);


              } catch (IOException e) {
                  e.printStackTrace();
              }

              ImageButton_offer.setImageBitmap(resized2);

              break;












         case MultiImages:
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (data.getClipData() != null) {
            int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) {
                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                filepath.add(imageUri);

                SetGridView();
                //do something with the image (save it to some directory or whatever you need to do with it here)
                }
        } else if (data.getData() != null) {
        String imagePath = data.getData().getPath();
        //do something with the image (save it to some directory or whatever you need to do with it here)
        }









          }}

        }}

   public void  UploadingImages(ArrayList<Uri> arraylist)
   {
            for (int i=0 ; i < arraylist.size();i++)
            {
            StorageReference file =Storage.child("photo").child(arraylist.get(i).getLastPathSegment());
            final int finalI = i;
            file.putFile(arraylist.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            Uri down =taskSnapshot.getDownloadUrl();
            post.child("img"+(finalI+1)).setValue(down.toString());
            }
    });


        }
   }



    public void SetGridView(){

        GridView gridView =(GridView)findViewById(R.id.gridview);
        GridView_Adapter gridView_adapter=new GridView_Adapter(filepath,this);
        gridView.setAdapter(gridView_adapter);

    }


    public void UploadOffer(DatabaseReference reference)
    {
        reference.child("catorgy_name").setValue(editText_shop.getText().toString());

        ShopVisitors.child(editText_shop.getText().toString()).child("ShopName").setValue(editText_shop.getText().toString());
        ShopVisitors.child(editText_shop.getText().toString()).child("visits").setValue(String.valueOf(initialVisits));

        reference.child("shop_details").setValue(editText_details.getText().toString().toLowerCase().trim());
        reference.child("shop_mobile").setValue(editText_mobile.getText().toString().toLowerCase().trim());
        reference.child("shop_mobile2").setValue(editText_mobile2.getText().toString().toLowerCase().trim());
        reference.child("shop_mobile3").setValue(editText_mobile3.getText().toString().toLowerCase().trim());
        reference.child("shop_mobile4").setValue(editText_mobile4.getText().toString().toLowerCase().trim());
        reference.child("shop_home").setValue(editText_home.getText().toString().toLowerCase().trim());
        reference.child("shop_home2").setValue(editText_home2.getText().toString().toLowerCase().trim());
        reference.child("shop_home3").setValue(editText_home3.getText().toString().toLowerCase().trim());
        reference.child("Facebook").setValue(facebook.getText().toString().toLowerCase().trim());
        reference.child("Instgram").setValue(Instgram.getText().toString().toLowerCase().trim());
        reference.child("Twitter").setValue(Twitter.getText().toString().toLowerCase().trim());




        sh=getSharedPreferences("plz", Context.MODE_PRIVATE );

        String latiude=(sh.getString( "latitude","emputy" ) );
        String longtude=(sh.getString( "longtiude","emputy" ) );

        reference.child("latiude").setValue(latiude);
        reference.child("longtiude").setValue(longtude);
        reference.child("latiude").setValue(latiude);
        reference.child("longtiude").setValue(longtude);





    }




    public void uploadOfferImage(final DatabaseReference reference){

        StorageReference file=Storage.child("photos").child(imageuri_offer.getLastPathSegment());
        file.putFile(imageuri_offer).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            Uri uri=taskSnapshot.getDownloadUrl();
            reference.child("catorgy_image").setValue(uri.toString());

            }
        });






            }

}

