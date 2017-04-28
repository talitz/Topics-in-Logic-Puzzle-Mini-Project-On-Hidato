package hidato;

public class CellGenerator {
	private int index = -1;

	public Cell Generate(Integer value) {
		index++;
		return new Cell(index,value);
	}

	public Cell Generate() {
		index++;
		return new Cell(index);
	}
}
