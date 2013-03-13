import java.io.*;
import java.net.*;

public class Server {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java

    public static void main(String[] args) throws IOException {
    
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(50898);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        String inputLine, outputLine;

		Protocol protocol = new Protocol();

		outputLine = protocol.playerCommand("look");
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
             outputLine = protocol.playerCommand(inputLine);
             out.println(outputLine);
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    
    }
}
