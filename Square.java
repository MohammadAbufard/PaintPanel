package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Square extends Rectangle{
	private String col = "white";
	private double thickness = 1.0;

	public Square(Point edge, int length,int width, Point destination) {
		super(edge, length, width, destination);
		super.setColour(col);
		super.setThickness(thickness);
	}
	public Square(Point edge, int length,int width, Point destination, String col, double thickness) {
		super(edge, length, width, destination);
		this.col = col;
		super.setColour(col);
		super.setThickness(thickness);
	}

}
