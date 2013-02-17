import java.io.*;

class PlayGame {
	
	private static int[] playerCoords = new int[2];
	private static int gold = 0;
	
	private static void spawnPlayer() {
		int[] mapSize = Map.getSize();
		playerCoords[0] = (int) (Math.random() * (mapSize[0]));
		playerCoords[1] = (int) (Math.random() * (mapSize[1]));
		if (Map.symbolAt(playerCoords[0], playerCoords[1]) == '#') {
			spawnPlayer();
		}
	}

	public static String movePlayer(char direction) {
		String returnValue = "FAIL";
		switch (direction) {
			case 'N':	if (Map.symbolAt(playerCoords[0] - 1, playerCoords[1]) != '#') {
							playerCoords[0]--;
							returnValue = "SUCCESS";
						}
						break;
			case 'S':	if (Map.symbolAt(playerCoords[0] + 1, playerCoords[1]) != '#') {
							playerCoords[0]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'E':	if (Map.symbolAt(playerCoords[0], playerCoords[1] + 1) != '#') {
							playerCoords[1]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'W':	if (Map.symbolAt(playerCoords[0], playerCoords[1] -1) != '#') {
							playerCoords[1]--;
							returnValue = "SUCCESS";
						}
						break;
		}
		if (gold >= Map.getWin() & Map.symbolAt(playerCoords[0], playerCoords[1]) == 'E') {
			return "Congratulations, you win!";
		}
		return returnValue;
	}

	public static String processCommand(String command) {
		if (command.toUpperCase().equals("HELLO")) {
			return "GOLD " + Map.getWin();
		}
		else if (command.toUpperCase().startsWith("MOVE ") & command.length() == 6) {
			return movePlayer(command.toUpperCase().charAt(5));
		}
		else if (command.toUpperCase().equals("PICKUP")) {
			if (Map.symbolAt(playerCoords[0], playerCoords[1]) == 'G') {
				Map.setSymbol(playerCoords[0], playerCoords[1], '.');
				gold++;
				return "SUCCESS, GOLD COINS: " + gold;
			}
			else {
				return "FAIL";
			}
		}
		else if (command.toUpperCase().equals("LOOK")) {
			return Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1])) + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1])) + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]+1)) + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]-1));
		}
		else if (command.toUpperCase().equals("QUIT")) {
			return "Thanks for playing!";
		}
		else if (command.toUpperCase().equals("HELP")) {
			return "Available commands are: ABOUT | HELLO | HELP | LOOK | MOVE [N|S|E|W] | PICKUP | QUIT";
		}
		else if (command.toUpperCase().equals("ABOUT")) {
			return "Developed by Leon Byford (2013) <ldjb20@bath.ac.uk>";
		}
		else {
			return "No such command. Type \"HELP\" for list of available commands.";
		}
	}

	public static String[] promptUser() throws Exception {
		OutputHandler.addToOutput("> ");
		OutputHandler.printOutput();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		OutputHandler.clearScreen();
		String[] returnArray = {input, processCommand(input)};
		return returnArray;
	}

	public static void main(String[] args) throws Exception {
		OutputHandler.clearScreen();
		Map.load();
		spawnPlayer();
		String[] inputOutput = {null, null};
		while (true) {
			OutputHandler.addToOutput("DUNGEON OF DOOOM\n");
			OutputHandler.addToOutput(Map.getName() + " (" + Math.max(0, Map.getWin() - gold) + ")\n\n");
			Map.print(playerCoords[0], playerCoords[1]);
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