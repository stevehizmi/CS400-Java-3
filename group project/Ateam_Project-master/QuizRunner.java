import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/*
 * Runs the quiz with a GUI
 */
public class QuizRunner {
	ObservableList<String> quizTopics;
	ArrayList<Question> questions;
	TextField numQuestions; // gets desired quiz size
	Label numTotalQuestion; // total questions in topic(?)
	TextField usersAnswer;
	Button generateQuiz;
	ImageView questionImage;

	public QuizRunner(ArrayList<Question> questionList) {

	}

	public void runQuiz() {

	}

	public boolean testAnswer(String answer) {
		return false;
	}

	public String showFinalResults() {
		return null;
	}

}
