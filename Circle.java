package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle extends Shape{

	private Point centre;
	private int radius;
	private String col = "white";
	private double thickness = 1.0;

	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
		super.setColour(col);
		super.setThickness(thickness);
	}

	public Circle(Point centre, int radius, String col, double thickness) {
		this.centre = centre;
		this.radius = radius;
		this.col = col;
		this.thickness = thickness;
		super.setColour(col);
		super.setThickness(thickness);

	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setCircleColour(Paint colour) {
		this.colour = colour;
	}

	public Paint getCircleColour() {
		return colour;
	}
	public void setThickness (double thickness)
	{
		this.thickness = thickness;
	}
	public double getThickness () {
		return this.thickness;

	}

}
