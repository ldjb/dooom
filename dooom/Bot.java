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
			int priority = 0;
			while (true) {
				String possibilities = "";
				if (priority == 0) {
					if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]) == desire) {
						possibilities += "N";
					}
					if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]) == desire) {
						possibilities += "S";
					}
					if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+1) == desire) {
						possibilities += "E";
					}
					if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-1) == desire) {
						possibilities += "W";
					}
				}
				if (priority == 1) {
					if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]-1) == desire) {
						if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-1) != '#') {
							possibilities += "W";
						}
					}
					if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]+1) == desire) {
						if (Map.symbolAt(GameLogic.playerCoords[0]-1, GameLogic.playerCoords[1]) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+1) != '#') {
							possibilities += "E";
						}
					}
					if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]+1) == desire) {
						if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]+1) != '#') {
							possibilities += "E";
						}
					}
					if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]-1) == desire) {
						if (Map.symbolAt(GameLogic.playerCoords[0]+1, GameLogic.playerCoords[1]) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]-1) != '#') {
							possibilities += "W";
						}
					}
				}
				if (priority == 2) {
					break;
				}
				if (possibilities.length() > 0) {
					return sendCommand("MOVE " + possibilities.charAt((int) (Math.random() * possibilities.length())));
				}
				priority++;
			}
			if (desire != '.') {
				desire = '.';
			}
			else {
				break;
			}
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