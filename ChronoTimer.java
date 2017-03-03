
import java.util.ArrayList;
import java.util.LinkedList;
public class ChronoTimer {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private boolean[] channels;
	private boolean raceInProgress;
	private Timer timer;
	private LinkedList<Athlete> runners;
	private Modes mode;
	private String time;
	
	public ChronoTimer()
	{
		this.power = false;
		this.printer = false;
		this.eventLog = new ArrayList<String>();
		this.timer = new Timer();
		this.runners = new LinkedList<Athlete>();
		channels = new boolean[8];
		mode = Modes.NONE;
	}
	public void power()
	{
		if(power == false)
		{
			power = true;
		}
		else
		{
			this.power = true;
			this.printer = false;
			this.eventLog = new ArrayList<String>();
			this.timer = new Timer();
			this.runners = new LinkedList<Athlete>();
			channels = new boolean[8];
			mode = Modes.NONE;
		}
	}
	
	public void toggle(int i)
	{
		if (power)
		{
			channels[i-1] = !channels[i-1];
		}
	}
	
	public void trigger(int i)
	{
		if (raceInProgress && power)
		{
			if (i == 1)
			{
				timer.start();
			}
			else if (i == 2)
			{
				eventLog.add(timer.finish());
			}
		}
	}
	
	public void addRacer(String str)
	{
		if(raceInProgress && power)
			this.runners.add(new Athlete(str));
	}
	
	public void newRun()
	{
		if (power)
		{
			this.runners = new LinkedList<Athlete>();
			this.raceInProgress = true;
		}
	}
	
	public void endRun()
	{
		if (power)
		{
			this.raceInProgress = false;
		}
	}
	
	public void print()
	{
		if (power)
		{
			for(int i=0; i<eventLog.size(); i++){
				System.out.println(eventLog.get(i));
			}
		}
	}
	
	public void reset()
	{
		if (power)
		{
			this.power = true;
			this.printer = false;
			this.eventLog = new ArrayList<String>();
			this.timer = new Timer();
			this.runners = new LinkedList<Athlete>();
			channels = new boolean[8];
			mode = Modes.NONE;
		}
	}
	
	public void time (int h, int m, int s)
	{
		if (power)
		{
			this.time = "" + h + ";" + m + ":" + s;
		}
	}
	
	public void exit()
	{
		System.exit(0);
	}

}
