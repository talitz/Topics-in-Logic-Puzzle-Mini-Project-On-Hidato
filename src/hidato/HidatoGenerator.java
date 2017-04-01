package hidato;
public class HidatoGenerator {
	
	//for this time, we only retrive a static one until it works...
	//later on we can retrieve EASY, HARD ones and use this as a factory...
	
	public Hidato Generate(int start, int end) {
		CellGenerator cellGenerator = new CellGenerator();
		
		Cell[][] board = {   {cellGenerator.Generate(1),    cellGenerator.Generate(),  cellGenerator.Generate()},
						     {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate()},
						     {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate(9)}  };
		
		return new Hidato(1,9,board);
	}
}

