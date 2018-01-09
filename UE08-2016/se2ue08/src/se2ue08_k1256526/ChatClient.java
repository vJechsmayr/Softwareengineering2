package se2ue08_k1256526;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import inout.In;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {

		ChatReader reader;
		ChatWriter writer;
		final Object sync = new Object();
		String serverName;
		int port;
		Socket socket = null;

		System.out.println(">java chat.ChatClient");
		System.out.print("Server IP: ");
		serverName = In.readLine();
		System.out.print("Port: ");
		port = In.readInt();

		try {
			socket = new Socket(serverName, port);
		} catch (ConnectException e) {
			System.out.println("Server not found.");
			System.exit(0);
		}

		System.out.println("++ Connected to server");
		System.out.println("============================================");
		System.out.println("Press 'enter' for input or 'x' to terminate!");
		System.out.println("============================================");

		reader = new ChatReader(socket);
		writer = new ChatWriter(socket);

		reader.setSync(sync);
		writer.setSync(sync);

		reader.start();
		writer.start();

		// interrupt reader and writer, wait for them, and close the socket.
		writer.interrupt();
		reader.interrupt();
		try {
			writer.join();
			reader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		socket.close();

	}
}
