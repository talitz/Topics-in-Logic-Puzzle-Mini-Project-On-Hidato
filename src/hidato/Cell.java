package hidato;


//represents a cell in an Hidato riddle.
public class Cell {
	private Integer value;
	private Integer index;

	public Cell(int index, int value) {
		super();
		this.value = value;
		this.index = index;
	}

	public Cell(int index) {
		super();
		this.value = null;
		this.index = index;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public int getIndex() {
		return index.intValue();
	}

	public void setIndex(int index) {
		this.index = new Integer(index);
	}
}
