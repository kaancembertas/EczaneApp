package com.example.eczaneapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.file.Files;

public class EczaneBilgi extends AppCompatActivity {
    TextView header,subHeader;
    LinearLayout container;
    String url = "https://apps.istanbulsaglik.gov.tr/NobetciEczane/Home/GetEczaneler";

    class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection.Response response = Jsoup.connect(strings[0])
                        .userAgent("Mozilla/5.0")
                        .method(Connection.Method.POST)
                        .data("ilce",strings[1]).execute();
                Document document = response.parse();
                return document.body().toString();
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
                    Document document = Jsoup.parse(s);
                    Elements elements = document.getElementsByClass("card");
                    String eczane,telefon,fax,sgk,adres,tarif,headText="";
                    for(Element el:elements){
                        Document doc = Jsoup.parse(el.toString());
                        /* ------------Eczane İsmi ---------------*/
                        Elements eczaneQuery = doc
                                .select("div[class=card-header text-center]");
                        headText=eczaneQuery.get(0).text();
                        eczane=eczaneQuery.get(1).text();
                        /* ------------------------------------- */
                        Elements adresQuery = doc
                                .select("div>table>tbody>tr>td>label");
                        telefon = adresQuery.get(1).text();
                        fax = adresQuery.get(3).text();
                        sgk =adresQuery.get(5).text();
                        adres =adresQuery.get(7).text();
                        tarif =adresQuery.get(9).text();
                        /* ------------------------------------------*/

                        container.addView(new EczaneView(EczaneBilgi.this,
                                eczane,telefon,fax,sgk,adres,tarif));
                    }
                    subHeader.setText(headText);

                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eczane_bilgi);
        header = findViewById(R.id.header);
        container = findViewById(R.id.container);
        subHeader = findViewById(R.id.subHeader);
        //Get Intent Param and Set Header
        Intent intent = getIntent();
        String ilce = intent.getStringExtra("ilce");
        header.setText(ilce);

        GetData getEczane = new GetData();
        getEczane.execute(url,ilce);

    }
}
