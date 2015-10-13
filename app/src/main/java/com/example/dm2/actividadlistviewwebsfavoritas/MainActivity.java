package com.example.dm2.actividadlistviewwebsfavoritas;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ListView listaWebs;
    Web[]datosWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datosWeb=new Web[]{
                new Web("Google","http://www.google.es",R.drawable.ic_google),
                new Web("Bing","http://www.bing.es",R.drawable.ic_bing),
                new Web("Yahoo","http://www.yahoo.es",R.drawable.ic_yahoo),
                new Web("Marca","http://www.marca.es",R.drawable.ic_marca),
                new Web("El Correo","http://www.elcorreo.es",R.drawable.ic_elcorreo)
        };
        AdaptadorUrl adaptador=new AdaptadorUrl(this,datosWeb);
        listaWebs=(ListView)findViewById(R.id.ListViewWebs);
        listaWebs.setAdapter(adaptador);
        listaWebs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri= Uri.parse(datosWeb[position].getUrl());
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
