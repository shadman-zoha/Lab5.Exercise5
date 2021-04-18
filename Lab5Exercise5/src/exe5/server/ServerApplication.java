package exe5.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerApplication {

	public static void main(String[] args) {
		
		// Server UDP socket runs at this port
		final int serverPort=50001;
		int bufferSize = 1024;
		
		try {
			
			// Instantiate a new DatagramSocket to receive responses from the client
			DatagramSocket serverSocket = new DatagramSocket (serverPort);
			
			// Create buffers to hold receiving data
		    byte receivingDataBuffer[] = new byte[bufferSize];
		    
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
		    System.out.println("Waiting for a client to connect...");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
			
			// Print client sent data
		    String receivedData = new String (inputPacket.getData());
		    System.out.println("Sent from the client: " + receivedData);
			
			WordCount countWord = new WordCount();
			String sendData = countWord.getWordCount(receivedData);
			System.out.println("Number of words in the sentence: " + sendData);
			
			// Create buffer to send result to client
			
			byte sendingDataBuffer[]= sendData.getBytes();
			
			// Get client's address
			InetAddress clientAddress = inputPacket.getAddress(); 
			int clientPort = inputPacket.getPort();
			
			// Create new UDP packet with data to send to the client
			DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer,
					sendingDataBuffer.length, clientAddress, clientPort);
			
			
			// Send the created packet to client
		    serverSocket.send(outputPacket);
		    
		    
		    // Close the socket connection
		    serverSocket.close();
			
			
		
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
	

	}

}