package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	private String col = "white";
	private Paint colour = Color.WHITE;

//	public double thicknessDefault = 1.0;
	private double thickness = 1.0;

	private Circle circle; // the circle we are building
	private Rectangle rectangle; // the rectangle we are building
	private Square square; // the Square we are building
	private PolyLine polyline; // the polyline we are building
	private Canvas canvas;
	boolean start = false;
	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: blue");

		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "Circle"; // bad code here?

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		g.setStroke(Color.WHITE);
		g.setLineWidth(1.0);
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i + 1);
			g.setStroke(p2.getColour());
			g.setLineWidth(p2.getThickness());
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}

		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			g.setStroke(c.getColour());
			g.setLineWidth(c.getThickness());
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
			g.strokeOval(x-radius, y-radius, 2*radius,2* radius);
			g.setLineWidth(c.getThickness());

		}

		//Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangle();
		for (Rectangle r : rectangles) {
			int edge_x = r.getEdge().getX();
			int edge_y = r.getEdge().getY();
			int length = r.getLength();
			int width = r.getWidth();
			g.setStroke(r.getColour());
			g.setLineWidth(r.getThickness());

			if (r.getDestination().getX() > edge_x && r.getDestination().getY() > edge_y) {
				g.strokeRect(edge_x, edge_y, length, width);
			}
			else if (r.getDestination().getX() < edge_x && r.getDestination().getY() > edge_y) {
				g.strokeRect(r.getDestination().getX(), edge_y, length, width);
			}
			else if (r.getDestination().getX() > edge_x && r.getDestination().getY() < edge_y) {
				g.strokeRect(edge_x, r.getDestination().getY(), length, width);
			}
			else if (r.getDestination().getX() < edge_x && r.getDestination().getY() < edge_y) {
				g.strokeRect(r.getDestination().getX(), r.getDestination().getY(), length, width);
			}
		}

		//Draw Square
		ArrayList<Square> squares = this.model.getSquare();
		for (Square r : squares) {
			int edge_x = r.getEdge().getX();
			int edge_y = r.getEdge().getY();
			int length = r.getLength();
			int width = r.getWidth();
			g.setStroke(r.getRectangleColour());
			g.setStroke(r.getColour());
			g.setLineWidth(r.getThickness());

			if (r.getDestination().getX() > edge_x && r.getDestination().getY() > edge_y) {
				g.strokeRect(edge_x, edge_y, length, width);
			}
			else if (r.getDestination().getX() < edge_x && r.getDestination().getY() > edge_y) {
				g.strokeRect(r.getDestination().getX(), edge_y, length, width);
			}
			else if (r.getDestination().getX() > edge_x && r.getDestination().getY() < edge_y) {
				g.strokeRect(edge_x, r.getDestination().getY(), length, width);
			}
			else if (r.getDestination().getX() < edge_x && r.getDestination().getY() < edge_y) {
				g.strokeRect(r.getDestination().getX(), r.getDestination().getY(), length, width);
			}
		}

		//Draw Polyline
		ArrayList<PolyLine> polylines = this.model.getPolyline();
		for (PolyLine p : polylines) {
			g.setStroke(p.getColour());
			g.setLineWidth(p.getThickness());
			g.strokeLine(p.getInitial().getX(), p.getInitial().getY(), p.getDestination().getX(), p.getDestination().getY());
			}
	}

	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	public String getColour() {
		return this.col;
	}

	public void setColour(String command, Paint colour) {

		this.col = command;
		if (command == "Red") {
			this.colour = Color.RED;
		}
		else if (command == "Blue"){
			this.colour = Color.BLUE;
		}
		else if (command == "Green"){
			this.colour = Color.GREEN;
		}
		else if (command == "Orange"){
			this.colour = Color.ORANGE;
		}
		else if (command == "Yellow"){
			this.colour = Color.YELLOW;
		}
		else if (command == "Purple"){
			this.colour = Color.PURPLE;
		}
		else if (command == "Black"){
			this.colour = Color.BLACK;
		}
		else if (command == "White"){
			this.colour = Color.WHITE;
		}
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}
	}

	private void mouseMoved(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
		else if  (this.mode == "Rectangle") {
		}
		else if  (this.mode == "Square") {
		}
		else if  (this.mode == "Polyline") {
		}
	}

	private void mouseDragged(MouseEvent e) {
		if (this.mode == "Squiggle") {
			Point p1 = new Point((int) e.getX(), (int) e.getY(), col, thickness);
			this.model.addPoint(p1);
		} else if (this.mode == "Circle") {
			int x = (int)(this.circle.getCentre().getX()- e.getX());
			int y = (int)(this.circle.getCentre().getY() - e.getY());
			int radius = (int) Math.hypot(x, y);
			this.circle.setRadius(radius);
			this.circle.setColour(col);
			this.circle.setThickness(thickness);
			this.model.addCircle(this.circle);
		}
		else if (this.mode == "Rectangle") {
			int x = Math.abs((int)(e.getX() - this.rectangle.getEdge().getX()));
			int y = Math.abs((int)(e.getY() - this.rectangle.getEdge().getY()));
			Point point = new Point ((int)e.getX(), (int)e.getY());
			this.rectangle.setRectangle(x, y, point);
			this.rectangle.setColour(col);
			this.rectangle.setThickness(thickness);
			this.model.addRectangle(this.rectangle);
			}
		else if (this.mode == "Square") {
			int x = Math.abs((int)(e.getX() - this.square.getEdge().getX()));
			int y = Math.abs((int)(e.getY() - this.square.getEdge().getY()));
			Point point = new Point ((int)e.getX(), (int)e.getY());
			this.square.setRectangle(x, y, point);
			this.square.setColour(col);
			this.square.setThickness(thickness);
			this.model.addSquare(this.square);
			}

		else if (this.mode == "Polyline") {
			if (this.polyline != null) {
				Point destination_point = new Point ((int)e.getX(), (int)e.getY());
				this.polyline.setDestination(destination_point);
				this.model.addPolyline(this.polyline);

			}
		}
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
		else if  (this.mode == "Rectangle") {

		}
		else if  (this.mode == "Square") {
		}
		else if  (this.mode == "Polyline") {
		}
	}

	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			// Problematic notion of radius and centre!!
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;


			this.circle = new Circle(centre, radius, col, thickness);
			this.circle.setColour(col);
			this.circle.setThickness(thickness);
		}
		else if (this.mode == "Rectangle") {
			Point edge = new Point((int) e.getX(), (int) e.getY());
			int length = 0;
			int width = 0;
			this.rectangle = new Rectangle(edge, length, width, edge, col, thickness);
			this.rectangle.setThickness(thickness);
		}
		else if (this.mode == "Square") {
			Point edge = new Point((int) e.getX(), (int) e.getY());
			int length = 0;
			int width = 0;
			this.square = new Square(edge, length, width, edge, col, thickness);
			this.square.setThickness(thickness);
		}
		else if (this.mode == "Polyline") {
			if (start == false) {
				start = true;
					Point point = new Point((int) e.getX(), (int) e.getY());
					this.polyline = new PolyLine (point);
					this.polyline.setColour(col);
					this.polyline.setThickness(thickness);
			}
			else {
				this.polyline = new PolyLine(this.polyline.getDestination());
				this.polyline.setColour(col);
				this.polyline.setThickness(thickness);
			}
		}
	}


	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int x = (int)(this.circle.getCentre().getX()- e.getX());
				int y = (int)(this.circle.getCentre().getY() - e.getY());
				int radius = (int) Math.hypot(x, y);
				this.circle.setColour(col);
				this.circle.setRadius(radius);
				this.circle.setThickness(thickness);
				this.model.addCircle(this.circle);
				this.circle = null;

			}
		}
		else if (this.mode == "Rectangle") {
			if (this.rectangle != null) {
				int x = Math.abs((int)(e.getX() - this.rectangle.getEdge().getX()));
				int y = Math.abs((int)(e.getY() - this.rectangle.getEdge().getY()));
				Point point = new Point ((int)e.getX(), (int)e.getY());
				this.rectangle.setColour(col);
				this.rectangle.setRectangle(x, y, point);
				this.rectangle.setThickness(thickness);
				this.model.addRectangle(this.rectangle);
				this.rectangle = null;
				}
		}
		else if (this.mode == "Square") {
			if (this.square != null) {
				int x = Math.abs((int)(e.getX() - this.square.getEdge().getX()));
				int y = Math.abs((int)(e.getY() - this.square.getEdge().getY()));
				Point point = new Point ((int)e.getX(), (int)e.getY());

				this.square.setColour(col);
				this.square.setThickness(thickness);
				if (x > y) {
				this.square.setRectangle(x, x, point);
				}
				else {
					this.square.setRectangle(y, y, point);
				}
				this.model.addSquare(this.square);
				this.square = null;
				}
		} else if (this.mode == "Polyline") {
			if (this.polyline != null) {
				Point destination_point = new Point ((int)e.getX(), (int)e.getY());
				this.polyline.setDestination(destination_point);
				this.polyline.setColour(col);
				this.polyline.setThickness(thickness);
				this.model.addPolyline(this.polyline);

			}
		}

	}

	private void mouseEntered(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
		else if  (this.mode == "Rectangle") {

		}
		else if  (this.mode == "Square") {
		}
		else if  (this.mode == "Polyline") {
		}
	}

	private void mouseExited(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
		else if  (this.mode == "Rectangle") {
		}
		else if  (this.mode == "Square") {
		}
		else if  (this.mode == "Polyline") {
		}
	}
}
