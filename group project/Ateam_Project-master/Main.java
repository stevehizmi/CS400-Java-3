/**
 * Executes GUI functions and takes in user input
 *
 */


import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		// Created Button
		Button myButton = new Button("Start Quiz");
		// Loaded image from file and created an imageView
		File file = new File("Resources/mainImage.png");
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(file.toURI().toString()));
		imageView.setFitHeight(293.6);
		imageView.setFitWidth(374.5);
		
		EventHandler<ActionEvent> action = someAction();
		
		// create a menu 
        Menu menu1 = new Menu("Home"); 
        Menu menu2 = new Menu("Add Question");
        Menu menu3 = new Menu("Take Quiz");
        Menu menu4 = new Menu("Help"); 
  
        // create a menubar 
        MenuBar mb = new MenuBar(); 
  
        // add menu to menubar 
        mb.getMenus().add(menu1);
        mb.getMenus().add(menu2);
        mb.getMenus().add(menu3);
        mb.getMenus().add(menu4); 
  
        // create a VBox 
        VBox vb = new VBox(mb); 
		try {
			primaryStage.setTitle("Ateam14 - Quiz Generator");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 650, 500);
			this.homeView(root, myButton, imageView, vb);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private EventHandler<ActionEvent> someAction() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                

            }
        };
    }
	
	private void homeView(BorderPane root, Button myButton, ImageView imageView, VBox vb) {
		root.setPadding(new Insets(0, 0, 20, 0));
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Added control objects to the screen
		root.setTop(vb);
		root.setCenter(imageView);
		root.setBottom(myButton);
		BorderPane.setAlignment(myButton,Pos.CENTER);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
