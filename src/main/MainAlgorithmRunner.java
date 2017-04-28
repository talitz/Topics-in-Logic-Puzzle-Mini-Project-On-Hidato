package main;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class MainAlgorithmRunner {

	public static void main(String[]args) {
		CellGenerator cellGenerator = new CellGenerator();

		Cell[][] board = {{cellGenerator.Generate(1), cellGenerator.Generate(),  cellGenerator.Generate(), cellGenerator.Generate(), cellGenerator.Generate()},
						  {cellGenerator.Generate(),  cellGenerator.Generate(),  cellGenerator.Generate(10), cellGenerator.Generate(11), cellGenerator.Generate()},
						  {cellGenerator.Generate(),  cellGenerator.Generate(),  cellGenerator.Generate(), cellGenerator.Generate(), cellGenerator.Generate()},
						  {cellGenerator.Generate(),  cellGenerator.Generate(),  cellGenerator.Generate(), cellGenerator.Generate(), cellGenerator.Generate()},
						  {cellGenerator.Generate(),  cellGenerator.Generate(6),  cellGenerator.Generate(), cellGenerator.Generate(), cellGenerator.Generate(25)}};

		Hidato hidato = new Hidato(1,25,board);

		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		System.out.println(reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm());
	}
}
