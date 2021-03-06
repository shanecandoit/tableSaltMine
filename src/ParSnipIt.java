
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
//        String line = "12 * 13 + 13 * 14";//=338
        String line = "2 * 3 + 4 * 5";//=26
        System.out.println("line = " + line);
        String pre = infixToPrefixConvert(line, false);
        System.out.println("pre = " + pre);

//        String evald = eval(line, true);
//        System.out.println("evald = " + evald);

//        String lastExpr = evalPrefixGetLastExpr(pre);
//        System.out.println("lastExpr = " + lastExpr);

//        String evaldOnce = evalOneStep(pre, lastExpr);
//        System.out.println("evaldOnce = " + evaldOnce);

//        String evalCant = evalOneStep("42", "42");
//        System.out.println("evalCant = " + evalCant);

        System.out.println("expressionList");
        List<String> steps = evalExpressionList(pre);
        System.out.println("-steps.size() = " + steps.size());
        for(String expr:steps){
            System.out.println("-expr = " + expr);
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

    /**
     * Convert an infix expression to a prefix expression
     * @param infix the complex infix expression
     * @param reduce whether to reduce/evaluate or not
     * @return a prefix expression, or an answer if reduce
     */
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
        Stack<String> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(infix);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
//			System.out.println("token = " + token);
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
        String out = "";
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
    
    private static String evalPrefix(String pre){
        System.out.println("evalPrefix(String pre) pre = "+pre);
        if(pre.startsWith("(")){
            pre=pre.substring(1);
        }
        System.out.println("evalPrefix(String pre) pre = "+pre);
        if(pre.endsWith(")")){
            pre=pre.substring(0,pre.length()-1);
        }
        System.out.println("evalPrefix(String pre) pre = "+pre);
        String fn = pre.split(" ")[0];
        System.out.println("-fn = "+fn);
        String lo = pre.split(" ")[1];
        System.out.println("-lo = "+lo);
        String ro = pre.split(" ")[2];
        System.out.println("-ro = "+ro);
        
        double res;
        if(fn.equals("+")){
            res= Double.parseDouble(lo) + Double.parseDouble(ro);
        }else if(fn.equals("-")){
            res= Double.parseDouble(lo) - Double.parseDouble(ro);
        }else if(fn.equals("*")){
            res= Double.parseDouble(lo) * Double.parseDouble(ro);
        }else if(fn.equals("/")){
            res= Double.parseDouble(lo) / Double.parseDouble(ro);
        }else if(fn.equals("%")){
            res= Double.parseDouble(lo) % Double.parseDouble(ro);
        }else if(fn.equals("^")){
            res= Math.pow( Double.parseDouble(lo) , Double.parseDouble(ro) );
        }else {
            System.out.println("unknown function or operator "+fn);
            return "999999999999999999999";
        }
        
        //double resDoub = Double.parseDouble( res );
        int resInt = (int) Math.round(res);
        if( resInt == res ){
            System.out.println(" = "+resInt);
            return ""+resInt;
        }else{
            System.out.println(" = "+res);
            return ""+res;
        }
    }
    
    /**
     * Get a step-by-step list of expressions
     * Evaluate and show your work
     * @param expression A string containing a complex expression
     * @return a list of smaller and smaller expressions, 
     * ending with a single number
     */
    public static List<String> evalExpressionList(String expression){
        
        List<String> evalSteps = new ArrayList();
        evalSteps.add(expression);
        
        String bigExpr = expression;
        String lastExpr=evalPrefixGetLastExpr(bigExpr);
        int count=0;
        
        try{
            while( !bigExpr.equalsIgnoreCase( evalPrefix(lastExpr) ) ){
                System.out.println("!bigExpr.equalsIgnoreCase(lastExpr) = " + !bigExpr.equalsIgnoreCase(lastExpr));
                
                System.out.println("lastExpr = " + lastExpr);
                
                // lastExpr resolving to last number, and a result of operation?
                //(* 4 5) -> 5
                
                bigExpr = evalOneStep(bigExpr, lastExpr);
                System.out.println("bigExpr = " + bigExpr);
                
                evalSteps.add( bigExpr );
                
                lastExpr = evalPrefixGetLastExpr(bigExpr);
                
                count++;
                if(count>5){
                    return evalSteps;
                }
            }
        }catch(Exception e){
            /*
            !bigExpr.equalsIgnoreCase(lastExpr) = true
            start = -1
            end = 0
            Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
                at java.lang.String.substring(Unknown Source)
                at ParSnipIt.evalPrefixGetLastExpr(ParSnipIt.java:253)
                at ParSnipIt.evalExpressionList(ParSnipIt.java:233)
                at ParSnipIt.main(ParSnipIt.java:35)
            */
        }
        
        System.out.println("count = " + count);
        
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

        String evalExprResult = "" + evalPrefix(evalExpr);
		//System.out.println("---evalExprResult = " + evalExprResult);
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
