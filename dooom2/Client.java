import java.io.*;
import java.net.*;

public class Client {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java

	public static void main(String[] args) {
		
		try {
			Socket serverSocket;
			switch (args.length) { // check number of command-line arguments
				case 1 :	serverSocket = new Socket(args[0], 50898); // hostname has been specified
							break;
							
				case 2 :	serverSocket = new Socket(args[0], Integer.parseInt(args[1])); // both hostname and port have been specified
							break;
							
				default:	serverSocket = new Socket("localhost", 50898);
							break;
			}
			PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			if (args.length < 3) {
				// human player
				(new Thread(new Listener(out, stdIn))).start();
			}
			else {
				// bot player doesn't need stdIn
				(new Thread(new Listener(out))).start();
			}
			
			// keep printing data received from server
			while ((fromServer = in.readLine()) != null) {
				System.out.println(fromServer);
			}
			out.close();
			in.close();
			stdIn.close();
			serverSocket.close();
			
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
		
	}
	
}