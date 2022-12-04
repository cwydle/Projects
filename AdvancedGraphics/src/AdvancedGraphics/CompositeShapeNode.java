package AdvancedGraphics;

import java.awt.Color;
import java.awt.Graphics;

public class CompositeShapeNode extends Node {

	public CompositeShapeNode(){

	}

	/**
	 * the method draws the composite shape
	 * Graphics g - a component of graphics
	 */
	@Override
	public void draw(Graphics g) {
		Color orange = new Color(255, 102, 0);
		Color black = new Color(0, 0, 0);
		Color white = new Color(255,255, 255);

		g.setColor(orange);
		g.fillOval(170,85,210,180);
		g.setColor(black);
		g.drawOval(170,85,210,180);
		g.setColor(white);
		g.fillOval(188,125,175,135);
		g.setColor(black);
		g.drawOval(188,125,175,135);
		g.setColor(black);
		g.drawLine(230,198,325,200);
		g.drawArc(230,155,95,87,180,180);
		g.setColor(black);
		g.fillArc(230,155,95,87,180,180);
		g.setColor(black);
		g.drawArc(255,214,50,40,-170,-170);
		g.setColor(white);
		g.fillArc(255,214,50,40,-170,-190);
		g.fillArc(255,227,48,15,180,180);

		g.setColor(black);
		g.fillOval(255,125,14,24);
		g.setColor(black);
		g.fillOval(285,125,14,24);
		g.setColor(white);
		g.fillOval(259,135,7,7);
		g.setColor(white);
		g.fillOval(288,135,7,7);

		g.setColor(black);
		g.fillOval(260,148,34,34);
		g.setColor(black);
		g.drawOval(260,148,34,34);
		g.setColor(white);
		g.fillOval(265,158,10,10);
		g.setColor(black);

		g.drawLine(278,181,278,198);
		g.drawLine(310,170,368,150);
		g.drawLine(310,180,368,180);
		g.drawLine(310,190,368,210);
		g.drawLine(240,170,188,150);
		g.drawLine(240,180,188,180);
		g.drawLine(240,190,188,210);
	}
	@Override
	public String toString(int red, int green, int blue) {
		// TODO Auto-generated method stub
		return "Composite Shape";
	}
}
