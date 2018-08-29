package hu.bme.sch.parkettklub.smartcheckroom.core;

import java.util.ArrayList;
import java.util.List;

import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.BarcodeNumber;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.DBResponse;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Item;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Transaction;
import hu.bme.sch.parkettklub.smartcheckroom.core.network.NetworkDriver;

public class Core {

	private static final Core CORE = new Core();
	private DataBaseDriverI dataBaseDriver;
	private EnvironmentWrapperI environmentWrapper;
	private NetworkDriver networkDriver;
	private boolean isServer;

	public static Core getCore() {
		return CORE;
	}

	public static Core createCore(DataBaseDriverI dataBaseDriver, EnvironmentWrapperI environmentWrapper) {
		if (CORE.environmentWrapper == null) {
			CORE.dataBaseDriver = dataBaseDriver;
			CORE.environmentWrapper = environmentWrapper;
			CORE.isServer = dataBaseDriver != null;
			CORE.init();
		}
		return CORE;
	}

	private Core() {

	}

	private void init() {
		dataBaseDriver.init();
		networkDriver.init(isServer, CORE, environmentWrapper);
	}

	public void resetDataBase() {
		if (isServer) {
			dataBaseDriver.deleteAllCheckRoomItems();
		}
	}

	public DBResponse addItem(Item item) {
		if (isServer) {
			return dataBaseDriver.addItem(item);
		} else {
			return networkDriver.addItem(item);
		}
	}

	public DBResponse updateItem(Item item) {
		if (isServer) {
			return dataBaseDriver.updateItem(item);
		} else {
			return networkDriver.updateItem(item);
		}
	}

	public Item getItem(BarcodeNumber barcodeNumber) {
		if (isServer) {
			return dataBaseDriver.getItem(barcodeNumber);
		} else {
			return networkDriver.getItem(barcodeNumber);
		}
	}

	public DBResponse deleteItem(BarcodeNumber barcodeNumber) {
		if (isServer) {
			return dataBaseDriver.deleteItem(barcodeNumber);
		} else {
			return networkDriver.deleteItem(barcodeNumber);
		}
	}

	public ArrayList<Long> getFreeCheckRoomNumbers() {
		if (isServer) {
			return dataBaseDriver.getFreeCheckRoomNumbers();
		} else {
			return networkDriver.getFreeCheckRoomNumbers();
		}
	}

	public List<Item> listAllItems() {
		if (isServer) {
			return dataBaseDriver.listAllItems();
		} else {
			return networkDriver.listAllItems();
		}
	}

	public List<Transaction> listAllTransactions() {
		if (isServer) {
			return dataBaseDriver.listAllTransactions();
		} else {
			return networkDriver.listAllTransactions();
		}
	}

	public List<Transaction> listMyTransactions() {
		if (isServer) {
			return dataBaseDriver.listMyTransactions(0);
		} else {
			return networkDriver.listMyTransactions();
		}
	}

	public List<Transaction> listTransactions(int id) {
		return dataBaseDriver.listMyTransactions(id);
	}

	public void close() {
		dataBaseDriver.close();
		networkDriver.close();
	}
}