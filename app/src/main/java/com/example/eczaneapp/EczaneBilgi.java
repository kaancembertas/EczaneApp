package com.example.eczaneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class EczaneBilgi extends AppCompatActivity {
    TextView tv;

    class getData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eczane_bilgi);
        tv=findViewById(R.id.ilce);
        Intent intent = getIntent();
        String ilce = intent.getStringExtra("ilce");
        tv.setText(ilce);
    }
}
