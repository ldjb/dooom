import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java

	Socket clientSocket;
	
	static ServerSocket serverSocket;
	
	static DODMap map;
	
	static ArrayList<Socket> arrSockets = new ArrayList<Socket>();

	public Server(Socket client) {
		clientSocket = client;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine, outputLine;
			Protocol protocol = new Protocol(map);
			while ((inputLine = in.readLine()) != null) {
				// keep getting input from user and send back a response
				 outputLine = protocol.playerCommand(inputLine);
				 out.println(outputLine);
			}
			out.close();
			in.close();
			clientSocket.close();
			arrSockets.remove(clientSocket);
			if (arrSockets.size() == 0) { // if this is the last client, close socket
				serverSocket.close();
			}
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
	}

	public static void globalMessage(String msg) {
		for (Socket client : arrSockets) {
			try {
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				out.println(msg);
			}
			catch (Exception e) {
				System.err.println("Error: " + e);
				System.exit(-1);
			}
		}
	}

    public static void main(String[] args) {
    
		try {
        	if (args.length > 0) { // port has been specified
        		serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        	}
			else {
				serverSocket = new ServerSocket(50898);
			}
        }
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}

		Socket client = null;
		
		map = new DODMap();
		
		try {
			do { // keep repeating while there are clients connected
				try {
					client = serverSocket.accept();
					arrSockets.add(client);
				}
				catch (SocketException e) { // if socket has closed, quit program
					System.exit(0);
				}
				(new Thread(new Server(client))).start();
			} while (arrSockets.size() > 0);
			serverSocket.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
    
    }
}