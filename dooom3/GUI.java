import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class GUI extends JFrame {

	private GUI() {

		// initialise interface

		leftScrollPane = new JScrollPane();
		rightScrollPane = new JScrollPane();
		leftTextArea = new JTextArea();
		rightTextArea = new JTextArea();
		textField = new JTextField();
		heading = new JLabel();
		goldIndicator = new JLabel();
		submitButton = new JButton();

		menuBar = new JMenuBar();
			gameMenu = new JMenu();
				localGameMenuItem = new JMenuItem();
				networkGameMenuItem = new JMenuItem();
				quitMenuItem = new JMenuItem();
			helpMenu = new JMenu();
				aboutMenuItem = new JMenuItem();
				webMenuItem = new JMenuItem();

		buttonPanel = new JPanel();
		moveNButton = new JButton();
		moveSButton = new JButton();
		moveWButton = new JButton();
		moveEButton = new JButton();
		attackWButton = new JButton();
		attackNButton = new JButton();
		attackEButton = new JButton();
		attackSButton = new JButton();
		swordIcon = new JLabel();
		shoeIcon = new JLabel();
		pickupButton = new JButton();
		endturnButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(640, 480));

		leftTextArea.setBackground(Color.BLACK);
		leftTextArea.setDragEnabled(false);
		leftTextArea.setEditable(false);
		leftTextArea.setFocusable(false);
		leftTextArea.setFont(new Font("Monospaced", 1, 18));
		leftTextArea.setForeground(Color.GREEN);
		leftScrollPane.setViewportView(leftTextArea);

		rightTextArea.setBackground(Color.BLACK);
		rightTextArea.setDragEnabled(false);
		rightTextArea.setEditable(false);
		rightTextArea.setFocusable(false);
		rightTextArea.setFont(new Font("Monospaced", 1, 18));
		rightTextArea.setForeground(Color.GREEN);
		rightScrollPane.setViewportView(rightTextArea);

		textField.setEditable(false);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted();
			}
		});

		heading.setFont(new Font("Serif", 0, 24));
		heading.setHorizontalAlignment(SwingConstants.CENTER);

		submitButton.setEnabled(false);
		submitButton.setText("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted();
			}
		});

		gameMenu.setText("Game");

		localGameMenuItem.setText("Start single player game");
		localGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SINGLE_PLAYER = true;
				newGame();
			}
		});
		gameMenu.add(localGameMenuItem);
		
		networkGameMenuItem.setText("Start network game");
		networkGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SINGLE_PLAYER = false;
				newGame();
			}
		});
		gameMenu.add(networkGameMenuItem);

		quitMenuItem.setText("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		gameMenu.add(quitMenuItem);

		menuBar.add(gameMenu);

		helpMenu.setText("Help");

		aboutMenuItem.setText("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aboutDialogue();
			}
		});
		helpMenu.add(aboutMenuItem);

		webMenuItem.setText("Online help");
		webMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				launchWebpage();
			}
		});
		helpMenu.add(webMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		buttonPanel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		moveNButton.setEnabled(false);
		moveNButton.setText("↑");
		moveNButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE N");
			}
		});

		moveSButton.setEnabled(false);
		moveSButton.setText("↓");
		moveSButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE S");
			}
		});

		moveWButton.setEnabled(false);
		moveWButton.setText("←");
		moveWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE W");
			}
		});

		moveEButton.setEnabled(false);
		moveEButton.setText("→");
		moveEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE E");
			}
		});

		attackWButton.setEnabled(false);
		attackWButton.setText("←");
		attackWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK W");
			}
		});

		attackNButton.setEnabled(false);
		attackNButton.setText("↑");
		attackNButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK N");
			}
		});

		attackEButton.setEnabled(false);
		attackEButton.setText("→");
		attackEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK E");
			}
		});

		attackSButton.setEnabled(false);
		attackSButton.setText("↓");
		attackSButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK S");
			}
		});

		swordIcon.setIcon(new ImageIcon("resources/swords.png")); // http://commons.wikimedia.org/wiki/File:Swords.svg
		shoeIcon.setIcon(new ImageIcon("resources/shoe.png")); // http://www.clker.com/clipart-shoe-print-24.html

		pickupButton.setEnabled(false);
		pickupButton.setText("Pick up");
		pickupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("PICKUP");
			}
		});

		endturnButton.setEnabled(false);
		endturnButton.setText("End turn");
		endturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ENDTURN");
			}
		});

		GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(buttonPanelLayout);
		buttonPanelLayout.setHorizontalGroup(
			buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(buttonPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(moveWButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(buttonPanelLayout.createSequentialGroup()
						.addComponent(shoeIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(moveEButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addComponent(moveSButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(moveNButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(buttonPanelLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(attackWButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGroup(buttonPanelLayout.createSequentialGroup()
						.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(pickupButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(endturnButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 68, Short.MAX_VALUE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(attackNButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(attackSButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(buttonPanelLayout.createSequentialGroup()
						.addComponent(swordIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(attackEButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		buttonPanelLayout.setVerticalGroup(
			buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(buttonPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(moveNButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(attackNButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(pickupButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(shoeIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(moveWButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(moveEButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(attackWButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addComponent(swordIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(attackEButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(moveSButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(attackSButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(endturnButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
			);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(heading, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addComponent(goldIndicator))
					.addGroup(layout.createSequentialGroup()
						.addComponent(leftScrollPane, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(rightScrollPane, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
					.addGroup(layout.createSequentialGroup()
						.addComponent(textField)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(submitButton))
					.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(heading)
					.addComponent(goldIndicator, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(rightScrollPane, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addComponent(leftScrollPane))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(submitButton))
				.addContainerGap())
		);

		pack();
		
		setTitle("Dungeon of Dooom");
		targetField = "rightTextArea";

	}

	private void formSubmitted() {
		if (!textField.getText().equals("")) {
			if (SINGLE_PLAYER) {
			rightTextArea.setText(GameLogic.processCommand(textField.getText()));
			textField.setText(null);
			leftTextArea.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
			goldIndicator.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
			if (GameLogic.getGold() >= GameLogic.getWin()) {
				goldIndicator.setFont(new Font(goldIndicator.getFont().getName(), Font.BOLD, goldIndicator.getFont().getSize()));
			}
			if (rightTextArea.getText().startsWith("Congrat")) {
				textField.setEditable(false);
				submitButton.setEnabled(false);
				moveNButton.setEnabled(false);
				moveSButton.setEnabled(false);
				moveWButton.setEnabled(false);
				moveEButton.setEnabled(false);
				attackWButton.setEnabled(false);
				attackNButton.setEnabled(false);
				attackEButton.setEnabled(false);
				attackSButton.setEnabled(false);
				pickupButton.setEnabled(false);
				endturnButton.setEnabled(false);
				this.requestFocusInWindow();
			}
			}
			else {
				formSubmitted("SHOUT " + textField.getText());
				textField.setText(null);
			}
		}
	}

	private void formSubmitted(String command) {
		if (SINGLE_PLAYER) {
		rightTextArea.setText(GameLogic.processCommand(command));
		leftTextArea.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
		goldIndicator.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
		if (GameLogic.getGold() >= GameLogic.getWin()) {
			goldIndicator.setFont(new Font(goldIndicator.getFont().getName(), Font.BOLD, goldIndicator.getFont().getSize()));
		}
		if (rightTextArea.getText().startsWith("Congrat")) {
			textField.setEditable(false);
			submitButton.setEnabled(false);
			moveNButton.setEnabled(false);
			moveSButton.setEnabled(false);
			moveWButton.setEnabled(false);
			moveEButton.setEnabled(false);
			attackWButton.setEnabled(false);
			attackNButton.setEnabled(false);
			attackEButton.setEnabled(false);
			attackSButton.setEnabled(false);
			pickupButton.setEnabled(false);
			endturnButton.setEnabled(false);
			this.requestFocusInWindow();
		}
		}
		else {
			toServer.println(command);
			toServer.println("LOOK");
		}
	}
	
	private void aboutDialogue() {
		JOptionPane.showMessageDialog(this, "<html><div style='text-align: center;'><b>Dungeon of Dooom v3.3</b><br><br>Developed by Leon Byford, 2013.<br>&lt;ldjb20@bath.ac.uk&gt;<br><br>Utilises modified code originally generated by NetBeans.<br><br>Some graphical elements by Joel Gustavsson et al., Manuel Lopez, Palosirkka et al..</div></html>", "About", JOptionPane.PLAIN_MESSAGE);
	}

	private void launchWebpage() {
		try {
			Desktop.getDesktop().browse(new URI("http://people.bath.ac.uk/ldjb20/dooom/"));
		}
		catch (Exception e) {
		}
	}

	public void newGame() {
		if (SINGLE_PLAYER) {
			JFileChooser fc = new JFileChooser("maps");
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				localGameMenuItem.setEnabled(false);
				networkGameMenuItem.setEnabled(false);
				GameLogic.init(fc.getSelectedFile().toString());
				heading.setText(GameLogic.getMapName());
				leftTextArea.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
				goldIndicator.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
				if (GameLogic.getGold() >= GameLogic.getWin()) {
					goldIndicator.setFont(new Font(goldIndicator.getFont().getName(), Font.BOLD, goldIndicator.getFont().getSize()));
				}
				goldIndicator.setIcon(new ImageIcon("resources/gold.png")); // http://www.iconfinder.com/icondetails/65599/48/cash_gold_money_payment_icon
				textField.setEditable(true);
				submitButton.setEnabled(true);
				moveNButton.setEnabled(true);
				moveSButton.setEnabled(true);
				moveWButton.setEnabled(true);
				moveEButton.setEnabled(true);
				attackWButton.setEnabled(true);
				attackNButton.setEnabled(true);
				attackEButton.setEnabled(true);
				attackSButton.setEnabled(true);
				pickupButton.setEnabled(true);
				endturnButton.setEnabled(true);
			}
		}
		else {
			localGameMenuItem.setEnabled(false);
			networkGameMenuItem.setEnabled(false);
			goldIndicator.setIcon(new ImageIcon("resources/gold.png")); // http://www.iconfinder.com/icondetails/65599/48/cash_gold_money_payment_icon
			textField.setEditable(true);
			submitButton.setEnabled(true);
			moveNButton.setEnabled(true);
			moveSButton.setEnabled(true);
			moveWButton.setEnabled(true);
			moveEButton.setEnabled(true);
			attackWButton.setEnabled(true);
			attackNButton.setEnabled(true);
			attackEButton.setEnabled(true);
			attackSButton.setEnabled(true);
			pickupButton.setEnabled(true);
			endturnButton.setEnabled(true);

			try {
				serverSocket = new Socket("localhost", 50898);
				fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				toServer = new PrintWriter(serverSocket.getOutputStream(), true);
				(new Thread(new Listener(this, fromServer))).start();
				toServer.println("HELLO");
				toServer.println("LOOK");
			}
			catch (IOException e) {
				System.err.println("Error: " + e);
				System.exit(-1);
			}
		}
	}

	public void parseResponse(String text) {
		if (text.equals("LOOKREPLY")) {
			targetField = "leftTextArea";
			leftTextArea.setText(null);
		}
		else if (text.equals("SUCCESS") || text.equals("FAIL")) {
			targetField = "rightTextArea";
			rightTextArea.setText(null);
		}
		else if (text.equals("UPDATE")) {
			toServer.println("LOOK");
		}
		else if (text.equals("ENDGAME")) {
			textField.setEditable(false);
			submitButton.setEnabled(false);
			moveNButton.setEnabled(false);
			moveSButton.setEnabled(false);
			moveWButton.setEnabled(false);
			moveEButton.setEnabled(false);
			attackWButton.setEnabled(false);
			attackNButton.setEnabled(false);
			attackEButton.setEnabled(false);
			attackSButton.setEnabled(false);
			pickupButton.setEnabled(false);
			endturnButton.setEnabled(false);
			if (gold >= goal) {
				rightTextArea.setText("You win!");
			}
			else {
				rightTextArea.setText("You lose!");
			}
		}
		else if (text.startsWith("HELLO ")) {
			heading.setText(text.substring(6));
		}
		else if (text.startsWith("GOAL ")) {
			goal = Integer.parseInt(text.substring(5));
			goldIndicator.setText("0 / " + text.substring(5));
		}
		else if (text.startsWith("TREASUREMOD ")) {
			gold += Integer.parseInt(text.substring(12));
			goldIndicator.setText(Integer.toString(gold) + " / " + Integer.toString(goal));
		}
		else if (text.startsWith("MESSAGE ")) {
			rightTextArea.append("\"" + text.substring(8) + "\"\n");
		}
		else if (text.startsWith("ERROR ")) {
			rightTextArea.setText(text.substring(6) + "\n");
		}
		else if (targetField.equals("leftTextArea")) {
			leftTextArea.append(text + "\n");
		}
		else {
			rightTextArea.append(text + "\n");
		}
	}

	public static void main(String args[]) {
		new GUI().setVisible(true);
	}

	// Variables declaration
	private JButton submitButton;
	private JLabel heading;
	private JLabel goldIndicator;
	private JMenu gameMenu;
	private JMenu helpMenu;
	private JMenuBar menuBar;
	private JMenuItem localGameMenuItem;
	private JMenuItem networkGameMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem webMenuItem;
	private JScrollPane leftScrollPane;
	private JScrollPane rightScrollPane;
	private JTextArea leftTextArea;
	private JTextArea rightTextArea;
	private JTextField textField;

	private JButton attackSButton;
	private JButton pickupButton;
	private JButton endturnButton;
	private JButton moveNButton;
	private JButton moveSButton;
	private JButton moveWButton;
	private JButton moveEButton;
	private JButton attackWButton;
	private JButton attackNButton;
	private JButton attackEButton;
	private JLabel swordIcon;
	private JLabel shoeIcon;
	private JPanel buttonPanel;
	
	private Socket serverSocket;
	private BufferedReader fromServer;
	private PrintWriter toServer;

	private String targetField;
	private int goal = 0;
	private int gold = 0;
	private boolean SINGLE_PLAYER;

}