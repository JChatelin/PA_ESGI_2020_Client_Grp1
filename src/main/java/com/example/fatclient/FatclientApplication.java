package com.example.fatclient;

import Connection.ConnectionManager;
import Connection.PropertiesReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class FatclientApplication {

	public static void main(String[] args) throws Exception {
		System.out.println("hello ...");

		PropertiesReader pr = new PropertiesReader();
		Properties Cpr = pr.getConnecxionProps();
		System.out.println(Cpr);
		//String connectURL, String username, String password
		ConnectionManager cnm = new ConnectionManager( (String) Cpr.get("server_adress"),
				(String) Cpr.get("login"), (String) Cpr.get("passwd") );
		String token = cnm.Connect();


	}

}
