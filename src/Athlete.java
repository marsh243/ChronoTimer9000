

public class Athlete implements Comparable<Object>{
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
//		if (this.time == "")
//			return "DNF";
		return this.time;
	}
	
	public String parGRPGetTime()
	{
		return this.time;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String toString(){
		return (number+"\n"+time);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Athlete) {
			Athlete other = (Athlete) o;
			
			if (other.getTime() == "DNF")
				return -1;
			else if (time == "DNF")
				return 1;
			return time.compareTo(other.getTime());
		}
		return 0;
	}

}
