package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class PolyLine extends Shape{
	private Point initial;
	private Point destination;
	private String col = "white";
	private double thickness = 1.0;

	public PolyLine (Point initial, Point destination) {
		this.initial = initial;
		this.destination = destination;
		this.col = "white";
		super.setColour(col);
		super.setThickness(thickness);
	}

	public PolyLine (Point initial) {
		this.initial = initial;
		this.col = "white";
		super.setColour(col);
		super.setThickness(thickness);
	}

	public PolyLine () {
		this.col = "white";
		super.setColour(col);
	}


	public Point getInitial() {
		return initial;
	}

	public void setInitial(Point initial) {
		this.initial = initial;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	public void setPolyline (Point destination) {
		this.destination = destination;
	}

	public String toString() {
		return "(" + this.col + ", " + this.colour + ")";
	}

}
