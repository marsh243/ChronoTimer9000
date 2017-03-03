
public class Athlete {
	private String name;
	String time;
	public Athlete(String name, String time){
		this.name = name;
		this.time = time;
	}
	
	public Athlete(String name){
		this.name = name;
		this.time = "";
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String toString(){
		return (name+"\n"+time);
	}
}
