
import java.util.ArrayList;
import java.util.LinkedList;

//import Timer;
public class ChronoTimer {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private boolean[] channels;
	private boolean raceInProgress;
	private Timer indtimer;
	private Timer parTimer1;//channel 1 timer for PARIND
	private Timer parTimer2;//channel 3 timer for PARIND
	private LinkedList<Athlete> runners;
	private LinkedList<Athlete> currentlyRunning1;//takes racers when trigger 1 is pushed
	private LinkedList<Athlete> currentlyRunning2;//takes racers when trigger 3 is pushed
	private LinkedList<Athlete> finishedRunners;//Used for PARIND and adds runners to the linked list when trig 2 or 4 is pushed
	private Modes mode;
	private String time;
	private int numStarted;
	private int numFinished;
	private int numRunners;//counts number of runners added to the runners queue used for Mode PARIND
	private int runnerIndex;//used to keep track of what index was/is used in the runners linked list
	
	
	public ChronoTimer()
	{
		this.power = false;
		this.printer = false;
		this.eventLog = new ArrayList<String>();
		this.indtimer = new Timer();
		this.parTimer1 = new Timer();
		this.parTimer2 = new Timer();
		this.runners = new LinkedList<Athlete>();
		this.currentlyRunning1 = new LinkedList<Athlete>();
		this.currentlyRunning2 = new LinkedList<Athlete>();
		this.finishedRunners = new LinkedList<Athlete>();
		channels = new boolean[8];
		mode = Modes.NONE;
		numStarted = 0;
		numFinished = 0;
		numRunners = 0;
		runnerIndex = 0;
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
			this.indtimer = new Timer();
			this.parTimer1 = new Timer();
			this.parTimer2 = new Timer();
			this.runners = new LinkedList<Athlete>();
			this.currentlyRunning1 = new LinkedList<Athlete>();
			this.currentlyRunning2 = new LinkedList<Athlete>();
			this.finishedRunners = new LinkedList<Athlete>();
			channels = new boolean[8];
			mode = Modes.NONE;
		}
	}
	
	public void setMode(Modes m)
	{
		this.mode = m;
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
			if(mode == Modes.IND)
			{
				if ((i == 1) && numStarted < runners.size())
				{
					numStarted++;
					indtimer.start();
				}
				else if ((i == 2) && numFinished < runners.size())
				{
					numFinished++;
					eventLog.add(indtimer.finish());
				}
			}
			else if(mode == Modes.PARIND)
			{
				if ((i == 1) && numStarted < numRunners)
				{
					numStarted++;
					currentlyRunning1.add(runners.get(runnerIndex));
					parTimer1.start();
					runnerIndex++;
				}
				else if ((i == 3) && numStarted < numRunners)
				{
					numStarted++;
					currentlyRunning2.add(runners.get(runnerIndex));
					parTimer2.start();
					runnerIndex++;
				}
				//If there is more than one racer active, the finish event is associated with racers in a FIFO basis.
				else if(i == 2 && numFinished < numRunners)
				{
					numFinished++;
					finishedRunners.add(currentlyRunning1.removeFirst());
					finishedRunners.getLast().setTime(parTimer1.finish());//need to associate a time with each runner once they are finished
					eventLog.add(finishedRunners.getLast().getTime());
				}
				else if(i == 4 && numFinished < numRunners)
				{
					numFinished++;
					finishedRunners.add(currentlyRunning2.removeFirst());
					finishedRunners.getLast().setTime(parTimer2.finish());
					eventLog.add(finishedRunners.getLast().getTime());
				}
			}
		}
	}
	
	public void dnf()
	{
		if (power && raceInProgress && numFinished < runners.size() && mode == Modes.IND)
		{
			numFinished++;
			indtimer.DNF();
			eventLog.add("DNF");
		}
		else if(power && raceInProgress && numFinished < numRunners && mode == Modes.PARIND)
		{
			if(currentlyRunning1.contains(runners.get(runnerIndex - 1)))
			{
				numFinished++;
				finishedRunners.add(currentlyRunning1.removeFirst());
				finishedRunners.getLast().setTime("DNF");
				eventLog.add(finishedRunners.getLast().getTime());
			}
			else if(currentlyRunning2.contains(runners.get(runnerIndex - 1)))
			{
				numFinished++;
				finishedRunners.add(currentlyRunning2.removeFirst());
				finishedRunners.getLast().setTime("DNF");
				eventLog.add(finishedRunners.getLast().getTime());
			}
		}
	}
	
	public void cancel()
	{
		if(power && raceInProgress && mode == Modes.IND)
		{
			numStarted--;
			Athlete temp = runners.remove(numStarted);
			runners.add(numFinished, temp);
		}
		else if(power && raceInProgress && mode == Modes.PARIND)
		{
			if(currentlyRunning1.contains(runners.get(runnerIndex - 1)))
			{
				numStarted--;
				runnerIndex--;
				currentlyRunning1.removeFirst();
				//runners.add(numFinished, temp);
			}
			else if(currentlyRunning2.contains(runners.get(runnerIndex - 1)))
			{
				numStarted--;
				runnerIndex--;
				currentlyRunning2.removeFirst();
				//runners.add(numFinished, temp);
			}
		}
	}
	
	public void addRacer(String str)
	{
		if(raceInProgress && power)
		{
			this.runners.add(new Athlete(str));
			numRunners++;
		}
		
	}
	
	public void newRun()
	{
		if (power)
		{
			this.runners = new LinkedList<Athlete>();
			this.currentlyRunning1 = new LinkedList<Athlete>();
			this.currentlyRunning2 = new LinkedList<Athlete>();
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
			if(mode == Modes.IND)
			{
				for(int i=0; i<eventLog.size(); i++){
					System.out.print(runners.get(i).getName() + " ");
					System.out.println(eventLog.get(i));
				}
			}
			else if(mode == Modes.PARIND)
			{
				for(int i=0; i<eventLog.size(); i++){
					System.out.print(runners.get(i).getName() + " ");
					System.out.println(eventLog.get(i));
				}
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
			this.indtimer = new Timer();
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
