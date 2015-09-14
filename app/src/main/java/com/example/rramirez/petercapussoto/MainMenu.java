package com.example.rramirez.petercapussoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends AppCompatActivity {

    //Declare the list of Menu Entries
    private String[] menuEntries = {"Biografia" , "Videos" , "Fotos"};

    private ListView menuEntriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //Find the elements by ID.
        menuEntriesList =(ListView)findViewById(R.id.listView);

        //Create adapter and assign it to the menuEntriesList
        ArrayAdapter<String> MenuEntriesadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuEntries);
        menuEntriesList.setAdapter(MenuEntriesadapter);
        menuEntriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                switch(position){
                    case 0: Intent biography = new Intent(MainMenu.this, Biography.class);
                        startActivity(biography);
                        break;
                    case 1: Intent videos = new Intent(MainMenu.this, menu_video.class);
                        startActivity(videos);
                        break;
                    case 2: Intent photos = new Intent(MainMenu.this, photos.class);
                        startActivity(photos);
                        break;
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
