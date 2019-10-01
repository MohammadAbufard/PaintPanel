package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColourChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ColourChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Red", "Blue", "Green", "Orange", "Yellow", "Purple", "Black", "White" };

		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinWidth(400);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		if (command == "Red") {
			this.view.getPaintPanel().setColour(command, Color.RED);
		}
		else if (command == "Blue"){
			this.view.getPaintPanel().setColour(command, Color.BLUE);
		}
		else if (command == "Green"){
			this.view.getPaintPanel().setColour(command, Color.GREEN);
		}
		else if (command == "Orange"){
			this.view.getPaintPanel().setColour(command, Color.ORANGE);
		}
		else if (command == "Yellow"){
			this.view.getPaintPanel().setColour(command, Color.YELLOW);
		}
		else if (command == "Purple"){
			this.view.getPaintPanel().setColour(command, Color.PURPLE);
		}
		else if (command == "Black"){
			this.view.getPaintPanel().setColour(command, Color.BLACK);
		}
		else if (command == "White"){
			this.view.getPaintPanel().setColour(command, Color.WHITE);
		}
		System.out.println("Colour set to: " + command);
	}
}
