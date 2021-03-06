import java.util.*;

public class PlayTicTacToe {

static GameMethods game = new TicTacToe(); 
	public static void main(String[] args) {
		
		while(!game.terminal()){
			System.out.println(game.display());
			game.makeMove("x",getMove());
			if(game.terminal()){
				break;
			}
			int v = GameMethods.range[1];
			GameMethods succ = null;
			for (GameMethods next : game.list()){
			    int val = next.minimax();
			    if (val <= v) {
				v = val;
				succ = next;
			    }
			}
			game = succ;
		}
		System.out.println(game.display());
		if (game.getWinner() == "tie") { 
			System.out.println("tie!");
		} else {
			System.out.println("The " + game.getWinner() + " player has won!");
		}

	}

	public static int getMove(){
		int move = 0;
		System.out.println("Enter a number between 1 and 9:");
		Scanner input = new Scanner(System.in); 
		//checks that inputed value is an integer
		if(!input.hasNextInt()) 
		{
			System.out.println("A move has to be an integer between 1 and 9"); 
			return getMove(); 
		}
		
		else
		{
			move=input.nextInt(); 
			//checks that inputed value is between 1 and 9
			if(move<1 || move >9)
			{
				System.out.println("A move has to be an integer between 1 and 9"); 
				return getMove(); 
			}
			 //determines whether slot is empty 
			if(!game.isValidMove(move))
			{
				System.out.println("That slot is taken, enter an empty slot"); 
				return getMove(); 
			}
		}
		
		
		return move; 
		
	}

}