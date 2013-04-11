import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
		setTitle("Dungeon of Dooom");
		//newGame();
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
		jMenuItem2 = new JMenuItem();
		jMenuItem3 = new JMenuItem();
		jMenuItem4 = new JMenuItem();
		jMenu2 = new JMenu();

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
		jButton1.setFocusable(false);
		jButton1.setText("Submit");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				formSubmitted();
			}
		});

		jMenu1.setText("Game");

		jMenuItem1.setText("Start");
		jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newGame();
			}
		});
		jMenu1.add(jMenuItem1);

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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					//.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2))
					.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
						.addComponent(jTextField1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton1)))
				.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				//.addComponent(jLabel1)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
					.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addComponent(jScrollPane1))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(jButton1))
				.addContainerGap())
		);

		pack();
	}

	public void formSubmitted() {
		if (!jTextField1.getText().equals("")) {
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
				this.requestFocusInWindow();
			}
		}
	}
	
	public void aboutDialogue() {
		JOptionPane.showMessageDialog(this, "<html><div style='text-align: center;'><b>Dungeon of Dooom v3.1</b><br><br>Developed by Leon Byford, 2013.<br>&lt;ldjb20@bath.ac.uk&gt;<br><br>Uses code generated by NetBeans.<br><br>Some graphical elements &copy;2011 Manuel Lopez.</div></html>", "About", JOptionPane.PLAIN_MESSAGE);
	}

	public void launchWebpage() {
		try {
			Desktop.getDesktop().browse(new URI("http://people.bath.ac.uk/ldjb20/dooom/"));
		}
		catch (Exception e) {
		}
	}

	public void newGame() {
		JFileChooser fc = new JFileChooser("maps");
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			jMenuItem1.setEnabled(false);
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
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem4;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextArea jTextArea1;
	private JTextArea jTextArea2;
	private JTextField jTextField1;

}
