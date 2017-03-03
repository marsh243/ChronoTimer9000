
public class Athlete {
	private String name;
	String time;
	public Athlete(String name, int time){
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
	
	public void toString(){
		System.out.println(name+"\n"+time);
	}
}
