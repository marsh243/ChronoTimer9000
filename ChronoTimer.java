import java.util.ArrayList;
public class ChronoTimer {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private Timer timer;
	private Athlete racer;
	
	public ChronoTimer()
	{
		this.power = false;
		this.printer = false;
		this.eventLog = new ArrayList<String>();
		this.timer = new Timer();
		this.racer = new Athlete();
		
	}
	public void power()
	{
		if(power == false)
		{
			power = true;
		}
		else
		{
			new ChronoTimer();
		}
	}
	
	public void toggle(int i)
	{
		
	}
	
	public void trigger(int i)
	{
		
	}
	
	public void num(int i)
	{
		
	}
	
	public void newRun()
	{
		
	}
	
	public void endRun()
	{
		
	}
	
	public void print(String str)
	{
		if(!printer){
			//do nothing
		}
		else {
			System.out.println(str);
		}
	}
	
	public void exit()
	{
		
	}

}
