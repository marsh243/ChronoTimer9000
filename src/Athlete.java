import java.util.HashMap;
import java.util.Map;

public class Athlete implements Comparable<Object>{
	private String number;
	String time;
	
	private Map<String, String> names; // Hard coded names to relate to bib numbers
	
	public Athlete(String name, String time){
		this.number = name;
		this.time = time;
		
		this.names = new HashMap<String, String>();
		names.put("367", "Bob Smith");
		names.put("211", "Mary Edwards");
		names.put("226", "Ed Snowden");
		names.put("217", "Tom Brookes");
		names.put("101", "Mary Smith");
		names.put("171", "Kelly Oliver");
		names.put("345", "Todd Lindsey");
		names.put("165", "Phil Estrada");
	}
	
	public Athlete(String name){
		this.number = name;
		this.time = "";
		
		this.names = new HashMap<String, String>();
		names.put("367", "Bob Smith");
		names.put("211", "Mary Edwards");
		names.put("226", "Ed Snowden");
		names.put("217", "Tom Brookes");
		names.put("101", "Mary Smith");
		names.put("171", "Kelly Oliver");
		names.put("345", "Todd Lindsey");
		names.put("165", "Phil Estrada");
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public String parGRPGetTime()
	{
		return this.time;
	}
	
	// Returns a name if the number is knows. Else returns blank string.
	public String getName()
	{
		if (names.containsKey(number))
			return names.get(number);
		return "";
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String toString(){
		if (time != "")
			return (number+": "+time+"\n");
		else
			return number + "\n";
	}
	
	/*The comparator to sort by time*/
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
