

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class titleScreen extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel l1;
	Timer tClock; 
	protected TitlePanel tp; 
		
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 	
		
	public titleScreen()
	{
		
		
		setVisible(true);
		//Inintialize the timer
		tClock = new Timer(1,this); 
		tClock.start();
				
		
		//Inintialize the gamepanel
		tp = new TitlePanel();
		
		
		//Title screen frame parameters
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Frame size: width=730   ,  height = 300  
		setSize(730,300);
		
		setLocation(dim.width/2 - this.getSize().width/2, 
				    dim.height/2 - this.getSize().height/2);
				
		setResizable(false);
		setTitle("Block-O");
		add(tp);
		
	}
	
	
	
	
	
	//This method is needed with ActionListener
	//Listens for command from button, then operates due to if statements
	 public void actionPerformed(ActionEvent e) {
		 
		 String cmd = e.getActionCommand();//Receives action from button, then assigns the appropriate String
		 
		 if (cmd == "start") {
			 setVisible(false); //Makes title screen invisible for now
		      new Blocko();     //Starts everything within the Blocko class
		    } 
		 else if (cmd == ""){
		      System.out.println("Sorry, action not available yet :3");
		    }
		 
		 
		repaint();
		 
	    }
	 
	 
	 
	 
	 

	 
	 
}
