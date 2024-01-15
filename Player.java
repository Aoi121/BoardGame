public class Player{
	private int pos[] = new int[2];
	private int truePos;
    private int selectedPosition;
    private int selected;
	
    /* selectedPosition
     * 0 - UP 
     * 1 - LEFT
     * 2 - DOWN
     * 3 - RIGHT
     */
	public Player(int pos[], int boardWidth) {
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
		truePos = boardWidth * (pos[1] - 1) + pos[0] - 1;
        selectedPosition = 3;
        selected = truePos+1;
	}
    
    public int calculateTruePos(int boardWidth){
        truePos = boardWidth * (pos[1] - 1) + pos[0] - 1;
        switch(selectedPosition){
            case 0:
                selected = truePos-boardWidth;
                break;
            case 1:
                selected = truePos-1;
                break;
            case 2:
                selected = truePos+boardWidth;
                break;
            case 3:
                selected = truePos+1;
                break;
        }
        return truePos;
    }
	
    public int calculateTruePos_Temp(int boardWidth){
        return boardWidth * (pos[1] - 1) + pos[0] - 1;
    }

    public void setPos(int pos[]){
        this.pos = pos;
    }

    public void setSelected(int selected){
        this.selected = selected;
    }
    
    public void setSelectedPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    public void updateSelected(){
       ; 
    }

    public int[] getPos(){return pos;}

    public int getTruePos(){return truePos;}
	
    public int getSelected(){return selected;}


    public String toString(){
        return String.format("%d,%d", pos[0], pos[1]);
    }
}