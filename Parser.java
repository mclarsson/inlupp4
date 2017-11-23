import java.io.StreamTokenizer;
import java.io.IOException;

class Parser{
    StreamTokenizer st;

    public Parser(){
        st = new StreamTokenizer(System.in);
        st.ordinaryChar('-');
        st.eolIsSignificant(true);

	System.out.println("\nNew Parser");
	Variable x = new Variable("x");
	Sin si = new Sin(x);
	Cos co = new Cos(si);
	Exp e = new Exp(co);
	Negative n = new Negative(e);
	Constant y = new Constant(42.0);
	Addition a = new Addition(n, y);
	Multiplication m = new Multiplication(x, a);
	Subtraction s = new Subtraction(y, m);
	Division d = new Division(m, s);

	System.out.println(d);
    }

    public Sexpr expression() throws IOException{
	System.out.println(" -- expression -- ");
        Sexpr expr = term();
	st.nextToken();
        while (st.ttype == '+' || st.ttype == '-'){

	    if (st.ttype == '+') {
                expr = new Addition(expr, term());
            }

	    st.nextToken();
        }

        return expr;
    }

    private Sexpr term() throws IOException{
	System.out.println(" -- term -- ");
        Sexpr prod = factor();
        while (st.nextToken() == '*'){
            prod = new Multiplication(prod, factor());
        }

        st.pushBack();
        return prod;
    }

    private Sexpr factor() throws IOException{
        Sexpr result;
	System.out.println(" -- factor -- ");
        if (st.nextToken() != '(') {
            st.pushBack();
            result = atom();
        } else {

            result = expression();

            if (st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
        }

        return result;
    }

    private Sexpr atom() throws IOException {
	System.out.println(" -- atom -- ");
	st.nextToken();
        if (st.ttype == st.TT_NUMBER) {
	    System.out.println(" -- constant " + st.nval + " -- ");
	    return new Constant(st.nval);
        } else {
	    System.out.println(" -- variable " + st.sval + " -- ");
	    return new Variable(st.sval);
	}
    }
}

class SyntaxErrorException extends RuntimeException{
    public SyntaxErrorException(){
        super();
    }
    public SyntaxErrorException(String msg){
        super(msg);
    }
}
