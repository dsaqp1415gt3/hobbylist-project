package edu.upc.eetac.dsa.vargaft.hobbylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HobbylistMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbylist_main);
    }

    public void IrAMovies(View v){
        Intent gomovies=new Intent(this,MovieActivity.class);
        startActivity(gomovies);
    }

    public void IrABooks(View v){
        Intent gobooks=new Intent(this,BookActivity.class);
        startActivity(gobooks);
    }

    public void IrAGames(View v){
        Intent gogames=new Intent(this,GameActivity.class);
        startActivity(gogames);
    }

    public void IrAMessages(View v){
        Intent gomessages=new Intent(this,MessageActivity.class);
        startActivity(gomessages);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hobbylist_main, menu);
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
