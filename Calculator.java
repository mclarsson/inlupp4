
import java.io.IOException;

public class Calculator {
    public static void main(String[] args){
        Parser p = new Parser();
	Sexpr expr;

        System.out.println("Welcome to the parser!");

	while (true) {
	    System.out.println("Please enter an expression: ");
	    try {
		expr = p.expression();
		System.out.println("result: " + expr);
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
