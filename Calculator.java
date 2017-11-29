

import java.io.IOException;
import java.util.*;

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


        System.out.println("Welcome to the parser!\n");

	System.out.println("Enter \"q\" to exit at any point.");

	while (true) {
	    System.out.println("Please enter an expression: ");
	    try {
		expr = p.expression();

                if (expr.isVars()) {
                    for (String key : variables.keySet()) {
                        System.out.println(key + " = " + variables.get(key));
                    }
                } else if (expr.isQuit()){
                    return;
                } else {
		    System.out.println("input: " + expr);
		    System.out.println("result: " + expr.eval(variables));
                }

	    } catch (SyntaxErrorException e) {
		System.out.print("Syntax Error: ");
		System.out.println(e.getMessage());
	    } catch (IOException e) {
		System.err.println("IO Exception!");
	    }
	}
    }
}
