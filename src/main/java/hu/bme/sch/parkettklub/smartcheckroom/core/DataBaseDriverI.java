package hu.bme.sch.parkettklub.smartcheckroom.core;

import java.util.ArrayList;
import java.util.List;

import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.BarcodeNumber;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.DBResponse;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Item;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Transaction;

public interface DataBaseDriverI {
	public void init();
	//Database handling
	public void deleteAllCheckRoomItems();

	//Item handling
	public ArrayList<Long> getFreeCheckRoomNumbers();
	public DBResponse addItem(Item item);
	public DBResponse updateItem(Item item);
	public Item getItem(BarcodeNumber barcodeNumber);
	public DBResponse deleteItem(BarcodeNumber barcodeNumber);
	
	//Transactions handling
	public void addTransaction(int id, Transaction transaction);
	
	//Get database items
	public List<Item> listAllItems();
	public List<Transaction> listAllTransactions();
	public List<Transaction> listMyTransactions(int id);

	public void close();
}