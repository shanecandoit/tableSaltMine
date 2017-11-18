
import java.util.ArrayList;
import java.util.List;
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

        String lastExpr = evalPrefixGetLastExpr(pre);
        System.out.println("lastExpr = " + lastExpr);

        String evaldOnce = evalOneStep(pre, lastExpr);
        System.out.println("evaldOnce = " + evaldOnce);

        String evalCant = evalOneStep("42", "42");
        System.out.println("evalCant = " + evalCant);

        System.out.println("expressionList");
        List<String> steps = evalExpressionList(pre);
        System.out.println("steps.size() = " + steps.size());
        for(String expr:steps){
            System.out.println("expr = " + expr);
        }
        
//		String tree = prefixToTree(pre);
//		System.out.println("tree = " + tree);
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
        Stack<String> operandStack = new  Stack<String>();
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
    
    private static List<String> evalExpressionList(String expression){
        
        List<String> evalSteps = new ArrayList();
        evalSteps.add(expression);
        String bigExpr = expression;
        String result="";
        int count=0;
        do{
            String lastExpr = evalPrefixGetLastExpr(bigExpr);
            result = evalOneStep(bigExpr, lastExpr);
            bigExpr = result;
            count++;
            if(count>5){
                return evalSteps;
            }
        }while( !bigExpr.equalsIgnoreCase(result));
        
        System.out.println("count = " + count);
        System.out.println("result = " + result);
        
        return evalSteps;
    }

    private static String evalPrefixGetLastExpr(String line) {
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

    private static String evalOneStep(String wholeExpr, String evalExpr) {
        System.out.println("evalOneStep(String wholeExpr, String evalExpr)");
        System.out.println("wholeExpr = " + wholeExpr);
        // (+ (* 12 13) (* 13 14))
        System.out.println("evalExpr  = " + evalExpr);
        // (* 13 14)

        // replace only last instance of evalExpr
        // there may be more than one
        int lastOccur = wholeExpr.lastIndexOf(evalExpr);

        String before = wholeExpr.substring(0, lastOccur);
//		System.out.println("before = " + before);
        // (+ (* 12 13) 
        int off = lastOccur + evalExpr.length();
//                System.out.println("off = " + off);
        // 22
        String after = wholeExpr.substring(off);
//		System.out.println("after = " + after);
        // )

        String evalExprResult = "" + eval(evalExpr, true);
//		System.out.println("evalExprResult = " + evalExprResult);
        // evalExprResult = 14)
        // remove trailing paren, why is it there?
        if (evalExprResult.endsWith(")")) {
            evalExprResult = evalExprResult.substring(0, evalExprResult.length() - 1);
        }
//		System.out.println("evalExprResult = " + evalExprResult);

        String nextExpr = before + evalExprResult + after;
        System.out.println("nextExpr = " + nextExpr);
        //nextExpr = (+ (* 12 13) 14)
        return nextExpr;
    }

}
