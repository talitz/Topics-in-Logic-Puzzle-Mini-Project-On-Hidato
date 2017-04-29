package main;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class MainAlgorithmRunner {

	public static void solveHidatoPuzzleWithReductionToHemiltonianPathWithRestrictions() throws Exception {
		CellGenerator cellGenerator = new CellGenerator();
		
		Cell[][] board = {{cellGenerator.Generate(),    cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate(),cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),    cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate(),cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate(),cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),   cellGenerator.Generate(),cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),   cellGenerator.Generate(4),cellGenerator.Generate()},
						  {cellGenerator.Generate(),    cellGenerator.Generate(36),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate(5),cellGenerator.Generate(1)}};

		Hidato hidato = new Hidato(1,board.length*board.length,board);
		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);
		System.out.println(reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm());
	}
	
	public static void main(String[] args) throws Exception {
		solveHidatoPuzzleWithReductionToHemiltonianPathWithRestrictions();
	}

}
