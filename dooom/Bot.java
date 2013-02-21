class Bot {

	private static char desire = 'G';
	private static char[][] mapData;

	public static void markAsSeen() {
		if (relSymbol(0, 0) != 'E') {
			mapData[GameLogic.getCoords()[0]][GameLogic.getCoords()[1]] = 'S';
		}
	}

	public static void initMap() {
		mapData = new char[Map.getSize()[0]][Map.getSize()[1]];
		markAsSeen();
	}

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
		if (GameLogic.getCoords()[0]+y < 0 | GameLogic.getCoords()[0]+y >= Map.getSize()[0] | GameLogic.getCoords()[1]+x < 0 | GameLogic.getCoords()[1]+x >= Map.getSize()[1]) {
			return '#';
		}
		if (mapData[GameLogic.getCoords()[0]+y][GameLogic.getCoords()[1]+x] != '\u0000') {
			return mapData[GameLogic.getCoords()[0]+y][GameLogic.getCoords()[1]+x];
		}
		return Map.symbolAt(GameLogic.getCoords()[0]+y, GameLogic.getCoords()[1]+x);
	}

	public static String[] nextCommand() throws Exception {
		if (relSymbol(0, 0) == desire) {
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
					if (relSymbol(2, -1) == desire) {
						if (relSymbol(2, 0) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
						}
						if (relSymbol(1, -1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(1, -2) == desire) {
						if (relSymbol(0, -2) != '#') {
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
						if (relSymbol(1, -1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(-1, -2) == desire) {
						if (relSymbol(0, -2) != '#') {
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
						if (relSymbol(-1, -1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(-2, -1) == desire) {
						if (relSymbol(-2, 0) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
						}
						if (relSymbol(-1, -1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
				}
				if (possibleMoves == 4) {
					break;
				}
				if (possibilities.length() > 0) {
					markAsSeen();
					return sendCommand("MOVE " + possibilities.charAt((int) (Math.random() * possibilities.length())));
				}
				possibleMoves++;
			}
			if (desire == 'E') { // not sure about this whole part
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
				if (relSymbol(-1, 0) != '#') {
					markAsSeen();
					return sendCommand("MOVE N");
				}
			}
			else if (random < 0.5) {
				if (relSymbol(1, 0) != '#') {
					markAsSeen();
					return sendCommand("MOVE S");
				}
			}
			else if (random < 0.75) {
				if (relSymbol(0, 1) != '#') {
					markAsSeen();
					return sendCommand("MOVE E");
				}
			}
			else {
				if (relSymbol(0, -1) != '#') {
					markAsSeen();
					return sendCommand("MOVE W");
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		OutputHandler.clearScreen();
		GameLogic.init();
		initMap();
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