package ca.utoronto.utm.paint;


public class Point extends Shape{
	int x, y;
	private String col = "white";
	private double thickness = 1.0;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
		super.setColour(col);
		super.setThickness(thickness);
	}

	Point(int x, int y, String col, double thickness) {
		this.x = x;
		this.y = y;
		this.col = col;
		super.setColour(col);
		super.setThickness(thickness);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
