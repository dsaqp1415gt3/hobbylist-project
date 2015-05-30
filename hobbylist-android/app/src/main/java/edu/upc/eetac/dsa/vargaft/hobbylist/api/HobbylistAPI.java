package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Vargaft on 29/05/2015.
 */
public class HobbylistAPI {
    private final static String TAG = HobbylistAPI.class.getName();
    private static HobbylistAPI instance = null;
    private URL url;

    private HobbylistRootAPI rootAPI = null;

    private HobbylistAPI(Context context) throws IOException, AppException {
        super();

        AssetManager assetManager = context.getAssets();
        Properties config = new Properties();
        config.load(assetManager.open("config.properties"));
        String urlHome = config.getProperty("hobbylist.home");
        url = new URL(urlHome);

        Log.d("LINKS", url.toString());
        getRootAPI();
    }
//FALLA AQUÍ!!!
    public final static HobbylistAPI getInstance(Context context) throws AppException {
        if (instance == null)
            try {
                instance = new HobbylistAPI(context);
            } catch (IOException e) {
                throw new AppException(
                        "Can't load configuration file");
            }
        return instance;
    }

    private void getRootAPI() throws AppException {
        Log.d(TAG, "getRootAPI()");
        rootAPI = new HobbylistRootAPI();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Hobbylist API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, rootAPI.getLinks());
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Hobbylist API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Hobbylist Root API");
        }

    }

    public ListaCollection getLists() throws AppException {
        Log.d(TAG, "getLists()");
        ListaCollection lists = new ListaCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
                    .get("lists").getTarget()).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Hobbylist API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, lists.getLinks());

            JSONArray jsonLists = jsonObject.getJSONArray("lists");
            for (int i = 0; i < jsonLists.length(); i++) {
                Lista lista = new Lista();
                JSONObject jsonLista = jsonLists.getJSONObject(i);
                lista.setHobbyid(jsonLista.getInt("hobbyid"));
                lista.setClassification(jsonLista.getString("classification"));
                lista.setTitle(jsonLista.getString("title"));
                lista.setSynopsis(jsonLista.getString("synopsis"));
                lista.setGenre(jsonLista.getString("genre"));
                lista.setDirector(jsonLista.getString("director"));
                lista.setAuthor(jsonLista.getString("author"));
                lista.setCompany(jsonLista.getString("company"));
                lista.setYear(jsonLista.getString("year"));
                lista.setTag(jsonLista.getString("tag"));
                lista.setRank(jsonLista.getString("rank"));
                jsonLinks = jsonLista.getJSONArray("links");
                parseLinks(jsonLinks, lista.getLinks());
                lists.getLists().add(lista);
            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Hobbylist API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Hobbylist Root API");
        }

        return lists;
    }

    private void parseLinks(JSONArray jsonLinks, Map<String, Link> map)
            throws AppException, JSONException {
        for (int i = 0; i < jsonLinks.length(); i++) {
            Link link = null;
            try {
                link = SimpleLinkHeaderParser
                        .parseLink(jsonLinks.getString(i));
            } catch (Exception e) {
                throw new AppException(e.getMessage());
            }
            String rel = link.getParameters().get("rel");
            String rels[] = rel.split("\\s");
            for (String s : rels)
                map.put(s, link);
        }
    }
}
