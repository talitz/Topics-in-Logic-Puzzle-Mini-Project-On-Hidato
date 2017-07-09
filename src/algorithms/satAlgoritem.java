package algorithms;

import java.util.ArrayList;

import CNF.CNF;
import CNF.Clause;
import CNF.Literal;

public class satAlgoritem {
	
	   public int[][] graph;
	   public ArrayList<Literal> myLiteral;
       public CNF CNFclause;
       public int size;
       	
	   public  satAlgoritem(int[][] graph,int size,int [] value)
	   {
		   myLiteral=new ArrayList<Literal>();
		   this.size=size;
		   this.graph=graph;
		   addLitral();
		   CNFclause=new CNF(myLiteral);   
		     createC1();
		     createC2();
		     createC3();
		     createC4();
		     createC5(value);
		  }
	   
	

	public void addLitral()
	   {
		for(int i=1;i<=size;i++)
		   {
		   for(int j=1;j<=size;j++)
		   {
		   myLiteral.add(new Literal(i,j,false,false));
		   myLiteral.add(new Literal(i,j,true,false));
		   }
		  }
	   }
	   
	   public void createC1()
	   {
		   Clause c;
		   for(int i=0;i<size;i++)
		   {
			   c=new Clause();
			   for(int j=0;j<size;j++)
				   	c.addLiteral(myLiteral.get(i*size*2+j*2));
			   		CNFclause.addClause(c);
		   }
		   
	   }
	   public void createC2()
	   {
		  Clause c;
		  for(int i=0;i<size;i++)
			  for(int j=0;j<size-1;j++)
				  for(int n=j+1;n<size;n++)
				  {
					  c=new Clause();
					  c.addLiteral(myLiteral.get(i*size*2+j*2+1));
					  c.addLiteral(myLiteral.get(i*size*2+n*2+1));
					  CNFclause.addClause(c);
				  }
			  
	   }
	   
	   public void createC3()
	   {
		   Clause c;
		   for(int j=0;j<size-1;j++)
				  for(int n=j+1;n<size;n++)
					  for(int i=0;i<size;i++)
					  {
						  c=new Clause();
						  c.addLiteral(myLiteral.get(j*size*2+i*2+1));
						  c.addLiteral(myLiteral.get(n*size*2+i*2+1));
						  CNFclause.addClause(c);
						  
						  
					  }
	   }
	   
	   public void createC4()
	   {	   
		   Clause c;
		   for(int i=0;i<graph.length;i++)
			   for(int n=0;n<size-1;n++)
			   {
				   c=new Clause();
				   c.addLiteral(myLiteral.get(i*size*2+n*2+1));
				   for(int j=0;j<graph[i].length;j++)
				   if(graph[i][j]==1)
				   {
					  c.addLiteral(myLiteral.get(j*size*2+(n+1)*2)); 
					   
				   }
				   CNFclause.addClause(c);
			   }
	   }
	   
	   public void createC5(int value[])
	   {
		   Clause c=new Clause();
		   c.addLiteral(myLiteral.get(size*2*(value[0]-1)));	  
		   CNFclause.addClause(c);
		   c=new Clause();
		   c.addLiteral(myLiteral.get(size*2*(value[size-1]-1)+2*(size-1)));
		   CNFclause.addClause(c);
					
				  
	   }	   
		   
 }

		   























