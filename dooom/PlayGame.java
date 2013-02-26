import java.io.*;

class PlayGame {

	private static String[] promptUser() throws Exception {
		OutputHandler.addToOutput("> ");
		OutputHandler.printOutput();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		OutputHandler.clearScreen();
		String[] returnArray = {input, GameLogic.processCommand(input)};
		return returnArray;
	}

	public static void main(String[] args) throws Exception {
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