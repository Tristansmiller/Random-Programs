package main;
import gui.World;

import javax.swing.JFrame;

public class Core extends JFrame {
	
	
	public Core(){
		add(new World());
		//add(new StartScreen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setLocationRelativeTo(null);
		setTitle("Dodging Game");
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String args[]){
		new Core();
	}

}
