import java.util.ArrayList;

/*
 * Contains fields that each question holds
 */
public class Question implements QuestionInterface {

	String question;
	String topic;
	String image;
	String metaData;
	ArrayList<String> options;
	ArrayList<Boolean> isCorrect;

	public Question() {

	}

	@Override
	public Boolean testAnswer(String answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMeta() {
		// TODO Auto-generated method stub
		return null;
	}

}
