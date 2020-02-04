

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Rectangle; 
import java.awt.Point; 

import javax.swing.JPanel;
import javax.swing.Timer;


public class PDevPanel extends JPanel implements MouseListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Integer value being incremented. Used to move the blocks around 
	int titleTimer;
	
	//Created clips for different audio files
	static Sound uiClick= new Sound("src/ui_click.wav");
	static Sound score= new Sound("src/score.wav");
	static Sound down= new Sound("src/down.wav");
	static Sound retroChoice= new Sound("src/retroChoice.wav");
	static Sound origChoice= new Sound("src/origChoice.wav");
	static Sound nextLevel= new Sound("src/nextLevel.wav");
	static Sound gameEnded= new Sound("src/Blocko_game_end.wav");
	static Sound title= new Sound("src/Blocko_title.wav");//Creates Sound object for title song
		

	//Background and start text images 
	Image bImage; 
	Image[] pubImage; 
	Image[] devImage; 

	//Boolean telling whether or not the start text should appear 
	boolean displayStart; 
	
	//Rectangle and point being used to handle the mouseclicks
	Rectangle backBounds = new Rectangle(10,12,68,20); 
	Point point; 
	
	Timer tClock; 
	
	int circ1X;
	int circ2X; 
	
	int dev1X;
	int dev2X; 
	
	//TitlePanel tp = new TitlePanel(); 
	
	
	
	public PDevPanel(){
		
		tClock = new Timer(1,this); 
		tClock.start();
	
		//Initializing the start dislay boolean. At first, it's not displayed
		displayStart = false; 
		
		//Giving the point object some parameters
		point = new Point(0,0); 
		
		pubImage = new Image[3];
		devImage = new Image[2];
		
		circ1X = -170; 
		circ2X = 730; 
		
		dev1X = -160;
		dev2X = 730; 
		
		addMouseListener(this);
		setSize(730,300);
		
		
	}
	
	
	//MouseListener Functions ---------------------------------------
	public void mouseClicked(MouseEvent e) {
		System.out.println("The frame was clicked.");
		point = e.getPoint(); 
		if(backBounds.contains(point)){
			System.out.println("Back Clicked");
			uiClick.play();
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
	
	
	
	public void actionPerformed(ActionEvent e){
		this.repaint(); 
	}
	
	
	
	
	
	//Paint Method that gets called in actionPerformed method   
	public void paint(Graphics g){
		
		//Prepare the background image
		bImage = Toolkit.getDefaultToolkit().getImage("src/black.gif");
		prepareImage(bImage, this);  
		
		//Draw the background image
		g.drawImage(bImage, 0, 0, null);
		
		
		pubImage[0] = Toolkit.getDefaultToolkit().getImage("src/ctm.gif");
		pubImage[1] = Toolkit.getDefaultToolkit().getImage("src/wCircle.gif");
		pubImage[2] = Toolkit.getDefaultToolkit().getImage("src/wCircle.gif");
		
		devImage[0] = Toolkit.getDefaultToolkit().getImage("src/m.gif");
		devImage[1] = Toolkit.getDefaultToolkit().getImage("src/g.gif");
		//g.fillRect(10,12,68,20);

		
		g.drawImage(pubImage[1], circ1X, 0, null);
		g.drawImage(pubImage[2], circ2X , 100, null);
		g.drawImage(pubImage[0], 200, 60, null);
		
		
		//Move the images 
		if(titleTimer<1000){
			circ1X++; 
			circ2X--; 
		}
		
		if(titleTimer >1100){
			g.drawImage(devImage[0], dev1X, 70, null);
			g.drawImage(devImage[1], dev2X, 70, null);
		}
		
		//Move the images 
		if(titleTimer>1100 && titleTimer < 1460){
			dev1X++; 
			dev2X--; 
		}
		
		//2400 and then the titleScreen begins 
		if(titleTimer > 2400){
			
			Blocko.frame.add(Blocko.tp);
			Blocko.frame.remove(this);
			Blocko.frame.validate(); 
			title.play(); title.loop(); //Plays title song and loop
		}
		
		titleTimer++;
		//System.out.println(titleTimer + " ");
		
		
	}
	
}
