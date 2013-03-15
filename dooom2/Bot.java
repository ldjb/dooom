public class Bot {
	
	public static String getCommand() {
	
		String[] commands = {
			"MOVE N",
			"MOVE E",
			"MOVE S",
			"MOVE W",
			"PICKUP"
		};
		
		// randomly choose a command
		return commands[(int) (Math.random() * commands.length)];
	
	}
	
}