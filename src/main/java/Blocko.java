

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.event.ActionEvent; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/*              Notes
	Key Codes
		Up arrow key  	38
		Down arrow key  40
		Left arrow key  37
		Right arrow key 39

 */
//The class that contains most of the universal variables and runs the game
public class Blocko implements ActionListener{
	//Values for screen size and grid layout
	final static int rows = 25;
	final static int columns = 50;
	
	//Frame size: width=730   ,  height = 310
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
	final int frame_width = 730; 
	final int frame_height = 310;
	
	//Integer Array carrying all high scores
	static int[] highScores= new int[5];
	
	//Initializing the JFrame
	public static JFrame frame = new JFrame("Blocko");
	
	
	public static boolean activeBlock=false;
	

	//Make the object that will carry the score of the player
	public static int playerScore; 
	

	/*Making a double array of integer values that will represent the grid
			This double array of integers is used extensively throughout the game
				Some uses: 
					Checking for end game
					Placing the blocks
					Checking for a 3x3 square or line
	*/
	static int     igrid[][] = new int[rows][columns];  


	//Making the all panel objects that will later be referenced throughout the game
	public static GamePanel gp = new GamePanel(); 
	public static TitlePanel tp = new TitlePanel(); 
	public static PDevPanel pp = new PDevPanel(); 
	public static ScoresPanel sp = new ScoresPanel(); 
	
	//Blank Constructor
	public Blocko() {}

	
	public Blocko(int x) {

		//Assign an initial value to the player's score
		playerScore = 0; 
		
		//Adding the Developer and Publisher panel onto the JFrame and Setting up its parameters
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.add(pp); 
		
		//Centering the game frame    
		frame.setSize(frame_width,frame_height);
		frame.setLocation(dim.width/2 - frame.getSize().width/2, 
						  dim.height/2 - frame.getSize().height/2);
						  
		//Some other frame parameters(Setting the frame to unresizable/visible)
		frame.setVisible(true);
		frame.setResizable(false);	


	}

	
	
	//Main that Runs Blocko
	public static void main(String args[]) {
		for(int i=0;i<highScores.length; i++){
			highScores[i]=0;
		}
		
		getHighScores();
		
		new Blocko(1); //Runs Blocko 
	}
	
	//Reading and writing the text file that carries the High Scores
	public static void getHighScores(){
		try {
			BufferedReader in =new BufferedReader(
							   new FileReader("highScores.txt"));
			
			String line= in.readLine();
			int counter=0;
			while(line!=null){
				highScores[counter]=Integer.parseInt(line);
				line=in.readLine();
				counter++;
			}
			
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File has not been created yet");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	 
	 
	 
	

	//Action Performed Function. Calls all necessary functions  	
	public void actionPerformed(ActionEvent e){

		
	}

	

	/*
	//Print out the Game Grid
	public void printGrid(){


		for(int t=0; t<25; t++){
			for(int k=t; k<(t+1); k++) // Rows
			{
				for(int l=0; l<columns; l++) // Columns
				{
					System.out.print(igrid[k][l]);
				}
			}	
			System.out.println();
		}


		System.out.println(); 
		System.out.println(); 

	}
	*/

	

}
