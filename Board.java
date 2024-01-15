import java.util.HashMap;
public class Board{
	private String board[];
	private int width;
	private char moves[] = {'q','w','a','s','d','i','j','k','l','e'};
	private String noCollide[] = {"1","4"};
	private String interactables[] = {"4","5"};
	private HashMap<String, String> interactTileset = new HashMap<String, String>();

	public Board() {
		board = new String[]{
			"2","2","2","2","2","2","2","0",
			"2","1","1","1","2","1","2","0",
			"2","1","3","1","5","1","2","0",
			"2","1","1","1","2","1","2","0",
			"2","2","2","2","2","2","2","0",
		};
        width = 0;
        while(board[width] != "0"){
            width++;
        }
        width++;

		interactTileset.put("4","5");
		interactTileset.put("5","4");
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
		int tp = p.calculateTruePos_Temp(width);
		String tile = board[tp];
		for(int i = 0; i < noCollide.length; i++){
			if(tile == noCollide[i]){
				return true;
			}
		}
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
				case 'i':
					p.setSelectedPosition(0);
					break;
				case 'j':
					p.setSelectedPosition(1);
					break;
				case 'k':
					p.setSelectedPosition(2);
					break;
				case 'l':
					p.setSelectedPosition(3);
					break;
				case 'e':
					//check if board[selected] in interactables
					//use hashmap to swap tiles
					String tempTile = board[p.getSelected()];
					for(int j = 0; j < interactables.length; j++){
						if(tempTile.equals(interactables[j])){
							board[p.getSelected()] = interactTileset.get(board[p.getSelected()]);
						}
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

	public String drawBoard(Player p) {
		String str = "";
		int selected = p.getSelected();
		for(int i = 0; i < board.length; i++) {
			if(i == selected){
				switch(board[i]) {
				case "0":
					str += "\n";
					break;
				case "1":
					str += "\u001b[36m.\u001b[0m ";
					break;
				case "2":
					str += "\u001b[36m#\u001b[0m ";
					break;
				case "3":
					str += "\u001b[36m@\u001b[0m ";
					break;
				case "4":
					str += "\u001b[36m:\u001b[0m ";
					break;
				case "5":
					str += "\u001b[36m|\u001b[0m ";
					break;
				}
			}
			else{
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
				case "5":
					str += "| ";
					break;
				}
			}
		}
		return str;
	}
}