public class Board {
   private String board[];
   private int width;
   private String moves[] = {"a","s","d","w"};
   private String noCollide[] = {"0",};

   public Board(){
        board = new String[]{
            "1","1","1","1","1","2",
            "1","0","0","0","1","2",
            "1","0","3","0","1","2",
            "1","0","0","0","1","2",
            "1","1","1","1","1","2",
        };
        width = 0;
        while(board[width] != 2){
            width++;
        }
        width++;
        System.out.println(width);
    }

    public int doMove(String move, int truePos){
        for(int i = 0; i < moves.length; i++){
            if(move.equalsIgnoreCase(moves[i])){
                switch (move) {
                    case "a":
                        String tile = board[i-1];
                        for (int j = 0; j < noCollide.length; j++) {
                            if(tile == noCollide[j]){

                            }
                        }
                        break;
                
                    default:
                        break;
                }
            }
        }
        return 0;
    }

    public int getWidth(){return width;}

    public String toString(){
        String str = "";
        for (int i = 0; i < board.length; i++) {
            switch(board[i]){
                case 0:
                    str += ". ";
                    break;
                case 1:
                    str += "# ";
                    break;
                case 2:
                    str += "\n";
                    break;
                case 3:
                    str += "@ ";
                    break;
            }
        }
        return str;
    }
}
