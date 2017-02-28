
public class Athlete {
	private String name, time
	public Athlete(String name, time){
		this.name = name;
		this.time = time;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public void toString(){
		System.out.println(name+"\n"+time);
	}
}
