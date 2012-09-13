
import java.util.*;

abstract class GameMethods{

	static int[] range = {-10,10};

	abstract ArrayList<GameMethods> list();

	abstract Boolean cutOff();

	abstract int evaluation();

	//determine whether the game is still going 
	abstract Boolean terminal();

	//send a move to the board 
	abstract void makeMove(String p, int mv);

	//displays the board 
	abstract String display();

	//determine the winner
	abstract String getWinner();

    abstract Boolean isMaxTurn();
   
    //determines whether slot is empty 
    abstract Boolean isValidMove(int a); 

	public int minimax(){
		int alpha = range[0];
		int beta = range[1];
		if(this.isMaxTurn()) {
		    return maxValue(this,alpha,beta);
		}
		else {
		    return minValue(this,alpha,beta);
		}
	}

	protected static int maxValue(GameMethods g, int alpha, int beta){
		if(g.cutOff()){
			return g.evaluation();
		}
		int v = GameMethods.range[0];
		int val;
		for (GameMethods next: g.list()){
			val = minValue(next,alpha,beta);
			if (val >= v) {v = val;}
			if (v >= beta) {return v;}
			if (v > alpha) { alpha = v;}
		}
		return v;
	}

	protected static int minValue(GameMethods g, int alpha, int beta){
		if(g.cutOff()){
			return g.evaluation();
		}
		int v = GameMethods.range[1];
		int val;
		for (GameMethods next: g.list()){
			val = maxValue(next,alpha,beta);
			if (val <= v) {v = val;}
			if (v <= alpha) {return v;}
			if (v < beta) { beta = v;}
		}
		return v;
	}

}
