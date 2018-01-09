package net3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketsServerDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(12345);

		// /////////////////////////////////
		// Version 3: calling accept repeatedly

		while (true) {
			Socket socket = null;
			PrintWriter writer = null;
			BufferedReader reader = null;
			try {
				System.out.println("Waiting for client requests... ");
				socket = server.accept();
				System.out.println("Connected to client ");
				
				writer = new PrintWriter(socket.getOutputStream());
				writer.println("Hello World!");
				writer.println("Line 2!");
				writer.flush();
				
				socket.shutdownOutput();
				
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String readLine = reader.readLine();
				System.out.println(readLine);
				
			} finally {
				System.out.println("Closing stuff");
				writer.close();
				reader.close();
				socket.close();
			}
		}

	}

}
