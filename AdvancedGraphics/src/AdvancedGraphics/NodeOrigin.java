package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;

public class NodeOrigin extends Node {

	private int w = 0;
	private int h = 0;
	
	public NodeOrigin() {

	}
	
	public void draw(Graphics g) {
		w = (int) g.getClipBounds().getWidth();
		h = (int) g.getClipBounds().getHeight();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(w/2 - 10, h/2 - 10, 20, 20);
	}
		/**
	 * method that returns grid
	 * @return returns the string " Origin "
	 */

	@Override
	public String toString(int red, int green, int blue) {
		// TODO Auto-generated method stub
		return "Origin";
	}
}
