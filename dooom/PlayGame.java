import java.io.*;

class PlayGame {

	private static String[] promptUser() {
		OutputHandler.addToOutput("> "); // display prompt
		OutputHandler.printOutput(); // flush output buffer
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			// read input and store input and result in an array
			String input = in.readLine();
			OutputHandler.clearScreen();
			String[] returnArray = {input, GameLogic.processCommand(input)};
			return returnArray;
		}
		catch (IOException e) {
			// in theory should never happen
			System.err.println("Error: Input could not be read.");
			System.exit(-1);
			return null;
		}
	}

	public static void main(String[] args) {
		OutputHandler.clearScreen(); // scroll page contents above viewable area
		GameLogic.init(); // initialise the game
		String[] inputOutput = {null, null};
		while (true) {
			OutputHandler.addToOutput("DUNGEON OF DOOOM\n"); // display name of game
			// display map name and number of remaining gold pieces necessary to collect
			OutputHandler.addToOutput(GameLogic.getMapName() + " (" + Math.max(0, GameLogic.getWin() - GameLogic.getGold()) + ")\n\n");
			GameLogic.printMap('P');
			OutputHandler.addToOutput("\n");
			if (inputOutput[0] != null) {
				// if the user has entered some input (i.e. first time through this loop)
				OutputHandler.addToOutput("> " + inputOutput[0] + "\n");
				OutputHandler.addToOutput(inputOutput[1] + "\n");
				OutputHandler.printOutput();
				// if user wants to quit the game or has won, quit the game
				if (inputOutput[0].toUpperCase().equals("QUIT") | inputOutput[1].startsWith("Congrat")) {
					System.exit(0);
				}
			}
			else {
				OutputHandler.addToOutput("\n\n");
			}
			inputOutput = promptUser();
		}
	}

}
