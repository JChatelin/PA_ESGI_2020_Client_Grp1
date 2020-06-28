package Services;

import Exceptions.LoadMusicsListFailedException;
import Models.Music;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MusicsListLoader {
    private List<Music> musics;
    public String loadLink;

    public MusicsListLoader() {
    }

    public List<Music> loadList() throws Exception{
        System.out.println("Connecting to server...");
        String token = "";

        URL url = new URL(loadLink);
        HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
        connexion.setRequestMethod("GET");
        connexion.setConnectTimeout(1000);
        connexion.setReadTimeout(1000);

        BufferedReader In = new BufferedReader(
                new InputStreamReader(connexion.getInputStream()));
        String inputLine;
        StringBuilder  content = new StringBuilder ();
        while ((inputLine = In.readLine()) != null) {
            content.append(inputLine);
        }

        In.close();
        JSONArray resarray = new JSONArray(content.toString());

        if(connexion.getResponseCode() == 200){
            JSONObject currentresobject = new JSONObject();
            for (int i =0 ; i<resarray.length() ; i++){
                currentresobject = resarray.getJSONObject(i);
                Music actMus = new Music((int) currentresobject.get("id"), (String) currentresobject.get("name"),
                        (String) currentresobject.get("downloadLink"), false, 0);
                musics.add(actMus);
            }
        }else{
            throw new LoadMusicsListFailedException("Error while loading musics list ! " +
                    "please check the provided properties, and check the server.");
        }
        return musics;
    }
}
