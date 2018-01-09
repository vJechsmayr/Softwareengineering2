package prims;

import inout.Input;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private static String server;
	private static int port;
	private static Socket socket = null;
	private static MessageWrt writer;
	private static MessageRd reader;
	private static final Object syncObj = new Object();
	private static Buffer buffer = new Buffer();
	
	public static void main(String[] args) throws IOException{
		
		readServerData();
		connect();
		
		reader = new MessageRd(socket, buffer);
		writer = new MessageWrt(socket, buffer);
		
		reader.setSync(syncObj);
		writer.setSync(syncObj);
		
		reader.start();
		writer.start();
		
		reader.interrupt();
		writer.interrupt();
		
		try
		{
			reader.join();
			writer.join();
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		socket.close();
	}
	
	private static void connect() {
		try{
			socket = new Socket(server, port);
		}catch(Exception ex)
		{
			System.out.println("Server not found!");
			System.exit(0);
		}
		
		System.out.println("++ Connected to server");
		System.out.println("Enter 'x' to exit");
	}

	private static void readServerData()
	{
		System.out.println(">java Client");
		System.out.println("Server IP: ");
		server = Input.readString();
		System.out.println("Port number: ");
		port = Input.readInt();
	}
}
