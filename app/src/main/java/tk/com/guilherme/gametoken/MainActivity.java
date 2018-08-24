package tk.com.guilherme.gametoken;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String url = "https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/games";
    private ProgressDialog progressDialog;
    private List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameList = new ArrayList<>();
        //gameList.add(new Game(1, "skyrim", "PC", "http", "http3", "18/08/2018", R.drawable.skyrim));
        gameList.add(new Game(2, "Sky", "PS4", "http2", "http4", "19/08/2018", R.drawable.skyrim));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);

        new LoadInfo().execute();

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, gameList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    class LoadInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show progressDialog to user until the loading is done
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Carregando informações da base de dados...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Asks for the JSON through URL
            String json = httpHandler.makeServiceCall(url);

            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);

                    JSONArray games = jsonObject.getJSONArray("games");

                    // Iterates through all JSON objects
                    for (int i = 0; i < games.length(); i++) {
                        JSONObject obj = games.getJSONObject(i);

                        String id = obj.getString("id");
                        String name = obj.getString("name");
                        String imageUrl = obj.getString("image");
                        String releaseDate = obj.getString("release_date");
                        String trailerUrl = obj.getString("trailer");
                        String platforms = obj.getString("platforms");
                        //JSONObject platforms = obj.getJSONObject("platforms");

                        /*for (int j = 0; j < platforms.length(); j++) {
                            String plat = platforms.getJSONObject("")
                            stringBuilder.append(singleLine).append('\n');
                        }
                            StringBuilder stringBuilder = new StringBuilder();*/

                        gameList.add(new Game(id, name, platforms, imageUrl, trailerUrl, releaseDate));

                    }
                } catch (final JSONException e) {
                    Log.e("MainActivity", "JSON parse error: " + e.getMessage());
                }
            } else {
                Log.e("MainActivity", "Not possible to read JSON");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

                /*ListAdapter adapter = new SimpleAdapter(
                        MainActivity.this, contactList,
                        R.layout.list_item, new String[]{"name", "email",
                        "mobile"}, new int[]{R.id.name,
                        R.id.email, R.id.mobile});

                lv.setAdapter(adapter);*/
        }

    }

    public List<Game> getGameList() {
        return gameList;
    }
}
