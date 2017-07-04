package hidato;

public class Hidato {

	private int start;
	private int end;
	private Cell[][] board;

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
	
	public boolean isCellWithExistingValue(int index) {
		Cell cell = getCellByVertexIndex(index);
		if(cell.getValue() == null) return false;
		else return true;
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
		ans += "Start = " + start + ", End = "+end + "\n\n";
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
