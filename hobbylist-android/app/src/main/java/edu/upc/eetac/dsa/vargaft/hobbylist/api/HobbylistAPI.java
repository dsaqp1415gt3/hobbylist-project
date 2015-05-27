package edu.upc.eetac.dsa.vargaft.hobbylist.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

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

    public MovieCollection getMovies() throws AppException {
        Log.d(TAG, "getMovies()");
        MovieCollection movies = new MovieCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks().get("movies").getTarget()).openConnection();
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
            parseLinks(jsonLinks, movies.getLinks());

            movies.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            movies.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonMovies = jsonObject.getJSONArray("movies");
            for (int i = 0; i < jsonMovies.length(); i++) {
                Movie movie = new Movie();
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);
                movie.setMovieid(jsonMovie.getInt("movieid"));
                movie.setTitle(jsonMovie.getString("title"));
                movie.setTag(jsonMovie.getString("tag"));
                movie.setUsername(jsonMovie.getString("username"));
                movie.setLastModified(jsonMovie.getLong("lastModified"));
                movie.setCreationTimestamp(jsonMovie.getLong("creationTimestamp"));
                jsonLinks = jsonMovie.getJSONArray("links");
                parseLinks(jsonLinks, movie.getLinks());
                movies.getMovies().add(movie);
            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Movie API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Movie Root API");
        }

        return movies;
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
