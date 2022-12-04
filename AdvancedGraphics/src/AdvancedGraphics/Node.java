package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;
/**
 * 
 * abstract class
 *
 */
public abstract class Node {

// Every subclass supports a Color...
// Access: protected (subclasses can access and modify directly)
protected Color c;

public abstract String toString(int red, int green, int blue);

// Every subclass of Node is forced to implement this method
public abstract void draw(Graphics g);

} 
