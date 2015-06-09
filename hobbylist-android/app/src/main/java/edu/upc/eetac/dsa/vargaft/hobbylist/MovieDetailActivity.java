package edu.upc.eetac.dsa.vargaft.hobbylist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import edu.upc.eetac.dsa.vargaft.hobbylist.api.AppException;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.HobbylistAPI;
import edu.upc.eetac.dsa.vargaft.hobbylist.api.Lista;

/**
 * Created by Vargaft on 07/06/2015.
 */
public class MovieDetailActivity extends Activity {
    private final static String TAG = MovieDetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_layout);
        String urlLista = (String) getIntent().getExtras().get("url");
        (new FetchMoviesTask()).execute(urlLista);
    }

    private void loadLista(Lista lista) {
        TextView tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        TextView tvDetailSynopsis = (TextView) findViewById(R.id.tvDetailSynopsis);
        TextView tvDetailDirector = (TextView) findViewById(R.id.tvDetailDirector);
        TextView tvDetailGenre = (TextView) findViewById(R.id.tvDetailGenre);

        tvDetailTitle.setText(lista.getTitle());
        tvDetailSynopsis.setText(lista.getSynopsis());
        tvDetailDirector.setText(lista.getDirector());
        tvDetailGenre.setText(lista.getGenre());
    }

    private class FetchMoviesTask extends AsyncTask<String, Void, Lista> {
        private ProgressDialog pd;

        @Override
        protected Lista doInBackground(String... params) {
            Lista lista = null;
            try {
                lista = HobbylistAPI.getInstance(MovieDetailActivity.this)
                        .getLista(params[0]);
            } catch (AppException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            return lista;
        }

        @Override
        protected void onPostExecute(Lista result) {
            loadLista(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MovieDetailActivity.this);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

}