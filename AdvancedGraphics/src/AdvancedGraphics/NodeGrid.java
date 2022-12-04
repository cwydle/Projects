package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;

public class NodeGrid extends Node {

	private int w = 0;
	private int h = 0;
	
	public NodeGrid() {

	}
	
	public void draw(Graphics g) {
		
		w = (int) g.getClipBounds().getWidth();
		h = (int) g.getClipBounds().getHeight();
		
		g.setColor(Color.LIGHT_GRAY);

		for (int i=0; i<w; i+=20) {
			g.drawLine(i, 0, i, h);
		}
		
		for (int j=0; j<h; j+=20) {
			g.drawLine(0, j, w, j);
		}
		
	}
	@Override
	/**
	 * method that returns grid
	 * @return returns the string "Grid"
	 */
	public String toString(int red, int green, int blue) {
		// TODO Auto-generated method stub
		return "Grid";
	}
}
