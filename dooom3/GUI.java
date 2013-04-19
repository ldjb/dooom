import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * Creates new form GUI
	 */
	private GUI() {
		initComponents();
		setTitle("Dungeon of Dooom");
		//newGame();
		targetField = "jTextArea2";
	}

	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		jScrollPane2 = new JScrollPane();
		jTextArea1 = new JTextArea();
		jTextArea2 = new JTextArea();
		jTextField1 = new JTextField();
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jButton1 = new JButton();

		jMenuBar1 = new JMenuBar();
		jMenu1 = new JMenu();
		jMenuItem1 = new JMenuItem();
		jMenuItem1andahalf = new JMenuItem();
		jMenuItem2 = new JMenuItem();
		jMenuItem3 = new JMenuItem();
		jMenuItem4 = new JMenuItem();
		jMenu2 = new JMenu();

		jPanel1 = new JPanel();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();
		jButton7 = new JButton();
		jButton8 = new JButton();
		jButton9 = new JButton();
		jButton10 = new JButton();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jButton11 = new JButton();
		jButton12 = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(640, 480));

		jTextArea1.setBackground(Color.BLACK);
		jTextArea1.setDragEnabled(false);
		jTextArea1.setEditable(false);
		jTextArea1.setFocusable(false);
		jTextArea1.setFont(new Font("Monospaced", 1, 18));
		jTextArea1.setForeground(Color.GREEN);
		jScrollPane1.setViewportView(jTextArea1);

		jTextArea2.setBackground(Color.BLACK);
		jTextArea2.setDragEnabled(false);
		jTextArea2.setEditable(false);
		jTextArea2.setFocusable(false);
		jTextArea2.setFont(new Font("Monospaced", 1, 18));
		jTextArea2.setForeground(Color.GREEN);
		jScrollPane2.setViewportView(jTextArea2);

		jTextField1.setEditable(false);
		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted();
			}
		});

		jLabel1.setFont(new Font("Serif", 0, 24)); // NOI18N
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jButton1.setEnabled(false);
		jButton1.setText("Submit");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted();
			}
		});

		jMenu1.setText("Game");

		jMenuItem1.setText("Start single player game");
		jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SINGLE_PLAYER = true;
				newGame();
			}
		});
		jMenu1.add(jMenuItem1);
		
		jMenuItem1andahalf.setText("Start network game");
		jMenuItem1andahalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SINGLE_PLAYER = false;
				newGame();
			}
		});
		jMenu1.add(jMenuItem1andahalf);

		jMenuItem2.setText("Quit");
		jMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Help");

		jMenuItem3.setText("About");
		jMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aboutDialogue();
			}
		});
		jMenu2.add(jMenuItem3);

		jMenuItem4.setText("Online help");
		jMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				launchWebpage();
			}
		});
		jMenu2.add(jMenuItem4);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		jPanel1.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jButton3.setEnabled(false);
		jButton3.setText("↑");
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE N");
			}
		});

		jButton4.setEnabled(false);
		jButton4.setText("↓");
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE S");
			}
		});

		jButton5.setEnabled(false);
		jButton5.setText("←");
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE W");
			}
		});

		jButton6.setEnabled(false);
		jButton6.setText("→");
		jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("MOVE E");
			}
		});

		jButton7.setEnabled(false);
		jButton7.setText("←");
		jButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK W");
			}
		});

		jButton8.setEnabled(false);
		jButton8.setText("↑");
		jButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK N");
			}
		});

		jButton9.setEnabled(false);
		jButton9.setText("→");
		jButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK E");
			}
		});

		jButton10.setEnabled(false);
		jButton10.setText("↓");
		jButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ATTACK S");
			}
		});

		jLabel3.setIcon(new ImageIcon("resources/swords.png")); // http://commons.wikimedia.org/wiki/File:Swords.svg
		jLabel4.setIcon(new ImageIcon("resources/shoe.png")); // http://www.clker.com/clipart-shoe-print-24.html

		jButton11.setEnabled(false);
		jButton11.setText("Pick up");
		jButton11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("PICKUP");
			}
		});

		jButton12.setEnabled(false);
		jButton12.setText("End turn");
		jButton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted("ENDTURN");
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(jButton11, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(jButton12, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 68, Short.MAX_VALUE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton10, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton9, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton11, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton9, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(jButton10, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton12, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addComponent(jLabel2))
					.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
					.addGroup(layout.createSequentialGroup()
						.addComponent(jTextField1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton1))
					.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(jLabel1)
					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addComponent(jScrollPane1))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton1))
				.addContainerGap())
		);

		pack();
	}

	private void formSubmitted() {
		if (!jTextField1.getText().equals("")) {
			if (SINGLE_PLAYER) {
			jTextArea2.setText(GameLogic.processCommand(jTextField1.getText()));
			jTextField1.setText(null);
			jTextArea1.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
			jLabel2.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
			if (GameLogic.getGold() >= GameLogic.getWin()) {
				jLabel2.setFont(new Font(jLabel2.getFont().getName(), Font.BOLD, jLabel2.getFont().getSize()));
			}
			if (jTextArea2.getText().startsWith("Congrat")) {
				jTextField1.setEditable(false);
				jButton1.setEnabled(false);
				jButton3.setEnabled(false);
				jButton4.setEnabled(false);
				jButton5.setEnabled(false);
				jButton6.setEnabled(false);
				jButton7.setEnabled(false);
				jButton8.setEnabled(false);
				jButton9.setEnabled(false);
				jButton10.setEnabled(false);
				jButton11.setEnabled(false);
				jButton12.setEnabled(false);
				this.requestFocusInWindow();
			}
			}
			else {
				formSubmitted("SHOUT " + jTextField1.getText());
				jTextField1.setText(null);
			}
		}
	}

	private void formSubmitted(String command) {
		if (SINGLE_PLAYER) {
		jTextArea2.setText(GameLogic.processCommand(command));
		jTextArea1.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
		jLabel2.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
		if (GameLogic.getGold() >= GameLogic.getWin()) {
			jLabel2.setFont(new Font(jLabel2.getFont().getName(), Font.BOLD, jLabel2.getFont().getSize()));
		}
		if (jTextArea2.getText().startsWith("Congrat")) {
			jTextField1.setEditable(false);
			jButton1.setEnabled(false);
			jButton3.setEnabled(false);
			jButton4.setEnabled(false);
			jButton5.setEnabled(false);
			jButton6.setEnabled(false);
			jButton7.setEnabled(false);
			jButton8.setEnabled(false);
			jButton9.setEnabled(false);
			jButton10.setEnabled(false);
			jButton11.setEnabled(false);
			jButton12.setEnabled(false);
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
				jMenuItem1.setEnabled(false);
				jMenuItem1andahalf.setEnabled(false);
				GameLogic.init(fc.getSelectedFile().toString());
				jLabel1.setText(GameLogic.getMapName());
				jTextArea1.setText(Map.get('P', GameLogic.getCoords()[0], GameLogic.getCoords()[1]));
				jLabel2.setText(Integer.toString(GameLogic.getGold()) + "/" + Integer.toString(GameLogic.getWin()));
				if (GameLogic.getGold() >= GameLogic.getWin()) {
					jLabel2.setFont(new Font(jLabel2.getFont().getName(), Font.BOLD, jLabel2.getFont().getSize()));
				}
				jLabel2.setIcon(new ImageIcon("resources/gold.png")); // http://www.iconfinder.com/icondetails/65599/48/cash_gold_money_payment_icon
				jTextField1.setEditable(true);
				jButton1.setEnabled(true);
				jButton3.setEnabled(true);
				jButton4.setEnabled(true);
				jButton5.setEnabled(true);
				jButton6.setEnabled(true);
				jButton7.setEnabled(true);
				jButton8.setEnabled(true);
				jButton9.setEnabled(true);
				jButton10.setEnabled(true);
				jButton11.setEnabled(true);
				jButton12.setEnabled(true);
			}
		}
		else {
			jMenuItem1.setEnabled(false);
			jMenuItem1andahalf.setEnabled(false);
			jLabel2.setIcon(new ImageIcon("resources/gold.png")); // http://www.iconfinder.com/icondetails/65599/48/cash_gold_money_payment_icon
			jTextField1.setEditable(true);
			jButton1.setEnabled(true);
			jButton3.setEnabled(true);
			jButton4.setEnabled(true);
			jButton5.setEnabled(true);
			jButton6.setEnabled(true);
			jButton7.setEnabled(true);
			jButton8.setEnabled(true);
			jButton9.setEnabled(true);
			jButton10.setEnabled(true);
			jButton11.setEnabled(true);
			jButton12.setEnabled(true);

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

	public static void main(String args[]) {
		new GUI().setVisible(true);
	}

	// Variables declaration
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem1andahalf;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem4;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextArea jTextArea1;
	private JTextArea jTextArea2;
	private JTextField jTextField1;

	private JButton jButton10;
	private JButton jButton11;
	private JButton jButton12;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JButton jButton9;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JPanel jPanel1;
	
	private Socket serverSocket;
	private BufferedReader fromServer;
	private PrintWriter toServer;

	private String targetField;
	private int goal = 0;
	private int gold = 0;
	private boolean SINGLE_PLAYER;

	//temp stuff
	public void parseResponse(String text) {
		if (text.equals("LOOKREPLY")) {
			targetField = "jTextArea1";
			jTextArea1.setText(null);
		}
		else if (text.equals("SUCCESS") || text.equals("FAIL")) {
			targetField = "jTextArea2";
			jTextArea2.setText(null);
		}
		else if (text.equals("UPDATE")) {
			toServer.println("LOOK");
		}
		else if (text.equals("ENDGAME")) {
			jTextField1.setEditable(false);
			jButton1.setEnabled(false);
			jButton3.setEnabled(false);
			jButton4.setEnabled(false);
			jButton5.setEnabled(false);
			jButton6.setEnabled(false);
			jButton7.setEnabled(false);
			jButton8.setEnabled(false);
			jButton9.setEnabled(false);
			jButton10.setEnabled(false);
			jButton11.setEnabled(false);
			jButton12.setEnabled(false);
			if (gold >= goal) {
				jTextArea2.setText("You win!");
			}
			else {
				jTextArea2.setText("You lose!");
			}
		}
		else if (text.startsWith("HELLO ")) {
			jLabel1.setText(text.substring(6));
		}
		else if (text.startsWith("GOAL ")) {
			goal = Integer.parseInt(text.substring(5));
			jLabel2.setText("0 / " + text.substring(5));
		}
		else if (text.startsWith("TREASUREMOD ")) {
			gold += Integer.parseInt(text.substring(12));
			jLabel2.setText(Integer.toString(gold) + " / " + Integer.toString(goal));
		}
		else if (text.startsWith("MESSAGE ")) {
			jTextArea2.append("\"" + text.substring(8) + "\"\n");
		}
		else if (text.startsWith("ERROR ")) {
			jTextArea2.setText(text.substring(6) + "\n");
		}
		else if (targetField.equals("jTextArea1")) {
			jTextArea1.append(text + "\n");
		}
		else {
			jTextArea2.append(text + "\n");
		}
	}


}
