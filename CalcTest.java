
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.ArrayList;

public class CalcTest {

    HashMap<String, Sexpr> variables;

    public CalcTest() {
	variables = new HashMap<String, Sexpr>();
    }

    private InputStream createStream(String s) throws UnsupportedEncodingException {
	return new ByteArrayInputStream(s.getBytes("UTF-8"));
    }

    private Parser createParser(String s) throws UnsupportedEncodingException {
	return new Parser(createStream(s));
    }

    private Sexpr expression(String s) {

	try {
	    Parser p = createParser(s);
	    return p.expression();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// TODO
	assertTrue(false);
	return new Constant(0);
    }

    private double eval(String s) {
	return expression(s).eval(variables).getValue();
    }

    @Test
    public void AdditionTest() {
	assertEquals(eval("1 + 2"), 3.0, 0);
	assertEquals(eval("2 + 1"), 3.0, 0);
	assertEquals(eval("0 + 0"), 0.0, 0);
    }
}
