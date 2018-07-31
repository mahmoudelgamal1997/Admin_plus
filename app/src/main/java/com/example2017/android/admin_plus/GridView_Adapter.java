package com.example2017.android.admin_plus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by M7moud on 31-Jul-18.
 */
public class GridView_Adapter extends BaseAdapter {

    ArrayList<Gridview_item> arrayList =new ArrayList<>();
    Context c;

    public GridView_Adapter(ArrayList<Gridview_item> arrayList, Context c) {
        this.arrayList = arrayList;
        this.c = c;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=LayoutInflater.from(c).inflate(R.layout.gridview_item,null);
        ImageView img=(ImageView)view.findViewById(R.id.spacecraftImg);
        TextView txt=(TextView)view.findViewById(R.id.nameTxt);

        final Gridview_item temp=(Gridview_item)this.getItem(i);


        img.setImageURI(temp.getUri());

        txt.setText(temp.getName());





        return view;
    }
}
