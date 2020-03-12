package com.example.eczaneapp;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


public class EczaneView extends LinearLayout {
    public EczaneView(Context context,String eczane,String telefon,String fax,String sgk,String adres,String tarif) {
        super(context);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0,30,0,0);
        this.setLayoutParams(params);
        this.setPadding(10,0,10,0);
        this.setOrientation(VERTICAL);

        this.addView(header(context,eczane));
        this.addView(table(context,telefon,fax,sgk,adres,tarif));
    }

    private LinearLayout header(Context context,String eczane){
        LinearLayout l = new LinearLayout(context);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        l.setLayoutParams(params);
        l.setPadding(0,5,10,5);
        l.setBackgroundColor(ContextCompat.getColor(context,R.color.EczaneHeader));

        //Header Text
        TextView txt = new TextView(context);
        params.gravity = Gravity.CENTER;
        txt.setLayoutParams(params);
        txt.setGravity(Gravity.CENTER);
        txt.setTextSize(16);
        txt.setTextColor(ContextCompat.getColor(context,R.color.black));
        txt.setText(eczane);

        l.addView(txt);
        return l;
    }

    private TableLayout table(Context context,String telefon,String fax,String sgk,String adres,String tarif){
        TableLayout t = new TableLayout(context);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );

        t.setLayoutParams(params);
        t.setBackgroundColor(ContextCompat.getColor(context,R.color.tableBackground));

        t.addView(row(context,"Telefon: ",telefon));
        t.addView(row(context,"Fax: ",fax));
        t.addView(row(context,"SGK Anlaşması: ",sgk));
        t.addView(row(context,"Adres: ",adres));
        t.addView(row(context,"Adres Tarifi: ",tarif));
        return t;
    }

    private TableRow row(Context context,String first,String second){
        TableRow.LayoutParams params = new TableRow.LayoutParams(
               500,
                LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        //params.gravity = Gravity.RIGHT;

        TableRow r = new TableRow(context);
        r.setLayoutParams(tableRowParams);
        r.setPadding(5,5,5,5);

        TextView txt1 = new TextView(context);
        txt1.setTextSize(15);
        txt1.setLayoutParams(params);
        txt1.setGravity(Gravity.CENTER);
        txt1.setTextColor(ContextCompat.getColor(context,R.color.black));
        txt1.setText(first);

        TextView txt2 = new TextView(context);
        txt2.setTextSize(15);
        txt2.setLayoutParams(params);
        txt2.setGravity(Gravity.CENTER);
        txt2.setTextColor(ContextCompat.getColor(context,R.color.black));
        txt2.setText(second);



        r.addView(txt1);
        r.addView(txt2);
        return r;
    }

}
