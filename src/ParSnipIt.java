
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author s137p379 Prefix strings borrowed from:
 * https://github.com/jetpackman/Infix-to-Prefix-Java/blob/master/src/Prefixer.java
 */
public class ParSnipIt {

	public static void main(String[] args) {

//        String line = "1 + 2 * A + sin(6)";
		String line = "12 * 13 + 13 * 14";//=338
		System.out.println("line = " + line);
		String pre = infixToPrefixConvert(line, false);
		System.out.println("pre = " + pre);

		String evald = eval(line, true);
		System.out.println("evald = " + evald);

		String evalOnce = prefixGetDeepestLast(pre);
		System.out.println("evalOnce = " + evalOnce);

		String evaldOnce = evalOneStep(pre, evalOnce);

		String tree = prefixToTree(pre);
		System.out.println("tree = " + tree);
	}

	public static boolean isOperand(String s) {
		return !(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("(") || s.equals(")"));
	}

	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s.trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String operationCombine(Stack<String> operatorStack, Stack<String> operandStack, boolean reduce) {
		String operator = operatorStack.pop();
		String rightOperand = operandStack.pop();
		String leftOperand = operandStack.pop();
		if (reduce && isNumber(rightOperand) && isNumber(leftOperand)) {
			int left = Integer.parseInt(leftOperand);
			int right = Integer.parseInt(rightOperand);
			int result = 0;
			if (operator.equals("+")) {
				result = left + right;
			} else if (operator.equals("-")) {
				result = left - right;
			} else if (operator.equals("*")) {
				result = left * right;
			} else if (operator.equals("/")) {
				result = left / right;
			}
			return "" + result;

		}
		String operand = "(" + operator + " " + leftOperand + " " + rightOperand + ")";
		return operand;
	}

	public static int rank(String s) {
		if (s.equals("+") || s.equals("-")) {
			return 1;
		} else if (s.equals("/") || s.equals("*")) {
			return 2;
		} else {
			return 0;
		}
	}

	public static String infixToPrefixConvert(String infix, boolean reduce) {
		Stack<String> operandStack = new Stack<String>();
		Stack<String> operatorStack = new Stack<String>();

		StringTokenizer tokenizer = new StringTokenizer(infix);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			//System.out.println("token = " + token);
			if (isOperand(token)) {
				operandStack.push(token);
			} else if (token.equals("(") || operatorStack.isEmpty()
					|| rank(token) > rank(operatorStack.peek())) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				while (!operatorStack.peek().equals("(")) {
					operandStack.push(operationCombine(operatorStack, operandStack, reduce));
				}
				operatorStack.pop();
			} else if (rank(token) <= rank(operatorStack.peek())) {
				while (!operatorStack.isEmpty() && rank(token) <= rank(operatorStack.peek())) {
					operandStack.push(operationCombine(operatorStack, operandStack, reduce));
				}
				operatorStack.push(token);
			}
		}
		while (!operatorStack.isEmpty()) {
			//System.out.println("operandStack.size() = " + operandStack.size());
			operandStack.push(operationCombine(operatorStack, operandStack, reduce));
			//System.out.println("operandStack.peek() = " + operandStack.peek());
		}
		return (operandStack.peek());
	}

	public static String eval(String infix, boolean reduce) {
		Stack<String> operandStack = new Stack<String>();
		Stack<String> operatorStack = new Stack<String>();

		StringTokenizer tokenizer = new StringTokenizer(infix);
		String lastOperand = "";
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
//			System.out.println("token = " + token);
			lastOperand = token;
			if (isOperand(token)) {
				operandStack.push(token);
			} else if (token.equals("(") || operatorStack.isEmpty()
					|| rank(token) > rank(operatorStack.peek())) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				while (!operatorStack.peek().equals("(")) {
					operandStack.push(operationCombine(operatorStack, operandStack, reduce));
				}
				operatorStack.pop();
			} else if (rank(token) <= rank(operatorStack.peek())) {
				while (!operatorStack.isEmpty() && rank(token) <= rank(operatorStack.peek())) {
					operandStack.push(operationCombine(operatorStack, operandStack, reduce));
				}
				operatorStack.push(token);
			}
		}
		while (!operatorStack.isEmpty()) {
			operandStack.push(operationCombine(operatorStack, operandStack, reduce));
		}
//		System.out.println("operandStack.elements().toString() = "  + a2s( operandStack.toArray( ) ));
//		System.out.println("operatorStack.elements().toString() = " + a2s(operatorStack.toArray()));
//		operandStack.push(operationCombine(operatorStack, operandStack, true));
//		System.out.println("operandStack.elements().toString() = " + a2s(operandStack.toArray()));
//		System.out.println("operatorStack.elements().toString() = " + a2s(operatorStack.toArray()));

		return operandStack.peek();
	}

	public static String prefixToTree(String pre) {
		System.out.println("prefixToTree pre = " + pre);
		System.out.println("pre.length() = " + pre.length());
		//pre = (+ (+ 1 (* 2 3)) sin(6))

		String indent = "-";
		String out = "\n";
		int deep = 0;
		for (char ch : pre.toCharArray()) {
			if (ch == '(') {
				// down and in
				deep++;
				out += ch;
			} else if (ch == ')') {
				// down and out
				deep--;
				out += ch;
			} else if (ch == ' ') {
				out += "\n";
				for (int i = 0; i < deep; i++) {
					out += indent;
				}
			} else {
				out += ch;
			}
		}
		System.out.println("prefixToTree out = " + out);
		return out;
	}

	/*
    
tree = 
(+
-(+
--1
--(*
---2
---A))
-sin(6))
	 */
	private static String a2s(Object[] toArray) {
		String out = "[";
		for (Object o : toArray) {
			out += o + ",";
		}
		out += "]";
		return out;
	}

	private static String a2sNoArrayNotation(Object[] toArray) {
		String out = "";
		for (Object o : toArray) {
			out += o + " ";
		}
		//out+="";
		return out;
	}

	private static String prefixGetDeepestLast(String line) {
		int start = line.lastIndexOf("(");
		System.out.println("start = " + start);
		int end = line.indexOf(")", start) + 1;
		System.out.println("end = " + end);
		String deepestLast = line.substring(start, end);
		System.out.println("deepestLast = " + deepestLast);
		String deepestNoParens = deepestLast.substring(1, deepestLast.length() - 1);
		System.out.println("deepestNoParens = " + deepestNoParens);

		String[] parts = deepestNoParens.split(" ");
		String op = parts[0];
		System.out.println("op = " + op);
		String left = parts[1];
		System.out.println("left = " + left);
		String right = parts[2];
		System.out.println("right = " + right);

		// minus must be escaped
		String binaries = "[+\\-*/%^]";
		if (op.matches(binaries)) {
			System.out.println("binary op = " + op);
		} else {
			System.out.println("non-binary function op = " + op);
		}

		return "(" + op + " " + left + " " + right + ")";
	}

	private static String evalOneStep(String pre, String evalOnce) {
		System.out.println("evalOneStep(String pre, String evalOnce)");
		System.out.println("pre = " + pre);
		System.out.println("evalOnce = " + evalOnce);

		// replace only last instance of evealOnce
		int lastOccur = pre.lastIndexOf(evalOnce);

		String before = pre.substring(0, lastOccur);
		System.out.println("before = " + before);
		String after = pre.substring(lastOccur + pre.length());
		System.out.println("after = " + after);

		String evalExpr = "" + eval(evalOnce, true);
		System.out.println("evalExpr = " + evalExpr);

		String result = before + evalExpr + after;
		System.out.println("result = " + result);
		return result;
	}

}
