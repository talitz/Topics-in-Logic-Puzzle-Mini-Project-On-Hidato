package main;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class MainAlgorithmRunner {

	public static void solveHidatoPuzzleWithReductionToHemiltonianPathWithRestrictions() throws Exception {
		CellGenerator cellGenerator = new CellGenerator();
		
		Cell[][] board = {{cellGenerator.Generate(25),     cellGenerator.Generate(),      cellGenerator.Generate(),       cellGenerator.Generate(),      cellGenerator.Generate()},
						  {cellGenerator.Generate(8),      cellGenerator.Generate(),      cellGenerator.Generate(),      cellGenerator.Generate(),      cellGenerator.Generate()},
						  {cellGenerator.Generate(7),      cellGenerator.Generate(3),     cellGenerator.Generate(),      cellGenerator.Generate(),       cellGenerator.Generate()},
						  {cellGenerator.Generate(6),      cellGenerator.Generate(),     cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate()},
						  {cellGenerator.Generate(5),      cellGenerator.Generate(),     cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(1)},
						 };

		Hidato hidato = new Hidato(1,board.length*board.length,board);
		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);
		System.out.println(reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm());
	}
	
	public static void main(String[] args) throws Exception {
		solveHidatoPuzzleWithReductionToHemiltonianPathWithRestrictions();
	}

}
