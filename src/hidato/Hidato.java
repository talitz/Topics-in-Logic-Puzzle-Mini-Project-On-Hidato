package hidato;

import java.util.ArrayList;

public class Hidato {
	private int start;
    private int end;
    private Cell[][] board;
    private ArrayList<Integer> existingValues;
    private ArrayList<Integer> nonExistingValues;
	private int runner = -1;
	
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
	
	public Cell getCellByVertexValue(int value) {
		for(int i=0; i<board.length; i++) {
			for(int j=0;j<board.length;j++) {
				Cell current = board[i][j];
				if(current.getValue().equals(value)) return current;
			}
		}
		return null;
	}

	public int tryToMatchValueToCell(int vertexIndex) {
		runner = (runner + 1)%end;
		return nonExistingValues.get(runner);
	}
	

}
