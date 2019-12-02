package com.mycompany.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

public class Util {
	private static Contract contract = null;

	public static Contract getChaincode() throws IOException {

		if (contract == null) {
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);

			// load a CCP
			Path networkConfigPath = Paths.get("..", "..", "first-network", "connection-org1.yaml");

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			Gateway gateway = builder.connect();

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			contract = network.getContract("java-chaincode");
		}

		return contract;
	}
}
