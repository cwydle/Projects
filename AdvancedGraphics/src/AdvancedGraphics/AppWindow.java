package AdvancedGraphics;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;
/**
 * This class is a JAVA GUI of a graphing calculator that takes in user inputs through 
text fields and button selections
 * @author Crystal Luong and Riya Goyal
 *
 */
public class AppWindow {
	Color c = new Color(0, 0, 0);
	private JFrame frame;
	private NodePlot plot;
	private JTextField txtRemovals;
	private JList<String> jlist;
	private DefaultListModel<String> model;
	// JList<Node> list = new JList<Node>();


	/**
	 * Create the application.
	 * @throws FileNotFoundException
	 */
	public AppWindow() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException
	 */
	private void initialize() throws FileNotFoundException {

		frame = new JFrame("Advanced Graphics");
		plot = new NodePlot();

		frame.setResizable(false);
		frame.setBounds(100, 100, 537, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JScrollPane scrollPane = new JScrollPane(plot);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(119, 8, 403, 403);
		frame.getContentPane().add(scrollPane);


		JButton btnGrid = new JButton("Add Grid");
		btnGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeGrid());
				model.addElement(new NodeGrid().toString(0, 0, 0));
			}
		});
		btnGrid.setBounds(12, 28, 97, 23);
		frame.getContentPane().add(btnGrid);

		JButton btnOrigin = new JButton("Add Origin");
		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeOrigin());
				model.addElement(new NodeOrigin().toString(0, 0, 0));

			}
		});
		btnOrigin.setBounds(12, 60, 97, 23);
		frame.getContentPane().add(btnOrigin);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plot.clear();
				model.removeAllElements();
			}
		});
		btnClear.setBounds(12, 388, 97, 23);
		frame.getContentPane().add(btnClear);

		JButton btnAddRandom = new JButton("Add 10");
		btnAddRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(10);
			}
		});
		btnAddRandom.setBounds(12, 94, 97, 23);
		frame.getContentPane().add(btnAddRandom);

		JButton btnAdd = new JButton("Add 1000");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(1000);

			}
		});
		btnAdd.setBounds(12, 128, 97, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnRecolor = new JButton("Recolor");
		btnRecolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReColor();
			}
		});
		btnRecolor.setBounds(12, 161, 97, 23);
		frame.getContentPane().add(btnRecolor);

		JButton btnComposite = new JButton("Composite");
		btnComposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawComposite();
				model.addElement(new CompositeShapeNode().toString(0, 0, 0));
			}
		});
		btnComposite.setBounds(12, 195, 97, 23);
		frame.getContentPane().add(btnComposite);

		JButton btnRemovelast = new JButton("Last");
		btnRemovelast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastOne();
			}
		});
		btnRemovelast.setBounds(12, 320, 97, 23);
		frame.getContentPane().add(btnRemovelast);
		
		
		JScrollPane pane = new JScrollPane(jlist);
		model = new DefaultListModel<String>();
		pane.setLocation(12, 422);
		pane.setSize(490, 101);
		pane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setViewportView(jlist);
		frame.getContentPane().add(pane);
		
		jlist = new JList<String>(model);
		pane.setViewportView(jlist);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL);
				
		jlist.setBorder(new LineBorder(new Color(0, 0, 0)));
		jlist.setBackground(Color.PINK);


		txtRemovals = new JTextField("Removals");
		txtRemovals.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtRemovals.setHorizontalAlignment(SwingConstants.CENTER);
		txtRemovals.setBackground(new Color(240, 240, 240));
		txtRemovals.setEditable(false);
		txtRemovals.setBounds(12, 289, 97, 20);
		frame.getContentPane().add(txtRemovals);
		txtRemovals.setColumns(10);



		JButton btnRemoveSelected = new JButton("Selected");

		btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSelected();
			}
		});
		btnRemoveSelected.setBounds(12, 354, 97, 23);
		frame.getContentPane().add(btnRemoveSelected);


		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmExit);

		JMenuItem mntmReadFile = new JMenuItem("Read");
		mntmReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReadingFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmReadFile);
	}
	/**
	 * this is a method that removes the last shape painted on the plot and 
	 * the jList element 
	 */
	private void removeLastOne() {
		plot.removeLast();
		int index = model.getSize()-1;		
		model.remove(index);  
	}
	
	/**
	 * this is a method that reads a line from the text file "data.txt" and plots
	 * the shape using the numbers given in the text file
	 */
	private void ReadingFile() throws FileNotFoundException{

		File filename = new File("data.txt");

		int xlocation = 0;
		int ylocation = 0;
		int radius = 0;
		int width = 0;
		int height = 0 ;
		int red = 0;
		int green = 0;
		int blue = 0;
		Color color = new Color (0,0,0);

		ArrayList<String> list = new ArrayList<String>();

		try (Scanner in = new Scanner(filename)) {
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] values = line.split(",");
				for(int i = 0; i< values.length; i++) {
					list.add(values[i]);
				}
			}
			String firstvalue = "";
			firstvalue = (String) list.get(0);
			firstvalue = firstvalue.toLowerCase();

			if(firstvalue.equals("rectangle")) {
				xlocation = Integer.valueOf(list.get(1));
				ylocation = Integer.valueOf(list.get(2));
				width = Integer.valueOf(list.get(3));
				height =Integer.valueOf(list.get(4));
				red = Integer.valueOf(list.get(5));
				green = Integer.valueOf(list.get(6));
				blue = Integer.valueOf(list.get(7));
				color = new Color(red,green,blue);
				plot.addItem(new NodeRectangle(xlocation, ylocation, width, height, color));
				model.addElement(new NodeRectangle(xlocation,ylocation,width,height,c).toString(red, green, blue));

			}
			else if (firstvalue.equals(list)) {
				xlocation = Integer.valueOf(list.get(1));
				ylocation = Integer.valueOf(list.get(2));
				radius = Integer.valueOf(list.get(3));
				red = Integer.valueOf(list.get(4));
				green = Integer.valueOf(list.get(5));
				blue = Integer.valueOf(list.get(6));
				color = new Color(red,green,blue);
				plot.addItem(new NodeCircle(xlocation, ylocation, radius, color));
				model.addElement(new NodeCircle(xlocation,ylocation,radius,c).toString(red, green, blue));

			}
			else {
				System.out.println("Put either rectangle or circle parameters in the text file in draw");
			}

		}
	}
	/**
	 * this is a method that calls CompositeShapeNode() from the Node class
	 * and plots/draw the composite shape
	 */
	private void drawComposite() {
		plot.addItem(new CompositeShapeNode());
	}

	// Use this method to help you create a shape loader
	/**
	 * this is a method that randomly generates numbers for the given variables 
	 * that make up the shape. The shape is determined by an if statement randomly
	 * @param int count - number of times the for loop will iterate 
	 */
	private void drawRandomShapes(int count) {
		Random generator = new Random();

		for (int i=0; i<count; i++) {

			// Create random X and Y locations
			int x = generator.nextInt(601)-200;
			int y = generator.nextInt(601)-200;

			// Set a random radius or height/width
			int r = generator.nextInt(201);
			int w = generator.nextInt(401);
			int h = generator.nextInt(401);

			// Create random color components (0-255)
			int red = generator.nextInt(128)+128;
			int green = generator.nextInt(128)+128;
			int blue = generator.nextInt(128)+128;

			this.c = new Color(red,green,blue);
			if (generator.nextBoolean()) {
				plot.addItem(new NodeCircle(x,y,r,c));
				model.addElement(new NodeCircle(x,y,r,c).toString(red, green, blue));
			} else {
				plot.addItem(new NodeRectangle(x,y,w,h,c));
				model.addElement(new NodeRectangle(x,y,w,h,c).toString(red, green, blue));

			}

		}

	}
	/**
	 * this is a method that removes the shape on the plot and the jList element
	 * the shape that gets removed is dependent on the selected index of the jList
	 */
	private void removeSelected() {
		int selected = jlist.getSelectedIndex();
		if (selected != -1) {
			model.removeElementAt(selected);
			plot.removeItem(selected);
		}
	}
	/**
	 * this is a method that has a for loop that randomly generates integers for 
	 * the colors rgb and calls the .repaint() method to repaint the color of 
	 * the shapes that are already on the plot
	 */
	private void ReColor() {
		Random generator = new Random();
		for(Node item : plot.getList()) {
			int red = generator.nextInt(128)+128;
			int green = generator.nextInt(128)+128;
			int blue = generator.nextInt(128)+128;
			item.c = new Color(red, green, blue);
		}
		plot.repaint();
	}

	public void setVisible(boolean b) {
		if (b) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
