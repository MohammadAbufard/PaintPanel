package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ThicknessPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view;

	public ThicknessPanel(View view) {

		this.view = view;
		String [] buttonLabels = {"2" , "4", "8","12", "16" };

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

		try {
			String command = ((Button) event.getSource()).getText();
			this.view.getPaintPanel().setThickness(Double.parseDouble(command));

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}

	}

}
