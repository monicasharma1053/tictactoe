import java.io.*;

public class PlayTicTacToe {


	public static void main(String[] args) {
		GameMethods game = new TicTacToe();

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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
	         move = Integer.parseInt(br.readLine());
	      } catch (IOException ioe) {
	         System.out.println("IO error occured while trying to get your move");
	         System.exit(1);
	      }
		if (move < 1 || move > 9) {
			System.out.println("A move has to be between 1 and 9.");
			return getMove();
		}

		return move;
	}

}