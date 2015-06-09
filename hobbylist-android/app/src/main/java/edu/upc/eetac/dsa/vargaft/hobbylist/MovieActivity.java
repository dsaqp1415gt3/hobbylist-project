package edu.upc.eetac.dsa.vargaft.hobbylist;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.vargaft.hobbylist.api.AppException;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.HobbylistAPI;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.Lista;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.ListaCollection;


public class MovieActivity extends ListActivity {

    private final static String TAG = MovieActivity.class.toString();
    private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus" };
    private ArrayList<Lista> listsList;
    private MovieAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        listsList = new ArrayList<Lista>();
        adapter = new MovieAdapter(this, listsList);
        setListAdapter(adapter);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ruben", "ruben"
                        .toCharArray());
            }
        });
        (new FetchMoviesTask()).execute();
    }

    private class FetchMoviesTask extends AsyncTask<Void, Void, ListaCollection> {
        private ProgressDialog pd;

        @Override
        protected ListaCollection doInBackground(Void... params) {
            ListaCollection lists = null;
            try {
                lists = HobbylistAPI.getInstance(MovieActivity.this)
                        .getLists();
            } catch (AppException e) {
                e.printStackTrace();
            }
            return lists;
        }

        @Override
        protected void onPostExecute(ListaCollection result) {
            addMovies(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MovieActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }
    }

    private void addMovies(ListaCollection lists){
        listsList.addAll(lists.getLists());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Lista lista = listsList.get(position);
        Log.d(TAG, lista.getLinks().get("self").getTarget());

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("url", lista.getLinks().get("self").getTarget());
        startActivity(intent);
    }
}
