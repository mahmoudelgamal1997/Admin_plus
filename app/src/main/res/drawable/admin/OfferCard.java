package com.example2017.android.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OfferCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_card);
    }



    public void addperson(View v){

        Intent intent = new Intent(this,person.class);
        startActivity(intent);

    }

    public void addshop(View v){

        Intent intent = new Intent(this,shop_in_code.class);
        startActivity(intent);

    }

    public void details(View v){

        Intent intent = new Intent(this,details.class);
        startActivity(intent);

    }
    public void onecode(View v){

        Intent intent = new Intent(this,shop_in_one_code.class);
        startActivity(intent);

    }

    public void delete(View v){

        Intent intent = new Intent(this,delete.class);
        startActivity(intent);

    }

    public void visit(View v){

        Intent intent = new Intent(this,ShopVisitor.class);
        startActivity(intent);

    }
}
