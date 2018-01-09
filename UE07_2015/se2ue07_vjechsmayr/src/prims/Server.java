package prims;

import inout.Input;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private static int port;
	private static ServerSocket server = null;
	private static Socket socket = null;
	private static MessageRd reader;
	private static final Object syncObj = new Object();
	private static Buffer buffer = new Buffer();
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("> Java Server");
		System.out.println("Port Number: ");
		port = Input.readInt();	
		System.out.println("Waiting for client request");
		
		server = new ServerSocket(port);
		
		socket = server.accept();
		System.out.println("++ Connected to client");
		
		reader = new MessageRd(socket,buffer);

		reader.setSync(syncObj);
		reader.start();
		reader.interrupt();
		
		try
		{
			reader.join();
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		socket.close();
	}
}
