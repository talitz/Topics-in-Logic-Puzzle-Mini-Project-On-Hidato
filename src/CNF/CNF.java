package CNF;

import java.util.ArrayList;

public class CNF { 
		
	    private ArrayList<Clause> MyClauses;
		private ArrayList<Literal> MyLiterals;


		public CNF(ArrayList<Literal> MyLiterals)
		{
			MyClauses= new ArrayList<Clause>();
			this.MyLiterals = MyLiterals;
		}  

		public void addClause(Clause c)
		{
			this.MyClauses.add(c);
		}
		public void setClause(ArrayList<Clause> MyClauses)
		{
			this.MyClauses=MyClauses;
		}

		public ArrayList<Literal> getMyLiterals() {
			return this.MyLiterals;
		} 
		public ArrayList<Clause> getMyClauses() {
			return this.MyClauses;
		} 
		
		public Boolean isSatisfied() {
			for (int i = 0; i < this.MyClauses.size(); i++) {
				if(!this.MyClauses.get(i).isSatisfied()) return false;
			}
			return true;
		}

		
		public String toString() {
			String str = "";
			for (int i = 0; i < this.MyClauses.size(); i++) {

				if(i < this.MyClauses.size()-1) {
					str += this.MyClauses.get(i).toString()+ " ^ ";
				} else {
					str += this.MyClauses.get(i).toString();
				}
			} 
			return str;
		}
}
