package com.example.fatclient;

import Connection.ConnectionManager;
import Connection.PropertiesReader;
import Services.TrackDownload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class FatclientApplication {

	public static void main(String[] args) throws Exception {
		System.out.println("Running...");

		PropertiesReader pr = new PropertiesReader();
		Properties Cpr = pr.getConnecxionProps();
		System.out.println( " ---- Propoerties readed :" + Cpr);

		//String connectURL, String username, String password
		ConnectionManager cnm = new ConnectionManager( (String) Cpr.get("login_adress"),
				(String) Cpr.get("login"), (String) Cpr.get("passwd") );
		String token = cnm.Connect();

		TrackDownload trservice = new TrackDownload("https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_700KB.mp3", "test");
		String resdownload = trservice.runDownload();
		System.out.println(resdownload);

		// le client va récupérer les props de connexion => compte pour client lourd
		// Route pour récuprer l'ensmble des musics qui sont pas traités json {nom, lien pour download, ....}
		// traitement loud
		// Route post pour envoyé les résultats de traitement

	}

}
