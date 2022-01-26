/*
 * Defines the public methods within Question
 */
public interface QuestionInterface {
	/**
	 * Checks if an answer is correct
	 * 
	 * @param answer
	 * @return true if corret, false otherwise
	 */
	Boolean testAnswer(String answer);

	/**
	 * getter for the question
	 * 
	 * @return the question
	 */
	String getQuestion();

	/**
	 * getter for the topic of the question
	 * 
	 * @return the topic
	 */
	String getTopic();

	/**
	 * getter for the image of the question
	 * 
	 * @return the image, or null if there is no image
	 */
	String getImage();

	/**
	 * getter for the meta data of the question
	 * 
	 * @return metadata, or null if there isn't any
	 */
	String getMeta();
}
