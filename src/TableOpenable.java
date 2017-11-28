
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author shanecandoit
 */
public class TableOpenable extends JFrame implements ActionListener {

//	private JTable table;
	private boolean hasInputBar = false;
	private JLabel status;
	private JTabbedPane tabbedPane;
	private int lastTabIndex = 0;

	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
		}
		return tabbedPane;
	}

	public JLabel getStatusBar() {
		if (status == null) {
			// TODO: should it just auto-update even if not null?
			status = new JLabel(getStatusString());

			status.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
			status.setPreferredSize(new Dimension(300, 30));
		}
		String tx = getStatusString();
		status.setToolTipText(tx);
		status.setToolTipText(tx);
		return status;
	}

	public TableOpenable() {
		// a no-input constructor builds a demo table
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setupTabs();
		setupTable();
		setupInputBar();

		setupStatus();
		getContentPane().add(getStatusBar(), BorderLayout.PAGE_END);

	}

	private void setupTabs() {
		JTabbedPane tabs = getTabbedPane();

//		tabs.setTabPlacement(JTabbedPane.BOTTOM);
		getContentPane().add(tabs);
		tabs.addChangeListener(new ChangeListener() {
			// This method is called whenever the selected tab changes
			@Override
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				lastTabIndex = pane.getSelectedIndex();
				System.out.println("Change tabs to index " + lastTabIndex);

				// update the status bar
				System.out.println("getStatusString() = " + getStatusString());
				//setupStatus();
				updateStatus();
				System.out.println("getStatusString() = " + getStatusString());
			}
		});
	}

	public int getRow() {
		JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
		JTable table = (JTable) scrollPane.getComponent(0);
		return table.getSelectedRow();
	}

	public int getColumn() {
		JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
		JTable table = (JTable) scrollPane.getComponent(0);
		return table.getSelectedColumn();
	}

//	public String getValueAt(String title, int row, int col) {
//		JTabbedPane tabs = getTabbedPane();
//		int count = tabs.getTabCount();
//		for (int i = 0; i < count; i++) {
//			String tab = tabs.getTitleAt(i);
//			if (tab.equals(title)) {
//				System.out.println("found table with name");
//				// TODO
//				JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
//				JTable table = (JTable) scrollPane.getComponent(0);
//				String val = (String) table.getValueAt(row, col);
//				if (val == null) {
//					val = "";
//				}
//				return val;
//			}
//		}
//		System.out.println("getValueAt not found title " + title + ", row " + row + ", col" + col);
//		return null;
//	}

	public void openNewDoc(String name) {

		JTabbedPane jt = getTabbedPane();
		// only open a new tab if its not already open
		int count = jt.getTabCount();
		boolean found = false;
		for (int i = 0; i < count; i++) {
			String title = jt.getTitleAt(i);
			title = title.substring(1);// trim leading @ or #
			if (name.equals(title)) {
				System.out.println(name + " is already open at index " + i);
				System.out.println("go to that tab");
				jt.setSelectedIndex(i);
				found = true;
				break;
			}
		}
		if (!found) {
			setupTable(name);
		}
	}

	private void setupInputBar() {

		// setup some buttons
		JPanel buttons = new JPanel();
		JButton newTable = new JButton("newTable");
		JButton openTable = new JButton("openTable");

		newTable.setActionCommand("new");
		newTable.addActionListener(this);
		openTable.setActionCommand("open");
		openTable.addActionListener(this);

		buttons.add(newTable, BorderLayout.WEST);
		buttons.add(openTable, BorderLayout.WEST);
		getContentPane().add(buttons, BorderLayout.PAGE_START);

		// url-ish input bar along top
		if (hasInputBar) {
			javax.swing.JTextField input = new javax.swing.JTextField("Input goes here");
			input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
			getContentPane().add(input, BorderLayout.PAGE_START);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println("action = " + action);
		// new
		if (action.equals("new")) {
			newTable();
		}
		// open
		if (action.equals("open")) {
			System.out.println("Show a file picker");
		}
	}

	private void setupTable() {
		// setup demo table
		String[] columnNames = demoColumns();
		String[][] tableData = demoTableData();

		JTable table;
		table = new JTable(tableData, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		table.setSelectionBackground(Color.lightGray);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		scrollPane.setMinimumSize(new Dimension(250, 150));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		tableListeners(table);

		getTabbedPane().add(scrollPane, "@demo");
	}

	private void newTable() {
		// setup demo table
		String[] columnNames = {"A", "B", "C", "D", "E"};
		String[][] tableData = {
			{"", "", "", "", ""},
			{"", "", "", "", ""},
			{"", "", "", "", ""},
			{"", "", "", "", ""},
			{"", "", "", "", ""}
		};

		JTable table;
		table = new JTable(tableData, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		table.setSelectionBackground(Color.lightGray);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		scrollPane.setMinimumSize(new Dimension(250, 150));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		tableListeners(table);

		getTabbedPane().add(scrollPane, "#" + System.currentTimeMillis());
	}

	private void setupTable(String hashOrName) {
		// setup demo table

		System.out.println("setupTable(String hashOrName) hashOrName = " + hashOrName);

		// TODO load data from hashName;
		String[] columnNames = {"a", "b", "c"};
		String[][] tableData = {
			{"a1", "b1", "c1"},
			{"a2", "b2", "c2"},
			{"a3", "b3", "c3"}
		};

		JTable table;
		table = new JTable(tableData, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		table.setSelectionBackground(Color.lightGray);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 300));
		scrollPane.setMinimumSize(new Dimension(250, 150));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		tableListeners(table);

		//getContentPane().add(scrollPane, BorderLayout.CENTER);
		getTabbedPane().add(scrollPane, "@" + hashOrName);

		// goto the newly created tab, -1 because [1,2] -> [0,1]
		int newTabLastIndex = getTabbedPane().getTabCount() - 1;
		getTabbedPane().setSelectedIndex(newTabLastIndex);
	}

	private void tableListeners(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					System.out.println("row = " + row);
					System.out.println("col = " + col);

					openCell(row, col);
				}
			}

			private void openCell(int row, int col) {
				String content = (String) table.getValueAt(row, col);

				if (content != null && !content.isEmpty()) {
					System.out.println("cell contents = " + content);

					if (content.startsWith("@")) {
						String nameOrHash = content.substring(1);
						System.out.println("open a new table, load document " + nameOrHash);

						/* Create and display the form */
						java.awt.EventQueue.invokeLater(new Runnable() {
							public void run() {
//								new TableOpenable(nameOrHash, parent);
//								openNewDoc(nameOrHash);

								openNewDoc(nameOrHash);
							}
						});
					}
				}
			}
		});

		table.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("released " + e.getKeyCode());
				final int down = 40;
				final int left = 37;
				final int up = 38;
				final int right = 39;
				final int enter = 10;
				final int tab = 9;
				final int escape = 27;
				switch (e.getKeyCode()) {

					case escape:
						System.out.println("Pressed ESCAPE. Close open tab.");
//						dispose();
//						System.exit(0);
						//close current tab
						int i = getTabbedPane().getSelectedIndex();
						// TODO: remove ui parts?
						getTabbedPane().remove(i);
						if (i <= 0) {
							System.out.println("last tab closed. quitting");
							dispose();
							System.exit(0);
						}

					case enter:
						if (e.isControlDown()) {
							System.out.println("Pressed Control Enter. Open any document in this cell");

//							int row = table.rowAtPoint(e.getPoint());
//							int col = table.columnAtPoint(e.getPoint());
//							if (row >= 0 && col >= 0) {
//								String content = (String) table.getValueAt(row, col);
//
//								if (content != null && !content.isEmpty()) {
//									System.out.println("cell contents = " + content);
//
//									if (content.startsWith("@")) {
//										System.out.println("open a new table, load document " + content.substring(1));
//									}
//								}
//							}
						}
					default:
						return;
				}
			}
		});
	}

//	private int getRow() {
//		return table.getSelectedRow();
//	}
//
//	private int getCol() {
//		return table.getSelectedColumn();
//	}

	private static void createAndShowGUI() {

		style();

		//Create and set up the content pane.
		TableOpenable demo = new TableOpenable();
		demo.setTitle("playing w tables");
		demo.setLocationByPlatform(true);

		//Display the window.
		demo.pack();
		demo.setVisible(true);
	}

	private static void style() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			Logger.getLogger(TableOpenable.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {

		createAndShowGUI();
	}

	// setup for modal dialog on clicking status bar
	// status along the bottom
	private void setupStatus() {
		String statusString = getStatusString();
		JLabel status = getStatusBar();
//		status.setText(statusString);

		final JFrame thisFrame = this;
		thisFrame.setEnabled(true);

		JFrame jf = new JFrame("status notes");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea tx = new JTextArea(statusString.replace(", ", "\n"));
		tx.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		status.removeAll();
		status.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JOptionPane.showMessageDialog(null, tx);
			}
		});
		jf.pack();
	}

	private void updateStatus() {
		String statusString = getStatusString();
		JLabel statusBar = getStatusBar();
		this.status.setText(statusString);

		final JFrame thisFrame = this;
		thisFrame.setEnabled(true);

		JFrame jf = new JFrame("status notes");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea tx = new JTextArea(statusString.replace(", ", "\n"));
		tx.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		MouseListener[] mls = status.getMouseListeners();
		for (MouseListener ml : mls) {
			statusBar.removeMouseListener(ml);
		}

		statusBar.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JOptionPane.showMessageDialog(null, tx);
			}
		});
		jf.pack();
	}


	// TODO read these from sql or files?
	private String getStatusString() {

		// status shown for selected tab
//		String tabName.
		String currentTabTitle = getTabbedPane().getTitleAt(lastTabIndex);
		// how to manage username?
		String username = System.getProperty("user.name");

		// state = ???
		String state = "ok";
		// auotmatically generated
		String hash = "#a654f6";
		// a name is registered to a hash and an author
		// an author may update a name
//		String name = "@nameOfThisSpreadSheetUniqueToHashAndAuthor";
		String name = currentTabTitle;
		// create a ssl key ???
//		String author = "author@example.com";
		String author = username;
		// which document precedes this one?, set by author
		String previous = "previousHashOrName";
		String dateCreated = Calendar.getInstance().getTime().toString();
		String statusString = String.format("status: %s, "
				+ "hash: %s, "
				+ "name: %s, "
				+ "author: %s, "
				+ "date-created: %s, "
				+ "previous: %s, "
				+ "dependencies: ---", state, hash, name, author, dateCreated, previous);
		return statusString;
	}

	private String[] demoColumns() {
		String[] demo = {"English",
			 "Spanish", "Italian", "Empty"};
		return demo;
	}

	private String[][] demoTableData() {
		String[][] data = {{"one", "uno", "uno", null},
		{"two", "dos", "due", null},
		{"three", "tres", "tre", null},
		{"four", "cuatro", "@quattro", null},
		{"five", "cinco", "cinque", null},
		{"six", "seis", "sei", null},
		{"seven", "siete", "sette", null}};
		return data;
	}

}
