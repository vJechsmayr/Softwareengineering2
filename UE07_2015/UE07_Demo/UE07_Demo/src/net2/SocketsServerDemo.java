package net2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketsServerDemo {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(12345);
		// ///////////////////////////////
		// Version 2: using a PrintWriter and shutdownOutput

		System.out.println("Waiting for client requests... ");
		Socket socket = server.accept();
		System.out.println("Connected to client ");
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.println("Hello World!");
		writer.flush();
		
		socket.shutdownOutput(); // required allow client terminate input
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream())); 
		System.out.println(reader.readLine()); 
		socket.close();
		server.close();
	}

}
