package com.mycompany.controllers;

import static spark.Spark.get;
import static spark.Spark.post;

import org.example.EnrollAdmin;
import org.example.RegisterUser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

		get("/queryCar/:id", (req, res) -> {

			byte[] result = Util.getChaincode().evaluateTransaction("queryCar", req.params(":id"));
			String results = new String(result);
			System.out.println(new String(result));
			return results;
		});

		post("/createCar", (req, res) -> {
			JSONParser parser = new JSONParser();
			JSONObject jObj = (JSONObject) parser.parse(req.body());
			System.out.println("============jObj=" + jObj);
			Util.getChaincode().submitTransaction("createCar", (String) jObj.get("id"), (String) jObj.get("make"),
					(String) jObj.get("model"), (String) jObj.get("color"), (String) jObj.get("owner"));
			return "Success";
		});

		get("/changeCarOwner/:id/:owner", (req, res) -> {

			Util.getChaincode().submitTransaction("changeCarOwner", req.params(":id"), req.params(":owner"));
			return "Success";
		});

	}
}