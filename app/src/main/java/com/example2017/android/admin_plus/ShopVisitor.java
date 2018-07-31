package com.example2017.android.admin_plus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopVisitor extends AppCompatActivity {
ListView listView;
DatabaseReference visit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_visitor);
        Firebase.setAndroidContext(this);

    listView=(ListView)findViewById(R.id.listView2);

        visit= FirebaseDatabase.getInstance().getReference().child("ShopVisitors");

        FirebaseListAdapter<ShopVisit_listItem> firebaseListAdapter=new FirebaseListAdapter<ShopVisit_listItem>(
                this,
                ShopVisit_listItem.class,
                R.layout.item_listview,
                visit


        ) {
            @Override
            protected void populateView(View v, ShopVisit_listItem model, int position) {

                TextView txt=(TextView)v.findViewById(R.id.shop_name);
                TextView txt2=(TextView)v.findViewById(R.id.code);

                txt.setText(model.getShopName());
                txt2.setText(model.getVisits());
            }
        };

    listView.setAdapter(firebaseListAdapter);

    }

}
