public class Board{
	private String board[];
	private int width;
	private char moves[] = {'q','w','a','s','d'};
	private String noCollide[] = {"1","4"};
	
	public Board() {
		board = new String[]{
			"2","2","2","2","2","0",
			"2","1","1","1","2","0",
			"2","1","3","4","2","0",
			"2","1","1","1","2","0",
			"2","2","2","2","2","0",
		};
        width = 0;
        while(board[width] != "0"){
            width++;
        }
        width++;
		System.out.println(width);
	}

	public int getWidth() {return width;}

	public boolean checkCollision(Player p, char move){
		switch(move){
			case 'w':
				p.setPos(new int[]{p.getPos()[0],p.getPos()[1]-1});
				break;
			case 'a':
				p.setPos(new int[]{p.getPos()[0]-1,p.getPos()[1]});
				break;
			case 's':
				p.setPos(new int[]{p.getPos()[0],p.getPos()[1]+1});
				break;
			case 'd':
				p.setPos(new int[]{p.getPos()[0]+1,p.getPos()[1]});
				break;
		}
		System.out.println("P: " + p);
		int tp = p.calculateTruePos_Temp(width);
		System.out.println("TP: " + tp);
		String tile = board[tp];
		System.out.println("T " + tile);
		for(int i = 0; i < noCollide.length; i++){
			if(tile == noCollide[i]){
				System.out.println("T");
				return true;
			}
		}
		System.out.println("F");
		return false;
	}

	public String doMove(char move, Player p, String oldTile){
		//continue
		int i = 0;
		for (char eligibleMove : moves){
			if(eligibleMove == move){
				break;
			}
			i++;
		}
		System.out.println(i);
		System.out.println("MOVE: " + move);
		System.out.println(moves.length);
		if(i != moves.length){
			int[] oldPos = p.getPos();
			switch(move){
				//May not need this case.
				case 'w':
					if(!checkCollision(p, move)){
						p.setPos(oldPos);
					}
					break;
				case 'a':
					if(!checkCollision(p, move)){
						p.setPos(oldPos);
					}
					break;
				case 's':
					if(!checkCollision(p, move)){
						p.setPos(oldPos);
					}
					break;
				case 'd':
					if(!checkCollision(p, move)){
						p.setPos(oldPos);
					}
					break;
				case 'q':
					return "-1";
			}
		}
		return updateBoard(p, oldTile);
	}

	public String updateBoard(Player p, String oldTile){
		board[p.getTruePos()] = oldTile;
		p.calculateTruePos(width);
		String newOldTile = board[p.getTruePos()];
		board[p.getTruePos()] = "3";
		return newOldTile;
	}

	public String toString() {
		String str = "";
		for(int i = 0; i < board.length; i++) {
			switch(board[i]) {
			case "0":
				str += "\n";
				break;
			case "1":
				str += ". ";
				break;
			case "2":
				str += "# ";
				break;
			case "3":
				str += "@ ";
				break;
			case "4":
				str += ": ";
				break;
			}
		}
		return str;
	}
}