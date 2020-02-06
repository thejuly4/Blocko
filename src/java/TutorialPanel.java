import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Rectangle; 
import java.awt.Point; 

import javax.swing.JPanel;
import javax.swing.Timer;

//Panel that handles everything on the Tutorial Screen
public class TutorialPanel extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;

	//Creating the Background image object  
	Image bImage, bbImage; 

	//X and Y values for the tutorial screen
	int screenX=0, screenY=0; 
	boolean backClicked = false; 
	
	//Rectangle and point being used to handle the mouseclicks. The rectangle is the HotSpot
	Rectangle backBounds = new Rectangle(6,5,55,25); 
	Point point; 
	
	Timer tClock; 

	public TutorialPanel(){
		
		tClock = new Timer(1,this); 
		tClock.start();
		
		//Giving the point object some parameters
		point = new Point(0,0); 

		//Adding the mouselistener to the panel and setting the size of it
		addMouseListener(this);
		setSize(730,300);
	}
	
	//MouseListener Functions --------------------------------------------------
	public void mouseClicked(MouseEvent e) {
		//System.out.println("The frame was clicked.");
		//Get the click from the user and check if it's in the image's hotspot
		point = e.getPoint(); 
		if(backBounds.contains(point)){
			//Adds the titlePanel onto the frame, removes the current panel, and validates frame
			System.out.println("Back Clicked");
			PDevPanel.uiClick.play();
			
			backClicked = true; 
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
	
	//ActionPerformed method called by the timer
	public void actionPerformed(ActionEvent e){
		this.repaint(); 
	}

	//Paint Method that gets called in actionPerformed method   
	public void paint(Graphics g){
		
		bbImage = Toolkit.getDefaultToolkit().getImage("src/black.gif");
		prepareImage(bImage, this); 
		//Draw the background image
		g.drawImage(bbImage, 0, 0, null);

		//Prepare the Tutorial screen image
		bImage = Toolkit.getDefaultToolkit().getImage("src/tutScreen.gif");
		prepareImage(bImage, this); 
		//Draw the background image
		g.drawImage(bImage, screenX, screenY, null);
		
		//Move the tutorial screen
		if(backClicked){
			screenX+=3;
			
			if(screenX > 1010){
				Blocko.frame.add(Blocko.tp);
				Blocko.frame.remove(this);
				Blocko.frame.validate(); 
				backClicked = false; 
				screenX = 0; 
			}
			
		}

		//Rectangle used for the back button
		//g.fillRect(6,5,55,25);

	}
}