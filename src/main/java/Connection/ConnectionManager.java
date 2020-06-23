package Connection;



import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionManager {

    String connectURL;
    String username;
    String password;

    public ConnectionManager(){

    }

    public ConnectionManager(String connectURL, String username, String password) {
        this.connectURL = connectURL;
        this.username = username;
        this.password = password;
    }

    public String Connect() throws Exception{
        System.out.println("Connecting to server...");
        String token = "";
        String params = "?email=su2@local.me&password=0000";
        URL url = new URL("http://localhost:8000/api/login" + params);
        HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
        connexion.setRequestMethod("POST");
        connexion.setConnectTimeout(5000);
        connexion.setReadTimeout(5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connexion.getInputStream()));
        String inputLine;
        StringBuilder  content = new StringBuilder ();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        JSONObject jsonrespons = new JSONObject(content.toString());
        if(connexion.getResponseCode() == 200){
            System.out.println("Connexion success !");
            token = (String) jsonrespons.get("token");
            return token;
        }else{
            System.out.println("Connexion error");
            return "CONNECTIONFAILED";
        }

    }

}
