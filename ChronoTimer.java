import java.util.ArrayList;
public class ChronoTimer {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private boolean[] channels;
	private boolean raceInProgress;
	private Timer timer;
	private LinkedList<Athlete> runners;
	
	public ChronoTimer()
	{
		this.power = false;
		this.printer = false;
		this.eventLog = new ArrayList<String>();
		this.timer = new Timer();
		this.runners = new LinkedList<Athlete>();
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
	
	public void addRacer(String str)
	{
		if(raceInProgress)
			this.runners.add(new Athlete(str));
	}
	
	public void newRun()
	{
		this.runners = new LinkedList<Athlete>();
		this.raceInProgress = true;
	}
	
	public void endRun()
	{
		this.raceInProgress = false;
	}
	
	public void print()
	{
		for(int i=0; i<eventLog.size(); i++){
			System.out.println(eventLog.get(i));
		}
	}
	
	public void exit()
	{
		System.exit(0);
	}

}
