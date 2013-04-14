import java.io.*;
import java.net.*;

public class Speaker implements Runnable {

	private BufferedReader stdIn;

	private GUI gui;
	private PrintWriter toServer;

	public Speaker(GUI x, PrintWriter ts) {
		gui = x;
		toServer = ts;
		
		try {
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
		
	}
	
	public void run() {
		try {
			String fromUser;
			
			while ((fromUser = stdIn.readLine()) != null) {
				toServer.println(fromUser);
			}
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
	}

}