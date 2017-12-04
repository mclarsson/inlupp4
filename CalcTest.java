
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
        assertEquals(eval("1 + 1"), 2.0, 0);
    }
    @Test
    public void SubtractionTest() {
        assertEquals(eval("1 - 4"), (-3.0), 0);
        assertEquals(eval("1 -- 1"), 2.0, 0);
        assertEquals(eval("-1 - 2"), (-3.0), 0);
        assertEquals(eval("-1 --2"), 1.0, 0);
    }
    @Test
    public void MultiplicationTest() {
        assertEquals(eval("1 * 2"), 2.0, 0);
        assertEquals(eval("1 * 0"), 0.0, 0);
        assertEquals(eval("-4 * 10"), (-40.0), 0);
    }
    @Test
    public void DivisionTest() {
        assertEquals(eval("10 / 2"), 5.0, 0);
        assertEquals(eval("1 / 0"), Double.POSITIVE_INFINITY, 0);
        assertEquals(eval("2 / 0.5"), 4, 0) ;
        assertEquals(eval("-5 / 5"), -1, 0);
    }
    @Test
    public void NegationTest() {
        assertEquals(eval("4 -------5"), -1, 0);
        assertEquals(eval("----------------------------6"), 6, 0);
    }
    @Test
    public void UnaryTest() {
        assertEquals(eval("cos("+ Math.PI + ")"), -1.0, 0);
        assertEquals(eval("sin(0)"), 0, 0);
        assertEquals(eval("exp(1)"), Math.E, 0);
        assertEquals(eval("1 = a = b = c = d = e"), 1.0, 0);
        assertEquals(eval("a"), 1.0, 0);
        assertEquals(eval("b"), 1.0, 0);
        assertEquals(eval("e"), 1.0, 0);
        assertEquals(eval("log(100000000000)"), 11, 0);            
    }
    @Test
    public void WrongInput() {
        String[] tests = {"1++",
                          "foo(x + y)",
                          "1**",
                          "1/////////",
                          "1+",
                          "2352352&",
                          "1 + 4 * 5 / 3 + #"};
        for (String test : tests) {
            try {
                createParser(test).expression();
                assertTrue(false);
            } catch (Exception e) {}
        }
    }
    
}
