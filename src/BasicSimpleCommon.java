
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;


/**
 *
 * @author shanecandoit
 */
public class BasicSimpleCommon extends javax.swing.JFrame {

    private List<String> steps;

	/**
	 * Creates new form BasicSimpleCommon
	 */
	public BasicSimpleCommon() {
		setTitle("Evaluation Map");
		setupStyle();
		initComponents();
		jTextPaneInput.setText("2 * 3 + 4 * 5");

		// setup slider and label
		jSliderTimeline.setMaximum(10);
		jSliderTimeline.setValue(0);
		jLabelTimer.setText("# 0");
        steps=null;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneInput = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonBegin = new javax.swing.JButton();
        jSliderTimeline = new javax.swing.JSlider();
        jButtonPrevious = new javax.swing.JButton();
        jButtonEnd = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jLabelTimer = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPaneOutput = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setResizeWeight(0.5);

        jTextPaneInput.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jTextPaneInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPaneInputKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPaneInput);

        jSplitPane2.setLeftComponent(jScrollPane1);

        jTable1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jSplitPane2.setRightComponent(jScrollPane3);

        jSplitPane1.setTopComponent(jSplitPane2);

        jButtonBegin.setText("|<");
        jButtonBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBeginActionPerformed(evt);
            }
        });

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jButtonEnd.setText(">|");
        jButtonEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });

        jButtonNext.setText(">");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jLabelTimer.setText("# ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButtonBegin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPrevious)
                .addGap(18, 18, 18)
                .addComponent(jSliderTimeline, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEnd))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonBegin)
                .addComponent(jButtonPrevious))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonEnd)
                .addComponent(jButtonNext)
                .addComponent(jLabelTimer))
            .addComponent(jSliderTimeline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextPaneOutput.setEditable(false);
        jScrollPane4.setViewportView(jTextPaneOutput);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPaneInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPaneInputKeyReleased
        // TODO add your handling code here:
		int key=evt.getKeyCode();
		if(key==10){
			evaluateText();
		}
				
    }//GEN-LAST:event_jTextPaneInputKeyReleased

    private void jButtonBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBeginActionPerformed
        // TODO add your handling code here:
		System.out.println("jButtonBeginActionPerformed BEGIN");
		int begin = jSliderTimeline.getMinimum();
		jSliderTimeline.setValue(begin);

		String time = "# "+begin;
		jLabelTimer.setText(time);
        
        // show prefixTable of current step in time
        if(steps!=null){
            prefixTable( steps.get(begin) );
    
            // highlight line begin
            clearHighlights();
            highlightLine(begin);
        }
    }//GEN-LAST:event_jButtonBeginActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        // TODO add your handling code here:
		System.out.println("jButtonPreviousActionPerformed PREVIOUS");
		
		int prev = jSliderTimeline.getValue()-1;
		if (prev >= jSliderTimeline.getMinimum()) {
			jSliderTimeline.setValue(prev);


			String time = "# "+prev;
			jLabelTimer.setText(time);
            
            // show prefixTable of current step in time
            if(steps!=null){
                prefixTable( steps.get(prev) );
                
                // highlight line
                clearHighlights();
                highlightLine(prev);
            }
		}
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        // TODO add your handling code here:
		System.out.println("jButtonNextActionPerformed NEXT");

		int next = jSliderTimeline.getValue()+1;
		if (next <= jSliderTimeline.getMaximum()) {
			jSliderTimeline.setValue(next);

			String time = "# "+next;
			jLabelTimer.setText(time);
            
            // show prefixTable of current step in time
            if(steps!=null){
                prefixTable( steps.get(next) );
                
                // highlight line 
                clearHighlights();
                highlightLine(next);
            }
		}
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        // TODO add your handling code here:
		System.out.println("jButtonEndActionPerformed END");
		int end=jSliderTimeline.getMaximum();
		jSliderTimeline.setValue(end);

		String time = "# " + end;
		jLabelTimer.setText(time);
        
        // show prefixTable of current step in time
        if(steps!=null){
            prefixTable( steps.get(end) );
            
            // highlight line
            clearHighlights();
            highlightLine(end);
        }
    }//GEN-LAST:event_jButtonEndActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BasicSimpleCommon().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBegin;
    private javax.swing.JButton jButtonEnd;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JLabel jLabelTimer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSliderTimeline;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPaneInput;
    private javax.swing.JTextPane jTextPaneOutput;
    // End of variables declaration//GEN-END:variables

	private void setupStyle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
			Logger.getLogger(BasicSimpleCommon.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void evaluateText() {
		
            String input = jTextPaneInput.getText();
            input=input.trim();
			System.out.println("input = " + input);
            
            // get prefixed input
            String pre = ParSnipIt.infixToPrefixConvert(input, false);
            
            // get single-steps evals of prefixed input
            List<String> steps = ParSnipIt.evalExpressionList(pre);
            this.steps = steps;
    
            // show the eval-steps
            String output="";
			int i=0;
            for(String step:steps){
				output += step + "\n";
				System.out.println(i + " step = " + step);
				i++;
            }
            jTextPaneOutput.setText(output);
            highlightLine(0);
            
            // setup timeline
            int stepSize = steps.size();
//            System.out.println("stepSize = " + stepSize);
//            System.out.println("steps:");
//            for(String step:steps){
//                System.out.println("-step = " + step);
//            }
            // minus 1 so we dont overshoot on timeline and get AWT errors
            jSliderTimeline.setMaximum( stepSize-1 );

            // show prefixTable of input
            infixTable(input);

            // output shows steps, not final expression
//            String output= evalBasic(input);
//            System.out.println("output = " + output);
//            jTextPaneOutput.setText(output);
	}

	// result of applying binary operator op to two operands val1 and val2
    private static double evalOp(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("/")) return val1 / val2;
        if (op.equals("%")) return val1 % val2;
        if (op.equals("^")) return Math.pow(val1, val2);
        if (op.equals("*")) return val1 * val2;
        throw new RuntimeException("Invalid operator");
    }
	
	private String evalBasic(String input) {
		// from https://algs4.cs.princeton.edu/13stacks/Evaluate.java.html
		// https://algs4.cs.princeton.edu/13stacks/EvaluateDeluxe.java.html
		
		String[] parts = input.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
		System.out.println("parts = " + array2string(parts));
		// parts = [1,+,=,2,]
		// no += operation
		
		
		
		Queue<String> tokens = new ArrayDeque();
		for(String part: parts){
			tokens.add(part);
		}
		System.out.println("tokens# = " + tokens.size());
		
		// precedence order of operators
        TreeMap<String, Integer> precedence = new TreeMap();
        precedence.put("(", 0);   // for convenience with algorithm
        precedence.put(")", 0);  
        precedence.put("+", 1);   // + and - have lower precedence than * and /
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("%", 2);
        precedence.put("^", 3);

        Stack<String> ops  = new Stack();
        Stack<Double> vals = new Stack();

        while (!tokens.isEmpty()) {

            // read in next token (operator or value)
            String s = tokens.remove();

            // token is a value
            if (!precedence.containsKey(s)) {
                vals.push(Double.parseDouble(s));
                continue;
            }

            // token is an operator
            while (true) {

                // the last condition ensures that the operator with higher precedence is evaluated first
                if (ops.isEmpty() || s.equals("(") || (precedence.get(s) > precedence.get(ops.peek()))) {
                    ops.push(s);
                    break;
                }

                // evaluate expression
                String op = ops.pop();

                // but ignore left parentheses
                if (op.equals("(")) {
                    assert s.equals(")");
                    break;
                }

                // evaluate operator and two operands and push result onto value stack
                else {
                    double val2 = vals.pop();
                    double val1 = vals.pop();
                    vals.push(evalOp(op, val1, val2));
                }
            }
        }

        // finished parsing string - evaluate operator and operands remaining on two stacks
        while (!ops.isEmpty()) {
            String op = ops.pop();
            double val2 = vals.pop();
            double val1 = vals.pop();
            vals.push(evalOp(op, val1, val2));
        }

        // last value on stack is value of expression
        return vals.pop().toString();
	}
	
	public static String array2string(String[] in){
		String res="[";
		for(String s:in){
			res+=s+",";
		}
		res+="]";
		return res;
	}
    public static String array2string(char[] in){
		String res="[";
		for(char s:in){
			res+=s+",";
		}
		res+="]";
		return res;
	}

    private void prefixTable(String prefix) {
        System.out.println("prefixTable(String prefix) prefix = " + prefix);
//		String inline = ParSnipIt.infixToPrefixConvert(input, false);
//		System.out.println("inline = " + inline);
        String tree = ParSnipIt.prefixToTree( prefix );
        System.out.println("tree = " + tree);
        
        
        clearTableCells();
        
        int deep=0;
        int down=0;
        String buff="";
		char indent='-';
        char[] chars = tree.toCharArray();
//        System.out.println("chars = " + array2string(chars));
        for(char ch:chars){
//            System.out.println("ch = " + ch);
            if(ch==indent){
                deep++;
                buff="";
            }
            if(ch=='\n'){
                down++;
                // reset depth each row
                deep=0;
                buff="";
            }
            buff+=ch;
//            System.out.println("buff = " + buff);
            if(buff!=null && buff.length()>1 && buff.startsWith("(")){
                buff=buff.substring(1);
            }
            String put = buff.replaceAll(""+indent, "");
//            System.out.println("put = " + put);
//            System.out.println("down = " + down);
//            System.out.println("deep = " + deep);
            jTable1.setValueAt(put, down, deep);
        }
    }
    
    private void infixTable(String input) {
        System.out.println("infixTable(String input) = " + input);
		String prefix = ParSnipIt.infixToPrefixConvert(input, false);
		System.out.println("prefix = " + prefix);
        prefixTable(prefix);
    }

    private static int getLineEndIdx(String text, int lineNo) {
        int lineEndIdx = 0;
        for(int i = 1; 
                i <= lineNo && lineEndIdx + 1 < text.length(); 
                i++){
            lineEndIdx = text.indexOf('\n', lineEndIdx + 1) ;
        }
        return lineEndIdx;
    }

    private void highlightLine(int begin) {
        System.out.println("highlightLine(int begin) begin = " 
                + begin);
        try{
            Highlighter hilit = new DefaultHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
            jTextPaneOutput.setHighlighter(hilit);
            int start = getLineEndIdx(jTextPaneOutput.getText(), begin);
            System.out.println("start = " + start);
            int end = getLineEndIdx(jTextPaneOutput.getText(), begin+1 );
            System.out.println("end = " + end);
            hilit.addHighlight(
                start,
                end,
                painter);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void clearHighlights() {
        Highlighter.Highlight[] highlights 
                = jTextPaneOutput.getHighlighter().getHighlights();
        for(Highlight hili : highlights){
            jTextPaneOutput.getHighlighter().removeHighlight(hili);
        }
    }

    private void clearTableCells() {
        int cols = jTable1.getColumnCount();
        int rows = jTable1.getRowCount();
        for( int r=0; r<rows; r++){
            for( int c=0; c<cols; c++){
                jTable1.setValueAt(null, r, c);
            }
        }
    }
}