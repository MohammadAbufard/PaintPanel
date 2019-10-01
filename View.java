package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColourChooserPanel colourChooserPanel;
	private ThicknessPanel thicknessPanel;
	private TextField thicknessTextField = new TextField();



	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colourChooserPanel = new ColourChooserPanel(this);
		this.thicknessPanel = new ThicknessPanel(this);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Change Colour");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		//Adding the Change Thickness
		menuItem = new MenuItem("Change Thickness");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		return menuBar;
	}

	public String getThicknessText() {

		return thicknessTextField.toString();
	}

	@Override
	public void handle(ActionEvent event) {

		if (((MenuItem)event.getSource()).getText() == "Change Colour") {
			BorderPane colours = new BorderPane();
			colours.setCenter(this.colourChooserPanel);

			Scene scene2 = new Scene(colours);
			Stage stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.setTitle("Change Colour");
			stage2.show();
		}
		if (((MenuItem)event.getSource()).getText() == "Change Thickness") {

//			HBox hbox = new HBox(8);
//			hbox.setPadding(new Insets(15,15,15,15));
//			Label width = new Label("Width: ");
//			thicknessTextField.setPrefWidth(70);
//			Button setWidthBtn = new Button("Save");
//			hbox.getChildren().add(width);
//			hbox.getChildren().add(thicknessTextField);
//			hbox.getChildren().add(setWidthBtn);
//			setWidthBtn.setOnAction(this);
			BorderPane thicknessPane = new BorderPane();
			thicknessPane.setCenter(this.thicknessPanel);

			Scene thicknessScene = new Scene(thicknessPane);
			Stage thicknessStage = new Stage();
			thicknessStage.setScene(thicknessScene);
			thicknessStage.setTitle("Change Thickness");
			thicknessStage.show();
		}

		System.out.println(((MenuItem)event.getSource()).getText());
	}

}
