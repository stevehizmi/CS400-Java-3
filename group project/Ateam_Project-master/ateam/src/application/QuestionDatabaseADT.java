package application;

import java.io.File;
import java.util.List;

import javafx.collections.ObservableList;

public interface QuestionDatabaseADT {
	
	void addQuestion(String Question);
	
	void saveQuestionsToJSON(File file);
	
	List<Question> getQuestions(String q);

	void loadQuestionsFromJSON(File file);
	
	ObservableList<String> getTopics();
	

}
