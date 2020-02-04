

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
import java.awt.event.*;
import java.awt.Rectangle; 
import java.awt.Point; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;
import javax.swing.Timer;

//Class that handles everything in the title screen
public class TitlePanel extends JPanel implements MouseListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Boolean used to indicate whether or not the save state for the game was found
	boolean saveFound=true;
	boolean playerSettingsSave=true;
	
	
	//Integer value being incremented. Used to move the blocks around 
	int titleTimer;
	boolean loadedTimer; 
	int loadInt = 0;
		
	
	//Lets us know what block we're dealing with when moving them around 
	//int blockNum; 
	//int blockNum2; 
	 
	
	//Background and start text images 
	Image bImage; 
	Image titleImgs[]; 

	//Contains the block images 
	Image tImage[]; 
	
	//Booleans for clicks
	boolean startClicked = false; 
	boolean tutClicked = false; 
	boolean optClicked = false; 
	boolean loadClicked = false; 
	boolean quitClicked = false; 
	
	//All objects used for block movement
	int blockX[]; 
	int blockY[]; 
	int[] blockChoice = new int[3]; 
	Random r = new Random(); 
	
	//Rectangles and point being used to handle the mouseclicks
	Rectangle startImageBounds = new Rectangle(330,217,55,20); 
	Rectangle loadImageBounds = new Rectangle(270,243,45,20); 
	Rectangle optionsImageBounds = new Rectangle(375,187,85,20); 
	Rectangle tutorialImageBounds = new Rectangle(255,187,80,20); 
	Rectangle quitImageBounds = new Rectangle(395,242,47,20); 
	Rectangle scoreImageBounds = new Rectangle(610,254,120,20); 
	Point point; 
	
	//Creating a timer object
	private Timer tClock; 
	

	GamePanel gp = new GamePanel(); 
	SettingsPanel sp = new SettingsPanel(); 
	TutorialPanel tup = new TutorialPanel(); 
	
	public static boolean gpStart; 
	
	public TitlePanel(){
		
		//Initializing the tile object 
		tClock = new Timer(1,this); 
		tClock.start();

		//Initializing the title images(Start, Load, Settings, Tutorial, Quit)
		titleImgs = new Image[10]; 
		
		//Initializing the block images
		tImage = new Image[60]; 
		blockX = new int[60];
		blockY = new int[60];
		
		
		//Block movement------------------------------------------------
		//Random selection of blocks
		blockChoice[0] = r.nextInt(5)+1; 
		blockChoice[1] = r.nextInt(5)+1; 
		blockChoice[2] = r.nextInt(5)+1; 
		
		//Moving blocks X and Y
		blockX[6] = -20;
		blockY[6] = 40;
		
		//Set 1 (Y goes up to 80)
		blockX[20] = -20;
		blockY[20] = 40;
		
		blockX[21] = -120;
		blockY[21] = 70;
		
		blockX[22] = -150;
		blockY[22] = 50;
		
		blockX[23] = -180;
		blockY[23] = 80;
		
		blockX[24] = -70;
		blockY[24] = 75;
		
		
		//Set 2
		blockX[25] = -20;
		blockY[25] = 100;
		
		blockX[26] = -200;
		blockY[26] = 120;
		
		blockX[27] = -150;
		blockY[27] = 110;
		
		blockX[28] = -140;
		blockY[28] = 130;
		
		blockX[29] = -220;
		blockY[29] = 145;
		
		//Set 3
		blockX[30] = -20;
		blockY[30] = 150;
		
		blockX[31] = -100;
		blockY[31] = 120;
		
		blockX[32] = -150;
		blockY[32] = 110;
		
		blockX[33] = -170;
		blockY[33] = 130;
		
		blockX[34] = -175;
		blockY[34] = 140;
		
		
		
		
		
		//Giving the point object some parameters
		point = new Point(0,0); 
		
		//Adding the mouseListener onto the panel and setting the size of it 
		addMouseListener(this);
		setSize(730,300);
		System.out.println("Test");
		
		try{
			// Open file to read from, named SavedObj.sav.
			FileInputStream saveFile = new FileInputStream("playerSettings.sav");

			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);
			// readObject() returns a generic Object, we cast those back
			// into their original class type.
			GamePanel.level = (int) save.readObject();
			GamePanel.gameScreenTheme = (int) save.readObject();

			// Close the file.
			save.close(); // This also closes saveFile.
			}
			catch(FileNotFoundException f){
			System.out.println("No save file found for player settings. Starting with default settings");
			playerSettingsSave=false;
			}
			catch(Exception exc){
			exc.printStackTrace();
			}
		
		
	}
	
	
	//MouseListener Functions ---------------------------------------------
	public void mouseClicked(MouseEvent e) {
		//System.out.println("The frame was clicked.");
		point = e.getPoint(); 
		//Check if the start image was clicked 
		if(startImageBounds.contains(point)){
			//Start Clicked. Gamepanel added to frame, current panel is removed, and game start variables are reset
			System.out.println("Start Clicked");
			PDevPanel.uiClick.play();
			Blocko.frame.add(gp);
			Blocko.frame.remove(this);
			Blocko.frame.validate(); 
			gp.repaint(); 
			gpStart = true; 
			titleTimer = 0; 
		}
		//Check if the Load image was clicked
		if(loadImageBounds.contains(point)){
			//Load Clicked. Save state loaded
			System.out.println("Load Clicked"); 
			PDevPanel.uiClick.play();
			titleTimer = 0; 
			loadedTimer = true; 
			try{
				// Open file to read from, named SavedObj.sav.
				FileInputStream saveFile = new FileInputStream("saveState.sav");

				// Create an ObjectInputStream to get objects from save file.
				ObjectInputStream save = new ObjectInputStream(saveFile);
				// readObject() returns a generic Object, we cast those back
				// into their original class type.
				Blocko.igrid = (int[][]) save.readObject();
				GamePanel.level = (int) save.readObject();
				GamePanel.score = (int) save.readObject();

				// Close the file.
				save.close(); // This also closes saveFile.
				}
				catch(FileNotFoundException f){
				System.out.println("No save file found");
				saveFound=false;
				}
				catch(Exception exc){
				exc.printStackTrace(); // If there was an error, print the info.
				}
			if(saveFound){
			// Print the values, to see that they've been recovered.
			System.out.println("\nRestored Object Values:\n");
			System.out.println("\tThe igrid");
			System.out.println("\tLevel "+GamePanel.level);
			System.out.println("\tScore "+GamePanel.score);
			}
		}
		//Check if the options image was clicked 
		if(optionsImageBounds.contains(point)){
			//Options clicked. Add the settings panel onto the frame, remove this one, validate
			System.out.println("Options Clicked");
			PDevPanel.uiClick.play();
			Blocko.frame.add(sp);
			Blocko.frame.remove(this);
			Blocko.frame.validate(); 
			sp.repaint(); 
			titleTimer = 0; 
		}
		//Check if the tutorial image was clicked 
		if(tutorialImageBounds.contains(point)){
			//Tutorial clicked. Add the Tutorial panel onto the frame, remove this one, validate
			System.out.println("Tutorial Clicked");
			PDevPanel.uiClick.play();
			Blocko.frame.add(tup);
			Blocko.frame.remove(this);
			Blocko.frame.validate(); 
			tup.repaint(); 
			titleTimer = 0; 
		}
		//Check if the Quit image was clicked 
		if(quitImageBounds.contains(point)){
			//Quit the game
			PDevPanel.uiClick.play();
			System.out.println("Quit Clicked");
			System.exit(1);
		}
		if(scoreImageBounds.contains(point)){
			//View the High Scores
			PDevPanel.uiClick.play();
			System.out.println("High scores Clicked");
			Blocko.frame.add(Blocko.sp);
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
		point = e.getPoint(); 
		if(startImageBounds.contains(point)){
			//System.out.println("Start Pressed");
			startClicked = true; 
		}
		if(tutorialImageBounds.contains(point)){
			//System.out.println("Tutorial Pressed");
			tutClicked = true; 
		}
		if(optionsImageBounds.contains(point)){
			System.out.println("Options Pressed"); 
			optClicked = true; 
		}
		if(loadImageBounds.contains(point)){
			System.out.println("Load Pressed"); 
			loadClicked = true; 
		}
		if(quitImageBounds.contains(point)){
			System.out.println("Quit Pressed");
			quitClicked = true; 
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		//System.out.println("The left mouse button was released.");
		point = e.getPoint(); 
		if(startImageBounds.contains(point)){
			//System.out.println("Start Released");
			startClicked = false; 
		}
		if(tutorialImageBounds.contains(point)){
			//System.out.println("Tutorial Released");
			tutClicked = false; 
		}
		if(optionsImageBounds.contains(point)){
			//System.out.println("Options Released"); 
			optClicked = false; 
		}
		if(loadImageBounds.contains(point)){
			//System.out.println("Load Released"); 
			loadClicked = false; 
		}
		if(quitImageBounds.contains(point)){
			System.out.println("Quit Release");
			quitClicked = false; 
		}
	}
	
	
	
	//ActionPerformed called by the timer
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
		if(startClicked)
			titleImgs[0] = Toolkit.getDefaultToolkit().getImage("src/startg1.gif");
		else
			titleImgs[0] = Toolkit.getDefaultToolkit().getImage("src/startg.gif");
		g.fillRect(330,217,55,20);		
		
		//Prepare Load Text
		if(loadClicked)	
			titleImgs[1] = Toolkit.getDefaultToolkit().getImage("src/loadg1.gif");
		else 				
			titleImgs[1] = Toolkit.getDefaultToolkit().getImage("src/loadg.gif");
		g.fillRect(270,243,45,20);
		
		
		titleImgs[5] = Toolkit.getDefaultToolkit().getImage("src/loaded.gif");
		g.fillRect(270,243,45,20);
		
		//Prepare Options Text
		if(optClicked)
			titleImgs[2] = Toolkit.getDefaultToolkit().getImage("src/settingsg1.gif");
		else 
			titleImgs[2] = Toolkit.getDefaultToolkit().getImage("src/settingsg.gif");
		
		g.fillRect(375,187,85,20);
		
		//Prepare Tutorial Text
		if(tutClicked)
			titleImgs[3] = Toolkit.getDefaultToolkit().getImage("src/tutorialg1.gif");
		else 
			titleImgs[3] = Toolkit.getDefaultToolkit().getImage("src/tutorialg.gif");
		
		g.fillRect(255,187,80,20);
		
		//Prepare Quit Text
		if(quitClicked)	
			titleImgs[4] = Toolkit.getDefaultToolkit().getImage("src/quitg1.gif");
		else 
			titleImgs[4] = Toolkit.getDefaultToolkit().getImage("src/quitg.gif");
		
		g.fillRect(395,242,47,20);
		
		
			
		//Draw the start text 
		g.drawImage(titleImgs[0], 334, 220, null);		
		
		
		
		g.drawImage(titleImgs[2], 380, 190, null);
		g.drawImage(titleImgs[3], 255, 190, null);
		
		g.drawImage(titleImgs[1], 275, 245, null);
		if(loadedTimer == true){
			g.drawImage(titleImgs[5], 256, 267, null);
			loadInt++; 
			//System.out.println(loadInt);
			if(loadInt > 200){
				loadedTimer = false;
				loadInt = 0;
			}	
		}
		g.drawImage(titleImgs[4], 400, 245, null);
		

		
		
		g.fillRect(610,254,120,20);
		titleImgs[6] = Toolkit.getDefaultToolkit().getImage("src/scoresText.gif");
		prepareImage(titleImgs[6], this); 
		g.drawImage(titleImgs[6], 610, 255, null);
		

		//Prepare the blocks making up the title of the game 
		for (int i = 0; i < tImage.length; i++) {
			
			//Block color changes depending on the theme selected
			tImage[i] = Toolkit.getDefaultToolkit().getImage("src/t" + GamePanel.gameScreenTheme + ".gif");
			prepareImage(tImage[i], this); 

			//Set the coordinates of the blocks
			//blockY[i] = 30; 
			//blockX[i] = -20; 
		}
		
		
		
		
		
		
	
		
		//Moving blocks across the screen
		/*
		Blocks 20-35 will be used for the blocks moving across the title screen
		
		
		*/
		g.drawImage(tImage[6], blockX[6], blockY[6], null);
		
		//Set 1 
		g.drawImage(tImage[20], blockX[20] , blockY[20], null);
		g.drawImage(tImage[21], blockX[21] , blockY[21], null);
		g.drawImage(tImage[22], blockX[22] , blockY[22], null);
		g.drawImage(tImage[23], blockX[23] , blockY[23], null);
		g.drawImage(tImage[24], blockX[24] , blockY[24], null);
		
		//Set 2
		g.drawImage(tImage[25], blockX[25] , blockY[25], null);
		g.drawImage(tImage[26], blockX[26] , blockY[26], null);
		g.drawImage(tImage[27], blockX[27] , blockY[27], null);
		g.drawImage(tImage[28], blockX[28] , blockY[28], null);
		g.drawImage(tImage[29], blockX[29] , blockY[29], null);		
		
		//Set 3 
		g.drawImage(tImage[30], blockX[30] , blockY[30], null);
		g.drawImage(tImage[31], blockX[31] , blockY[31], null);
		g.drawImage(tImage[32], blockX[32] , blockY[32], null);
		g.drawImage(tImage[33], blockX[33] , blockY[33], null);
		g.drawImage(tImage[34], blockX[34] , blockY[34], null);		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Drawing the blocks for the title(BLOCK-O)
		//B
		g.drawImage(tImage[0], 20 , 30, null);
		g.drawImage(tImage[1], 20 , 49, null);
		g.drawImage(tImage[2], 20 , 68, null);
		g.drawImage(tImage[3], 20 , 87, null);
		g.drawImage(tImage[4], 20 , 106, null);
		g.drawImage(tImage[5], 20 , 125, null);
		g.drawImage(tImage[6], 20 , 144, null);
		
		
		g.drawImage(tImage[7], 39 , 30, null);
		g.drawImage(tImage[8], 57 , 30, null);
		g.drawImage(tImage[9], 76 , 30, null);
		
		g.drawImage(tImage[10], 39 , 87, null);
		g.drawImage(tImage[11], 57 , 87, null);
		g.drawImage(tImage[12], 76 , 87, null);
		
		g.drawImage(tImage[13], 39 , 144, null);
		g.drawImage(tImage[14], 57 , 144, null);
		g.drawImage(tImage[15], 76 , 144, null);
		
		g.drawImage(tImage[13], 95 , 49, null);
		g.drawImage(tImage[14], 95 , 68, null);
		
		g.drawImage(tImage[13], 95 , 106, null);
		g.drawImage(tImage[14], 95 , 125, null);
		
		
		//L
		g.drawImage(tImage[0], 125 , 30, null);
		g.drawImage(tImage[1], 125 , 49, null);
		g.drawImage(tImage[2], 125 , 68, null);
		g.drawImage(tImage[3], 125 , 87, null);
		g.drawImage(tImage[4], 125 , 106, null);
		g.drawImage(tImage[5], 125 , 125, null);
		g.drawImage(tImage[6], 125 , 144, null);
		
		g.drawImage(tImage[4], 144 , 144, null);
		g.drawImage(tImage[5], 163 , 144, null);
		g.drawImage(tImage[6], 182 , 144, null);
			
		
		
		//O

		g.drawImage(tImage[1], 201 , 49, null);
		g.drawImage(tImage[2], 201 , 68, null);
		g.drawImage(tImage[3], 201 , 87, null);
		g.drawImage(tImage[4], 201 , 106, null);
		g.drawImage(tImage[5], 201 , 125, null);

		
		g.drawImage(tImage[0], 220 , 30, null);
		g.drawImage(tImage[0], 239 , 30, null);
		g.drawImage(tImage[0], 258 , 30, null);
		
		g.drawImage(tImage[0], 220 , 144, null);
		g.drawImage(tImage[0], 239 , 144, null);
		g.drawImage(tImage[0], 258 , 144, null);
		
		g.drawImage(tImage[1], 277 , 49, null);
		g.drawImage(tImage[2], 277 , 68, null);
		g.drawImage(tImage[3], 277 , 87, null);
		g.drawImage(tImage[4], 277 , 106, null);
		g.drawImage(tImage[5], 277 , 125, null);

		//C
		g.drawImage(tImage[1], 308 , 49, null);
		g.drawImage(tImage[2], 308 , 68, null);
		g.drawImage(tImage[3], 308 , 87, null);
		g.drawImage(tImage[4], 308 , 106, null);
		g.drawImage(tImage[5], 308 , 125, null);
		
		g.drawImage(tImage[0], 327 , 30, null);
		g.drawImage(tImage[0], 346 , 30, null);
		g.drawImage(tImage[0], 365 , 30, null);
		
		g.drawImage(tImage[0], 327 , 144, null);
		g.drawImage(tImage[0], 346 , 144, null);
		g.drawImage(tImage[0], 365 , 144, null);
		
		g.drawImage(tImage[1], 384 , 49, null);
		g.drawImage(tImage[1], 384 , 125, null);

		//K
		g.drawImage(tImage[0], 420 , 30, null);
		g.drawImage(tImage[1], 420 , 49, null);
		g.drawImage(tImage[2], 420 , 68, null);
		g.drawImage(tImage[3], 420 , 87, null);
		g.drawImage(tImage[4], 420 , 106, null);
		g.drawImage(tImage[5], 420 , 125, null);
		g.drawImage(tImage[6], 420 , 144, null);
		
		g.drawImage(tImage[6], 496 , 30, null);
		g.drawImage(tImage[6], 477 , 49, null);
		g.drawImage(tImage[6], 458 , 68, null);
		g.drawImage(tImage[6], 439 , 87, null);
		g.drawImage(tImage[6], 496 , 144, null);
		g.drawImage(tImage[6], 477 , 125, null);
		g.drawImage(tImage[6], 458 , 106, null);
		
		// --
		g.drawImage(tImage[6], 519 , 87, null);
		g.drawImage(tImage[6], 538 , 87, null);
		g.drawImage(tImage[6], 557 , 87, null);
		g.drawImage(tImage[6], 576 , 87, null);
		
		//O(2)
		g.drawImage(tImage[1], 610 , 49, null);
		g.drawImage(tImage[2], 610 , 68, null);
		g.drawImage(tImage[3], 610 , 87, null);
		g.drawImage(tImage[4], 610 , 106, null);
		g.drawImage(tImage[5], 610 , 125, null);
		g.drawImage(tImage[6], blockX[6] , blockY[6], null);

		
		
		
		g.drawImage(tImage[0], 629 , 30, null);
		g.drawImage(tImage[0], 648 , 30, null);
		g.drawImage(tImage[0], 667 , 30, null);
		
		g.drawImage(tImage[0], 629 , 144, null);
		g.drawImage(tImage[0], 648 , 144, null);
		g.drawImage(tImage[0], 667 , 144, null);
		
		
		
		
		g.drawImage(tImage[1], 686 , 49, null);
		g.drawImage(tImage[2], 686 , 68, null);
		g.drawImage(tImage[3], 686 , 87, null);
		g.drawImage(tImage[4], 686 , 106, null);
		g.drawImage(tImage[5], 686 , 125, null);	
		
	
		//Block movement------------------------------------------------------
		
		//Set 1
		if(blockChoice[0]==1)
			blockX[20]++;
		if(blockChoice[0]==2)
			blockX[21]++;
		if(blockChoice[0]==3)
			blockX[22]++;
		if(blockChoice[0]==4)
			blockX[23]++;
		if(blockChoice[0]==5)
			blockX[24]++;
		
		//Set 2
		if(titleTimer > 200){
			
		if(blockChoice[1]==1)
			blockX[25]++;
		if(blockChoice[1]==2)
			blockX[26]++;
		if(blockChoice[1]==3)
			blockX[27]++;
		if(blockChoice[1]==4)
			blockX[28]++;
		if(blockChoice[1]==5)
			blockX[29]++;
		}
		
		if(titleTimer > 400){
		//Set 3
		if(blockChoice[2]==1)
			blockX[30]++;
		if(blockChoice[2]==2)
			blockX[31]++;
		if(blockChoice[2]==3)
			blockX[32]++;
		if(blockChoice[2]==4)
			blockX[33]++;
		if(blockChoice[2]==5)
			blockX[34]++;		
		}
		
		
		
		for (int i = 20; i < 25; i++) {
			
			if(blockX[i] > 800){
				blockX[i] = -20;
				blockChoice[0] = r.nextInt(5)+1; 
				/*
				for (int j = 0; j < blockChoice.length; j++) {
					System.out.print(blockChoice[j] + " ");
				}
				System.out.println(); 
				*/
			}
			
		}
		
		for (int i = 25; i < 30; i++) {
			
			if(blockX[i] > 800){
				blockX[i] = -20;
				blockChoice[1] = r.nextInt(5)+1; 
				/*
				for (int j = 0; j < blockChoice.length; j++) {
					System.out.print(blockChoice[j] + " ");
				}
				System.out.println(); 
				*/
			}
		}
		
		for (int i = 30; i < 35; i++) {
			
			if(blockX[i] > 800){
				blockX[i] = -20;
				blockChoice[2] = r.nextInt(5)+1; 
				/*
				for (int j = 0; j < blockChoice.length; j++) {
					System.out.print(blockChoice[j] + " ");
				}
				System.out.println(); 
				*/
			}
		}
		
		
		
		
		
		titleTimer++;
		//System.out.print(titleTimer + " ");
		
		
	}

	
	
}
