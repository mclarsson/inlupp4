
import java.io.IOException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;

public class CalcTest {
    @Test
    public void testParser() {
	String filename = "./parserInput.txt";
	Parser p = null;

	try {
	    p = new Parser(filename);
	} catch (IOException e ) {
	    System.out.println("FILE NOT FOUND");
	}

	Sexpr expr;
	while (true) {
	    try {
		expr = p.expression();
		System.out.println("\nresult: " + expr);
	    } catch (SyntaxErrorException e) {
		System.out.println(e.getMessage());
		return;
	    } catch (IOException e) {
		System.err.println("IO Exception!");
		return;
	    }
	 }
    }
}
