

import java.io.IOException;
import java.util.HashMap;

public class Calculator {
    /**
     * Main function for the program. Starts the calculator and initializes a new parser.
     * Throws exceptions when necessary.
     *
     */
    public static void main(String[] args){
        Parser p = new Parser();
	Sexpr expr;
	HashMap<String, Sexpr> variables = new HashMap<String, Sexpr>();


        System.out.println("Welcome to the parser!");

	while (true) {
	    System.out.println("Please enter an expression: ");
	    try {
		expr = p.expression();
		System.out.println("result: " + expr.eval(variables));
	    } catch (SyntaxErrorException e) {
		System.out.print("Syntax Error: ");
		System.out.println(e.getMessage());
		return;
	    } catch (IOException e) {
		System.err.println("IO Exception!");
	    }
	}
    }
}
