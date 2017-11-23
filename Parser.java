import java.io.StreamTokenizer;
import java.io.IOException;

class Parser{
    StreamTokenizer st;

    public Parser(){
        st = new StreamTokenizer(System.in);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
        st.eolIsSignificant(true);
    }

    public Sexpr expression() throws IOException{
	System.out.println("expression");
        Sexpr expr = term();
	st.nextToken();
        while (st.ttype == '+' || st.ttype == '-') {

	    if (st.ttype == '+') {
                expr = new Addition(expr, term());
            } else {
		expr = new Subtraction(expr, term());
	    }

	    st.nextToken();
        }

	st.pushBack();

        return expr;
    }

    private Sexpr term() throws IOException{
	System.out.println("term");
        Sexpr term = factor();

        while (st.nextToken() == '*') {
	    term = new Multiplication(term, factor());
	}

	st.pushBack();

	while (st.nextToken() == '/') {
	    System.out.println("div");
	    term = new Division(term, factor());
        }

	st.pushBack();

        return term;
    }

    private Sexpr factor() throws IOException{
	System.out.println("factor");
        Sexpr result;
        if (st.nextToken() != '(' && st.ttype != st.TT_WORD) {
            st.pushBack();
            result = atom();
        } else if (st.ttype == st.TT_WORD) {
	    System.out.println(" -- UNARY");
	    System.out.println(st.sval);
	    result = new Sin(expression());

	} else {
	    System.out.println(" -- PARENT");
            result = expression();

            if (st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
        }

        return result;
    }

    private Sexpr atom() throws IOException {
	System.out.println("atom");
	st.nextToken();
        if (st.ttype == st.TT_NUMBER) {
	    return new Constant(st.nval);
        } else {
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
