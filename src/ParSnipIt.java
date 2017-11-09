
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author s137p379
 */
public class ParSnipIt {

	// postfix
	//http://www.java2s.com/Code/Java/Collections-Data-Structure/Convertsinfixarithmeticexpressionstopostfix.htm
	private Deque<String> theStack;
	private String input;
	private String output = "";

	public ParSnipIt(String in) {
		input = in;
		int stackSize = input.length();
		theStack = new ArrayDeque(stackSize);
	}

	public String doTrans() {
		for (int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			switch (ch) {
				case '+':
				case '-':
					gotOper(ch, 1);
					break; //   (precedence 1)
				case '*': // it's * or /
				case '/':
					gotOper(ch, 2); // go pop operators
					break; //   (precedence 2)
				case '(': // it's a left paren
					theStack.push("" + ch); // push it
					break;
				case ')': // it's a right paren
					gotParen(ch); // go pop operators
					break;
				default: // must be an operand
					output = output + ch; // write it to output
					break;
			}
		}
		while (!theStack.isEmpty()) {
			output = output + theStack.pop();
		}
		System.out.println(output);
		return output; // return postfix
	}

	public void gotOper(char opThis, int prec1) {
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop().charAt(0);
			if (opTop == '(') {
				theStack.push("" + opTop);
				break;
			}// it's an operator
			else {// precedence of new op
				int prec2;
				if (opTop == '+' || opTop == '-') {
					prec2 = 1;
				} else {
					prec2 = 2;
				}
				if (prec2 < prec1) // if prec of new op less
				{ //    than prec of old
					theStack.push("" + opTop); // save newly-popped op
					break;
				} else // prec of new not less
				{
					output = output + opTop; // than prec of old
				}
			}
		}
		theStack.push("" + opThis);
	}

	public void gotParen(char ch) {
		while (!theStack.isEmpty()) {
			char chx = theStack.pop().charAt(0);
			if (chx == '(') {
				break;
			} else {
				output = output + chx;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String input = "1+2*4/5-7+3/6";
		String output;
		ParSnipIt theTrans = new ParSnipIt(input);
		output = theTrans.doTrans();
		String pref = theTrans.doTrans();
		System.out.println("Postfix is " + output + '\n');
	}

	/*
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
        System.out.println("Enter the expression that you want to convert to prefix.");
        String expression = input.next();
		System.out.println("expression = " + expression);
		
        ParSnipIt translator = new ParSnipIt(expression);
        System.out.println(translator.translate());
	}

	static private String expression;
	private Stack<Character> stack = new Stack();

	//Constructor
	public ParSnipIt(String infixExpression)
	{
		expression = infixExpression;
	}



	public String translate()
	{
		System.out.println("translate");
		
		//Declare Method Variables
//		String input = "";
		String output = "";
		char character = ' ';
//		char nextCharacter = ' ';


		for(int limit = expression.length() -1; limit >= 0 ; limit--)
		{
			System.out.println("limit = " + limit);
			
			character = expression.charAt(limit);
			System.out.println("character = " + character);

			if(isOperator(character))
			{
				output = output + character + " ";
				System.out.println("output = " + output);
			}   
			else if(character == '(')
			{
				stack.push(character);
			}
			else if(character == ')')
			{
				//while(!stack.top().equals('('))
				System.out.println("stack.size() = " + stack.size());
				while(!stack.elementAt(stack.size()).equals('('))
					output = output + stack.pop() + " ";
				if(stack.size()>0){
					stack.pop();
				}
			}
			else
			{
				if(Character.isDigit(character) && (limit + 1) < limit && Character.isDigit(expression.charAt(limit+1)))
				{
					stack.push(character);
					stack.push(expression.charAt(limit+1));
				}
				else if(Character.isDigit(character))
				{
					stack.push(character);
				}   
				else
				{
					output = output + character;
				}
			}
		}

		while(!stack.isEmpty())
		{
			output = output + stack.pop() + " ";
		}

		return output;
	}

	//Check priority on characters
	public static int precedence(char operator)
	{
		if(operator == '+' || operator =='-')
			return 1;
		else if(operator == '*' || operator == '/')
			return 2;
		else
			return 0;
	}

	public boolean isOperator(char element)
	{
		if(element == '*' || element == '-' || element == '/' || element == '+')
			return true;
		else
			return false;
	}

	 */
 /*
	public void run ( Queue<String> scanner ) {
		//scanner.remove();
		statement( scanner );
	}


	private void statement ( Queue<String> scanner ) {
		//   statement = { expression  ";" } "."  
		while(scanner.peek() != "." ) {
		int value = expression( );
		System.out.println("=> " + value);
		scanner.remove();  // flush ";"
		} 
	} 


	private int expression ( ) {
		//    expression = term { ( "+" | "-" ) term }
		int left = term( );
		while (scanner.token == Token.plusop || 
		   scanner.token == Token.minusop) {
		int saveToken = scanner.token;
		scanner.getToken( );
		switch (saveToken) {
			case Token.plusop:
			left += term( );
			break;
			case Token.minusop:
			left -= term( );
			break;
		} // switch
		} // while
		return left;
	} // expression


	private int term ( ) {
		//    term = factor { ( "*" | "/" ) factor }
		int left = factor( );
		while (scanner.token == Token.timesop || 
		   scanner.token == Token.divideop) {
		int saveToken = scanner.token;
		scanner.getToken( );
		switch (saveToken) {
			case Token.timesop:
			left *= factor( );
			break;
			case Token.divideop:
			left /= factor( );
			break;
		} // switch
		} // while
		return left;
	} // term


	private int factor ( ) {
		//    factor    = number | "(" expression ")"
		int value = 0;
		switch (scanner.token) {
		case Token.number:
			value = scanner.number( );
			scanner.getToken( );  // flush number
			break;
		case Token.lparen:
			scanner.getToken( );
			value = expression( );
			if (scanner.token != Token.rparen)
			scanner.error("Missing ')'");
			scanner.getToken( );  // flush ")"
			break;
		default:
			scanner.error("Expecting number or (");
			break;
		} // switch
		return value;
	} // factor
	 */
 /* This program illustrates recursive descent parsing using a
   pure procedural approach.

   The grammar:

   statement = { expression  ";" } "."  
   expression = term { ( "+" | "-" ) term }
   term      = factor { ( "*" | "/" ) factor }
   factor    = number | "(" expression ")"
    
	 */
}
