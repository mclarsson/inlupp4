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

    public double expression() throws IOException{
        double sum = term();
        st.nextToken();
        while (st.ttype == '+' || st.ttype == '-'){
            if(st.ttype == '+'){
                sum += term();
            }else{
                sum -= term();
            }
            st.nextToken();
        }
        st.pushBack();
        return sum;
    }

    private double term() throws IOException{
        double prod = factor();
        while (st.nextToken() == '*'){
            prod *= factor();
        }
        st.pushBack();
        return prod;
    }

    private double factor() throws IOException{
        double result;
        if(st.nextToken() != '('){
            st.pushBack();
            result = number();
        }else{
            result = expression();
            if(st.nextToken() != ')'){
                throw new SyntaxErrorException("expected ')'");
            }
        }
        return result;
    }

    private double number() throws IOException{
        if(st.nextToken() != st.TT_NUMBER){
            throw new SyntaxErrorException("Expected number");
        }
        return st.nval;
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
