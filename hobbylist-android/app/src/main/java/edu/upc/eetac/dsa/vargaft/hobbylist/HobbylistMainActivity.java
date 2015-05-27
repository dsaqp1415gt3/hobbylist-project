package edu.upc.eetac.dsa.vargaft.hobbylist;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.vargaft.hobbylist.api.AppException;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.HobbylistAPI;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.Movie;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.MovieCollection;


public class HobbylistMainActivity extends ListActivity {
    private final static String TAG = HobbylistMainActivity.class.toString();
    private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus" };
    private ArrayList<Movie> moviesList;
    private MovieAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbylist_main);

        moviesList = new ArrayList<Movie>();
        adapter = new MovieAdapter(this, moviesList);
        setListAdapter(adapter);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ruben", "ruben"
                        .toCharArray());
            }
        });
        (new FetchMoviesTask()).execute();
    }

    private class FetchMoviesTask extends AsyncTask<Void, Void, MovieCollection> {
        private ProgressDialog pd;

        @Override
        protected MovieCollection doInBackground(Void... params) {
            MovieCollection movies = null;
            try {
                movies = HobbylistAPI.getInstance(HobbylistMainActivity.this).getMovies();
            } catch (AppException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override
        protected void onPostExecute(MovieCollection result) {
            addMovies(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(HobbylistMainActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

    private void addMovies(MovieCollection movies){
        moviesList.addAll(movies.getMovies());
        adapter.notifyDataSetChanged();
    }
}
