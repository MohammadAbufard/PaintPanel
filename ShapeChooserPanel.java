package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private Label shape;
	private int counter = 1;

	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Current Selected Shape:", "1", ""};

		int row = 0;
		for (String label : buttonLabels) {
			if (label == "") {
				shape = new Label("Circle");
				shape.setMinWidth(100);
				shape.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
				//this.counter = counter;
				//counter++;
				this.add(shape, 0, row);
				row++;
			}
			else if (label == "Current Selected Shape:") {
				Label currentShape = new Label("Current Shape:");
				currentShape.setMaxWidth(100);
				//this.counter = counter;
				//counter++;
				this.add(currentShape, 0, row);
				row++;
			}
			else if (label == "1") {
				Label currentShape = new Label("");
				currentShape.setMaxWidth(100);
				//this.counter = counter;
				//counter++;
				this.add(currentShape, 0, row);
				row++;
			}
			else{
				Button button = new Button(label);
				button.setMinWidth(100);
				this.add(button, 0, row);
				//this.counter = counter;
				//counter++;
				row++;
				button.setOnAction(this);
			}

		}
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().setMode(command);
		//This.counter = txt;
		shape.setText(command);
		System.out.println(command);
	}
}
