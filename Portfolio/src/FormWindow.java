import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import Accounts.Account;
import Accounts.CheckingAccount;
import Accounts.Client;
import Accounts.Portfolio;

import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Dimension;
/*
 * @author Crystal Luong and Jithin Civichan
 * This program creates a portfolio manager application and allows users to load and save a client and to add or remove portfolios and accounts. 
 * the program updates a the transaction as they occur and has a status box displaying client value and each portfolio's total value.
 */

public class FormWindow {

	// FormWindow's Instance Variables
	private JFrame frame;
	
	// FormWindow has one Client object associated with it
	private Client client;
	private JTextField inputFirstName;
	private JTextField inputLastName;
	private JTextField inputPhoneNumber;
	private JList<String> formLedger;
	private List<String> historyData = new ArrayList<>();
	private JList<String> history;
	private DefaultTreeModel tree;
	private JTree formTree;
	private DefaultMutableTreeNode selectedTreeNode;
	

	/**
	 * Launch the application.
	 * This happens first
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// Call the FormWindow Constructor
					FormWindow window = new FormWindow();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * This happens second
	 */
	public FormWindow() {
		
		// Call the initialize method to configure the form
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * This happens third
	 */
	private void initialize() {
		// Setup the form
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Create & setup the ScrollPane for the ledger and attach it to the form
		JScrollPane ledgerPane = new JScrollPane();
		ledgerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ledgerPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ledgerPane.setBounds(10, 11, 571, 142);
		frame.getContentPane().add(ledgerPane);
				
		// Setup a list model for the ledger
		DefaultListModel<String> ledger = new DefaultListModel<>();
		
		// Create the JList with the ledger list model and attach it to the ScrollPane
		formLedger = new JList<String>(ledger);
		ledgerPane.setViewportView(formLedger);
		
		initTree();
		
		// Create & setup the ScrollPane for the tree and attach it to the form
		JScrollPane treePane = new JScrollPane();
		treePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		treePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		treePane.setBounds(10, 165, 414, 387);
		frame.getContentPane().add(treePane);
		
		// Create the JTree with the tree list model and attach it to the ScrollPane
		formTree = new JTree(tree);
		
		formTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = 
					       (DefaultMutableTreeNode)formTree.getLastSelectedPathComponent();
				
				selectedTreeNode = selectedNode;
				
				
			}
		});

		treePane.setViewportView(formTree);
		
		history = new JList();
		history.setVisibleRowCount(20);
		history.setBounds(548, 165, 437, 387);
		frame.getContentPane().add(history);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(603, 24, 104, 16);
		frame.getContentPane().add(lblFirstName);
		
		inputFirstName = new JTextField();
		lblFirstName.setLabelFor(inputFirstName);
		inputFirstName.setBounds(749, 19, 180, 26);
		frame.getContentPane().add(inputFirstName);
		inputFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(603, 61, 104, 16);
		frame.getContentPane().add(lblLastName);
		
		inputLastName = new JTextField();
		lblLastName.setLabelFor(inputLastName);
		inputLastName.setColumns(10);
		inputLastName.setBounds(749, 56, 180, 26);
		frame.getContentPane().add(inputLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(603, 96, 104, 16);
		frame.getContentPane().add(lblPhoneNumber);
		
		inputPhoneNumber = new JTextField();
		lblPhoneNumber.setLabelFor(inputPhoneNumber);
		inputPhoneNumber.setColumns(10);
		inputPhoneNumber.setBounds(749, 91, 180, 26);
		frame.getContentPane().add(inputPhoneNumber);
		
		JButton btnUpdate = new JButton("Update Interface");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = inputFirstName.getText();
				String lastName = inputLastName.getText();
				String phoneNumber = inputPhoneNumber.getText();
				
				
				client.setFirstName(firstName);
				client.setLastName(lastName);
				client.setPhoneNumber(phoneNumber);
				
				addToHistory("Updating Client: " + firstName + ", " + lastName + ", " + phoneNumber);
				
				render();
			}
		});
		btnUpdate.setBounds(698, 124, 147, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedTreeNode == null) return;
				
				Portfolio p = client.getClientPortfolioByName(selectedTreeNode.getUserObject().toString());
				
				if (p != null) {
					if (client.removePortfolio(p)) {
						addToHistory("Removed portfolio: " + selectedTreeNode.getUserObject().toString());
					};
				}
				
				client.getPortfolios().forEach(port -> {
					if (port.removeAccountByNumber(selectedTreeNode.getUserObject().toString())) {
						addToHistory("Removed account: " + selectedTreeNode.getUserObject().toString());
					};
				});

				render();
			}
		});
		btnRemove.setForeground(Color.RED);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(10, 564, 117, 29);
		frame.getContentPane().add(btnRemove);
		
		// Expand all the tree nodes on the form
		for (int i=0; i<formTree.getRowCount(); i++) {
			formTree.expandRow(i);	
		}
		
		// Create and attach the menu bar on the form
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		// Create the File menu and attach it to the menu bar
		JMenu mnClient = new JMenu("Client");
		menuBar.add(mnClient);
		
		// Create the load menu item and attach it to the File menu
		JMenuItem mntmLoadClient = new JMenuItem("Load Client");
		mntmLoadClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showOpenDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = loadClient(picker.getSelectedFile().getName());
			    	
			    	// Update the ledger with load status
//			    	ledger.addElement("Load result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());
			    	
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Load result: User cancelled");
			    	
			    }
			}
		});
		
		// Create a new default Client
		JMenuItem mntmNewClient = new JMenuItem("New Client");
		mntmNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newClient();
				
			}
		});
		mnClient.add(mntmNewClient);
		mnClient.add(mntmLoadClient);

		// Create the save menu item and attach it to the File menu
		JMenuItem mntmSaveClient = new JMenuItem("Save Client");
		mntmSaveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showSaveDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = saveClient(picker.getSelectedFile().getAbsolutePath());
			    	
			    	// Update the ledger with save status
//			    	ledger.addElement("Save result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());

		    	// Check to see if user cancelled
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Save result: User cancelled");
			    	
			    }
				
			}
		});
		mnClient.add(mntmSaveClient);
		
		JMenu mnPortfolio = new JMenu("Portfolio");
		menuBar.add(mnPortfolio);
		
		JMenuItem mntmNewPortfolio = new JMenuItem("New Portfolio");
		mnPortfolio.add(mntmNewPortfolio);
		
		mntmNewPortfolio.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane("Create a new portfolio.");
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 1, 1, 1));
				JLabel nameLabel = new JLabel("Name");
				JTextField nameField = new JTextField();

				JButton okButton = new JButton("OK");
				
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String text = nameField.getText();
						
						if (text == null || text.length() == 0) return;
						
						Portfolio portfolio = new Portfolio(text);
						
						client.addPortfolio(portfolio);
						
						nameField.setText("");
						
						addToHistory("Added portfolio: " + portfolio.getDescription());
						
						render();
					}
				});
				
				nameLabel.setLabelFor(nameField);
				
				panel.add(nameLabel);
				panel.add(nameField);
				panel.add(okButton);
				
				optionPane.showOptionDialog(frame,
						panel,
						"Create a new portfolio.",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null, new Object[] {},
						null);
				
			}
		});
		
		JMenuItem mntmLoadPortfolio = new JMenuItem("Load Portfolio");
		mnPortfolio.add(mntmLoadPortfolio);
		
		mntmLoadPortfolio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showOpenDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = loadPortfolio(picker.getSelectedFile().getName());
			    	
			    	// Update the ledger with load status
//			    	ledger.addElement("Load result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());
			    	
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Load result: User cancelled");
			    	
			    }
			}
		});
		
		JMenuItem mntmSavePortfolio = new JMenuItem("Save Portfolio");
		mnPortfolio.add(mntmSavePortfolio);
		
		mntmSavePortfolio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showSaveDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = savePortfolio(picker.getSelectedFile().getAbsolutePath());
			    	
			    	// Update the ledger with save status
//			    	ledger.addElement("Save result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());

		    	// Check to see if user cancelled
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Save result: User cancelled");
			    	
			    }
				
			
				
			}
		});
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mntmNewAccount = new JMenuItem("New Account");
		mnAccount.add(mntmNewAccount);
		
		mntmNewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 1, 1, 1));
				JOptionPane optionPane = new JOptionPane("Create a new account.", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
				JLabel balanceLabel = new JLabel("Balance");
				JTextField balanceField = new JTextField();
				JButton okButton = new JButton("OK");
				
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String text = balanceField.getText();
						
						double balance;
						
						try {
							balance = Double.parseDouble(text);
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
							return;
						}
						
						if (selectedTreeNode == null) return;
						
						Account account = new CheckingAccount(balance);
						
						Portfolio p = client.getClientPortfolioByName(selectedTreeNode.getUserObject().toString());
						
						if (p == null) return;
						
						p.addAccount(account);
						
						balanceField.setText("");
						
						addToHistory("Added account: " + account.getDescription());
						
						render();
					}
				});
				
				balanceLabel.setLabelFor(balanceField);
				
				panel.add(balanceLabel);
				panel.add(balanceField);
				panel.add(okButton);
				
				optionPane.showOptionDialog(frame,
						panel,
						"Create a new account.",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null, new Object[] {},
						null);
				
			}
		});
		
		JMenuItem mntmLoadAccount = new JMenuItem("Load Account");
		mnAccount.add(mntmLoadAccount);
		
		mntmLoadAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showOpenDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = loadAccount(picker.getSelectedFile().getName());
			    	
			    	// Update the ledger with load status
//			    	ledger.addElement("Load result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());
			    	
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Load result: User cancelled");
			    	
			    }
			
				
			}
		});
		
		JMenuItem mntmSaveAccount = new JMenuItem("Save Account");
		mnAccount.add(mntmSaveAccount);
		
		mntmSaveAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the FileChooser object
				JFileChooser picker = new JFileChooser();
				
			    // Show the Dialog box and get user input
			    int result = picker.showSaveDialog(null);
			    
			    // Check to see that the user selected a file
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	
			    	// Load the client using the selected filename
			    	boolean success = saveAccount(picker.getSelectedFile().getAbsolutePath());
			    	
			    	// Update the ledger with save status
//			    	ledger.addElement("Save result: " + success + " for " + picker.getSelectedFile().getAbsoluteFile());

		    	// Check to see if user cancelled
			    } else if (result == JFileChooser.CANCEL_OPTION) {

			    	// Update the ledger with user cancellation
			    	ledger.addElement("Save result: User cancelled");
			    	
			    }
				
			
				
			}
		});
		client = new Client();
		render();
				
	}
	
	/**
	 * Load a Client object from a Binary file 
	 * @param filename The full path to a binary file on the file system
	 * @return returns true if the Client object was loaded successfully
	 */
	private boolean loadClient(String filename) {

		// Setup a reference to the FileInputStream
	    FileInputStream file;
		
		try {
			// Open the file
			file = new FileInputStream(filename);
			
			// Create the writer
			ObjectInputStream in = new ObjectInputStream(file);
			
			Object obj = in.readObject();

			// Setup a reference to an ArrayList of type object and read in the list
			@SuppressWarnings("unchecked")
			ArrayList<Object> objList = (ArrayList<Object>) obj;
			

			// Load the Client object
			this.client = (Client) objList.get(0);
				
			// Close the file
			file.close();
			
			addToHistory("Loaded client: " + client.getId());
			
			render();
			// Return a reference to the client
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILING HERE");
			
			return false;
			
		}
		
	}
	
	private void newClient() {
		this.client = new Client();
		
		this.render();
	}
	
	/**
	 * Save a Client object to a Binary file 
	 * @param filename The full path to a binary file on the file system
	 * @return returns true if the Client object was saved successfully
	 */
	private boolean saveClient(String filename) {
		
		// Create a reference to a FileOutputStream object
		FileOutputStream file;
		
		try {
			
			// Create the FileOutputStream object and pass in the the file
			file = new FileOutputStream(filename);
			
			// Create the writer
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			// Create a new empty list of type Object
			ArrayList<Object> myList = new ArrayList<>();
	
			// Add your client object to the list
			myList.add(client);
			
			// Write the list out to file (binary)
			out.writeObject(myList);
	
			// Close the file
			file.close();
			
			return true;
		
		} catch (Exception e) {
			
			return false;
			
		}
	}
	/*
	 * method that repaints what was already in the JTree and formLedger with new information the user inputs 
	 */
	private void render() {		
		updateListData();
		initTree();
		frame.repaint();
	}
	/**
	 * This method adds a line of text to a box, displaying what the user's input into a JList
	 * @param text gives an update of what the user saved
	 */
	private void addToHistory(String text) {
		historyData.add(text);
		
		history.setListData(historyData.toArray(new String[0]));
	}
	/*
	 * This method creates a list of type Sting and updates the list of data that the user has done.
	 * It adds the ArrayList to the formLedger 
	 */
	private void updateListData() {
		if (client == null) return;
		
		List<String> list = new ArrayList<>();

		list.add("Last Name: " + client.getLastName());
		list.add("First Name: " + client.getFirstName());
		list.add("Phone Number: " + client.getPhoneNumber());
		list.add("Total Value: " + client.getClientPortfolioValue());
		list.add("Portfolio Count: " + client.getPortfolioCount());
		list.add("Portfolio Names: " + client.getClientPortfolioNames());
		
		String[] arr = list.toArray(new String[0]);
		
		
		formLedger.setListData(arr);
	}
	
	/*
	 * a method that creates a tree model and adds it to the JTree
	 */
	private void initTree() {
		// Setup a root node for the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Portfolio(s)");
				
		// Setup a tree model for the tree
		tree = new DefaultTreeModel(root);
		
		if (client == null) return;
		
		client.getPortfolios().forEach(p -> {
			
			// Add a sub-node to the root node 
			DefaultMutableTreeNode secondTierNode = new DefaultMutableTreeNode(p.getName());
			root.add(secondTierNode);
			
			p.getPortfolioAccounts().forEach(a -> {
				// Add a sub-node to a sub-node
				DefaultMutableTreeNode thirdTierNode = new DefaultMutableTreeNode(a.getAccountNumber());
				secondTierNode.add(thirdTierNode);
			});
		});;
		formTree.setModel(tree);
	}
	/*
	 * method that saves a binary file of the user's Portfolio and adds it to the history box
	 * @param filename The name of the Portfolio's binary file that the user wants to save 
	 * @return true or false depending if the user successfully loads an Portfolio
	 */
	private boolean savePortfolio(String filename) {
		
		// Create a reference to a FileOutputStream object
		FileOutputStream file;
		
		try {
			if (selectedTreeNode == null) return false;
			
			Portfolio p = client.getClientPortfolioByName(selectedTreeNode.getUserObject().toString());
			
			if (p == null) return false;
			
			// Create the FileOutputStream object and pass in the the file
			file = new FileOutputStream(filename);
			
			// Create the writer
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			// Create a new empty list of type Object
			ArrayList<Object> myList = new ArrayList<>();
	
			// Add your Portfolio object to the list
			myList.add(p);
			
			// Write the list out to file (binary)
			out.writeObject(myList);
	
			// Close the file
			file.close();
			
			addToHistory("Saved portfolio: " + p.getDescription());
			
			return true;
		
		} catch (Exception e) {
			
			return false;
			
		}
	}
	/*
	 * method that saves a binary file of the user's account and adds it to the history box
	 * @param filename The name of the account's binary file that the user wants to save 
	 * @return true or false depending if the user successfully loads an account
	 */
	private boolean saveAccount(String filename) {
			// Create a reference to a FileOutputStream object
			FileOutputStream file;
			
			try {
				if (selectedTreeNode == null) return false;
				
				Account a = client.getPortfolios().stream()
						.flatMap(p -> p.getPortfolioAccounts().stream())
						.filter(acc -> acc.getAccountNumber().equals(selectedTreeNode.getUserObject().toString()))
						.findFirst().orElse(null);
				
				if (a == null) return false;
				
				
				// Create the FileOutputStream object and pass in the the file
				file = new FileOutputStream(filename);
				
				// Create the writer
				ObjectOutputStream out = new ObjectOutputStream(file);
				
				// Create a new empty list of type Object
				ArrayList<Object> myList = new ArrayList<>();
		
				// Add your client object to the list
				myList.add(a);
				
				// Write the list out to file (binary)
				out.writeObject(myList);
		
				// Close the file
				file.close();
				
				addToHistory("Saved account: " + a.getDescription());
				
				return true;
			
			} catch (Exception e) {
				
				return false;
				
			}
		}
	/*
	 * method that loads up a binary file of the user's Portfolio and adds it to the list of Portfolio. Adds it to the history box
	 * @param filename The name of the Portfolio's binary file that the user wants to load up.
	 * @return true or false depending if the user successfully loads a Portfolio
	 */
	private boolean loadPortfolio(String filename) {
		// Setup a reference to the FileInputStream
	    FileInputStream file;
		
		try {
			// Open the file
			file = new FileInputStream(filename);
			
			// Create the writer
			ObjectInputStream in = new ObjectInputStream(file);
			System.out.println(456);
			
			Object obj = in.readObject();

			System.out.println(obj);

			// Setup a reference to an ArrayList of type object and read in the list
			@SuppressWarnings("unchecked")
			ArrayList<Object> objList = (ArrayList<Object>) obj;
			

			// Load the Portfolio object
			Portfolio p = (Portfolio) objList.get(0);
			
			if (p != null) {
				client.addPortfolio(p);
			}
				
			// Close the file
			file.close();
			addToHistory("Loaded portfolio: " + p.getDescription());
			
			render();
			// Return a reference to the Portfolio
			
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILING HERE");
			
			return false;
			
		}	
	}
	/*
	 * method that loads up a binary file of the user's account and adds it to the list of accounts. Adds it to the history box
	 * @param filename The name of the account's binary file that the user wants to load up.
	 * @return true or false depending if the user successfully loads an account
	 */

	private boolean loadAccount(String filename) {

		// Setup a reference to the FileInputStream
	    FileInputStream file;
		
		try {
			if (selectedTreeNode == null) return false;
			
			Portfolio p = client.getClientPortfolioByName(selectedTreeNode.getUserObject().toString());
			
			if (p == null) return false;
			
			// Open the file
			file = new FileInputStream(filename);
			
			// Create the writer
			ObjectInputStream in = new ObjectInputStream(file);
			
			Object obj = in.readObject();

			// Setup a reference to an ArrayList of type object and read in the list
			@SuppressWarnings("unchecked")
			ArrayList<Object> objList = (ArrayList<Object>) obj;
			

			Account a = (Account) objList.get(0);
			// Load the Account object
			p.addAccount(a);
				
			// Close the file
			file.close();

			addToHistory("Loaded account: " + a.getDescription());
			
			render();
			// Return a reference to the Account
			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILING HERE");
			
			return false;
			
		}	
	}
}