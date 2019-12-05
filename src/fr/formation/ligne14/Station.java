package fr.formation.ligne14;


public class Station {
	
	private String name;
	private Line line;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "Station [name=" + name + ", line=" + line + "]";
	}
	

}
