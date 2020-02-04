

public class Block2 implements Block{

	private int x,y;
	private int rot;
	private boolean inGame; 

	public Block2(){
		inGame = true; 
		x=12;
		y=0;

		rot = 0;

		//HIORZ
		Blocko.igrid[x][y]=1;
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x][y+2]=1;
		Blocko.igrid[x][y+3]=1;
	}

	public void moveRight(){
		//check that nothing above move;
		//if where you're going is empty don't go pass zero

		if(movelimitRight()==false && rot == 0){
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x][y+3]=0;		


			y++; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x][y+3]=1;
		}


		//	rotate once
		if(movelimitRight()==false && rot == 1 ){
			Blocko.igrid[x][y] = 0;
			Blocko.igrid[x+1][y] = 0;
			Blocko.igrid[x+2][y] = 0;
			Blocko.igrid[x+3][y] = 0;	


			y++; 
			Blocko.igrid[x][y] = 1;
			Blocko.igrid[x+1][y] = 1;
			Blocko.igrid[x+2][y] = 1;
			Blocko.igrid[x+3][y] = 1;
		}
	}

	public void moveUp(){

		if(movelimitUp()==false && rot == 0){

			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x][y+3]=0;

			x--; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x][y+3]=1;
		}

		if(movelimitUp()==false && rot == 1){

			Blocko.igrid[x][y] = 0;
			Blocko.igrid[x+1][y] = 0;
			Blocko.igrid[x+2][y] = 0;
			Blocko.igrid[x+3][y] = 0;

			x--; 
			Blocko.igrid[x][y] = 1;
			Blocko.igrid[x+1][y] = 1;
			Blocko.igrid[x+2][y] = 1;
			Blocko.igrid[x+3][y] = 1;
		}

	}
	public void moveDown(){


		if(movelimitDown()==false && rot == 0){

			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x][y+3]=0;
			x++; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x][y+3]=1;
		}


		if (movelimitDown()==false && rot == 1){

			Blocko.igrid[x][y] = 0;
			Blocko.igrid[x+1][y] = 0;
			Blocko.igrid[x+2][y] = 0;
			Blocko.igrid[x+3][y] = 0;

			x++; 
			Blocko.igrid[x][y] = 1;
			Blocko.igrid[x+1][y] = 1;
			Blocko.igrid[x+2][y] = 1;
			Blocko.igrid[x+3][y] = 1;
		}
	}



	public void rotate(){
		if (rot == 0  && canRotate() ){
			rot = 1;

			Blocko.igrid[x][y] = 0;
			Blocko.igrid[x][y+1] = 0;
			Blocko.igrid[x][y+2] = 0;
			Blocko.igrid[x][y+3] = 0;


			Blocko.igrid[x][y] = 1;
			Blocko.igrid[x+1][y] = 1;
			Blocko.igrid[x+2][y] = 1;
			Blocko.igrid[x+3][y] = 1;
		}
		else if(rot == 1  && canRotate()) {
			
			rot = 0;
			Blocko.igrid[x][y] = 0;
			Blocko.igrid[x+1][y] = 0;
			Blocko.igrid[x+2][y] = 0;
			Blocko.igrid[x+3][y] = 0;
			
			Blocko.igrid[x][y] = 1;
			Blocko.igrid[x][y+1] = 1;
			Blocko.igrid[x][y+2] = 1;
			Blocko.igrid[x][y+3] = 1;

			
		}



	}



	//This checks whether the block has reached the end of the grid or not
	public boolean movelimitRight(){
		boolean checker = false;

		//If there is nothing in the block's way the entire path, it will eventually make an IndexOutOfBoundsException
		//this will catch the exception and stop the block at the end of the grid
		try {
			if(rot==0){
				if(Blocko.igrid[x][y+4]!=0 ) checker=true;
			}
			if(rot==1){
				if(Blocko.igrid[x][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x+2][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x+3][y+1]!=0 ) checker=true;
			}
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean movelimitUp() {
		boolean checker = false;

		//If there is nothing in the block's way the entire path, it will eventually make an IndexOutOfBoundsException
		//this will catch the exception and stop the block at the end of the grid
		try {
			if(rot==0){
				if(Blocko.igrid[x-1][y]!=0 ) checker=true;
				if(Blocko.igrid[x-1][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x-1][y+2]!=0 ) checker=true;
				if(Blocko.igrid[x-1][y+3]!=0 ) checker=true;
			}
			if(rot==1){
				if(Blocko.igrid[x-1][y]!=0 ) checker=true;
			}
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean movelimitDown() {
		boolean checker = false;

		//If there is nothing in the block's way the entire path, it will eventually make an IndexOutOfBoundsException
		//this will catch the exception and stop the block at the end of the grid
		try {
			if(rot==0){
				if(Blocko.igrid[x+1][y]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+2]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+3]!=0 ) checker=true;
			}
			if(rot==1){
				if(Blocko.igrid[x+4][y]!=0 ) checker=true;
			}
		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean canRotate() {
		boolean checker = true;

		//If there is nothing in the block's way the entire path, it will eventually make an IndexOutOfBoundsException
		//this will catch the exception and stop the block at the end of the grid
		try {
			if(rot==0){
				if(Blocko.igrid[x+1][y]!=0 ) checker=false;
				if(Blocko.igrid[x+2][y+1]!=0 ) checker=false;
				if(Blocko.igrid[x+3][y+2]!=0 ) checker=false;
			}
			if(rot==1){
				if(Blocko.igrid[x][y+1]!=0 ) checker=false;
				if(Blocko.igrid[x][y+2]!=0 ) checker=false;
				if(Blocko.igrid[x][y+3]!=0 ) checker=false;
			}
		} catch (IndexOutOfBoundsException e) {
			checker=false;
		}

		return checker;
	}

	public void clearBlock(){}

	public boolean inGame() {
		return this.inGame;
	}

}

