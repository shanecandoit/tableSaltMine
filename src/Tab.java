
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;




/**
 *
 * @author s137p379
 */
public class Tab extends javax.swing.JFrame {

	/**
	 * Creates new form Tab
	 */
	public Tab() {
		style();
		initComponents();
		popUps();
		
		cells = new TreeMap();
		lastHash="";
		
		loadLastFile();
	}
	
	private String title = "Data flow editor";
	private Map<String, String> cells;
	private String lastHash;

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("Insert Row");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Insert Column");
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(0.5);

        jTextPane1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        jSplitPane2.setTopComponent(jScrollPane2);

        jTextPane2.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jScrollPane3.setViewportView(jTextPane2);

        jSplitPane2.setRightComponent(jScrollPane3);

        jSplitPane1.setLeftComponent(jSplitPane2);

        jTable1.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "A", "B", "C", "D", "E", "F", "G"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTable1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jSplitPane1.setRightComponent(jScrollPane1);

        jTabbedPane1.addTab("tab1", jSplitPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // tab=9, enter=10
		int key=evt.getKeyCode();
		if(key==9||key==10){
			tableUpdate();
		}
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        //System.out.println("jTable1KeyPressed");
		//tableUpdate();
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        //System.out.println("jTable1MouseExited");
		//tableUpdate();
    }//GEN-LAST:event_jTable1MouseExited

    private void jTable1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTable1CaretPositionChanged
//        System.out.println("jTable1CaretPositionChanged");
//		tableUpdate();
    }//GEN-LAST:event_jTable1CaretPositionChanged

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
//	    System.out.println("jTable1MouseReleased");
		tableUpdate();
    }//GEN-LAST:event_jTable1MouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Tab().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables

	private void style() {
		setTitle(title);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void tableUpdate() {
		
		int cols = jTable1.getColumnCount();
		int rows = jTable1.getRowCount();
		
		
		Map oldCells = cells;
		cells = new TreeMap();
		
		for(int r=0; r<rows; r++){
			for(int c=1; c<cols; c++){
				
				String header = jTable1.getColumnName(c);
				String loc = header+(r+1);
				
				// read from table gui
				String val = (String) jTable1.getValueAt(r, c);
				
				// read from oldVals
				String oldVal = (String) oldCells.get(loc);
				if(oldVal!=null&&oldVal.startsWith("=")){
					// dont overwrite cells with calculated value
					val=oldVal;
				}
				
				if(val==null || val.isEmpty()){
					continue;
				}
				
				
				//System.out.println(loc+" r"+r+" c"+header+" "+val);
				
				cells.put(loc, val);
			}
		}
		
		listCells();
	}

	private void listCells() {
		System.out.println("listCells");
		
		String cellMap = "";
		
		for(String k:cells.keySet()){
			String val = cells.get(k);
//			System.out.println("k"+k+" v"+val);
			cellMap+=k+"\t"+val+"\n";
		}
		
		jTextPane1.setText(cellMap);
		
		try {
			String sha256 = hash(cellMap);
			if(lastHash.equalsIgnoreCase(sha256)){
				// dont need to save, same as last time
				// dont save but calculate some values
				evalExpressions();
				return;
			}else{
				lastHash=sha256;
				int thisTab = jTabbedPane1.getTabPlacement();
				System.out.println("thisTab = " + thisTab);
				jTabbedPane1.setTitleAt(thisTab-1, lastHash);
			}
			
			System.out.println("saving cells to:"+sha256);
			String tempFile = System.getProperty("user.home") + File.separator + ".tabl";
			PrintWriter writer = new PrintWriter(tempFile, "UTF-8");
			writer.println(cellMap);
			writer.print(sha256);
			writer.close();
			System.out.println("saved");
			
		} catch (NoSuchAlgorithmException | FileNotFoundException | UnsupportedEncodingException ex) {
			Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// deal with dependencies
		Map<String, List<String>> deps = checkDependencies( cells );
		
		// evaluate expressions
		evalExpressions();
		
		//		merkle(cells);
	}
	
	
	public static String hashTableLines(String tableLines){
		try {
			return hash(tableLines);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "";
	}
	
	
/*
	merkle over the treemap
	*/
//	private void merkle(Map<String, String> map) {
//		System.out.println("map #"+map.size());
//	}

	private void popUps() {
		
		jTable1.setComponentPopupMenu(jPopupMenu1);
	}

	private static String hash(String locVals) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(locVals.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
		return encoded;
	}

	private Map<String,List<String>> checkDependencies(Map<String, String> cells) {
		
		Map<String,List<String>> cellDependencies = new TreeMap();
		
		// k is an address, B2
		for (String k: cells.keySet()) {
			
			String val = cells.get(k);
			
			if(val.startsWith("=")){
				// this is an equation
				
				String[] vars = getVars( val );
				
				cellDependencies.put(k, list(vars));
			}
		}
		return cellDependencies;
	}
	
	public static String[] filterVars(String[] in){
		String valid = "[a-zA-Z]+[0-9]+";
		List<String> res = new ArrayList<String>();
		for(String s:in){
			if( s.matches(valid) ){
				res.add(s);
			}
		}
		return res.toArray( new String[res.size()] );
	}
	
	public static String array(String[] in){
		String res="[";
		for(String s:in){
			res+=s+",";
		}
		res+="]";
		return res;
	}
	public static List<String> list(String[] in){
		List<String> res=new ArrayList();
		for(String s:in){
			res.add(s);
		}
		return res;
	}

	private void loadLastFile() {
		String path = System.getProperty("user.home") + File.separator + ".tabl";
		File file = new File(path);
		if( file.canRead() ){
			System.out.println("reading "+path);
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
				   // process the line.
				   System.out.println("line = " + line);
				   
				   if( line.contains("\t")){
						String key = line.split("\t")[0];
						String val = line.split("\t")[1];
						cells.put(key, val);
				   }else{
					   System.out.println("trash line = " + line);
				   }
				}
			} catch (Exception ex) {
				Logger.getLogger(Tab.class.getName()).log(Level.SEVERE, null, ex);
			}
			
			listCells();
			fromCellsToTable();
		}
	}

	private void fromCellsToTable() {
		for(String k:cells.keySet()){
			
			int col = colFromId(k);
			System.out.println("col = " + col);
			
//			String colAlpha = k.replaceAll("[0-9]", "");
//			int col = 0;
//			// this handles multiple letters: AA ??
//			for(char c:colAlpha.toCharArray()){
//				String s = (""+c+"").toUpperCase();
//				
//				int x=s.charAt(0)-'A';
//				col+=x;
//				col*=10;
//			}
//			col/=10;
//			System.out.println("col = " + col);
			
//			int row = Integer.parseInt( k.toUpperCase().replaceAll("[A-Z]", "") );
			int row = rowFromId(k);
			System.out.println("row = " + row);
			
			String val = cells.get(k);
			System.out.println("val = " + val);
			
			System.out.println("k = " + k);
			
			
			putCell(val,row,col);
		}
	}
	
	
	
	/*
	eval expressions
	from: https://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
	*/
	public static double eval(final String str) {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ') nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
				return x;
			}

			// Grammar:
			// expression = term | expression `+` term | expression `-` term
			// term = factor | term `*` factor | term `/` factor
			// factor = `+` factor | `-` factor | `(` expression `)`
			//        | number | functionName factor | factor `^` factor

			double parseExpression() {
				double x = parseTerm();
				for (;;) {
					if      (eat('+')) x += parseTerm(); // addition
					else if (eat('-')) x -= parseTerm(); // subtraction
					else return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				for (;;) {
					if      (eat('*')) x *= parseFactor(); // multiplication
					else if (eat('/')) x /= parseFactor(); // division
					else if (eat('%')) x %= parseFactor(); // modulo //////////SP
					else return x;
				}
			}

			double parseFactor() {
				if (eat('+')) return parseFactor(); // unary plus
				if (eat('-')) return -parseFactor(); // unary minus

				double x;
				int startPos = this.pos;
				if (eat('(')) { // parentheses
					x = parseExpression();
					eat(')');
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z') nextChar();
					String func = str.substring(startPos, this.pos);
					x = parseFactor();
					if (func.equals("sqrt")) x = Math.sqrt(x);
					else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
					else throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char)ch);
				}

				if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}

	private void evalExpressions() {
		System.out.println("evalExpressions");
		for(String k:cells.keySet()){
			
			String val = cells.get(k);
			if(val.startsWith("=")){
				int row = rowFromId(k);
				int col = colFromId(k);
				
				String expr = val.replace("=", "");
				System.out.println("expr = " + expr);
				
				//expr = a1+a2
				String[] vars = getVars( val );
				System.out.println("vars = " + vars);
				for(String var:vars){
					String sub = cells.get(var.toUpperCase());
					System.out.println("sub = " + sub);
					
					expr = expr.replaceAll(var, sub);
					System.out.println("expr = " + expr);
				}
				
				double ans = eval(expr);
				System.out.println("ans = " + ans);
				
				putCell(""+ans, row, col);
			}
		}
	}

	private int colFromId(String k) {
		String colAlpha = k.replaceAll("[0-9]", "");
		int col = 0;
		// this handles multiple letters: AA ??
		for(char c:colAlpha.toCharArray()){
			String s = (""+c+"").toUpperCase();

			int x=s.charAt(0)-'A';
			col+=x;
			col*=10;
		}
		col/=10;
		System.out.println("col = " + col);
		return col;
	}

	private int rowFromId(String k) {
		int row = Integer.parseInt( k.toUpperCase().replaceAll("[A-Z]", "") );
		System.out.println("row = " + row);
		return row;
	}

	private String[] getVars(String val) {
		String[] parts = val.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
		System.out.println("parts = " + array(parts));
		//parts = [=,a1,+,a2,]

		String[] validDeps = filterVars(parts);
		System.out.println("validDeps = " + array(validDeps));
		//validDeps = [a1,a2,]
		return validDeps;
	}

	private void putCell(String val, int row, int col) {
		// col 1 reserved for col numbers
		// A1 is actually (0,2)
		jTable1.setValueAt(val, row-1, col+1);
	}
}
