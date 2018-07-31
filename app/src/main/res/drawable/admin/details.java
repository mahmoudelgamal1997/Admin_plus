package com.example2017.android.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class details extends AppCompatActivity {
ListView listView;
    DatabaseReference information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Firebase.setAndroidContext(this);

    information= FirebaseDatabase.getInstance().getReference().child("details");
    listView=(ListView)findViewById(R.id.listView);

        FirebaseListAdapter<item_in_deatails_listview> firebaseListAdapter=new FirebaseListAdapter<item_in_deatails_listview>(
                this,
                item_in_deatails_listview.class,
                R.layout.item_listview,
                information
        ) {
            @Override
            protected void populateView(View v, item_in_deatails_listview model, int position)
            {

                TextView txt_code=(TextView)v.findViewById(R.id.code);
                TextView txt_shop=(TextView)v.findViewById(R.id.shop_name);
                TextView txt_time=(TextView)v.findViewById(R.id.date_time);

               txt_code.setText(model.getCode());
                txt_shop.setText(model.getShop());
                txt_time.setText(model.getDate());


            }
        };


listView.setAdapter(firebaseListAdapter);

    }


}
