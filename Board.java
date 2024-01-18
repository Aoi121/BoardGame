import java.util.HashMap;
public class Board{
	private String board[];
	private int width;
	private char moves[] = {'q','w','a','s','d','i','j','k','l','e'};
	private String noCollide[] = {"1","4","10"};
	private String interactables[] = {"4","5","7","8","11"};
	private HashMap<String, String> interactTileset = new HashMap<String, String>();
	private HashMap<String, String> switchTileset = new HashMap<String, String>();
	private String switches[];

	public Board() {
		/*
		 * Key:
		 * 0 - \n
		 * 1 - Open Space - .
		 * 2 - Wall - #
		 * 3 - Player - @
		 * 4 - Open Door - :
		 * 5 - Closed Door - |
		 * 6 - Locked door - \
		 * 7 - On Switch - X
		 * 8 - Off Switch - Θ
		 * 9 - Interactable Wall - ▒
		 * 10 - Interactable Wall Slot - *
		 * 11 - Finish - +
		 */
		//Variables so the alignment of the map doesn't get skewed.
		String A = "10";
		String B = "11";
		board = new String[]{
			"2","2","2","2","2","2","2","2","2","2","2","0",
			"2","1","1","1","1","2","1","1","1","1","2","0",
			"2","1","1", B ,"1","2","1","1","1","1","2","0",
			"2","1","1","1","1","2","2","6","2","1","2","0",
			"2","9","9","9","2","2","1","1","2","7","2","0",
			"2","1","1","1","2","1","1","1","2","2","2","0",
			"2","1","3","1","5","1","1","1","1","7","2","0",
			"2","1","1","1","2","1","1","1","1","1","2","0",
			"2","2","2","2","2","2","2","2","2","2","2","0",
		};
		switches = new String[]{
			"0","0","0","0","0","0","0","0","0","0","0","0",
			"0","0","0","0","0","0","0","0","0","0","0","0",
			"0","0","0","0","0","0","0","0","0","0","0","0",
			"0","0","0","0","0","0","0","1","0","0","0","0",
			"0","2","2","2","0","0","0","0","0","2","0","0",
			"0","0","0","0","0","0","0","0","0","0","0","0",
			"0","0","0","0","1","0","0","0","0","1","0","0",
			"0","0","0","0","0","0","0","0","0","0","0","0",
			"0","0","0","0","0","0","0","0","0","0","0","0",
		};
        width = 0;
        while(board[width] != "0"){
            width++;
        }
        width++;

		interactTileset.put("4","5");
		interactTileset.put("5","4");
		interactTileset.put("7","8");
		interactTileset.put("8","7");

		switchTileset.put("4","6");
		switchTileset.put("5","6");
		switchTileset.put("6","5");
		switchTileset.put("5","6");
		switchTileset.put("9","10");
		switchTileset.put("10","9");
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

	public String switchLogic(Player p){
		int selected = p.getSelected();
		String switchId = switches[selected];
		if(switchId.equals("0")){
			System.out.println("Error: Switch has invalid id of 0");
			return "0";
		}
		for(int i = 0; i < switches.length; i++){
			if(switches[i].equals(switchId) && i != selected){
				board[i] = switchTileset.get(board[i]);
			}
		}
		board[selected] = interactTileset.get(board[selected]);
		return "1";
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
							if(tempTile.equals("7") || tempTile.equals("8")){
								if(switchLogic(p).equals("0")){
									System.exit(0);
								}
							}
							else if(tempTile.equals("11")){
								System.out.println("\nYou won!");
								return "-1";
							}
							else{
								board[p.getSelected()] = interactTileset.get(board[p.getSelected()]);
							}
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
				case "6":
					str += "\u001b[36m\\\u001b[0m ";
					break;
				case "7":
					str += "\u001b[36mX\u001b[0m ";
					break;
				case "8":
					str += "\u001b[36m\u0398\u001b[0m ";
					break;
				case "9":
					str += "\u001b[36m\u2592\u001b[0m ";
					break;
				case "10":
					str += "\u001b[36m*\u001b[0m ";
					break;
				case "11":
					str += "\u001b[36m+\u001b[0m ";
					break;
				default:
					System.out.println("Error in drawBoard: Tile not found.");
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
				case "6":
					//0142
					str += "\\ ";
					break;
				case "7":
					//0394
					str += "X ";
					break;
				case "8":
					//0398
					str += "\u0398 ";
					break;
				case "9":
					//2592
					str += "\u2592 ";
					break;
				case "10":
					str += "* ";
					break;
				case "11":
					str += "+ ";
					break;
				default:
					System.out.println("Error in drawBoard: Tile not found.");
					break;
				}
			}
		}
		return str;
	}
}