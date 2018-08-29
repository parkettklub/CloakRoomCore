package hu.bme.sch.parkettklub.smartcheckroom.core.network;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Time;
import java.util.Date;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Item;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Transaction;

public class NetworkClient extends Client {

	public NetworkClient(int udpPort, int timeout, int tcpPort) {
		Kryo kryo = getKryo();
		kryo.register(Item.class);
		kryo.register(Transaction.class);
		kryo.register(Date.class);
		kryo.register(Time.class);
		start();
	}

	public boolean findServer(int udpPort, int timeout, int tcpPort) throws IOException {
		InetAddress address = discoverHost(udpPort, timeout);
		if (null != address) {
			connect(timeout, address.getHostAddress(), tcpPort, udpPort);
			return true;
		} else {
			return false;
		}
	}

	public int sendData(Object request) {
		return sendTCP(request);
	}
}