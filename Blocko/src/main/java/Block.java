


public interface Block {
	
	public void moveRight();
	public void moveUp();
	public void moveDown();
	
	public void rotate();
	public boolean canRotate();
	
	public boolean movelimitRight();
	public boolean movelimitUp();
	public boolean movelimitDown();
	
	public void clearBlock();
	public boolean inGame(); 
	
}
