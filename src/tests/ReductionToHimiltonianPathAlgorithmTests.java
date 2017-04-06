package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hidato.Cell;
import hidato.CellGenerator;
import hidato.Hidato;
import hidato.HidatoGenerator;
import reductions.ReductionToHamiltonianPathAlgorithm;

public class ReductionToHimiltonianPathAlgorithmTests {
    
	private CellGenerator cellGenerator = new CellGenerator();
	
	@Test
	public void testCase1() {
		
		Cell[][] board = {   {cellGenerator.Generate(1),    cellGenerator.Generate(2),  cellGenerator.Generate(3)},
							 {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate(4)},
							 {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate(9)}  };

		Hidato hidato =  new Hidato(1,9,board);
		
		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		String path = reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm();
		assertEquals(path,"0;1;2;5;7;3;6;4;8");
	}
	
	@Test
	public void testCase2() {
		
		Cell[][] board = {   {cellGenerator.Generate(1),    cellGenerator.Generate(),  cellGenerator.Generate()},
							 {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate()},
							 {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate(9)}  };

		Hidato hidato =  new Hidato(1,9,board);
		
		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		String path = reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm();
		assertEquals(path,"0;1;2;4;3;6;7;5;8");
	}
	
	@Test
	public void testCase3() {
		
		Cell[][] board = {   {cellGenerator.Generate(1),    cellGenerator.Generate(2),  cellGenerator.Generate(3)},
							 {cellGenerator.Generate(6),    cellGenerator.Generate(5),  cellGenerator.Generate(4)},
							 {cellGenerator.Generate(),    cellGenerator.Generate(),  cellGenerator.Generate(9)}  };

		Hidato hidato =  new Hidato(1,9,board);
		
		ReductionToHamiltonianPathAlgorithm reductionToHamiltonianPath = new ReductionToHamiltonianPathAlgorithm(hidato);

		String path = reductionToHamiltonianPath.setAndRunReductionToHamiltonPathAlgorithm();
		assertEquals(path,"0;1;2;5;4;3;6;7;8");
	}

}
