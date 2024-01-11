package board;

public class Player{
	private int pos[] = new int[2];
	private int truePos;
	
	public Player(int pos[], int boardWidth) {
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
		truePos = boardWidth * (pos[1] - 1) + pos[0] - 1;
	}

    public int calculateTruePos(int boardWidth){
        truePos = boardWidth * (pos[1] - 1) + pos[0] - 1;
        return truePos;
    }
	
    public int getTruePos(){
        return truePos;
    }
	
    public String toString(){
        return String.format("%d,%d", pos[0], pos[1]);
    }
}