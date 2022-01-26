package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Label label = new Label("CS400 MyFirstJavaFX");
			Button button = new Button("Done");
			ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
			final ComboBox comboBox = new ComboBox(options);
			Image image = new Image("me.png");
			TextArea textArea = new TextArea();
			ImageView imageView = new ImageView(image);
			BorderPane root = new BorderPane();
			root.setCenter(imageView);
			root.setTop(label);
			root.setLeft(comboBox);
			root.setBottom(button);
			root.setRight(textArea);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
