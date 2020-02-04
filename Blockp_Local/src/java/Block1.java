

public class Block1 implements Block{

	private int x,y;
	private boolean inGame; 
	
	public Block1(){

		inGame = true; 
		
		x=12;
		y=0;
		//HIORZ
                
		Blocko.igrid[x][y]=1;
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x+1][y]=1;
		Blocko.igrid[x+1][y+1]=1;
	}

	public void moveRight(){
		//check that nothing above move;
		//if where you're going is empty don't go pass zero

		if(movelimitRight()==false){
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x+1][y]=0;
			Blocko.igrid[x+1][y+1]=0;	


			y++; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x+1][y]=1;
			Blocko.igrid[x+1][y+1]=1;
		}
	}

	public void moveUp(){
		Blocko.igrid[x][y]=0;
		Blocko.igrid[x][y+1]=0;
		Blocko.igrid[x+1][y]=0;
		Blocko.igrid[x+1][y+1]=0;
		
		if(movelimitUp()==false){
		x--; 
		Blocko.igrid[x][y]=1;
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x+1][y]=1;
		Blocko.igrid[x+1][y+1]=1;
		}

	}
	public void moveDown(){
		Blocko.igrid[x][y]=0;
		Blocko.igrid[x][y+1]=0;
		Blocko.igrid[x+1][y]=0;
		Blocko.igrid[x+1][y+1]=0;
		
		if(movelimitDown()==false){
		x++; 
		Blocko.igrid[x][y]=1;
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x+1][y]=1;
		Blocko.igrid[x+1][y+1]=1;
		}
	}



	public void rotate(){

	}


	public boolean movelimitRight(){
		boolean checker = false;

		try {
			//Checks collision for the right
			if(Blocko.igrid[x][y+2]!=0 ) checker=true;
			if(Blocko.igrid[x+1][y+2]!=0) checker=true;		
			
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}
		
		return checker;
	}
	
	public boolean movelimitUp(){
		boolean checker = false;

		try {			
			//checks the collision for up
			if(Blocko.igrid[x-1][y]!=0 ) checker=true;
			if(Blocko.igrid[x-1][y+1]!=0 ) checker=true;
			
			
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}
		
		return checker;
	}


	public boolean movelimitDown(){
		boolean checker = false;

		try {			
			//checks the collision for up
			if(Blocko.igrid[x+2][y]!=0 ) checker=true;
			if(Blocko.igrid[x+2][y+1]!=0 ) checker=true;
			
			
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}
		
		return checker;
	}

	public boolean canRotate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void clearBlock(){
		Blocko.igrid[x][y]=0;
		Blocko.igrid[x][y+1]=0;
		Blocko.igrid[x+1][y]=0;
		Blocko.igrid[x+1][y+1]=0;
		
		this.inGame = false;
	}
	
	public boolean inGame() {
		return this.inGame;
	}

}
