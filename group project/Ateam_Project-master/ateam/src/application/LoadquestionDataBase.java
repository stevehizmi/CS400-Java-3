package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.scene.Node;

public class LoadquestionDataBase {
  // after parse the json, where to put, how to create a database?

  // fields
  ArrayList<Question> storeQuestion = null; // creating a data field for storing the questions.


  /**
   * Reading the json file and parsing the information it contains
   * 
   * @param jsonFilepath
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
 * @throws org.json.simple.parser.ParseException 
 * @throws org.json.simple.parser.ParseException 
   */
  public List<Question> constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {

    // check if the file path is valid.
    File tempFile = new File(jsonFilepath);
    boolean checkValid = tempFile.exists();


    // adding them
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jo = (JSONObject) obj;
    JSONArray packages = (JSONArray) jo.get("questionArray");



    // parse the json file based on info in each array.
    for (int i = 0; i < packages.size(); ++i) {
      JSONObject jsonpackage = (JSONObject) packages.get(i);

      JSONArray choiceArray = (JSONArray) jsonpackage.get("choiceArray");


      // Store the element in json array into arraylist.
      ArrayList<String> choicesArray = new ArrayList<String>();
      // Add every predecessor vertex of packageName2 and the edges between them into the graph.
      for (int j = 0; j < choiceArray.size(); ++j) {
        // add the vertex into the adjacency list.
        String choice = choiceArray.get(j).toString();
        // Adjacency lists add all depends no need to check duplication.
        choicesArray.add(choice);
      }


      String questionMetaData = (String) jsonpackage.get("meta-data");
      String questionText = (String) jsonpackage.get("questionText");
      String topic = (String) jsonpackage.get("topic");
      String image = (String) jsonpackage.get("image");
      System.out.print("click");
      // add the successor vertices into graph.
      // create the array list hold the dependency list (value) list.
      storeQuestion.add(new Question(questionMetaData, questionText, topic, image, choicesArray));

    }
    return storeQuestion;
  }

}
