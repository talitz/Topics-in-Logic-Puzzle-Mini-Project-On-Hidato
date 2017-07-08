package algorithms;

import java.util.Arrays;

import com.trevorstevens.javasat.Solver;

import CNF.GNP;
public class sample {

	public sample(int n,double pro){
		GNP gnf= new GNP(n,pro);
		System.out.println(Arrays.deepToString(gnf.graph));
		int [] a=gnf.pathArray;
		for(int i=0;i<a.length;i++)
			a[i]++;
		System.out.println(Arrays.toString(a));
		//int value[][]={{1, -1, -1, -1},{5}};
		satAlgoritem sat_algo= new satAlgoritem(gnf.graph, gnf.size,gnf.pathArray);
		System.out.println(sat_algo.CNFclause.toString());
		//Solver.solve(sat_algo.CNFclause);
		//Demo demo= new Demo(sat_algo);
		//System.out.println(Arrays.toString(demo.array_resualt));
		System.out.println(Arrays.toString(Solver.solve(sat_algo.CNFclause)));
		//DPLL.dpll(tr.args);
	}
	
	
}
