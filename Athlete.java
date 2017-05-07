

public class Athlete {
	private String number;
	String time;
	public Athlete(String name, String time){
		this.number = name;
		this.time = time;
	}
	
	public Athlete(String name){
		this.number = name;
		this.time = "";
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public String getTime(){
		if (this.time == "")
			return "DNF";
		return this.time;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String toString(){
		return (number+"\n"+time);
	}
}
