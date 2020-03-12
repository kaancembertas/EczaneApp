package com.example.eczaneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String ilceUrl = "https://apps.istanbulsaglik.gov.tr/NobetciEczane";
    ListView myList;
    TextView header;

    class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            final String myUrl = strings[0];
            String ilceler = "";
            try{
                Document doc = Jsoup.connect(myUrl).get();
                Elements els = doc.getElementsByClass("ilce-link");
                for(Element el:els){
                    ilceler+=el.attr("data-value")+";";
                }
                return ilceler;
            }catch (Exception ex){

            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String [] ilceler = s.split(";");
                    ArrayAdapter<String > adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,android.R.id.text1,ilceler);
                    myList.setAdapter(adapter);
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = findViewById(R.id.myList);
        header = findViewById(R.id.header);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ilce = myList.getItemAtPosition(position).toString();
                Intent i = new Intent(MainActivity.this,EczaneBilgi.class);
                i.putExtra("ilce",ilce);
                startActivity(i);
            }
        });

        GetData getIlce = new GetData();
        getIlce.execute(ilceUrl);
    }
}
