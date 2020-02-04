

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Rectangle; 
import java.awt.Point; 
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;
import javax.swing.Timer;

//Class that handles all that is related to the settings of the game
public class SettingsPanel extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Background and start text images 
	Image bImage; 
	Image startImage; 
	//Integer and boolean that moves the images 
	int move = 0; 
	boolean backClicked = false; 

	//Rectangle and point being used to handle the mouseclicks. Image HotSpots
	Rectangle backBounds = new Rectangle(10,12,68,20); 
	Rectangle minusLevel = new Rectangle(225,118,61,39); 
	Rectangle plusLevel = new Rectangle(435,118,61,39); 
	Rectangle retroOption = new Rectangle(250,215,75,44); 
	Rectangle origOption = new Rectangle(400,214,75,44); 
	Point point; 
	
	//Timer that is constantly calling paint method for the images inside the panel 
	Timer tClock; 
	
	//Images for the options regardless of theme
	private Image optionImgs[];
	
	//Array of the "Original" theme images 
	private Image originalN[]; 
		
	//Array of the "Retro" theme images 
	private Image retroN[]; 
	
	//Booleans for clicks 
	boolean retroClicked = false; 
	boolean origClicked = false; 
	boolean leftClicked = false; 
	boolean rightClicked = false; 

	public SettingsPanel(){
		
		//Initializing the arrays of images for the different themes
		optionImgs= new Image[9];
		retroN = new Image[10]; 
		originalN = new Image[10]; 
		
		//Initializing the Timer
		tClock = new Timer(1,this); 
		tClock.start();
	
		
		//Giving the point object some parameters
		point = new Point(0,0); 
		
		
		//Adding the mouseListener to the panel and setting the size of it
		addMouseListener(this);
		setSize(730,300);
	}
	
	
	//MouseListener Functions ------------------------------------------
	public void mouseClicked(MouseEvent e) {
		//System.out.println("The frame was clicked.");
		point = e.getPoint(); 
		if(backBounds.contains(point)){
			System.out.println("Back Clicked");
			PDevPanel.uiClick.play();
			
			try{
				// Open a file to write to
				FileOutputStream saveFile=new FileOutputStream("playerSettings.sav");

				// Create an ObjectOutputStream to put objects into save file.
				ObjectOutputStream save = new ObjectOutputStream(saveFile);

				// Now we do the save.
				save.writeObject(GamePanel.level);
				save.writeObject(GamePanel.gameScreenTheme);

				// Close the file.
				save.close(); // This also closes saveFile.
				}
				catch(Exception exc){
				exc.printStackTrace(); // If there was an error, print the info.
				}
			
			
			backClicked = true; 
		}
		if(minusLevel.contains(point)){
			if(GamePanel.level>1)GamePanel.level--;
			PDevPanel.down.play();
		}
		if(plusLevel.contains(point)){
			if(GamePanel.level<10)GamePanel.level++;
			PDevPanel.score.play();
		}
		if(retroOption.contains(point)){
			GamePanel.gameScreenTheme=2;
			PDevPanel.retroChoice.play();
			
		}
		if(origOption.contains(point)){
			GamePanel.gameScreenTheme=1;
			PDevPanel.origChoice.play();
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
		point = e.getPoint(); 
		if(retroOption.contains(point)){
			//System.out.println("retro clicked");
			retroClicked = true; 
		}
		if(origOption.contains(point)){
			//System.out.println("original clicked");
			origClicked = true; 
		}
		if(minusLevel.contains(point)){
			//System.out.println("Left Arrow Clicked");
			leftClicked = true; 
		}
		if(plusLevel.contains(point)){
			System.out.println("Right Arrow Clicked"); 
			rightClicked = true; 
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		//System.out.println("The left mouse button was released.");
		point = e.getPoint(); 
		if(retroOption.contains(point)){
			//System.out.println("retro released");
			retroClicked = false; 
		}
		if(origOption.contains(point)){
			//System.out.println("original released");
			origClicked = false; 
		}
		if(minusLevel.contains(point)){
			//System.out.println("Left Arrow Released");
			leftClicked = false; 
		}
		if(plusLevel.contains(point)){
			System.out.println("Right Arrow Released"); 
			rightClicked = false; 
		}
		
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
		
		
		//Prepare the start Text
		startImage = Toolkit.getDefaultToolkit().getImage("src/back.gif");
		//g.fillRect(10,12,68,20);
		
		//Draw the start text 
		g.drawImage(startImage, 5 + move , 0, null);
		
		//Prepare and draw images that will not be effected by theme
		optionImgs[0] = Toolkit.getDefaultToolkit().getImage("src/options.gif");
		g.drawImage(optionImgs[0], 280 + move , 10, null);
		
		optionImgs[1] = Toolkit.getDefaultToolkit().getImage("src/levelSelect.gif");
		g.drawImage(optionImgs[1], 273 + move , 75, null);
		
		optionImgs[2] = Toolkit.getDefaultToolkit().getImage("src/colorSchemeText.gif");
		g.drawImage(optionImgs[2], 260 + move , 175, null);
		
		optionImgs[4] = Toolkit.getDefaultToolkit().getImage("src/numBox.gif");
		g.drawImage(optionImgs[4], 337 + move , 118, null);
		
		
		
		if(leftClicked)
			optionImgs[3] = Toolkit.getDefaultToolkit().getImage("src/leftArrowg.gif");
		else
			optionImgs[3] = Toolkit.getDefaultToolkit().getImage("src/leftArrow.gif");
		g.drawImage(optionImgs[3], 225 + move , 118, null);
		
		
		if(rightClicked)
			optionImgs[5] = Toolkit.getDefaultToolkit().getImage("src/rightArrowg.gif");
		else
			optionImgs[5] = Toolkit.getDefaultToolkit().getImage("src/rightArrow.gif");
		
		g.drawImage(optionImgs[5], 435 + move , 118, null);
		
		
		
		
		
		if(retroClicked)
			optionImgs[6] = Toolkit.getDefaultToolkit().getImage("src/retroBox1.png");
		else
			optionImgs[6] = Toolkit.getDefaultToolkit().getImage("src/retroBox.png");
			
		g.drawImage(optionImgs[6], 250 + move , 215, null);
		
		
		if(origClicked)
			optionImgs[7] = Toolkit.getDefaultToolkit().getImage("src/origiBox1.png");
		else
			optionImgs[7] = Toolkit.getDefaultToolkit().getImage("src/origBox.png");
		
		g.drawImage(optionImgs[7], 400 + move , 214, null);
		
		
		
		//If the player picked the Retro theme, Draw this...
		if(GamePanel.gameScreenTheme == 2){
			
			//Preparing individual digit pictures for retro theme			
			
			
			if(GamePanel.level > 0 && GamePanel.level < 10){
				retroN[0] = Toolkit.getDefaultToolkit().getImage("src/rn" +GamePanel.level + ".gif");
				g.drawImage(retroN[0], 351 + move , 130, null);
			}
			else if(GamePanel.level == 10) {
				retroN[0] = Toolkit.getDefaultToolkit().getImage("src/rn1.gif");
				g.drawImage(retroN[0], 346 + move , 130, null);
				retroN[1] = Toolkit.getDefaultToolkit().getImage("src/rn0.gif");
				g.drawImage(retroN[1], 356 + move , 130, null);
			}
			
		}
		//If the player picked the Original theme, Draw this...
		if(GamePanel.gameScreenTheme == 1){
			
			//Preparing individual digit pictures for original theme				
			if(GamePanel.level > 0 && GamePanel.level < 10){
				originalN[0] = Toolkit.getDefaultToolkit().getImage("src/on" +GamePanel.level + ".gif");
				g.drawImage(originalN[0], 353 + move , 130, null);
			}
			else if(GamePanel.level == 10) {
				originalN[0] = Toolkit.getDefaultToolkit().getImage("src/on1.gif");
				g.drawImage(originalN[0], 347 + move , 130, null);
				originalN[1] = Toolkit.getDefaultToolkit().getImage("src/on0.gif");
				g.drawImage(originalN[1], 357 + move , 130, null);
			}
		}
		
		if(backClicked){
			move+=3; 
			
			if(move > 800){
				//Adds the titlePanel onto the frame, removes the current panel, and validates frame
				Blocko.frame.add(Blocko.tp);
				Blocko.frame.remove(this);
				Blocko.frame.validate(); 
				backClicked = false; 
				move = 0; 
			}
		}
		
		
	}//closes paint method
}//closes class
