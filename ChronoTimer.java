import java.util.ArrayList;
public class ChronoTimer {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private boolean[] channels;
	private boolean raceInProgress;
	private Timer timer;
	private Athlete racer;
	
	public ChronoTimer()
	{
		this.power = false;
		this.printer = false;
		this.eventLog = new ArrayList<String>();
		this.timer = new Timer();
		channels = new boolean[8];
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
		channels[i-1] = !channels[i-1];
	}
	
	public void trigger(int i)
	{
		if (raceInProgress)
		{
			if (i == 1)
			{
				timer.start();
			}
			else if (i == 2)
			{
				timer.finish();
			}
		}
	}
	
	public void addRacer(int i)
	{
		if (raceInProgress)
		{
			
		}
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
