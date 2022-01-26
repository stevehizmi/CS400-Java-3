import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


public class PackageManagerTest {
	
	PackageManager pack;


	@Before
	public void setUp() throws Exception {
		pack = new PackageManager();
	}

	@After
	public void tearDown() throws Exception {
		pack = null;
	}

	@Test
    public void test000_construct_graph() throws FileNotFoundException, IOException, ParseException {
       pack.constructGraph("valid.json"); //wont work for some reason
       System.out.println(pack.getAllPackages());
       
       
    }
}