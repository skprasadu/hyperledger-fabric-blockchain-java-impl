package com.mycompany.controllers;

import static spark.Spark.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.example.EnrollAdmin;
import org.example.RegisterUser;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

public class Main {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

    public static void main(String[] args) throws Exception {
		EnrollAdmin.main(null);
		RegisterUser.main(null);
    	
        get("/queryAllCars", (req, res) -> {
    		Path walletPath = Paths.get("wallet");
    		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

    		// load a CCP
    		Path networkConfigPath = Paths.get("..", "..", "first-network", "connection-org1.yaml");

    		Gateway.Builder builder = Gateway.createBuilder();
    		builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(true);

    		// create a gateway connection
    		try (Gateway gateway = builder.connect()) {

    			// get the network and contract
    			Network network = gateway.getNetwork("mychannel");
    			Contract contract = network.getContract("java-chaincode");

    			byte[] result;

    			result = contract.evaluateTransaction("queryAllCars");
    			String results = new String(result);
    			System.out.println(results);
    			
    			return results;
    		}	
        });
    }
}