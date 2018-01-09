package net1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketsServerDemo {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(12345);

		// /////////////////////////////////
		// Version 1: simple
		System.out.println("Waiting for client requests... ");
		Socket socket = server.accept();
		System.out.println("Connected to client ");
		socket.getOutputStream().write("Hello World!\n".getBytes());
		socket.getOutputStream().flush();
		socket.getOutputStream().write("Line 2\n".getBytes());
		socket.getOutputStream().flush();
		socket.close();
		server.close();

	}

}
