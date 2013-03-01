import java.io.*;

class PlayGame {

	private static String[] promptUser() {
		OutputHandler.addToOutput("> ");
		OutputHandler.printOutput();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
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
		OutputHandler.clearScreen();
		GameLogic.init();
		String[] inputOutput = {null, null};
		while (true) {
			OutputHandler.addToOutput("DUNGEON OF DOOOM\n");
			OutputHandler.addToOutput(GameLogic.getMapName() + " (" + Math.max(0, GameLogic.getWin() - GameLogic.getGold()) + ")\n\n");
			GameLogic.printMap('P');
			OutputHandler.addToOutput("\n");
			if (inputOutput[0] != null) {
				OutputHandler.addToOutput("> " + inputOutput[0] + "\n");
				OutputHandler.addToOutput(inputOutput[1] + "\n");
				OutputHandler.printOutput();
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
