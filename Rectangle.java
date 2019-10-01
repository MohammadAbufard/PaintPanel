package ca.utoronto.utm.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Rectangle extends Shape{

	private Point edge;
	private int length;
	private int width;
	private Point destination;
	private String col = "white";
	private double thickness = 1.0;

	public Rectangle(Point edge, int length,int width, Point destination) {
		this.edge = edge;
		this.length = length;
		this.width = width;
		this.destination = destination;
		super.setColour(col);
		super.setThickness(thickness);
	}

	public Rectangle(Point edge, int length,int width, Point destination, String col, double thickness) {
		this.edge = edge;
		this.length = length;
		this.width = width;
		this.destination = destination;
		this.col = col;
		super.setColour(col);
		super.setThickness(thickness);
	}
	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}
	public Point getEdge() {
		return edge;
	}
	public void setEdge(Point edge) {
		this.edge = edge;
	}
	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}
	public void setRectangle(int length, int width, Point destination) {
		this.length = length;
		this.width = width;
		this.destination = destination;
	}

	public void setRectangleColour(Paint colour) {
		this.colour = colour;
	}

	public Paint getRectangleColour() {
		return colour;
	}

}
