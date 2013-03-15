import java.io.*;
import java.net.*;

public class Client {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java

	public static void main(String[] args) {
		
		try {
			Socket echoSocket;
			switch (args.length) {
				case 1 :	echoSocket = new Socket(args[0], 50898);
							break;
							
				case 2 :	echoSocket = new Socket(args[0], Integer.parseInt(args[1]));
							break;
							
				default:	echoSocket = new Socket("localhost", 50898);
							break;
			}
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;
			
			(new Thread(new Listener(out, stdIn))).start();
			
			while ((fromServer = in.readLine()) != null) {
				System.out.println(fromServer);
			}
			out.close();
			in.close();
			stdIn.close();
			echoSocket.close();
			
		}
		catch (ConnectException e) {
			System.err.println("Error: Could not connect. " + e.getMessage());
			System.exit(-1);
		}
		catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}
		
	}
	
}