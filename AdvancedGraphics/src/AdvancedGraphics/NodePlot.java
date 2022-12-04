package AdvancedGraphics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class NodePlot extends JComponent {
	
	private ArrayList<Node> listNode = new ArrayList<>();
	
	public NodePlot() {
	}
	
	/**
	 * method draws whatever is added into the the ArrayList listNode
	 * @param g - a component of graphics
	 */
	public void paintComponent(Graphics g) {
		for (Node item : listNode) {
			item.draw(g);
		}
	}
	/**
	 * method that adds the shape on the plot
	 * @param node - abstract object that 
	 *
	 */
	public void addItem(Node node) {
		listNode.add(node);
		this.repaint();
	}
	
	/**
	 * 
	 * @param index - the position of the element
	 * method removes the item from the plot at the given index
	 */
	public void removeItem(int index) {
		listNode.remove(index);
		this.repaint();
	}
	
	/**
	 * method that removes the element created last
	 */
	public void removeLast()    
	{
	    int size = listNode.size();
	    listNode.remove(size-1);
	    this.repaint();
	}

	/**
	 * method that clears the plot of any shapes 
	 */
	public void clear() {
		listNode.clear();
		this.repaint();
	}
	
	/**
	 * 
	 * @return the ArrayList listNode
	 */
	public ArrayList<Node> getList() {
		return listNode;
	}
	
	
}
