package CNF;

import java.util.ArrayList;

/**
 * The Class represents a clause in a CNF. It had an id and a list of literals.
 */

public class Clause {
	private ArrayList<Literal> MyLiterals;
	public int id;


	public Clause()
	{
		MyLiterals=new ArrayList<Literal>();
	}


	public Clause(ArrayList<Literal> MyLiterals) {
		this.MyLiterals = MyLiterals;
	}


	public void addLiteral(Literal l)
	{
		this.MyLiterals.add(l);

	}


	public int getNumOfLitrals()
	{
		return MyLiterals.size(); 
	}
	
	public ArrayList<Literal> getMyLiterals() {
		return this.MyLiterals;
	}

	public Boolean isSatisfied() { 
		for (int i = 0; i < this.MyLiterals.size(); i++) {
			if(this.MyLiterals.get(i).isLiteralSatisfied()) 
				return true;

		}
		return false;
	}
	
	public String toString() {
		String str = "(";

		for (int i = 0; i < this.MyLiterals.size(); i++) {
			if(i < this.MyLiterals.size()-1) {
				str += this.MyLiterals.get(i).toString()+ " | ";
			} else {
				str += this.MyLiterals.get(i).toString();
			}
		} 
		str += ")";
		return str;
	}
}