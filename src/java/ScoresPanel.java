

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Rectangle; 
import java.awt.Point; 

import javax.swing.JPanel;
import javax.swing.Timer;


public class ScoresPanel extends JPanel implements MouseListener, ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//Integer value being incremented. Used to move the blocks around 
	int titleTimer;
		

	//Background and start text images 
	Image bImage; 
	Image startImage; 
	Image baImage; 
	
	//Number Images 
	Image number1[];
	Image number2[];
	Image number3[];
	Image number4[];
	Image number5[];
	
	
	int scoreDigit[][];

	//Boolean telling whether or not the start text should appear 
	boolean displayStart; 
	
	//Rectangle and point being used to handle the mouseclicks
	Rectangle backBounds = new Rectangle(6,5,55,25); 
	Point point; 
	
	Timer tClock; 
	
	//TitlePanel tp = new TitlePanel(); 
	
	public ScoresPanel(){
		
		tClock = new Timer(1,this); 
		tClock.start();
	
		//Initializing the start dislay boolean. At first, it's not displayed
		displayStart = false; 
		
		//Giving the point object some parameters
		point = new Point(0,0); 
		
		number1 = new Image[5];
		number2 = new Image[5];
		number3 = new Image[5];
		number4 = new Image[5];
		number5 = new Image[5];
		
		
		scoreDigit = new int[5][5];
		
		
		
		
		addMouseListener(this);
		setSize(730,300);
	}
	
	
	//MouseListener Functions ---------------------------------------
	public void mouseClicked(MouseEvent e) {
		System.out.println("The frame was clicked.");
		point = e.getPoint(); 
		if(backBounds.contains(point)){
			System.out.println("Back Clicked");
			PDevPanel.uiClick.play();
			Blocko.frame.add(Blocko.tp);
			Blocko.frame.remove(this);
			Blocko.frame.validate(); 
		}
	}
	public void mouseEntered(MouseEvent e) {
		//System.out.println("The mouse entered the frame."); 
	}
	public void mouseExited(MouseEvent e) {
		//System.out.println("The mouse exited the frame.");
		
	}
	public void mousePressed(MouseEvent e) {
		//System.out.println("The left mouse button was pressed.");
		
	}
	public void mouseReleased(MouseEvent e) {
		//System.out.println("The left mouse button was released.");
	}
	
	//Separate the player's score into individual digits
	 public void scoreSeparate(){
	       int number;
	       int counter = 0; 
	       
	       for (int i = 0; i < 5; i++) {
	       number = Blocko.highScores[i]; 

	       while (counter<5) {
	    	   //System.out.println( number % 10);
	          
	           scoreDigit[i][counter]=number % 10;
	           number = number / 10;
	           counter++;
	       }
	       counter = 0;
	       }
	       
	      
	 }
	
	
	
	
	public void actionPerformed(ActionEvent e){
		this.repaint(); 
		scoreSeparate();
	}
	
	
	
	//Paint Method that gets called in actionPerformed method   
	public void paint(Graphics g){
		
		//Prepare the background image
		bImage = Toolkit.getDefaultToolkit().getImage("src/scores.gif");
		prepareImage(bImage, this); 
		
		//Draw the background image
		g.drawImage(bImage, 0, 0, null);
		
		//Prepare the background image
		baImage = Toolkit.getDefaultToolkit().getImage("src/back.gif");
		prepareImage(baImage, this); 
				
		//Draw the background image
		g.drawImage(baImage, 0, 0, null);
		
		
		
		//Prepare the Numbers
		//1
		number1[0] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[0][0] +".gif");
		prepareImage(number1[0], this); 	
		g.drawImage(number1[0], 320, 80, null);

		number1[1] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[0][1] +".gif");
		prepareImage(number1[1], this); 	
		g.drawImage(number1[1], 335, 80, null);

		number1[2] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[0][2] +".gif");
		prepareImage(number1[2], this); 	
		g.drawImage(number1[2], 350, 80, null);
		
		number1[3] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[0][3] +".gif");
		prepareImage(number1[3], this); 	
		g.drawImage(number1[3], 365, 80, null);
		
		number1[4] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[0][4] +".gif");
		prepareImage(number1[4], this); 	
		g.drawImage(number1[4], 380, 80, null);
		
		
		//2
		number2[0] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[1][0] +".gif");
		prepareImage(number2[0], this); 	
		g.drawImage(number2[0], 320, 125, null);

		number2[1] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[1][1] +".gif");
		prepareImage(number2[1], this); 	
		g.drawImage(number2[1], 335, 125, null);

		number2[2] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[1][2] +".gif");
		prepareImage(number2[2], this); 	
		g.drawImage(number2[2], 350, 125, null);
		
		number2[3] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[1][3] +".gif");
		prepareImage(number2[3], this); 	
		g.drawImage(number2[3], 365, 125, null);
		
		number2[4] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[1][4] +".gif");
		prepareImage(number2[4], this); 	
		g.drawImage(number2[4], 380, 125, null);
		
		
		//3
		number3[0] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[2][0] +".gif");
		prepareImage(number3[0], this); 	
		g.drawImage(number3[0], 320, 170, null);

		number3[1] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[2][1] +".gif");
		prepareImage(number3[1], this); 	
		g.drawImage(number3[1], 335, 170, null);

		number3[2] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[2][2] +".gif");
		prepareImage(number3[2], this); 	
		g.drawImage(number3[2], 350, 170, null);
		
		number3[3] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[2][3] +".gif");
		prepareImage(number3[3], this); 	
		g.drawImage(number3[3], 365, 170, null);
		
		number3[4] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[2][4] +".gif");
		prepareImage(number3[4], this); 	
		g.drawImage(number3[4], 380, 170, null);
		
		
		//4
		number4[0] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[3][0] +".gif");
		prepareImage(number4[0], this); 	
		g.drawImage(number4[0], 320, 206, null);

		number4[1] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[3][1] +".gif");
		prepareImage(number4[1], this); 	
		g.drawImage(number4[1], 335, 206, null);

		number4[2] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[3][2] +".gif");
		prepareImage(number4[2], this); 	
		g.drawImage(number4[2], 350, 206, null);
		
		number4[3] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[3][3] +".gif");
		prepareImage(number4[3], this); 	
		g.drawImage(number4[3], 365, 206, null);
		
		number4[4] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[3][4] +".gif");
		prepareImage(number4[4], this); 	
		g.drawImage(number4[4], 380, 206, null);
		
		
		//5
		number5[0] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[4][0] +".gif");	
		prepareImage(number5[0], this); 	
		g.drawImage(number5[0], 320, 234, null);

		number5[1] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[4][1] +".gif");
		prepareImage(number5[1], this); 	
		g.drawImage(number5[1], 335, 234, null);

		number5[2] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[4][2] +".gif");
		prepareImage(number5[2], this); 	
		g.drawImage(number5[2], 350, 234, null);
		
		number5[3] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[4][3] +".gif");
		prepareImage(number5[3], this); 	
		g.drawImage(number5[3], 365, 234, null);
		
		number5[4] = Toolkit.getDefaultToolkit().getImage("src/on"+ scoreDigit[4][4] +".gif");
		prepareImage(number5[4], this); 	
		g.drawImage(number5[4], 380, 234, null);
		
		
		
		
		
		


		
	
		
		titleTimer++;
		//System.out.print(titleTimer + " ");
		
		
	}
	
}