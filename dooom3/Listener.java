import java.io.*;
import java.net.*;

public class Listener implements Runnable {

	private GUI gui;
	private BufferedReader fromServer;

	public Listener(GUI x, BufferedReader fs) {
		gui = x;
		fromServer = fs;
	}
	
	public void run() {
		try {
			String line;
			
			// keep printing data received from server
			while ((line = fromServer.readLine()) != null) {
				gui.parseResponse(line);
				System.out.println(line);
			}
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
	}

}