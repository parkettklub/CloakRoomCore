package hu.bme.sch.parkettklub.smartcheckroom.core.network;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Item;
import hu.bme.sch.parkettklub.smartcheckroom.core.dtos.Transaction;

public class NetworkServer extends Server {
	public NetworkServer(int tcpPort, int udpPort) throws IOException {
		Kryo kryo = getKryo();
		kryo.register(Item.class);
		kryo.register(Transaction.class);
		kryo.register(Date.class);
		kryo.register(Time.class);
		start();
		bind(tcpPort, udpPort);
	}
}