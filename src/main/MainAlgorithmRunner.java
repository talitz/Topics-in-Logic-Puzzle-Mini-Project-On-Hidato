package main;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class MainAlgorithmRunner {

	public static void main(String[] args) {
		CellGenerator cellGenerator = new CellGenerator();
		
		Cell[][] board = {{cellGenerator.Generate(1),   cellGenerator.Generate(),    cellGenerator.Generate(),   cellGenerator.Generate(23), cellGenerator.Generate(),  cellGenerator.Generate()},
						  {cellGenerator.Generate(11),  cellGenerator.Generate(),    cellGenerator.Generate(3),  cellGenerator.Generate(),   cellGenerator.Generate(),  cellGenerator.Generate(18)},
						  {cellGenerator.Generate(),    cellGenerator.Generate(13),  cellGenerator.Generate(),   cellGenerator.Generate(),   cellGenerator.Generate(),  cellGenerator.Generate()},
						  {cellGenerator.Generate(),    cellGenerator.Generate(),    cellGenerator.Generate(),   cellGenerator.Generate(),   cellGenerator.Generate(26),cellGenerator.Generate()},
						  {cellGenerator.Generate(8),   cellGenerator.Generate(),    cellGenerator.Generate(),   cellGenerator.Generate(15), cellGenerator.Generate(),  cellGenerator.Generate(30)},
						  {cellGenerator.Generate(),    cellGenerator.Generate(),    cellGenerator.Generate(36), cellGenerator.Generate(),   cellGenerator.Generate(),  cellGenerator.Generate(31)}};

		Hidato hidato = new Hidato(1,board.length*board.length,board);

		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		System.out.println(reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm());
	}
}
