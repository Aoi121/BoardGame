import java.util.Scanner;
public class main{
    public static void main(String[] args) {
       Board board = new Board();
       Player player = new Player(new int[]{3,3}, board.getWidth());
       Scanner scan = new Scanner(System.in);
       String playerInput;
       char moves[] = {'a','s','d','w'};

       while(true){
        player.calculateTruePos(board.getWidth());
        System.out.print(board);
        playerInput = scan.nextLine();
        board.doMove(playerInput, player.getTruePos());
       }
    }
}