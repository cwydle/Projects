package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class NodeRectangle extends Node {

	// Every subclass of Node must provide implementation 
	// for the draw() method (see below) as well as inheriting the 
	// protected instance variable c for the shape color
	
	private int x = 0;
	private int y = 0;
	private int shapeWidth = 0;
	private int shapeHeight = 0;
	private int plotWidth = 0;
	private int plotHeight = 0;

	public NodeRectangle(int x, int y, int shapeWidth, int shapeHeight, Color c) {
		this.x = x;
		this.y = y;
		this.shapeWidth = shapeWidth;
		this.shapeHeight = shapeHeight;
		this.c = c;
	}
	
	public void draw(Graphics g) {
		
		plotWidth = (int) g.getClipBounds().getWidth();
		plotHeight = (int) g.getClipBounds().getHeight();
		
		Graphics2D g2 = (Graphics2D)g; 

		
		int dX = x - (shapeWidth / 2) + (plotWidth / 2);
		int dY = y - (shapeHeight / 2) + (plotHeight / 2);
		
		g.setColor(c);
		g.drawRect(dX, dY, shapeWidth, shapeHeight);
				
	}
	/**
	 * @param int red - the red component of color
	 * @param int green- the green component of color
	 * @param int blue - the blue component of color
	 * the method 
	 * @return returns the shape, x location, y location, width, height, the red component, the green component, and the blue component,
	 */
	@Override
	public String toString(int red, int green, int blue) {
		// TODO Auto-generated method stub
		return "Rectangle" + " X" + x + " Y" + y + " W" +  shapeWidth + " H" + shapeHeight + " R" + red + " G" + green + " B" + blue;
	}
}
