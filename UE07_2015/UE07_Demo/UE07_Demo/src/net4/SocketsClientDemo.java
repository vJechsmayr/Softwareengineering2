package net4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketsClientDemo {
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		///////////////////////////////////
		// client socket demo
		
		Socket socket = new Socket("localhost", 12345);
		
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		
			String line;
			while ( (line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("OK"); 
			writer.flush(); 
			
		} finally {
			socket.close();
		}
	}
}
