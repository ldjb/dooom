class Bot {

	private static char desire = 'G';

	public static String[] sendCommand(String command) throws Exception {
		OutputHandler.addToOutput("> " + command);
		OutputHandler.printOutput();
		System.in.read();
		OutputHandler.clearScreen();
		String[] returnArray = {command, GameLogic.processCommand(command)};
		return returnArray;
	}
	
	public static void updateDesire() {
		if (GameLogic.getGold() < GameLogic.getWin()) {
			desire = 'G';
		}
		else {
			desire = 'E';
		}
	}

	public static String[] nextCommand() throws Exception {
		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]) == desire) {
			return sendCommand("PICKUP");
		}
		while (true) {
			if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]) == desire) {
				return sendCommand("MOVE N");
			}
			if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]) == desire) {
				return sendCommand("MOVE S");
			}
			if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+1) == desire) {
				return sendCommand("MOVE E");
			}
			if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-1) == desire) {
				return sendCommand("MOVE W");
			}
			if (desire != '.') {
				desire = '.';
			}
			else {
				break;
			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]-1) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]+1) == desire) {
// 				return sendCommand("MOVE N");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]-1) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]+1) == desire) {
// 				return sendCommand("MOVE S");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]-2, GameLogic.playerCoords[1]) == desire) {
// 				return sendCommand("MOVE N");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]+2, GameLogic.playerCoords[1]) == desire) {
// 				return sendCommand("MOVE S");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+2) == desire) {
// 				return sendCommand("MOVE E");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-2) == desire) {
// 				return sendCommand("MOVE W");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]-2, GameLogic.playerCoords[1]-1) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]-2, GameLogic.playerCoords[1]+1) == desire) {
// 				return sendCommand("MOVE N");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]+2, GameLogic.playerCoords[1]-1) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]+2, GameLogic.playerCoords[1]+1) == desire) {
// 				return sendCommand("MOVE S");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]+2) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]+2) == desire) {
// 				return sendCommand("MOVE E");
// 			}
// 			if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]-2) == desire
// 			  | Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]-2) == desire) {
// 				return sendCommand("MOVE W");
// 			}
		}
		while (true) {
			double random = Math.random();
			if (random < 0.25) {
				if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]) != '#') {
					return sendCommand("MOVE N");
				}
			}
			else if (random < 0.5) {
				if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]) != '#') {
					return sendCommand("MOVE S");
				}
			}
			else if (random < 0.75) {
				if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+1) != '#') {
					return sendCommand("MOVE E");
				}
			}
			else {
				if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-1) != '#') {
					return sendCommand("MOVE W");
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		OutputHandler.clearScreen();
		GameLogic.init();
		String[] inputOutput = {null, null};
		while (true) {
			updateDesire();
			OutputHandler.addToOutput("DUNGEON OF DOOOM\n");
			OutputHandler.addToOutput(GameLogic.getMapName() + " (" + Math.max(0, GameLogic.getWin() - GameLogic.getGold()) + ")\n\n");
			GameLogic.printMap('B');
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
			inputOutput = nextCommand();
		}
	}
	
}