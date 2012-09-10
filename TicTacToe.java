
import java.util.*;

public class TicTacToe extends GameMethods {

	Board board;
    String player;

    //default constructor calls board constructor 
	TicTacToe(){
	    board = new Board();
	    player = "x";
	}
	
	//constructor sets player values in board 
	TicTacToe(String pl, Board b){
	    if (pl == "o") {
		player = "o";
	    }
	    else {
		player = "x";
	    }
	    board = b;
	}

	
    public Boolean isMaxTurn() {
	    return (player == "x");
        }

	public ArrayList<GameMethods> list(){
	    ArrayList<GameMethods> succs = new ArrayList<GameMethods>();
	    String newplayer;
	    if(player == "x") {
		newplayer = "o";
	    }
	    else {
		newplayer = "x";
	    }
	    for (int i = 1; i < 10; i++){
		Board b = board.move(player,i);
		if (b != null){
		    succs.add(new TicTacToe(newplayer,b));
		}
	    }
	    return succs;
	}

	public Boolean cutOff(){
	    return terminal();
	}

	public int evaluation(){
	    return board.evaluation();
	}

	public Boolean terminal(){
	    return (board.checkWin() != "");
	}

	public String getWinner(){
	    return board.checkWin();
	}

	protected void setBoard(Board b){
	    board = b;
	}

	public Board getBoard() {
	    return board;
	}

	public void makeMove(String pl, int n){
	    Board b = board.move(pl,n);
	    if(b != null){
		setBoard(b);
	    }
	    if(pl == "x") {
		player = "o";
	    }
	    else {
		player = "x";
	    }
	}

	public String display(){
		return board.display();
	}

        public Boolean isValidMove(int n) {
	    return board.isEmpty(n);
	}

        public int compMove() {
	    int v = GameMethods.range[1];
	    TicTacToe succ = null;
	    int square = -1;
	    for (GameMethods next : this.list()){
		int val = next.minimax();
		if (val <= v) {
		    v = val;
		    succ = (TicTacToe)next;
		}
	    }
	    if (succ != null) {
		for (int i = 0; i <= 8; i++) {
		     if (board.getSlot(i) != succ.getBoard().getSlot(i)) {
			square = i;
			}
		}
	      this.setBoard(succ.getBoard());
	      if (player == "x") {
		player = "o";
		}
	      else {
		player = "x";
		}
	    }
	    return square;
	}

        public String getSquare(int n) {
	    return board.getSlot(n);
	}

}