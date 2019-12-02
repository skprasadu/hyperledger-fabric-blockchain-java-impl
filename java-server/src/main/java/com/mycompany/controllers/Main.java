package com.mycompany.controllers;

import static spark.Spark.get;

import org.example.EnrollAdmin;
import org.example.RegisterUser;

public class Main {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) throws Exception {
		EnrollAdmin.main(null);
		RegisterUser.main(null);

		get("/queryAllCars", (req, res) -> {

			byte[] result;

			result = Util.getChaincode().evaluateTransaction("queryAllCars");
			String results = new String(result);
			System.out.println(results);

			return results;
		});
	}
}