package hidato;

import java.util.ArrayList;

public class Hidato {

	private int start;
	private int end;
	private Cell[][] board;
	private ArrayList<Integer> existingValues = new ArrayList<Integer>();
	private ArrayList<Integer> nonExistingValues = new ArrayList<Integer>();

	public Hidato(int start, int end, Cell[][] board) {
		super();
		this.start = start;
		this.end = end;
		this.board = board;

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
	
	public Cell getCellByVertexValue(int value) {
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
		ans += "start = " + start + ", end = "+end + "\n";
		ans += "existing values: ";

		for(int i=0; i< existingValues.size(); i++) {
			if(i < existingValues.size()-1) {
				ans += ""+existingValues.get(i).intValue()+" ,";
			} else {
				ans += ""+existingValues.get(i).intValue();
			}
		}

		ans += "\n";
		ans += "non existing values: ";

		for(int i=0; i< nonExistingValues.size(); i++) {
			if(i < nonExistingValues.size()-1) {
				ans += ""+nonExistingValues.get(i).intValue()+" ,";
			} else {
				ans += ""+nonExistingValues.get(i).intValue();
			}
		}

		ans += "\n";

		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				ans += (board[i][j].getValue() == null) ? "_"+ "       " :  board[i][j].getValue() + "       ";
			}
			ans += "\n";
		}
		ans += "\n";
		return ans;
	}

}
