package application;

/**
 * Executes GUI functions and takes in user input
 *
 */
import java.awt.Desktop;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	// for file chooser operation.
	private Desktop desktop = Desktop.getDesktop();
	private File file;
	private List<Question> questionList;

	@Override
	public void start(Stage primaryStage) {

		// create a menu
		Label m1 = new Label("Home");
		Menu menu1 = new Menu("", m1);

		Label m2 = new Label("Add Question");
		
		Menu menu2 = new Menu("", m2);

		Label m4 = new Label("Help");
		m4.setOnMouseClicked(mouseEvent -> {
			help(primaryStage);
		});

		Menu menu4 = new Menu("", m4);

		// create a menu bar
		MenuBar mb = new MenuBar();

		// add menu to menu bar
		mb.getMenus().add(menu1);
		mb.getMenus().add(menu2);
		mb.getMenus().add(menu4);

		BorderPane root = new BorderPane();
		try {
			primaryStage.setTitle("Ateam14 - Quiz Generator");	
			Scene scene = new Scene(root, 800, 600);
			homePage(primaryStage, root, mb);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m1.setOnMouseClicked(mouseEvent -> {
			homePage(primaryStage, root, mb);
		});
		m2.setOnMouseClicked(mouseEvent -> {
			addQuestionPage(primaryStage, root, mb);
		});
	}

	private void homePage(Stage primaryStage, BorderPane root, MenuBar mb) {

		Label topicLabel = new Label("      Topics: ");
		topicLabel.setTextFill(Color.web("#fff0f5"));
		ObservableList<String> options = FXCollections.observableArrayList("RB Tree", "Binary Tree", "Treep",
				"Willow Tree", "Maple Tree");
		ComboBox box = new ComboBox(options);
		HBox hb = new HBox(10, topicLabel, box);

		Label numQuestions = new Label("# Questions? ");
		numQuestions.setTextFill(Color.web("#fff0f5"));
		TextField tf = new TextField();
		tf.setPrefWidth(86);
		HBox hb2 = new HBox(10, numQuestions, tf);

		VBox quizOptions = new VBox(10, hb2, hb);

		Label totalQLabel = new Label("Total Questions: ");
		totalQLabel.setTextFill(Color.web("#fff0f5"));
		Label totalQ = new Label("10");
		totalQ.setTextFill(Color.web("#fff0f5"));
		HBox hb3 = new HBox(10, totalQLabel, totalQ);

		hb.setPadding(new Insets(10, 10, 10, 10));

		// create a VBox
		VBox vb = new VBox(mb);
		
		Label mainLabel = new Label("      Quiz Generator");
		mainLabel.setStyle("-fx-font-size: 50");
		mainLabel.setTextFill(Color.web("#fff0f5"));
		
		// Created Button
		Button startButton = new Button("Start Quiz");
		startButton.setStyle("");
		startButton.setPrefHeight(40);
		startButton.setPrefWidth(100);

		// click button will open a new window.
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Label secondLabel = new Label("Start Quiz");

				StackPane secondaryLayout = new StackPane();
				secondaryLayout.getChildren().add(secondLabel);

				Scene secondScene = new Scene(secondaryLayout, 230, 100);

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Start Quiz");
				newWindow.setScene(secondScene);

				// Specifies the modality for new window.
				newWindow.initModality(Modality.WINDOW_MODAL);

				// Specifies the owner Window (parent) for new window
				newWindow.initOwner(primaryStage);

				// Set position of second window, related to primary window.
				newWindow.setX(primaryStage.getX() + 200);
				newWindow.setY(primaryStage.getY() + 100);

				newWindow.show();
			}
		});
		
		BorderPane top = new BorderPane();
		top.setTop(vb);
		top.setLeft(quizOptions);
		top.setRight(hb3);
		
		root.setPadding(new Insets(0, 0, 20, 0));
		root.setStyle("-fx-background-color: #778899");

		root.setTop(top);
		root.setCenter(mainLabel);
		root.setBottom(startButton);
		BorderPane.setAlignment(startButton, Pos.CENTER);
	}
	
	private void addQuestionPage(Stage primaryStage, BorderPane root, MenuBar mb) {
		// file chooser
				Button button = new Button("Upload JSON");
				try {

					// create a File chooser
					FileChooser fil_chooser = new FileChooser();

					// create a Label
					Label label = new Label("no files selected");

					// create a Button

					// create an Event Handler
					EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {

							// get the file selected
							file = fil_chooser.showOpenDialog(primaryStage);
							if (file != null) {

								label.setText(file.getAbsolutePath() + "  selected");
							}
							
							LoadquestionDataBase load = new LoadquestionDataBase();
							try {
								questionList = load.constructGraph(file.getAbsolutePath());
								
							}catch(Exception e1){
								e1.printStackTrace();
							}
						}
					};
					button.setOnAction(event);
					System.out.println((String) file.getAbsolutePath());
					LoadquestionDataBase load = new LoadquestionDataBase();
					load.constructGraph(file.getAbsolutePath());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				Button submitButton = new Button("Submit");
				try {
					EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							homePage(primaryStage, root, mb);
						}
					};
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				root.setTop(mb);
				root.setCenter(button);
				root.setRight(submitButton);
	}

	/**
	 * @param primaryStage
	 */
	private void help(Stage primaryStage) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);
		VBox dialogVbox = new VBox(5);
		File file = new File("Help.txt");
		List<String> text = read(file);
		for (String line : text) {
			dialogVbox.getChildren().add(new Text(line));
		}
		Scene dialogScene = new Scene(dialogVbox, 400, 300);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	private List<String> read(File file) {
		List<String> lines = new ArrayList<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static void main(String[] args) {
		launch(args);

	}
}
