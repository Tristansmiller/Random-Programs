import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CalcInterface extends JFrame {
	
	private JTextField textField;
	private JButton button0;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton buttonX;
	private JButton buttonCaret;
	private JButton buttonPlus;
	private JButton buttonMinus;
	private JButton buttonMultiply;
	private JButton buttonDivide;
	private JButton buttonDelete;
	private JButton buttonEquals;
	private JButton buttonTable;
	private JButton buttonDecimal;
	private JButton buttonGraph;
	private String[] currentStringSplit;
	

	
	public CalcInterface(){
		super("Tristan's Calculator");
		setLayout(new FlowLayout());
		CalcHandler handler = new CalcHandler();
		KeyHandler keyboard = new KeyHandler();
		
		textField = new JTextField("<",10);
		textField.setEditable(false);
		add(textField);
		textField.addKeyListener(keyboard);
		
		button0 = new JButton("0");
		add(button0);
		button0.addActionListener(handler);
		
		button1 = new JButton("1");
		add(button1);
		button1.addActionListener(handler);
		
		button2 = new JButton("2");
		add(button2);
		button2.addActionListener(handler);
		
		button3 = new JButton("3");
		add(button3);
		button3.addActionListener(handler);
		
		button4 = new JButton("4");
		add(button4);
		button4.addActionListener(handler);
		
		button5 = new JButton("5");
		add(button5);
		button5.addActionListener(handler);
		
		button6 = new JButton("6");
		add(button6);
		button6.addActionListener(handler);
		
		button7 = new JButton("7");
		add(button7);
		button7.addActionListener(handler);
		
		button8 = new JButton("8");
		add(button8);
		button8.addActionListener(handler);
		
		button9 = new JButton("9");
		add(button9);
		button9.addActionListener(handler); 
		
		buttonDecimal = new JButton(".");
		add(buttonDecimal);
		buttonDecimal.addActionListener(handler);
		
		buttonX = new JButton("X");
		add(buttonX);
		buttonX.addActionListener(handler);
		
		buttonCaret = new JButton("^");
		add(buttonCaret);
		buttonCaret.addActionListener(handler);
		
		buttonPlus = new JButton("+");
		add(buttonPlus);
		buttonPlus.addActionListener(handler);
		
		buttonMinus = new JButton("-");
		add(buttonMinus);
		buttonMinus.addActionListener(handler);
		
		buttonMultiply = new JButton("*");
		add(buttonMultiply);
		buttonMultiply.addActionListener(handler);
		
		buttonDivide = new JButton("/");
		add(buttonDivide);
		buttonDivide.addActionListener(handler);
		
		buttonDelete = new JButton("DELETE");
		add(buttonDelete);
		buttonDelete.addActionListener(handler);
		
		buttonEquals = new JButton("=");
		add(buttonEquals);
		buttonEquals.addActionListener(handler);
		
		buttonTable = new JButton("Table");
		add(buttonTable);
		buttonTable.addActionListener(handler);
		
		buttonGraph = new JButton("Graph");
		add(buttonGraph);
		buttonGraph.addActionListener(handler);
		
		setVisible(true);
		setSize(160,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void addToTextField(String s){
		if(textField.getText().split("<").length != 0){
			currentStringSplit = textField.getText().split("<");
			currentStringSplit[0]+=s;
			if(currentStringSplit.length>1)textField.setText(currentStringSplit[0]+"<"+currentStringSplit[1]);
			else textField.setText(currentStringSplit[0]+"<");}
		else textField.setText(s+"<");
	}
	
	public void deleteFromTextField(){
		if(textField.getText().split("<").length != 0 && textField.getText().charAt(0)!='<'){
			currentStringSplit = textField.getText().split("<");
			currentStringSplit[0]=currentStringSplit[0].substring(0, currentStringSplit[0].length()-1);
			if(currentStringSplit.length>1)textField.setText(currentStringSplit[0]+"<"+currentStringSplit[1]);
			else textField.setText(currentStringSplit[0]+"<");}
	}
	
	public void shiftCursorRight(){
		if(textField.getText().split("<").length>0)
			if(textField.getText().charAt(0)=='<'){
				String temp = currentStringSplit[1].substring(0,1);
				textField.setText(temp+"<"+currentStringSplit[1].substring(1, currentStringSplit[1].length()));
			}	
			else{
				currentStringSplit = textField.getText().split("<");
				if(textField.getText().split("<").length > 1){
					currentStringSplit[0] += currentStringSplit[1].substring(0,1);
					currentStringSplit[1] = currentStringSplit[1].substring(1,currentStringSplit[1].length());
					textField.setText(currentStringSplit[0]+"<"+currentStringSplit[1]);}}
	}
	
	public void shiftCursorLeft(){
		if(textField.getText().split("<").length>0 && textField.getText().charAt(0)!='<')

			if(textField.getText().charAt(textField.getText().length()-1)=='<'){
				String temp = currentStringSplit[0].substring(0,currentStringSplit[0].length()-1);
				textField.setText(temp+"<"+currentStringSplit[0].substring(currentStringSplit[0].length()-1, currentStringSplit[0].length()));
			}	
			else if(textField.getText().split("<").length > 1){
					currentStringSplit = textField.getText().split("<");
					currentStringSplit[1] = currentStringSplit[0].substring(currentStringSplit[0].length()-1,currentStringSplit[0].length())+currentStringSplit[1];
					currentStringSplit[0] = currentStringSplit[0].substring(0,currentStringSplit[0].length()-1);
					textField.setText(currentStringSplit[0]+"<"+currentStringSplit[1]);}
	}
	
	public String getTextField(){
		if(textField.getText().split("<").length!=0){
			String[] temp = textField.getText().split("<");
			String tempString = "";
			for(int k = 0;k<temp.length;k++)
				tempString+=temp[k];
			return tempString;}
		return null;
	}
	public void setTextField(String input){
		textField.setEditable(true);
		textField.setText(input+"<");
		textField.setEditable(false);
	}

	private class KeyHandler implements KeyListener{
		public void keyPressed(KeyEvent event){
			//System.out.println("Key char: "+event.getKeyChar());
			//System.out.println("Key code: "+event.getKeyCode());
			//System.out.println("Key location: "+event.getKeyLocation());
			if(event.getKeyCode()==KeyEvent.VK_0 || event.getKeyCode()==KeyEvent.VK_NUMPAD0){
					addToTextField("0");}
			else if(event.getKeyCode()==KeyEvent.VK_1 || event.getKeyCode()==KeyEvent.VK_NUMPAD1){
					addToTextField("1");}
			else if(event.getKeyCode()==KeyEvent.VK_2 || event.getKeyCode()==KeyEvent.VK_NUMPAD2){
					addToTextField("2");}
			else if(event.getKeyCode()==KeyEvent.VK_3 || event.getKeyCode()==KeyEvent.VK_NUMPAD3){
					addToTextField("3");}
			else if(event.getKeyCode()==KeyEvent.VK_4 || event.getKeyCode()==KeyEvent.VK_NUMPAD4){
					addToTextField("4");}
			else if(event.getKeyCode()==KeyEvent.VK_5 || event.getKeyCode()==KeyEvent.VK_NUMPAD5){
					addToTextField("5");}
			else if(event.getKeyCode()==KeyEvent.VK_6 || event.getKeyCode()==KeyEvent.VK_NUMPAD6){
					addToTextField("6");}
			else if(event.getKeyCode()==KeyEvent.VK_7 || event.getKeyCode()==KeyEvent.VK_NUMPAD7){
					addToTextField("7");}
			else if(event.getKeyCode()==KeyEvent.VK_8 || event.getKeyCode()==KeyEvent.VK_NUMPAD8){
					addToTextField("8");}
			else if(event.getKeyCode()==KeyEvent.VK_9 || event.getKeyCode()==KeyEvent.VK_NUMPAD9){
					addToTextField("9");}
			else if(event.getKeyCode()==KeyEvent.VK_DECIMAL || event.getKeyCode()==KeyEvent.VK_PERIOD){
				    addToTextField(".");}
			else if(event.getKeyCode()==KeyEvent.VK_X){addToTextField("X");}
			else if(event.getKeyCode()==KeyEvent.VK_UP){addToTextField("^");}
			else if(event.getKeyCode()==KeyEvent.VK_ADD){addToTextField("+");}
			else if(event.getKeyCode()==KeyEvent.VK_SUBTRACT){addToTextField("-");}
			else if(event.getKeyCode()==KeyEvent.VK_MULTIPLY){addToTextField("*");}
			else if(event.getKeyCode()==KeyEvent.VK_SLASH || event.getKeyCode()==KeyEvent.VK_DIVIDE){addToTextField("/");}
			else if(event.getKeyCode()==KeyEvent.VK_BACK_SPACE){deleteFromTextField();}
			else if(event.getKeyCode()==KeyEvent.VK_RIGHT){shiftCursorRight();}
			else if(event.getKeyCode()==KeyEvent.VK_LEFT){shiftCursorLeft();}
			else if(event.getKeyCode()==KeyEvent.VK_ENTER){if(Calc.isPolynomial(getTextField()))setTextField(""+Calc.computePolynomial(new Polynomial(getTextField())));
														   else setTextField(""+Calc.computeSimpleExpression(getTextField())); }
		}
		public void keyReleased(KeyEvent event){}
		public void keyTyped(KeyEvent event){}
	}
	
	private class CalcHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==button0){addToTextField("0");}			
			else if(event.getSource()==button1){addToTextField("1");}			
			else if(event.getSource()==button2){addToTextField("2");}			
			else if(event.getSource()==button3){addToTextField("3");}			
			else if(event.getSource()==button4){addToTextField("4");}			
			else if(event.getSource()==button5){addToTextField("5");}			
			else if(event.getSource()==button6){addToTextField("6");}			
			else if(event.getSource()==button7){addToTextField("7");}			
			else if(event.getSource()==button8){addToTextField("8");}			
			else if(event.getSource()==button9){addToTextField("9");}			
			else if(event.getSource()==buttonX){addToTextField("X");}
			else if(event.getSource()==buttonDecimal){addToTextField(".");}
			else if(event.getSource()==buttonCaret){addToTextField("^");}			
			else if(event.getSource()==buttonPlus){addToTextField("+");}			
			else if(event.getSource()==buttonMinus){addToTextField("-");}			
			else if(event.getSource()==buttonMultiply){addToTextField("*");}			
			else if(event.getSource()==buttonDivide){addToTextField("/");}
			else if(event.getSource()==buttonDelete){deleteFromTextField();}
			else if(event.getSource()==buttonEquals){if(Calc.isPolynomial(getTextField()))setTextField(""+Calc.computePolynomial(new Polynomial(getTextField())));
			   										 else setTextField(""+Calc.computeSimpleExpression(getTextField()));}
			else if(event.getSource()==buttonTable){ if(Calc.isPolynomial(getTextField()))
														JOptionPane.showMessageDialog(null, Calc.makeTable(new Polynomial(getTextField())));}
			else if(event.getSource()==buttonGraph){ if(Calc.isPolynomial(getTextField()))
														Calc.makeGraph(new Polynomial(getTextField()));
			}
			

		}
	}
}
