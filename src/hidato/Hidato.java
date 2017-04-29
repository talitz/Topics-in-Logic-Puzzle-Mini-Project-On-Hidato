package hidato;

import java.util.ArrayList;

public class Hidato {

	private int start;
	private int end;
	private Cell[][] board;
	private ArrayList<Integer> existingValues = new ArrayList<Integer>();
	private ArrayList<Integer> nonExistingValues = new ArrayList<Integer>();

	public Hidato(int start, int end, Cell[][] board) throws Exception{
		super();
		this.start = start;
		this.end = end;
		this.board = board;

		if(start != 1 || end != board.length*board.length) 
			throw new Exception("Hidato Exception :: Start/end values doesn't make sense.");
		
		boolean foundStart = false, foundEnd = false;
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length;j++) {
				Cell current = board[i][j];
				if(current.getValue() != null && (current.getValue() > this.end || current.getValue() < this.start)) {
					throw new Exception("Hidato Exception :: You can't create a Hidato puzzle with values not between startValue <= X <= endValue!");
				}
				if(current.getValue() != null && current.getValue() == this.start) foundStart = true;
				if(current.getValue() != null && current.getValue() == this.end) foundEnd = true;
			}
		}
		
		if(foundStart == false || foundEnd == false) throw new Exception("Hidato Exception :: start or end value doesn't exist in the board.");
	
		//when we create the Hidato riddle, it is useful to have all its current existing values
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length;j++) {
				Cell current = board[i][j];
				if(current.getValue() != null) {
					existingValues.add(current.getValue().intValue());
				}
			}
		}

		//also useful to know what's the values that can be filled in?
		for(int i=start; i<= end; i++) {
			if(!existingValues.contains(i))
				nonExistingValues.add(i);
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public ArrayList<Integer> getExistingValues() {
		return existingValues;
	}

	public ArrayList<Integer> getNonExistingValues() {
		return nonExistingValues;
	}


	public Cell getCellByVertexIndex(int index) {
		for(int i=0; i<board.length; i++) {
			for(int j=0;j<board[i].length;j++) {
				Cell current = board[i][j];
				if(current.getIndex() == index)
					return current;
			}
		}
		return null;
	}
	
	public Cell getCellByVertexValueIfExistsOrNull(int value) {
		for(int i=0; i<board.length; i++) {
			for(int j=0;j<board[i].length;j++) {
				Cell current = board[i][j];
				if(current.getValue() != null && current.getValue() == value)
					return current;
			}
		}
		return null;
	}

	public void addToExistingValue(int valueToTry) {
		existingValues.add(valueToTry);	
		nonExistingValues.remove(nonExistingValues.indexOf(valueToTry));
	}

	public void setCellWithNewValue(int index, Integer value) {
		for(int i=0; i<board.length; i++) {
			for(int j=0;j<board[i].length;j++) {
				Cell current = board[i][j];
				if(current.getIndex() == index) {
					current.setValue(value);
				}
			}
		}
	}

	public String toString() {
		String ans = "\n";
		ans += "Start = " + start + ", End = "+end + "\n";
		ans += "Existing values: {";

		for(int i=0; i< existingValues.size(); i++) {
			if(i < existingValues.size()-1) {
				ans += ""+existingValues.get(i).intValue()+" ,";
			} else {
				ans += ""+existingValues.get(i).intValue();
			}
		}

		ans += "}\n";
		ans += "Non existing values: {";

		for(int i=0; i< nonExistingValues.size(); i++) {
			if(i < nonExistingValues.size()-1) {
				ans += ""+nonExistingValues.get(i).intValue()+" ,";
			} else {
				ans += ""+nonExistingValues.get(i).intValue();
			}
		}

		ans += "}\n\n";

		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				Integer currValue = board[i][j].getValue();
				if(currValue == null) {
					ans += "_"+ "        ";
				} else if(currValue.intValue() > 9){ 
					ans += board[i][j].getValue() + "       ";
				} else {
					ans += board[i][j].getValue() + "        ";
				}
			}
			ans += "\n";
		}
		ans += "\n";
		return ans;
	}

}
