import java.util.*;

public class Board {

	String[] slot = new String[9];

	Board(){
	    for (int i = 0; i < 9; i++) {
		slot[i] = " ";
	    }
	}

	public String getSlot(int n){
		return slot[n];
	}

	protected void setSlot(int n,String pl){
		slot[n] = pl;
	}

        public Board move(String player,int n){
	    if(n > 0 && n < 10 && slot[n-1] == " ") {
		Board b = new Board();
		for (int i = 0; i < 9; i++){
		    b.setSlot(i,slot[i]);
		}
		b.setSlot(n-1,player);
		return b;
	    } else {
		return null;
	    }
	}
    
	public ArrayList<String[]> getLines(){
		ArrayList<String[]> lines = new ArrayList<String[]>();

		for (int i = 0; i < 3; i++){
			String[] row = new String[3];
			row[0] = slot[i*3];
			row[1] = slot[i*3+1];
			row[2] = slot[i*3+2];
			lines.add(row);
			String[] column = new String[3];
			column[0] = slot[i];
			column[1] = slot[i+3];
			column[2] = slot[i+6];
			lines.add(column);
		}

		String[] diagonal1 = {slot[0],slot[4],slot[8]};
		String[] diagonal2 = {slot[2],slot[4],slot[6]};

		lines.add(diagonal1);
		lines.add(diagonal2);

		return lines;
	}

	public String checkWin(){
		for (String[] line: getLines()){
			if(winningLine(line) != ""){
			    return winningLine(line);
			}
		}
		if (isFull()){
			return "tie";
		}
		return "";
	}

	protected String winningLine(String[] line){
		if (line[0] == line[1] && line[1] == line[2]){
			if (line[0] == "x"){
				return "x";
			} else if (line[0] == "o"){
				return "o";
			} 
		}
		return "";
	}

        public String display(){
	    String board = "-------------\n";
	    for (int i = 0; i < 3; i++){
		for (int j = 0; j < 3; j++) {
		    board += "| " + slot[i*3+j] + " ";
		}
		board += "|\n-------------\n";
	    }
	    return board;
	}

	public int evaluation(){
		if(checkWin() == "tie"){
			return 0;
		}
		else if (checkWin() == "o") {
		    return -1;
		}
		else if (checkWin() == "x") {
		    return 1;
		}
		else { return 0;}
	}

	protected Boolean isFull(){
	    Boolean full = true;
	    for (int i = 0; i < 9; i++){
		if (slot[i] == " "){
		    full = false;
		}
	    }
	    return full;
	}

        public Boolean isEmpty(int n) {
	    return (slot[n-1] == " ");
	}

}