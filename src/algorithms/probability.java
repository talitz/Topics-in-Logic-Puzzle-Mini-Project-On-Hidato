package algorithms;
import java.util.Arrays;
import com.trevorstevens.javasat.Solver;
import CNF.GNP;


public class probability {
	public double [] arr;
	public int num_of_comration;
	public double [] result;
	public int [] arr1;
	public int [] arr2;
	
	public probability(int length_path ,double [] arr,int num_of_comration)
	{
		this.arr=arr;
		this.num_of_comration=num_of_comration;
		co_probability(length_path);
		print();
	}

public void co_probability(int length_path)
{
	result=new double[arr.length];
	for(int i=0;i<arr.length;i++)
	{
		int counter=0;	
		for(int j=0;j<num_of_comration;j++)
		{	
			GNP gnf= new GNP(length_path,arr[i]);
			arr1=gnf.pathArray;
			int [] a=gnf.pathArray;
			for(int n=0;n<a.length;n++)
				a[n]++;
			satAlgoritem sat_algo= new satAlgoritem(gnf.graph, gnf.size,gnf.pathArray);
			arr2=Solver.solve(sat_algo.CNFclause);
			if(Arrays.equals(arr1, arr2) )
				counter++;
		}
		result[i]=(double)counter/num_of_comration;
	}
}

public void print()
{
	for(int i=0;i<result.length;i++)
	{
		System.out.println("with probability " +" "+ arr[i]+ " "
				+"we get the initial path "+result[i]*100+"%");
	}
}

}
