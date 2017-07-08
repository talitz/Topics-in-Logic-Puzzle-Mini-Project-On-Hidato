package CNF;

public class Literal {
	private int Id_ci; //Every literal has it's own id
	private int id_num;
	private Boolean Not; //true for not, false for the value itself 
	private Boolean Value; //Literal's value declared by Not
	private Boolean Assigning; //X = T\F

	public Literal(int Id_ci,int id_num, Boolean Not) {
		this.Id_ci = Id_ci;
		this.id_num= id_num;
		this.Not = Not;

	}

	public Literal(int Id_ci,int id_num, Boolean Not, Boolean Assigning) {
		this.Id_ci = Id_ci;
		this.id_num= id_num;
		this.Not = Not;
		this.Assigning = Assigning;
		if(Not) {
			this.Value = !Assigning;
		} else {
			this.Value = Assigning;
		}

	}

	public Boolean getValue() {
		return Value;
	}


	public void setAssigning(Boolean assigning) {
		Assigning = assigning;
		if(this.Not) {
			this.Value = !Assigning;
		} else {
			this.Value = Assigning;
		}
	}

	public int getId_ci() {
		return Id_ci;
	}


	public int getId_num() {
		return id_num;
	}
	
	public Boolean isLiteralSatisfied() {
		return this.Value;
	}
	
	public String toString() {
		if(!Not){
			return "C"+this.Id_ci+this.id_num;
		} else { 
			return "¬C"+this.Id_ci+this.id_num;
		}
	}

	public Boolean getNot() {
		return Not;
	}

	public void setNot(Boolean not) {
		Not = not;
	}

	public void setId_ci(int id_ci) {
		Id_ci = id_ci;
	}

	public void setId_num(int id_num) {
		this.id_num = id_num;
	}

	public void setValue(Boolean value) {
		Value = value;
	}


}