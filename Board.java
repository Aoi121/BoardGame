package board;

public class Board{
	private String board[];
	private int width;
	private String moves[] = {"w", "a", "s", "d"};
	private String noCollide[] = {};
	
	public Board() {
		board = new String[]{
				"1","1","1","1","2",
				"1","3","0","1","2",
				"1","0","0","1","2",
				"1","1","1","1","2"
		};
        width = 0;
        while(board[width] != "2"){
            width++;
        }
        width++;
	}

	public int getWidth() {return width;}

	public String toString() {
		String str = "";
		for(int i = 0; i < board.length; i++) {
			switch(board[i]) {
			case "0":
				str += "  ";
				break;
			case "1":
				str += "# ";
				break;
			case "2":
				str += "\n";
				break;
			case "3":
				str += "@";
				break;
			}
		}
		return str;
	}
}