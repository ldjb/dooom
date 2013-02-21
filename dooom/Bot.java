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

	public static char relSymbol(int y, int x) {
		return Map.symbolAt(GameLogic.playerCoords[0]+y, GameLogic.playerCoords[1]+x);
	}

	public static String[] nextCommand() throws Exception {
		if (Map.symbolAt(GameLogic.playerCoords[0], GameLogic.playerCoords[1]) == desire) {
			return sendCommand("PICKUP");
		}
		while (true) {
			int possibleMoves = 1;
			while (true) {
				String possibilities = "";
				if (possibleMoves == 1) {
					if (relSymbol(-1, 0) == desire) {
						possibilities += "N";
					}
					if (relSymbol(1, 0) == desire) {
						possibilities += "S";
					}
					if (relSymbol(0, 1) == desire) {
						possibilities += "E";
					}
					if (relSymbol(0, -1) == desire) {
						possibilities += "W";
					}
				}
				if (possibleMoves == 2) {
					if (relSymbol(-1, -1) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
					if (relSymbol(-1, 1) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(1, 1) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(1, -1) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
					if (relSymbol(-2, 0) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
					}
					if (relSymbol(0, 2) == desire) {
						if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(2, 0) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
					}
					if (relSymbol(0, -2) == desire) {
						if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
				}
				if (possibleMoves == 3) {
					if (relSymbol(-2, 1) == desire) {
						if (relSymbol(-2, 0) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
						}
						if (relSymbol(-1, 1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(-1, 2) == desire) {
						if (relSymbol(0, 2) != '#') {
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
						if (relSymbol(-1, 1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(1, 2) == desire) {
						if (relSymbol(0, 2) != '#') {
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
						if (relSymbol(1, 1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(2, 1) == desire) {
						if (relSymbol(2, 0) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
						}
						if (relSymbol(1, 1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					// still four more
				}
				if (possibleMoves == 4) {
					break;
				}
				if (possibilities.length() > 0) {
					return sendCommand("MOVE " + possibilities.charAt((int) (Math.random() * possibilities.length())));
				}
				possibleMoves++;
			}
			if (desire == 'E') {
				desire = 'G';
			}
			else if (desire == 'G') {
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