package main;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class MainAlgorithmRunner {

	public static void main(String[] args) {
		CellGenerator cellGenerator = new CellGenerator();
		
		Cell[][] board = {{cellGenerator.Generate(25),    cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate(10)},
						  {cellGenerator.Generate(),      cellGenerator.Generate(1),    cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate()},
						  {cellGenerator.Generate(),      cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(7),   cellGenerator.Generate()},
						  {cellGenerator.Generate(19),    cellGenerator.Generate(),     cellGenerator.Generate(),   cellGenerator.Generate(),    cellGenerator.Generate()}};

		

		
		Hidato hidato = new Hidato(1,board.length*board.length,board);

		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		System.out.println(reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm());
	}
}
