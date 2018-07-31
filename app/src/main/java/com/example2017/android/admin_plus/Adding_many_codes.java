package com.example2017.android.admin_plus;

import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by M7moud on 19-Jan-18.
 *
 * this class to add all codes in one step
 * we read codes from file and write in firebase
 * to use this class we can call it fron any activity
 *
 *
 *
 */
public class Adding_many_codes {

/*
add this code in shop_in_code

  Adding_many_codes a=new Adding_many_codes();
        a.add(this,shop);
 */

    public void add(shop_in_code m, DatabaseReference shop){
        BufferedReader reader=null;
        try {
         // Assets is the location  file which contains codes
            reader=new BufferedReader(new InputStreamReader(m.getAssets().open("somecode.txt")));

            String word;
            while ((word=reader.readLine())!=null){
                System.out.println(word);

                shop.child(word).child("value").setValue("0");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
