

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Rectangle; 
import java.util.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.*;


public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Making the integers for the delay of the timer, the initial level, and the player's score
	static int timerDelay;
	static int level=1;
	static int score=0;
	
	//Integer that is being incremeted as the level method is called. 
	//Used to change variables inside of the method only once
	int levelTimer; 
	
	//Random abject for spawning, Block object, and Spawn Timer
	int spawnTimer;
	int blockTimer = 0; 
	Random r; 
	Block theblock;
	
	//Game Timer
	private Timer gClock; 
	
	//Storing the score in some varibles to keep track of how it changes 
	int resultantScore;
	int previousScore; 
	
	//key movements
		private final int U = 38;
		private final int DO = 40;
		private final int L = 37;
		private final int R = 39;
		private boolean moveU = false;
		private boolean moveD = false;
		private boolean moveL = false;
		private boolean moveR = false;
	
	//Making a titlepanel object 
	public static TitlePanel tp = new TitlePanel(); 	
	
	//Delay variable for the levels
	int lvlDelay;
		
	//Booleans made to ask whether or not the game was paused and whether or not the game has started 
	private boolean gamePaused; 
	private boolean gameStart; 
	
	//End Game boolean
	public static boolean gameEnded; 
	
	//Block Spawning Arraylist. Used as reference to make the "next block" box 
	ArrayList<Integer> blockList = new ArrayList<Integer>(); 
	
	
	
	//Timer that is incremented to keep track of anything happening inside the panel
	int titleTimer;
	
	/*
		**Choose the theme**
			1 = Original
			2 = Retro
			3 = New Age	(Will be added later)	
	*/	
	//Integer determining what theme is being used. Referenced in settings and title panel
	static int gameScreenTheme = 2; 
	
	int blockNum; 
	
	//Integers that contain the x and y values of the blocks 
	int blockX;
	int blockY;
	
	//Rows and columns on the Grid
	final int rows = 25;
	final int columns = 50; 
	
	//Array of Block Images 
	Image bImage[][];
		
	
	//Image object for the Grid  
	Image gImage; 
	
	
	//Array of the "Original" theme images 
	private Image originalImgs[]; 
	private Image originalN[]; 
	
	//Array of the "Retro" theme images 
	private Image retroImgs[]; 
	private Image retroN[]; 
	
	//Array of blocks
	private Image blockImgs[]; 	
	
	//Used to store the individual digits of the player's score
	int scoreDigit[]; 
	
	//Used to store the blockChoice that is generated randomly
	int blockChoice; 
	
	//Integer that indicates what block is being held
	int heldBlock; 
	
	//Transparent images for pause and game over screen
	Image transImage[]; 
	
	
	//Rectangle and point being used to handle the mouseclicks
	Rectangle pResumeBounds = new Rectangle(297, 97, 148, 36); 
	Rectangle pSaveBounds = new Rectangle(273, 144, 200, 36); 
	Rectangle pTitleBounds = new Rectangle(272, 193, 200, 36); 
	Rectangle pQuitBounds = new Rectangle(324, 242, 80, 34); 
	//Rectangles for Game Over Screen
	Rectangle eRetryBounds = new Rectangle(317, 109, 95, 45); 
	Rectangle eTitleBounds = new Rectangle(263, 157, 210, 40); 
	Rectangle eQuitBounds = new Rectangle(320, 205, 85, 45); 
	
	Point point; 
	
	
	
	public GamePanel(){
		//Setting the timer delay
		timerDelay = 110; 
		
		//Parameters for the Timer
		gClock = new Timer(timerDelay,this); 
		gClock.start();
		
		//Initially making the gamePaused variable false because the game is not paused when started		
		gamePaused = false; 
		
		//Initially making the gameStart variable false because the game has not started
		gameStart = false; 
		
		//End game 
		gameEnded = false; 
		
		
		//Block Spawning integer and Random object
		spawnTimer = 0; 
		r = new Random(); 
		
		//Used to do something inside the level method once 
		levelTimer = 0; 
		
		//Used to keep track of the score 
		resultantScore = 0; 
		previousScore = 0; 
		
		//Initializing theblock used to spawn new blocks to a blank block
		theblock = new Block0();  

		
		//Giving the point object some parameters
		point = new Point(0,0); 
		

		//Initializing the arrays of images for the different themes
		originalImgs =  new Image[9]; 
		retroImgs =  new Image[11]; 
		retroN = new Image[5]; 
		originalN = new Image[5]; 
		
		//Initializing the digits array
		scoreDigit = new int[5]; 
		scoreDigit[0] = 0; 
		
		//Image blocks
		blockImgs = new Image[5]; 
		
		//Initializing the transparent images 
		transImage = new Image[3]; 
		
		//Initializing the double array of block images 
		bImage = new Image[rows][columns]; 
		
		//Initializing the x and y values of the blocks 
		blockX = 16; 
		blockY = 1; 
		
		
		//Setting the size of the gamePanel and adding the listeners to it 
		setSize(730,300);
		addMouseListener(this);
		Blocko.frame.addKeyListener(this);	
	}
	
	
	//MouseListener Functions -------------------------------------------------------------
	
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("The frame was clicked.");
		point = e.getPoint(); 
		//Only listen for the mouseclicks on the pause screen when the game is paused	
		if(gamePaused == true){
		
			if(pResumeBounds.contains(point)){
				System.out.println("Resume Game Clicked");
				gamePaused = false; 
				PDevPanel.uiClick.play();
			
			}
			else if(pSaveBounds.contains(point)){
				System.out.println("Save Game Clicked");
				PDevPanel.uiClick.play();
				try{
					// Open a file to write to
					FileOutputStream saveFile=new FileOutputStream("saveState.sav");

					// Create an ObjectOutputStream to put objects into save file.
					ObjectOutputStream save = new ObjectOutputStream(saveFile);

					// Now we do the save.
					save.writeObject(Blocko.igrid);
					save.writeObject(level);
					save.writeObject(score);

					// Close the file.
					save.close(); // This also closes saveFile.
					}
					catch(Exception exc){
					exc.printStackTrace(); // If there was an error, print the info.
					}
			
			}
			else if(pTitleBounds.contains(point)){
				System.out.println("Title Screen Clicked");
				PDevPanel.uiClick.play();
				Blocko.frame.remove(this);
				Blocko.frame.add(Blocko.tp);
				Blocko.frame.validate(); 
				Blocko.tp.repaint(); 
				
				//Reset the game panel
				gameStart = false; 
				gamePaused = false; 
				spawnTimer = 0; 
				blockList.clear(); 
				blockTimer = 0; 
				score = 0;
				level = 1; 
				TitlePanel.gpStart = false; 
				
				clearGrid(); 
			
			}
			else if(pQuitBounds.contains(point)){
				System.out.println("Quit Clicked");
				PDevPanel.uiClick.play();
				System.exit(0);
			}
		}
		
		//Only listen for the mouseclicks on the game over screen when the game has ended
		if(gameEnded == true){
			if(eRetryBounds.contains(point)){
				System.out.println("Retry Clicked");
				PDevPanel.uiClick.play();
				clearGrid(); 
				gameStart = false; 
				gameEnded = false; 
				spawnTimer = 0; 
				blockList.clear(); 
				blockTimer = 0; 
				score = 0; 
				level = 1; 
			
			}
			else if(eTitleBounds.contains(point)){
				System.out.println("Title Screen Clicked");
				PDevPanel.uiClick.play();
				Blocko.frame.remove(this);
				Blocko.frame.add(Blocko.tp);
				Blocko.frame.validate(); 
				Blocko.tp.repaint(); 
				
				//Reset the game panel
				gameEnded = false; 
				gameStart = false; 
				gamePaused = false; 
				clearGrid(); 
				spawnTimer = 0; 
				blockList.clear(); 
				blockTimer = 0;
				level = 1; 
				TitlePanel.gpStart = false; 
				
			}
			else if(eQuitBounds.contains(point)){
				System.out.println("Quit Clicked");
				PDevPanel.uiClick.play();
				System.exit(1);
			}
		
		
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
				//System.out.print("action "); 
				//fillGrid();
				//printGrid(); 
				
				
	}

	
	
	
	
	//Method called by the actionPerformed. Calls for Spawns, Checks, Change of Levels, Separation of Score digit
	public void blockMovement() {
		
		if(gamePaused == false && gameStart == true && gameEnded == false){
			//Spawning the Blocks
			if(spawnTimer==2){

				previousScore = score; 
				spawnBlock();
			}
			if(spawnTimer>6){
				theblock.moveRight(); 
			}
			if((spawnTimer>5 && theblock.movelimitRight()== true))
				spawnBlock();
		
		

			spawnTimer++; 
			
		
		
			//Check if the blocks have reached the top
			checkEndGame();
	
			//Check for a column
			columnCheck(); 
			
			//Check for a 3x3
			squareCheck(); 
			
			//Change movement speed depending on score
			levelChange();
			
			//Score Separating 
			scoreSeparate(); 
			
		} 
		
	}
	
	//Function that clears the grid. Called when resetting the game screen
	public void clearGrid() {
		for(int i=0; i<rows; i++) // Rows
				{
					for(int j=0; j<columns; j++) // Columns
					{
						Blocko.igrid[i][j] = 0; 
						
					}
				}
	}
	
	
	
	//Method that handles block spawning      
		public void spawnBlock() {
			blockChoice = 0;	
			//int blockChoice = 2;
			//System.out.print(blockChoice);
			
			blockTimer++; 
			//Making choices for the next box
			int choice1,choice2,choice3,choice4; 
			if(blockTimer == 1){
				//Giving the choices random values
				choice1 = r.nextInt(7)+1;	
				choice2 = r.nextInt(7)+1;	
				choice3 = r.nextInt(7)+1;	
				choice4 = r.nextInt(7)+1;	
				
				blockList.add(choice1);
				blockList.add(choice2);
				blockList.add(choice3);
				blockList.add(choice4);
				System.out.println(blockList); 
				
				
			}
			
			//Removing the choices from the list and adding a new number onto it 
			blockChoice = blockList.get(0); 
			System.out.print(blockChoice);
			
			blockList.remove(0);
			
			choice1 = r.nextInt(7)+1;	
			blockList.add(choice1);
			System.out.println(blockList); 
			
			//Making a the block according to what the blockChoice is
			if(blockChoice == 1)
				theblock = new Block1();
			else if(blockChoice == 2)
				theblock = new Block2();
			else if(blockChoice == 3)
				theblock = new Block3();
			else if(blockChoice == 4)
				theblock = new Block4();
			else if(blockChoice == 5)
				theblock = new Block5();
			else if(blockChoice == 6)
				theblock = new Block6();
			else if(blockChoice == 7)
				theblock = new Block7();
			
		}
		
		//Method that holds blocks
		public void holdBlock() {
			heldBlock = blockChoice; 
			theblock.clearBlock();
			//theblock = null; 
			//System.gc(); 
			spawnBlock();
				
		}
		
		//Check if the blocks have reached the top
		public void checkEndGame() {

			for (int j = 0; j < 25; j++) {
				if(Blocko.igrid[j][0] != 0 && Blocko.igrid[j][1] != 0 && Blocko.igrid[j][2] 
						!= 0 &&Blocko.igrid[j][3] != 0 &&Blocko.igrid[j][4] != 0 ){
					gameEnded = true; 
				}
			}

			if(gameEnded == true){
				System.out.println("The Game has Ended" + " "); 			
				//System.exit(1);
				PDevPanel.gameEnded.play();
				highScores("submit");
			}	
		}
		
		//Method used to make a high score text fie and sort the high scores
		public void highScores(String input){
			if(input.equalsIgnoreCase("store")){
				try(PrintWriter writer= new PrintWriter("highScores.txt","UTF-8");){
					writer.println(score);
					writer.close();
				}
				catch(IOException e){
					System.out.println(e);
				}
			}//closes if
			
			if(input.equalsIgnoreCase("submit")){
				Arrays.sort(Blocko.highScores);
				if(score<Blocko.highScores[0]){
					//Score is too low to be submitted
				}
				else if(score>Blocko.highScores[0] && score<Blocko.highScores[1]){
					Blocko.highScores[0]=score;
				}
				else if(score>Blocko.highScores[1] && GamePanel.score<Blocko.highScores[2]){
					Blocko.highScores[0]=Blocko.highScores[1];
					Blocko.highScores[1]=score;
				}
				else if(score>Blocko.highScores[2] && GamePanel.score<Blocko.highScores[3]){
					Blocko.highScores[0]=Blocko.highScores[1];
					Blocko.highScores[1]=Blocko.highScores[2];
					Blocko.highScores[2]=score;
				}
				else if(score>Blocko.highScores[3] && GamePanel.score<Blocko.highScores[4]){
					Blocko.highScores[0]=Blocko.highScores[1];
					Blocko.highScores[1]=Blocko.highScores[2];
					Blocko.highScores[2]=Blocko.highScores[3];
					Blocko.highScores[3]=score;
				}
				else{
					Blocko.highScores[0]=Blocko.highScores[1];
					Blocko.highScores[1]=Blocko.highScores[2];
					Blocko.highScores[2]=Blocko.highScores[3];
					Blocko.highScores[3]=Blocko.highScores[4];		
					
					Blocko.highScores[4]=score;
				}
				
				try(PrintWriter writer= new PrintWriter("highScores.txt","UTF-8");){
					for(int i =0; i<Blocko.highScores.length; i++){
						writer.println(Blocko.highScores[i]);
						
					}
					writer.close();
				}
				catch(IOException e){
					System.out.println(e);
				}
			}//closes if
			
			
		}
		
		//Separate the player's score into individual digits
		 public void scoreSeparate(){
		       int number;
		       int counter = 0; 
		       number = score; 

		       while (counter<5) {
		           //System.out.println( number % 10);
		          
		           scoreDigit[counter]=number % 10;
		           number = number / 10;
		           counter++;
		       }

		 }
		
		
		
		
		
		
		//Check if a 3x3 square or 25 vertical line was made  
		public void squareCheck(){
			
			for(int i=0; i<(rows-2); i++) // Rows
			{
				for(int j=0; j<(columns-2); j++) // Columns
				{
					//Checking for a 3x3 all over the grid
					if(Blocko.igrid[i][j]   !=0 && Blocko.igrid[i][j+1]   !=0 && Blocko.igrid[i][j+2]   !=0 &&
					   Blocko.igrid[i+1][j] !=0 && Blocko.igrid[i+1][j+1] !=0 && Blocko.igrid[i+1][j+2] !=0 &&
					   Blocko.igrid[i+2][j] !=0 && Blocko.igrid[i+2][j+1] !=0 && Blocko.igrid[i+2][j+2] !=0 	
					  )
					{
					//Clearing out the 3x3 
					Blocko.igrid[i][j]=0;
					Blocko.igrid[i+1][j]=0;
					Blocko.igrid[i+2][j]=0;
					Blocko.igrid[i][j+1]=0;
					Blocko.igrid[i][j+2]=0;
					Blocko.igrid[i+1][j+1]=0;
					Blocko.igrid[i+2][j+2]=0;
					Blocko.igrid[i+1][j+2]=0;
					Blocko.igrid[i+2][j+1]=0;

					System.out.println("There is a 3x3 somewhere!");
					//Adding the score
					score+=90;
					System.out.println(score);
					//Moving the blocks down after clearing the 3x3 square
					try{
						
						for(int k =columns-1 ; k > 0 ; k-- ){
							if(Blocko.igrid[i][k]!=0 && moveRight(i, k)){
								int tempNum=k;
								while(moveRight(i, tempNum)){
								
									Blocko.igrid[i][tempNum]=0;
									Blocko.igrid[i][tempNum+1]=1;
									tempNum++;
								}
								
							}
							if(Blocko.igrid[i+1][k]!=0 && moveRight(i, k)){
								int tempNum=k;
								while(moveRight(i+1, tempNum)){
								
									Blocko.igrid[i+1][tempNum]=0;
									Blocko.igrid[i+1][tempNum+1]=1;
									tempNum++;
								}
								
							}
							if(Blocko.igrid[i+2][k]!=0 && moveRight(i, k)){
								int tempNum=k;
								while(moveRight(i+2, tempNum)){
								
									Blocko.igrid[i+2][tempNum]=0;
									Blocko.igrid[i+2][tempNum+1]=1;
									tempNum++;
								}
								
							}
						}
						}
						catch(IndexOutOfBoundsException e){
							System.out.println(e);
						}		
					}
						 
				}
			}
			
			
		}
		
		//Check if a whole column was made  
		public void columnCheck(){
			int x=0;
			for(int y=columns-1; y>-1; y--) // Columns
			{
					if(Blocko.igrid[x][y]!=0 && Blocko.igrid[x+1][y]!=0 && Blocko.igrid[x+2][y]!=0 && Blocko.igrid[x+3][y]!=0
					&& Blocko.igrid[x+4][y]!=0 && Blocko.igrid[x+5][y]!=0 && Blocko.igrid[x+6][y]!=0 && Blocko.igrid[x+7][y]!=0
					&& Blocko.igrid[x+8][y]!=0 && Blocko.igrid[x+9][y]!=0 && Blocko.igrid[x+10][y]!=0 && Blocko.igrid[x+11][y]!=0
					&& Blocko.igrid[x+12][y]!=0 && Blocko.igrid[x+13][y]!=0 && Blocko.igrid[x+14][y]!=0 && Blocko.igrid[x+15][y]!=0
					&& Blocko.igrid[x+16][y]!=0 && Blocko.igrid[x+17][y]!=0 && Blocko.igrid[x+18][y]!=0 && Blocko.igrid[x+19][y]!=0
					&& Blocko.igrid[x+20][y]!=0 && Blocko.igrid[x+21][y]!=0 && Blocko.igrid[x+22][y]!=0 && Blocko.igrid[x+23][y]!=0
					&& Blocko.igrid[x+24][y]!=0){
						
					Blocko.igrid[x][y]=0;     Blocko.igrid[x+1][y]=0; Blocko.igrid[x+2][y]=0; Blocko.igrid[x+3][y]=0;
					Blocko.igrid[x+4][y]=0;   Blocko.igrid[x+5][y]=0; Blocko.igrid[x+6][y]=0; Blocko.igrid[x+7][y]=0;
					Blocko.igrid[x+8][y]=0;   Blocko.igrid[x+9][y]=0; Blocko.igrid[x+10][y]=0;Blocko.igrid[x+11][y]=0;
					Blocko.igrid[x+12][y]=0;  Blocko.igrid[x+13][y]=0;Blocko.igrid[x+14][y]=0;Blocko.igrid[x+15][y]=0;
					Blocko.igrid[x+16][y]=0;  Blocko.igrid[x+17][y]=0;Blocko.igrid[x+18][y]=0;Blocko.igrid[x+19][y]=0;
					Blocko.igrid[x+20][y]=0;  Blocko.igrid[x+21][y]=0;Blocko.igrid[x+22][y]=0;Blocko.igrid[x+23][y]=0;
					Blocko.igrid[x+24][y]=0;
					
					System.out.println("A whole column has been made!");
					score+=250;
					System.out.println(score);
						
					try{
						
						//for(int k =columns-1 ; k > -1 ; k-- ){
							for(int i=rows-1; i>-1; i--){
							if(Blocko.igrid[i][y]!=0 && moveRight(i, y)){
								//int tempNum=y;
								//while(moveRight(i, y)){
								
									Blocko.igrid[x][y]=0;
									Blocko.igrid[x][y+1]=1;
									//y++;
							//	}//ends while
							}
							}//ends if
					
						}//ends try
						catch(IndexOutOfBoundsException e){
							System.out.println(e);
						}
					
					} // end of if statement
								
			} // end of for loop
			
			
			
		}// end of columnCheck method
		
		
		
		
		
		
		
		
		
		
		//KeyListener Methods ---------------------------------------------------------------
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 38 || e.getKeyCode() == 87){
						
						//System.out.println("Up Arrow/W pressed");
						if(gamePaused == false && gameEnded == false && gameStart == true)
							theblock.moveUp();
						
					}
					
					else if(e.getKeyCode() == 40 || e.getKeyCode() == 83)
					{
						//System.out.println("Down Arrow/S pressed");
						if(gamePaused == false && gameEnded == false && gameStart == true)
							theblock.moveDown();
							
						//gameEnded = true; 	
					}
				
					
					else if(e.getKeyCode() == 37 || e.getKeyCode() == 65)
					{
						System.out.println("Left Arrow/A pressed");
                                                PDevPanel.title.changeVolume(1);
						//if(gamePaused == false)
							//theblock.rotate();
							//holdBlock();
						//Blocko.frame.remove(Blocko.gp);
						//Blocko.frame.add(tp);
						//Blocko.frame.validate(); 
						//tp.repaint();
					}
					
					else if(e.getKeyCode() == 39 )
					{
						//System.out.println("Right Arrow/D pressed");
                                                PDevPanel.title.changeVolume(2);
						if(gamePaused == false && TitlePanel.gpStart == true ){
							timerDelay = 15; 
							gClock.setDelay(timerDelay);
							
							gameStart = true; 

						}
					}
					
					else if(e.getKeyCode() == 32){
						
						//System.out.println("Space pressed");
						if(gamePaused == false && gameEnded == false && gameStart == true)
							theblock.rotate();
						
					}
					
					
					else if(e.getKeyCode() == 80 || e.getKeyCode() == 27)
					{
						//System.out.println("P/Esc Pressed");
						if(gamePaused == false && gameStart == true && gameEnded == false){
							gamePaused=true;
						}	
						else if(gamePaused == true){
							gamePaused=false; 
						}
					}
					

					//System.out.println("K"+e.getKeyCode());

					if(e.getKeyCode()==U){moveU=true;}
					if(e.getKeyCode()==L){moveL=true;}
					if(e.getKeyCode()==DO){moveD=true;}
					if(e.getKeyCode()==R){moveR=true;}

				}




				public void keyReleased(KeyEvent e) {
					//System.out.println("K"+e.getKeyCode());

					if(e.getKeyCode()==U){moveU=false; }
					if(e.getKeyCode()==L){moveL=false; }
					if(e.getKeyCode()==DO){moveD=false; }
					if(e.getKeyCode()==R){
						moveR=false; 
						//Revert back to the timer delay of the level
						timerDelay = lvlDelay; 
						gClock.setDelay(timerDelay);
					}
					

				}

				public void keyTyped(KeyEvent e) {}

				public void setMoveU(boolean b){moveU=b;}
				public void setMoveL(boolean b){moveL=b;}
				public void setMoveR(boolean b){moveR=b;}
				public void setMoveD(boolean b){moveD=b;}
				public boolean getMoveU(){return moveU;}
				public boolean getMoveL(){return moveL;}
				public boolean getMoveR(){return moveR;}
				public boolean getMoveD(){return moveD;}

				
				
				
				
				
				
				
				
				
			
	//Change the level of the game 
	public void levelChange(){
		if(levelTimer == 1){
			gClock.setDelay(lvlDelay);
			previousScore = score; 
			//System.out.println("delay");
		}

		if(score >(previousScore+500)){
			if(level!=10)level++; 
			levelTimer = 0; 
			PDevPanel.nextLevel.play();
			System.out.println("Proceded to level " + level);
		}	
		//resultantScore = (int)(score/500);`
		
		
		//Change the conditions of the game depending on the level 
		if(level == 1){ 
			lvlDelay=110;

			//System.out.println("Level 1");
			
		}
		else if(level == 2){
			lvlDelay=90;
			

		}
		else if(level == 3){
			lvlDelay=80;

		}		
		else if(level == 4){
			lvlDelay=70;

		}
		else if(level == 5){
			lvlDelay=60;

		}
		else if(level == 6){
			lvlDelay=50;

		}
		else if(level == 7){
			lvlDelay=40;

		}
		else if(level == 8){
			lvlDelay=30;

		}
		else if(level == 9){
			lvlDelay=20;

		}
		else if(level == 10){
			lvlDelay=10;

		}
	
		levelTimer++; 
	}
	
	
	
	
	
	
	
	public void paint(Graphics g){
		
		
		//Images for the "Original" design of the Gamescreen	
				if(gameScreenTheme == 1){
					//Level Text Image
					originalImgs[0] = Toolkit.getDefaultToolkit().getImage("src/lvl1.gif");
					prepareImage(originalImgs[0], this); 
					if(level > 0 && level < 10){
						originalImgs[7] = Toolkit.getDefaultToolkit().getImage("src/on" + level + ".gif");
						prepareImage(originalImgs[7], this);
					}
					else if(level == 10) {
						originalImgs[7] = Toolkit.getDefaultToolkit().getImage("src/on1.gif");
						prepareImage(originalImgs[7], this);	
						originalImgs[8] = Toolkit.getDefaultToolkit().getImage("src/on0.gif");
						prepareImage(originalImgs[8], this);
					}
					
				
					//Prepare the background image
					originalImgs[1] = Toolkit.getDefaultToolkit().getImage("src/menubackground.gif");
					prepareImage(originalImgs[1], this); 
				
					//Next Block Images
					originalImgs[2] = Toolkit.getDefaultToolkit().getImage("src/nexttext.gif");
					prepareImage(originalImgs[2], this);
					originalImgs[3] = Toolkit.getDefaultToolkit().getImage("src/nextbox.gif");
					prepareImage(originalImgs[3], this);
					
					//Blocks for nextbox					
					try {			
						blockImgs[0] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(3) + ".gif");
						prepareImage(blockImgs[0], this);
						blockImgs[1] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(2) + ".gif");
						prepareImage(blockImgs[1], this);
						blockImgs[2] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(1) + ".gif");
						prepareImage(blockImgs[2], this);
						blockImgs[3] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(0) + ".gif");
						prepareImage(blockImgs[3], this);			
					} catch (IndexOutOfBoundsException e) {
						//System.out.println("No blocks yet"); 
					}
					
					//Hold Images 
					originalImgs[4] = Toolkit.getDefaultToolkit().getImage("src/holdtext.gif");
					prepareImage(originalImgs[4], this);
					originalImgs[5] = Toolkit.getDefaultToolkit().getImage("src/holdbox.gif");
					prepareImage(originalImgs[5], this);
				
					//Score Info Images
					originalImgs[6] = Toolkit.getDefaultToolkit().getImage("src/scoretext.gif");
					prepareImage(originalImgs[6], this);
					
					//Number Images 
					originalN[0] = Toolkit.getDefaultToolkit().getImage("src/on" +scoreDigit[4] + ".gif");
					prepareImage(originalN[0], this);
					originalN[1] = Toolkit.getDefaultToolkit().getImage("src/on" +scoreDigit[3] + ".gif");
					prepareImage(originalN[1], this);
					originalN[2] = Toolkit.getDefaultToolkit().getImage("src/on" +scoreDigit[2] + ".gif");
					prepareImage(originalN[2], this);
					originalN[3] = Toolkit.getDefaultToolkit().getImage("src/on" +scoreDigit[1] + ".gif");
					prepareImage(originalN[3], this);
					originalN[4] = Toolkit.getDefaultToolkit().getImage("src/on" +scoreDigit[0] + ".gif");
					prepareImage(originalN[4], this);
				}
				
				
				//Images for the "Retro" design of the Gamescreen	
				if(gameScreenTheme == 2){
					//Prepare the background image
					retroImgs[0] = Toolkit.getDefaultToolkit().getImage("src/retro_border.gif");
					prepareImage(retroImgs[0], this); 
					retroImgs[1] = Toolkit.getDefaultToolkit().getImage("src/black.gif");
					prepareImage(retroImgs[1], this); 
				
					//Prepare the Next Block images 
					retroImgs[2] = Toolkit.getDefaultToolkit().getImage("src/retro_nexttext.gif");
					prepareImage(retroImgs[2], this);
					retroImgs[3] = Toolkit.getDefaultToolkit().getImage("src/retro_nextbox.gif");
					prepareImage(retroImgs[3], this);
					
					//Blocks for nextbox	
					try {			
						blockImgs[0] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(3) + ".gif");
						prepareImage(blockImgs[0], this);
						blockImgs[1] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(2) + ".gif");
						prepareImage(blockImgs[1], this);
						blockImgs[2] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(1) + ".gif");
						prepareImage(blockImgs[2], this);
						blockImgs[3] = Toolkit.getDefaultToolkit().getImage("src/block" + blockList.get(0) + ".gif");
						prepareImage(blockImgs[3], this);			
					} catch (IndexOutOfBoundsException e) {
						//System.out.println("No blocks yet"); 
					}
					
					//Prepare the Level Images
					retroImgs[4] = Toolkit.getDefaultToolkit().getImage("src/retro_leveltext.gif");
					prepareImage(retroImgs[4], this);
					if(level > 0 && level < 10){
						retroImgs[9] = Toolkit.getDefaultToolkit().getImage("src/rn" + level + ".gif");
						prepareImage(retroImgs[9], this);
					}
					else if(level == 10) {
						retroImgs[9] = Toolkit.getDefaultToolkit().getImage("src/rn1.gif");
						prepareImage(retroImgs[9], this);	
						retroImgs[10] = Toolkit.getDefaultToolkit().getImage("src/rn0.gif");
						prepareImage(retroImgs[10], this);
					}
					
					
					//Prepare the Block hold Images
					retroImgs[5] = Toolkit.getDefaultToolkit().getImage("src/retro_holdtext.gif");
					prepareImage(retroImgs[5], this);
					retroImgs[6] = Toolkit.getDefaultToolkit().getImage("src/retro_holdbox.gif");
					prepareImage(retroImgs[6], this);
				
					//Prepare the Score images 
					retroImgs[7] = Toolkit.getDefaultToolkit().getImage("src/retro_scoretext.gif");
					prepareImage(retroImgs[7], this);
					retroImgs[8] = Toolkit.getDefaultToolkit().getImage("src/retro_scorebox.gif");
					prepareImage(retroImgs[8], this);
					
					//Number Images 
					retroN[0] = Toolkit.getDefaultToolkit().getImage("src/rn" +scoreDigit[4] + ".gif");
					prepareImage(retroN[0], this);
					retroN[1] = Toolkit.getDefaultToolkit().getImage("src/rn" +scoreDigit[3] + ".gif");
					prepareImage(retroN[1], this);
					retroN[2] = Toolkit.getDefaultToolkit().getImage("src/rn" +scoreDigit[2] + ".gif");
					prepareImage(retroN[2], this);
					retroN[3] = Toolkit.getDefaultToolkit().getImage("src/rn" +scoreDigit[1] + ".gif");
					prepareImage(retroN[3], this);
					retroN[4] = Toolkit.getDefaultToolkit().getImage("src/rn" +scoreDigit[0] + ".gif");
					prepareImage(retroN[4], this);
					
				
				}
						
				
				
				
				
				
		
		
		//Images for the "Original" design of the Gamescreen	
		if(gameScreenTheme == 1){	
			//Sidebar background image 
			g.drawImage(originalImgs[1], 0, 0, null);
			
			//Level Image
			g.drawImage(originalImgs[0], 655, 70, null);			
			if(level > 0 && level < 10){
				g.drawImage(originalImgs[7], 667, 103, null);
			}
			else if(level == 10) {
				g.drawImage(originalImgs[7], 661, 103, null);
				g.drawImage(originalImgs[8], 671, 103, null);
			}
			
			
			//Hold Block Image
			//g.drawImage(originalImgs[4], 648, 80, null);
			//g.drawImage(originalImgs[5], 632, 115, null);
		
			//Next Text
			g.drawImage(originalImgs[2], 550, 10, null);
			g.drawImage(originalImgs[3], 525, 40, null);
			
			//Blocks for nextbox
			g.drawImage(blockImgs[0], 555, 70, null);
			g.drawImage(blockImgs[1], 555, 120, null);
			g.drawImage(blockImgs[2], 555, 170, null);
			g.drawImage(blockImgs[3], 555, 220, null);
			
			
			//Score text
			g.drawImage(originalImgs[6], 640, 210, null);
					
			
			
			//Numbers for the score 
			g.drawImage(originalN[0], 640, 240, null);
			g.drawImage(originalN[1], 655, 240, null);
			g.drawImage(originalN[2], 670, 240, null);
			g.drawImage(originalN[3], 685, 240, null);
			g.drawImage(originalN[4], 700, 240, null);
			
			
			
		}	
		
		
		
		
		//Images for the "Retro" design of the Gamescreen	
		if(gameScreenTheme == 2){
			//Sidebar background image 
			g.drawImage(retroImgs[1], 0, 0, null);
			g.drawImage(retroImgs[0], 521, 5, null);
		
			//Next Block images 
			g.drawImage(retroImgs[2], 547, 15, null);
			g.drawImage(retroImgs[3], 530, 40, null);	
			
			//Blocks for nextbox
			g.drawImage(blockImgs[0], 561, 55, null);
			g.drawImage(blockImgs[1], 561, 95, null);
			g.drawImage(blockImgs[2], 561, 135, null);
			g.drawImage(blockImgs[3], 561, 175, null);
			
			//Level Image 
			g.drawImage(retroImgs[4], 648, 50, null);
			if(level > 0 && level < 10){
				g.drawImage(retroImgs[9], 660, 75, null);
			}
			else if(level == 10) {
				g.drawImage(retroImgs[9], 655, 75, null);
				g.drawImage(retroImgs[10], 667, 75, null);
			}
				
		
			//Block hold images 
			//g.drawImage(retroImgs[5], 642, 15, null);
			//g.drawImage(retroImgs[6], 628, 40, null);
			
			//Score Images 
			g.drawImage(retroImgs[7], 633, 170, null);
			g.drawImage(retroImgs[8], 621, 195, null);
			
			//Numbers for the score 
			g.drawImage(retroN[0], 633, 200, null);
			g.drawImage(retroN[1], 648, 200, null);
			g.drawImage(retroN[2], 663, 200, null);
			g.drawImage(retroN[3], 678, 200, null);
			g.drawImage(retroN[4], 693, 200, null);
			
			
			
			
		}	
		

		//Prepare the Grid Image
		gImage = Toolkit.getDefaultToolkit().getImage("src/grid1.gif");
		prepareImage(gImage, this); 
		//Draw the grid, Grid Coordinates: (10,5)
		g.drawImage(gImage, 10, 5, null);
		
		
		
		
		/*
		For loop that recognizes and assigns the blocks on the igrid onto
		the Grid being drawn onto the game screen		
		*/
		
		for(int i=0; i<rows; i++) // Rows
		{
			for(int j=0; j<columns; j++) // Columns
			{
								
				bImage[i][j] = Toolkit.getDefaultToolkit().getImage("src/b"+Blocko.igrid[i][j] + ".gif");
				prepareImage(bImage[i][j], this);
				
			}
		}
		
		
		
		
		
		/*
		Placing the blocks on the Grid 
		------------------------------------------------------------
		  
		  Cell on first column, first row ([0][0]) is on x=16 y=1 	
		  10px increments to move a block horizontally
		  11px increments to move a block vertically  
		  
		  		
		Where the first few blocks should be drawn on the Grid 
		--------------------------------------------------------------------
		
		  g.drawImage(bImage[0][0], 16, 1, null);
		  g.drawImage(bImage[1][0], 86, 12, null);
		  g.drawImage(bImage[2][0], 16, 23, null);
				  
		  
		 */		

		//For loop that places all of the blocks on the Grid
		for(int i=0; i<rows; i++) // Rows
		{
			for(int j=0; j<columns; j++) // Columns
			{
				g.drawImage(bImage[i][j], blockX, blockY, null);	
				blockX+=10;
			
				if (j==49) {
					blockY += 11;
					blockX = 11; 
				}

			}
			
			 
		}
		blockY = 6;
		blockX = 11; 
		
		
		
		
		
		//Transparent image for pause screen
		transImage[2] = Toolkit.getDefaultToolkit().getImage("src/press.png");
		prepareImage(transImage[2], this);
		if(gameStart == false){
			g.drawImage(transImage[2], 0, 0, null);			
		}
		
		
		//Transparent image for pause screen
		transImage[0] = Toolkit.getDefaultToolkit().getImage("src/blackt1.png");
		prepareImage(transImage[0], this);
	
		if(gamePaused == true){
			//Bounds for "Resume" 
			//g.fillRect(297, 97, 148, 36);
			
			//Bounds for "Save Game" 
			//g.fillRect(273, 144, 200, 36);
			
			//Bounds for "Title Screen" 
			//g.fillRect(272, 193, 200, 36);
			
			//Bounds for "Quit" 
			//g.fillRect(324, 242, 80, 34);
			
			g.drawImage(transImage[0], 0, 0, null);			
		}
		
		
		//Transparent image for the GameOver screen
		transImage[1] = Toolkit.getDefaultToolkit().getImage("src/blackt2.png");
		prepareImage(transImage[1], this);
		
		if(gameEnded == true){
			//Bounds for "Retry" 
			//g.fillRect(317, 109, 95, 45);
			
			//Bounds for "Title Screens" 
			//g.fillRect(263, 157, 210, 40);
			
			//Bounds for "Quit" 
			//g.fillRect(320, 205, 85, 45);
		
			g.drawImage(transImage[1], 0, 0, null);
					
		}
			

			

			

			
		blockMovement();
		 
		//Timer counter 
		titleTimer++;
		//System.out.print(titleTimer + " "); 
		//System.out.print(Blocko.igrid[23][5]); 
	}
	
	private boolean moveRight(int x, int y){
		boolean checker = true;

		try {			
			//checks the collision for right
			if(Blocko.igrid[x][y+1]!=0 ) checker=false;
		} catch (IndexOutOfBoundsException e) {
			checker=false;
		}

		return checker;
	}
	
}
