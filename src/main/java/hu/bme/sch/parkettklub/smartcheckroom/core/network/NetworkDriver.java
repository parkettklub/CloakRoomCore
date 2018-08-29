package hu.bme.sch.parkettklub.smartcheckroom.core.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import hu.bme.sch.parkettklub.smartcheckroom.core.Core;
import hu.bme.sch.parkettklub.smartcheckroom.core.EnvironmentWrapperI;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.BarcodeNumber;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.DBResponse;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Item;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Transaction;
import hu.xannosz.microtools.Sleep;

public class NetworkDriver extends Listener {

	private static final int UDP_PORT = 9000;
	private static final int TIMEOUT = 5000;
	private static final int TCP_PORT = 5000;

	private NetworkServer server;
	private NetworkClient client;
	private boolean isServer;
	private Core core;
	private EnvironmentWrapperI environmentWrapper;
	private Object response;

	public void runClient() throws IOException {
		client = new NetworkClient(UDP_PORT, TIMEOUT, TCP_PORT);
		while (!client.findServer(UDP_PORT, TIMEOUT, TCP_PORT)) {
			client.addListener(this);
			environmentWrapper.showMessage("No server. Automatic retry will be in 3 seconds.");
			Sleep.sleepSeconds(3);
		}
		environmentWrapper.showMessage("Found server. Start.");
	}

	public void runServer() throws IOException {
		server = new NetworkServer(TCP_PORT, UDP_PORT);
		server.addListener(this);
	}

	public void init(boolean isServer, Core core, EnvironmentWrapperI environmentWrapper) {
		this.isServer = isServer;
		this.core = core;
		this.environmentWrapper = environmentWrapper;
		try {
			if (isServer) {
				runServer();
			} else {
				runClient();
			}
		} catch (IOException e) {
			environmentWrapper.showMessage("Something is wrong.");
		}
	}

	public void received(Connection connection, Object object) {
		if (isServer) {
			if (object instanceof hu.bme.sch.parkettklub.smartcheckroom.core.network.Request) {
				switch (((hu.bme.sch.parkettklub.smartcheckroom.core.network.Request) object).type) {
				case ADD_ITEM:

					break;
				}
			}
		} else {
			if (object instanceof DBResponse) {

			}
			if (object instanceof Item) {

			}
			if (object instanceof Integer) {

			}
		}
	}

	public DBResponse addItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	public DBResponse updateItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	public Item getItem(BarcodeNumber barcodeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public DBResponse deleteItem(BarcodeNumber barcodeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Long> getFreeCheckRoomNumbers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Item> listAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> listAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> listMyTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() {
		if (null != server) {
			server.close();
			server = null;
		}
		if (null != client) {
			client.close();
			client = null;
		}
	}
}