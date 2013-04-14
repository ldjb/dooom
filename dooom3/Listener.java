import java.io.*;
import java.net.*;

public class Listener implements Runnable {

// 	private Socket serverSocket;
// 	private BufferedReader in;

	private GUI gui;
	private BufferedReader fromServer;

	public Listener(GUI x, BufferedReader fs) {
		gui = x;
		fromServer = fs;
		
// 		try {
// 			serverSocket = new Socket("localhost", 50898);
// 			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));		
// 		}
// 		catch (Exception e) {
// 			System.err.println("Error: " + e);
// 			System.exit(-1);
// 		}
		
	}
	
	public void run() {
		try {
			String line;
			
			// keep printing data received from server
			while ((line = fromServer.readLine()) != null) {
				System.out.println(line);
			}
// 			in.close();
// 			serverSocket.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
	}

}