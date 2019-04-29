package online;

import java.io.IOException;
import java.awt.Point;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import others.Actor;
import others.AttackedCellInfo;
import others.CellInfo;

public class ServerServices {

	public static final int PORT = 6666;

	private static ServerSocket serversocket = null; // socket of server
	private static Socket client; // socket of client
	private static ObjectInputStream in; // to read incoming stream
	private static ObjectOutputStream out; // to send ( the result of operation)

	public static void startServer() {
		if (serversocket != null)
			return;

		try {

			serversocket = new ServerSocket(PORT); // serversocket is set to PORT=6666
//			System.out.println("The server is listening to port " + PORT);

			client = serversocket.accept(); // client accepts the given serversocket
			System.out.println("The client is conneceted");

			
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());

			


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void stopServer() {
		try {
			client.close();
			serversocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage(boolean flag) {
		try {
			out.writeBoolean(flag);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


	public static CellInfo[][] receiveEnemyMap() {
		CellInfo [][] enemyMap = null;
		
		try {
			enemyMap = (CellInfo [][]) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		System.out.println("The result is: " + enemyMap[5][5]);
		return enemyMap;
	}
	
	public static void sendOwnMap(CellInfo[][] mapInfo) {
		try {
			out.writeObject(mapInfo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Point waitForAttack() {
		Point p = null;
		
		try {
			p = (Point) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	public static void sendAttackInfo(Point p) {
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
