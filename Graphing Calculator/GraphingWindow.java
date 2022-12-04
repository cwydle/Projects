import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class GraphingWindow{

	private JTextField mChange;
	private JTextField bChange;
	private JRadioButton buttonx;
	private JRadioButton buttonx2;
	private JRadioButton buttonx3;
	private JRadioButton buttonsqrtx;
	private JRadioButton buttonlogx;
	private JButton graphme;
	private JFrame frame;
	private double m = 0; 
	private double b = 0; 

	public static boolean isNumeric(String string) {
		if (string == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphingWindow window = new GraphingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Graphing Calculator");
		frame.setBackground(Color.WHITE);
		frame.setBounds(200, 200, 607, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				drawGrid(g);
				g.setColor(Color.RED);
				
				if (buttonx.isSelected()) {
					drawX(g);
				}
				else if (buttonx2.isSelected()) {
					drawX2(g);
				}
				else if (buttonx3.isSelected()) {
					drawX3(g);
				}
				else if (buttonlogx.isSelected()) {
					drawlog(g);
				}
				else if (buttonsqrtx.isSelected()) {
					drawsqrt(g);
				}
			}
		};

		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		ButtonGroup drawbutton = new ButtonGroup();
		buttonx = new JRadioButton("x");
		buttonx.setBounds(425, 75, 80, 25);
		buttonx.setBackground(Color.WHITE);

		panel.add(buttonx);
		buttonx2 = new JRadioButton("x^2");
		buttonx2.setBounds(425, 103, 80, 25);
		buttonx2.setBackground(Color.WHITE);
		panel.add(buttonx2);

		buttonx3 = new JRadioButton("x^3");
		buttonx3.setBackground(Color.WHITE);
		buttonx3.setBounds(425, 131, 80, 25);
		panel.add(buttonx3);

		buttonsqrtx = new JRadioButton("sqrtx");
		buttonsqrtx.setBounds(501, 75, 80, 25);
		buttonsqrtx.setBackground(Color.WHITE);
		panel.add(buttonsqrtx);

		buttonlogx = new JRadioButton("logx");
		buttonlogx.setBackground(Color.WHITE);
		buttonlogx.setBounds(501, 103, 80, 25);
		panel.add(buttonlogx);

		drawbutton.add(buttonx);
		drawbutton.add(buttonx2);
		drawbutton.add(buttonx3);
		drawbutton.add(buttonsqrtx);
		drawbutton.add(buttonlogx);

		JTextArea function = new JTextArea("y = m*f(x)+b");
		function.setEditable(false);
		function.setFont(new Font("Monospaced", Font.PLAIN, 14));
		function.setBounds(441, 47, 117, 25);
		panel.add(function);
		JTextArea scalarm = new JTextArea("scalar(m)");
		scalarm.setEditable(false);
		JTextArea offsetb = new JTextArea("offset(b)");
		offsetb.setEditable(false);
		scalarm.setBounds(425, 163, 80, 25);
		offsetb.setBounds(425, 199, 80, 25);
		panel.add(offsetb);
		panel.add(scalarm);

		mChange = new JTextField();
		mChange.setBounds(511, 163, 70, 25);
		panel.add(mChange);
		bChange = new JTextField();
		bChange.setBounds(511, 199, 70, 25);
		panel.add(bChange);
		graphme = new JButton("Graph");
		graphme.setBounds(425, 235, 156, 25);
		graphme.setBackground(Color.cyan);
		
		panel.add(graphme);
		graphme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userm = mChange.getText();
				String userb = bChange.getText();
				if(GraphingWindow.isNumeric(userb) == true && GraphingWindow.isNumeric(userm) == true) {
					m = Double.valueOf(userm);
					b = Double.valueOf(userb);
					panel.repaint();
				}
				else {
					JOptionPane.showMessageDialog(frame, "error please enter numerical input");
				}
			}
		});		
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("x", 20, 190);
		g.drawString("y", 180, 20);
		g.drawString("1.0", 210, 105);
		g.drawLine(200, 0, 200, 400);
		g.drawLine(0, 200, 400, 200);
		g.drawLine(195, 300, 205, 300);
		g.drawLine(195, 100, 205, 100);
		g.drawLine(100, 195, 100, 205);
		g.drawLine(300, 195, 300, 205);
	}
	private void drawX(Graphics g) {

		double dx = .01; //make it smaller for precision
		for (double x=-2; x<=2; x+=dx) {
			double y = ((m*x)-b);
			int cX = (int) ((-x*100) + 200);
			int cY = (int) ((y*100) + 200);
			//need the variables with the transformation
			//transformation equations
			g.drawLine(cX, cY, cX, cY);
		}
	}
	//issues with -m 
	// the graph
	//fixed it by making m separate from x and by making m a negative
	private void drawX2(Graphics g) {
		//when m is less than 0 it gets wider
		//when m is greater than 0 it gets compressed
		double dx = .001; //make it smaller for precision
		for (double x=-2; x<=2; x+=dx) {
			double y = (((-m)*(Math.pow((x), 2)))-b);
			int cX = (int) ((-x*100) + 200);
			int cY = (int) ((y*100) + 200);
			//need the variables with the transformation
			//transformation equations
			g.drawLine(cX, cY, cX, cY);
		}
	}

	private void drawX3(Graphics g) {
		double dx = .001; //make it smaller for precision
		for (double x=-2; x<=2; x+=dx) {
			double y = ((Math.pow((m*x), 3))-b);
			int cX = (int) ((-x*100) + 200);
			int cY = (int) ((y*100) + 200);
			//need the variables with the transformation
			//transformation equations
			g.drawLine(cX, cY, cX, cY);
		}
	}
	private void drawsqrt(Graphics g) {
		//have issue with a horizontal line on the left side
		double dx = .0001; //make it smaller for precision
		for (double x=-2; x<=2; x+=dx) {
			double y = (((m)*(Math.sqrt(x)))+b);
			int cX = (int) ((x*100) + 200);
			int cY = (int) ((-y*100) + 200);
			g.drawLine(cX, cY, cX, cY);
		}
	}

	private void drawlog(Graphics g) {
		double dx = .0001; //make it smaller for precision
		for (double x=-2; x<=2; x+=dx) {
			double y = ((m)*(Math.log(x))+b);
			int cX = (int) ((x*100) + 200);
			int cY = (int) ((-y*100) + 200);

			g.drawLine(cX, cY, cX, cY);
		}
	}
}
