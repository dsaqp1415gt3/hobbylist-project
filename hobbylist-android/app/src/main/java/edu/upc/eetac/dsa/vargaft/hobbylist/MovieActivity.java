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
import edu.upc.eetac.dsa.vargaft.hobbylist.api.Lista;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.ListaCollection;


public class MovieActivity extends ListActivity {

    private final static String TAG = MovieActivity.class.toString();
    private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus" };
    private ArrayAdapter<String> adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ruben", "ruben"
                        .toCharArray());
            }
        });
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
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
            ArrayList<Lista> lists = new ArrayList<Lista>(result.getLists());
            for (Lista s : lists) {
                Log.d(TAG, s.getHobbyid() + "-" + s.getTitle());
            }
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
}
