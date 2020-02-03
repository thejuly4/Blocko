

public class Block4 implements Block{
	private int x,y;
	private int rot; 
	private boolean inGame; 
	
	public Block4(){

		x=12;
		y=0;
		inGame = true; 
		rot = 0; 
		
		//HIORZ
		Blocko.igrid[x][y]=1;
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x+1][y+1]=1;
		Blocko.igrid[x+1][y+2]=1;
	}

	public void moveRight(){
		//check that nothing above move;
		//if where you're going is empty don't go pass zero


		if(movelimitRight()==false && rot == 0 ){
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x+1][y+1]=0;
			Blocko.igrid[x+1][y+2]=0;	


			y++; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x+1][y+1]=1;
			Blocko.igrid[x+1][y+2]=1;
		}
		
		if(movelimitRight()==false && rot == 1){
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x-1][y+2]=0;
			Blocko.igrid[x+1][y+1]=0;


			y++; 
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x-1][y+2]=1;
			Blocko.igrid[x+1][y+1]=1;
		}
	}

	public void moveUp(){
		if(rot==0 && movelimitUp()==false){
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x+1][y+1]=0;
			Blocko.igrid[x+1][y+2]=0;	
	
			x--; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x+1][y+1]=1;
			Blocko.igrid[x+1][y+2]=1;
		
		}
		
		if(rot==1 && movelimitUp()==false){
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x-1][y+2]=0;
			Blocko.igrid[x+1][y+1]=0;	
	
			x--; 
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x-1][y+2]=1;
			Blocko.igrid[x+1][y+1]=1;
		
		}
		
		
	}
	public void moveDown(){
		if(rot == 0 && movelimitDown()==false){
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x+1][y+1]=0;
			Blocko.igrid[x+1][y+2]=0;	
	
			x++; 
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x+1][y+1]=1;
			Blocko.igrid[x+1][y+2]=1;
		
		}
		
		if(rot == 1 && movelimitDown()==false){
		Blocko.igrid[x][y+1]=0;
		Blocko.igrid[x][y+2]=0;
		Blocko.igrid[x-1][y+2]=0;
		Blocko.igrid[x+1][y+1]=0;	

		x++; 
		Blocko.igrid[x][y+1]=1;
		Blocko.igrid[x][y+2]=1;
		Blocko.igrid[x-1][y+2]=1;
		Blocko.igrid[x+1][y+1]=1;
		
		
		}
	}



	public void rotate(){
		if(rot == 0 && canRotate() ){
			rot = 1; 
			
			Blocko.igrid[x][y]=0;
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x+1][y+1]=0;
			Blocko.igrid[x+1][y+2]=0;	
	
			
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x][y+2]=1;
			Blocko.igrid[x-1][y+2]=1;
			Blocko.igrid[x+1][y+1]=1;
		}
		else if(rot == 1 && canRotate() ){
			rot = 0; 
			
			Blocko.igrid[x][y+1]=0;
			Blocko.igrid[x][y+2]=0;
			Blocko.igrid[x-1][y+2]=0;
			Blocko.igrid[x+1][y+1]=0;	
	
			
			Blocko.igrid[x][y]=1;
			Blocko.igrid[x][y+1]=1;
			Blocko.igrid[x+1][y+1]=1;
			Blocko.igrid[x+1][y+2]=1;	
			
		}
		
		
	}



	public boolean movelimitRight(){
		boolean checker = false;

		try {
			if(rot == 0){
				if(Blocko.igrid[x][y+2]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+3]!=0) checker=true;
			}
			if(rot == 1){
				if(Blocko.igrid[x-1][y+3]!=0 ) checker=true;
				if(Blocko.igrid[x][y+3]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+2]!=0 ) checker=true;
			}

		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean movelimitUp() {
		boolean checker = false;

		try {
			if(rot == 0){
				if(Blocko.igrid[x-1][y]!=0 ) checker=true;
				if(Blocko.igrid[x-1][y+1]!=0) checker=true;
				if(Blocko.igrid[x][y+2]!=0) checker=true;
			}
			if(rot == 1){
				if(Blocko.igrid[x-1][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x-2][y+2]!=0 ) checker=true;
			}

		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean movelimitDown() {
		boolean checker = false;

		try {
			if(rot == 0){
				if(Blocko.igrid[x+1][y]!=0 ) checker=true;
				if(Blocko.igrid[x+2][y+1]!=0) checker=true;
				if(Blocko.igrid[x+2][y+2]!=0) checker=true;
			}
			if(rot == 1){
				if(Blocko.igrid[x+2][y+1]!=0 ) checker=true;
				if(Blocko.igrid[x+1][y+2]!=0 ) checker=true;
			}

		} catch (IndexOutOfBoundsException e) {
			checker=true;
		}

		return checker;
	}

	public boolean canRotate() {
		boolean checker = true;

		try {
			if(rot == 0){
				if(Blocko.igrid[x][y+2]!=0) checker=false;
				if(Blocko.igrid[x-1][y+2]!=0) checker=false;
			}
			if(rot == 1){
				if(Blocko.igrid[x][y]!=0 ) checker=false;
				if(Blocko.igrid[x+1][y+2]!=0 ) checker=false;
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
