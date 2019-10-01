package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Shape {

	protected Paint colour;
	private double thickness;

	Shape() {
		this.colour = Color.WHITE;
		this.thickness = 1.0;
	}

	public void setColour(String col){
		if (col == "Red") {
			this.colour = Color.RED;
		}
		else if (col == "Blue"){
			this.colour = Color.BLUE;
		}
		else if (col == "Green"){
			this.colour = Color.GREEN;
		}
		else if (col == "Orange"){
			this.colour = Color.ORANGE;
		}
		else if (col == "Yellow"){
			this.colour = Color.YELLOW;
		}
		else if (col == "Purple"){
			this.colour = Color.PURPLE;
		}
		else if (col == "Black"){
			this.colour = Color.BLACK;
		}
		else if (col == "White"){
			this.colour = Color.WHITE;
		}
	}

	public Paint getColour() {
		return this.colour;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public double getThickness() {
		return this.thickness;
	}
}
