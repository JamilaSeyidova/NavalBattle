package online;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import others.Actor;
import others.CellInfo;

public class ClientServices {
	private static Socket socket;
	private static ObjectInputStream in; // to read incoming stream
	private static ObjectOutputStream out; // to send ( the result of operation)

	
	
	public static void joinServer(String serverIp) {
		try {

			// client socket connects to PORT at specified IP address:
			socket = new Socket(InetAddress.getByName(serverIp), ServerServices.PORT);
//			System.out.println("Client Connected!");

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static boolean receiveMessage() {
		boolean response = false;
		
		try {
			response = in.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The result is: " + response);
		return response;
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
