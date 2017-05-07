
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;

//import Timer;
public class ChronoTimer9000 {
	
	private boolean power, printer;
	private ArrayList<String> eventLog;
	private boolean[] channels;
	private boolean raceInProgress;
	private boolean grpStarted;
	private boolean startRaceScreen;
	private boolean parGrpStarted;
	private Timer indtimer;
	private Timer parTimer1;//channel 1 timer for PARIND
	private Timer parTimer2;//channel 3 timer for PARIND
	private Timer GrpTimer;
	private Timer ParGrpTimer;
	private LinkedList<Athlete> runners;//racer names/numbers
	private LinkedList<Athlete> displayRunners;//copies runners over to displayRunners to use for display of runners that are queued
	private LinkedList<Athlete> currentlyRunning;//All the current racers
	private LinkedList<Athlete> currentlyRunning1;//takes racers when trigger 1 is pushed
	private LinkedList<Athlete> currentlyRunning2;//takes racers when trigger 3 is pushed
	private LinkedList<Athlete> finishedRunners;//Used for PARIND and adds runners to the linked list when trig 2 or 4 is pushed
	private LinkedList<Athlete> GrpRunners;//
	private LinkedList<Athlete> ParGrpRunners;
	private LinkedList<Athlete> ParGrpRunnersFinished;
	private LinkedList<Athlete> displayToServer;
	private Modes mode;
	private String time;
	private int numStarted;
	private int numFinished;
	private int numRunners;//counts number of runners added to the runners queue used for Mode PARIND
	private int runnerIndex;//used to keep track of what index was/is used in the runners linked list
	private int channelNextToFinish;//TO DO: need to keep track of the next racer that is next to finish for cancel & DNF
	private int runNumber;
	private int grpRunnersFinished;
	private int grpRunnerCounter;
	private int displayRunnerCounter;
	private USBdevice usb;
	private boolean printPower;
	private Emulator frame;
	private String[] screens;
	private String[] ParGrpDisplayRunners;
	private int screen;
	
	public ChronoTimer9000(Emulator frame)
	{
		this.power = false;
		this.printPower = false;
		this.printer = false;
		this.grpStarted = false;
		this.parGrpStarted = false;
		this.eventLog = new ArrayList<String>();
		this.indtimer = new Timer();
		this.parTimer1 = new Timer();
		this.parTimer2 = new Timer();
		this.GrpTimer = new Timer();
		this.ParGrpTimer = new Timer();
		this.runners = new LinkedList<Athlete>();
		this.displayRunners = new LinkedList<Athlete>();
		this.currentlyRunning = new LinkedList<Athlete>();
		this.currentlyRunning1 = new LinkedList<Athlete>();
		this.currentlyRunning2 = new LinkedList<Athlete>();
		this.finishedRunners = new LinkedList<Athlete>();
		this.GrpRunners = new LinkedList<Athlete>();
		this.ParGrpRunners = new LinkedList<Athlete>();
		this.ParGrpRunnersFinished = new LinkedList<Athlete>();
		this.displayToServer = new LinkedList<Athlete>();
		this.channelNextToFinish = 0;
		this.runNumber = 0;
		this.grpRunnersFinished = 0;
		this.grpRunnerCounter = 1;
		this.displayRunnerCounter = 0;
		usb = new USBdevice();
		this.ParGrpDisplayRunners = new String[8];
		for(int i = 0; i < 8; i++)
		{
			ParGrpDisplayRunners[i] = "";
		}
		this.screens = new String[2];
		screens[0] = "";
		screens[1] = "";
		this.screen = 0;
		
		this.frame = frame;
		
		channels = new boolean[8];
		mode = Modes.NONE;
		numStarted = 0;
		numFinished = 0;
		numRunners = 0;
		runnerIndex = 0;
		
		// Updates the current race standings/results in a thread
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				while (true)
				{
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					displayRace();
				}
			}
		});
		t.start();
	}
	
	public void power()
	{	
		if(power == true)
		{
			frame.cbxTrig1.setEnabled(false);
			frame.cbxTrig2.setEnabled(false);
			frame.cbxTrig3.setEnabled(false);
			frame.cbxTrig4.setEnabled(false);
			frame.cbxTrig5.setEnabled(false);
			frame.cbxTrig6.setEnabled(false);
			frame.cbxTrig7.setEnabled(false);
			frame.cbxTrig8.setEnabled(false);
			frame.cbxTrig1.setSelected(false);
			frame.cbxTrig2.setSelected(false);
			frame.cbxTrig3.setSelected(false);
			frame.cbxTrig4.setSelected(false);
			frame.cbxTrig5.setSelected(false);
			frame.cbxTrig6.setSelected(false);
			frame.cbxTrig7.setSelected(false);
			frame.cbxTrig8.setSelected(false);
			screen = 0;
			screens[screen] = "";
			updateScreen();
			power = false;
		}
		else
		{
			this.power = true;
			writeln("Switching on...");
			this.printer = false;
			this.eventLog = new ArrayList<String>();
			this.indtimer = new Timer();
			this.parTimer1 = new Timer();
			this.parTimer2 = new Timer();
			this.GrpTimer = new Timer();
			this.runners = new LinkedList<Athlete>();
			this.currentlyRunning = new LinkedList<Athlete>();
			this.currentlyRunning1 = new LinkedList<Athlete>();
			this.currentlyRunning2 = new LinkedList<Athlete>();
			this.finishedRunners = new LinkedList<Athlete>();
			this.displayRunners = new LinkedList<Athlete>(); 

			this.channelNextToFinish = 0;
			channels = new boolean[8];
			mode = Modes.NONE;
			
			frame.cbxTrig1.setEnabled(true);
			frame.cbxTrig2.setEnabled(true);
			frame.cbxTrig3.setEnabled(true);
			frame.cbxTrig4.setEnabled(true);
			frame.cbxTrig5.setEnabled(true);
			frame.cbxTrig6.setEnabled(true);
			frame.cbxTrig7.setEnabled(true);
			frame.cbxTrig8.setEnabled(true);
		}
	}
	
	public void setMode()
	{
		if (power){
			if(this.mode==mode.NONE)this.mode=mode.IND;
			else if(this.mode==mode.IND)this.mode=mode.PARIND;
			else if(this.mode==mode.PARIND)this.mode=mode.GRP;
			else if(this.mode==mode.GRP)this.mode=mode.PARGRP;
			else {
				this.mode=mode.NONE;
			}
			
			clean(); // Clears partially entered data, etc.
			writeln(this.mode.toString());
		}
	}
	
	public void toggle(int i)
	{
		if (power)
			channels[i-1] = !channels[i-1];
	}
	
	public void trigger(int i)
	{
		if(channels[i-1])
		{
			if (power)
			{
				if(mode == Modes.IND)
				{
					if ((i == 1) && numStarted < runners.size())
					{
						if (numStarted == 0)
							println("\nIndividual Race:");
						numStarted++;
						indtimer.start();
					}
					else if ((i == 2) && numFinished < runners.size() && numStarted > numFinished)
					{
						numFinished++;
						String finish = indtimer.finish();
						eventLog.add(finish);
						println((runners.get(numFinished - 1) + ": "  + finish).replaceAll("\n", ""));
						displayToServer.addLast(runners.get(numFinished - 1));
						displayToServer.getLast().setTime(finish);
					}
				}
				else if(mode == Modes.PARIND)
				{
						if ((i == 1) && numStarted < numRunners)
						{
							if (numStarted == 0)
								println("\nParallel Individual Race:");
							
							numStarted++;
							currentlyRunning.add(runners.get(runnerIndex));
							currentlyRunning1.add(runners.get(runnerIndex));
							parTimer1.start();
							runnerIndex = findNextRunner(runners.get(runnerIndex));
							displayRunners.remove(displayRunnerCounter);
						}
						else if ((i == 3) && numStarted < numRunners)
						{
							if (numStarted == 0)
								println("\nParallel Individual Race:");
								numStarted++;
							currentlyRunning.add(runners.get(runnerIndex));
							currentlyRunning2.add(runners.get(runnerIndex));
							parTimer2.start();
							runnerIndex = findNextRunner(runners.get(runnerIndex));	
							displayRunners.remove(displayRunnerCounter);
						}
						//If there is more than one racer active, the finish event is associated with racers in a FIFO basis.
						else if(i == 2 && numFinished < numRunners)
						{
							numFinished++;
							if(currentlyRunning.contains(currentlyRunning1.getFirst()))
							{
								currentlyRunning.remove(currentlyRunning1.getFirst());
							}
							finishedRunners.add(currentlyRunning1.removeFirst());
							finishedRunners.getLast().setTime(parTimer1.finish());
							eventLog.add(finishedRunners.getLast().getTime());
							println((" " +finishedRunners.getLast().getNumber() + ": " + finishedRunners.getLast().getTime()).replaceAll("\n", ""));
							displayToServer.addLast(finishedRunners.getLast());
						}
						else if(i == 4 && numFinished < numRunners)
						{
							numFinished++;
							if(currentlyRunning.contains(currentlyRunning2.getFirst()))
							{
								currentlyRunning.remove(currentlyRunning2.getFirst());
							}
							finishedRunners.add(currentlyRunning2.removeFirst());
							finishedRunners.getLast().setTime(parTimer2.finish());
							eventLog.add(finishedRunners.getLast().getTime());
							println((" " +finishedRunners.getLast().getNumber() + ": " + finishedRunners.getLast().getTime()).replaceAll("\n", ""));
							displayToServer.addLast(finishedRunners.getLast());
						}
				}
				else if(mode == Modes.GRP)
				{
					if(i == 1 && !grpStarted)
					{
						GrpTimer.GRPStartTime();
						grpStarted = true;	
					}
					else if(i == 2)
					{
						if(grpStarted)
						{
							if(grpRunnersFinished >= numRunners)
							{
								if(numRunners < 10)
								{
									addRacer("00"+(grpRunnerCounter));
								}
								else
								{
									addRacer("0"+numRunners);
								}
								GrpRunners.add(runners.getLast());
								displayRunners.remove(displayRunnerCounter);
								GrpRunners.getLast().setTime(GrpTimer.GRPFinishTime());
								grpRunnerCounter++;
								grpRunnersFinished++;
								eventLog.add(GrpRunners.getLast().getTime());
								println((" " + GrpRunners.getLast().getNumber() + ": " + GrpRunners.getLast().getTime()).replaceAll("\n", ""));
								displayToServer.addLast(GrpRunners.getLast());
							}
							else
							{
								GrpRunners.add(runners.get(grpRunnersFinished));
								GrpRunners.getLast().setTime(GrpTimer.GRPFinishTime());
								grpRunnersFinished++;
								grpRunnerCounter++;
								eventLog.add(GrpRunners.getLast().getTime());
								displayRunners.remove(displayRunnerCounter);
								println((" " + GrpRunners.getLast().getNumber() + ": " + GrpRunners.getLast().getTime()).replaceAll("\n", ""));
								displayToServer.addLast(GrpRunners.getLast());
							}
						}
					}
				}
				else if(mode == Modes.PARGRP)
				{
					if(i == 1)
					{
						if(numRunners >= 1 && parGrpStarted == false)
						{
							for(int j = 0; j < 8; j++)
							{
								if(j == numRunners)
								{
									break;
								}
								ParGrpRunners.addLast(runners.get(j));
								//displayRunners.add(runners.get(j));
							}
							ParGrpTimer.ParGRPStartTime(ParGrpRunners.size());
							parGrpStarted = true;
							//runner in index 0 is assigned to this channel
							//start race
						}
						else if(numRunners >= 1 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")//after race is started, racer's time is ended on this channel
						{
							
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i- 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 2)
					{
						if(numRunners >= 2 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i- 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 3)
					{
						if(numRunners >= 3 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 4)
					{
						if(numRunners >= 4 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 5)
					{
						if(numRunners >= 5 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 6)
					{
						if(numRunners >= 6 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 7)
					{
						if(numRunners >= 7 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
					else if(i == 8)
					{
						if(numRunners >= 8 && parGrpStarted == true && displayRunners.get(i -1).getTime() == "")
						{
							ParGrpRunners.get(i - 1).setTime(ParGrpTimer.ParGRPFinish(i));
							println((" " + ParGrpRunners.get(i - 1).getNumber() +": " + ParGrpRunners.get(i -1).getTime()).replaceAll("\n", ""));
							ParGrpRunnersFinished.add(displayRunners.get(i - 1));
							ParGrpDisplayRunners[i-1] = "";
							displayRace();
							displayToServer.addLast(ParGrpRunners.get(i - 1));
						}
					}
				}
			}
		}
		else if (power)
		{
			writeln("channel " + i + " is not active");
		}
	}
	
	public void dnf()
	{
		if (power && raceInProgress && numFinished < runners.size() && numStarted > numFinished && mode == Modes.IND)
		{
			numFinished++;
			indtimer.DNF();
			eventLog.add("DNF");
			displayToServer.addLast(runners.get(numFinished - 1));
			displayToServer.getLast().setTime("DNF");
			println((runners.get(numFinished - 1) + ": "  + "DNF").replaceAll("\n", ""));
		}
		else if(power && raceInProgress && numFinished < numRunners && mode == Modes.PARIND)
		{
			if(!currentlyRunning1.isEmpty() && currentlyRunning.contains(currentlyRunning1.getFirst()))
			{
				numFinished++;
				finishedRunners.add(currentlyRunning1.removeFirst());
				parTimer1.DNF();
				finishedRunners.getLast().setTime("DNF");
				eventLog.add(finishedRunners.getLast().getTime());
				displayToServer.addLast(finishedRunners.getLast());
			}
			else if(!currentlyRunning2.isEmpty() && currentlyRunning.contains(currentlyRunning2.getFirst()))
			{
				numFinished++;
				finishedRunners.add(currentlyRunning2.removeFirst());
				parTimer2.DNF();
				finishedRunners.getLast().setTime("DNF");
				eventLog.add(finishedRunners.getLast().getTime());
				displayToServer.addLast(finishedRunners.getLast());
			}
		}
		else if(power && raceInProgress && mode == Modes.GRP)
		{
			if(grpStarted == true)
			{
				if(grpRunnersFinished >= numRunners)
				{
					if(numRunners < 10)
					{
						addRacer("00"+(grpRunnerCounter));
					}
					else
					{
						addRacer("0"+numRunners);
					}
					GrpRunners.add(runners.getLast());
					GrpRunners.getLast().setTime("DNF");
					grpRunnerCounter++;
					grpRunnersFinished++;
					eventLog.add(GrpRunners.getLast().getTime());
					displayToServer.add(GrpRunners.getLast());
				}
				else
				{
					GrpRunners.add(runners.get(grpRunnersFinished));
					GrpRunners.getLast().setTime("DNF");
					grpRunnersFinished++;
					grpRunnerCounter++;
					eventLog.add(GrpRunners.getLast().getTime());
					displayToServer.add(GrpRunners.getLast());
				}
			}
		}
		else if(power && raceInProgress && mode == Modes.PARGRP)
		{
			for(int i = 0; i < displayRunners.size(); i++)
			{
				if(ParGrpRunners.get(i).getTime() == "")
				{
					ParGrpRunners.get(i).setTime("DNF");
					eventLog.add(ParGrpRunners.get(i).getTime());
					displayToServer.add(ParGrpRunners.get(i));
				}
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
			if(currentlyRunning.getFirst().equals(currentlyRunning1.getFirst()))
			{
				numStarted--;
				parTimer1.cancel();
				runnerIndex = runners.indexOf(currentlyRunning.getFirst());
				currentlyRunning.removeFirst();
				currentlyRunning1.removeFirst();
			}
			else if(currentlyRunning.getFirst().equals(currentlyRunning2.getFirst()))
			{
				numStarted--;
				parTimer2.cancel();
				runnerIndex = runners.indexOf(currentlyRunning.getFirst());
				currentlyRunning.removeFirst();
				currentlyRunning2.removeFirst();
			}
		}
		else if(power && raceInProgress && mode == Modes.GRP)
		{
			this.GrpRunners = new LinkedList<Athlete>();
			this.GrpTimer = new Timer();
			this.grpRunnersFinished = 0;
			this.grpRunnerCounter = 1;
			this.numRunners = 0;
			this.eventLog = new ArrayList<String>();
		}
	}
	
	public void addRacer(String str)
	{
		if(raceInProgress && power)
		{
			this.runners.add(new Athlete(str));
			numRunners++;
			this.displayRunners.add(runners.getLast());
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
			runNumber++;
		}
	}
	
	public LinkedList<Athlete> endRun()
	{
		if (power && raceInProgress)
		{
			if(mode == Modes.PARGRP)
			{
				dnf();
				//saveToUSB();
				//this.raceInProgress = false;
				printResults();
				return displayToServer;
			}
			if(mode == Modes.GRP)
			{
				for(int i=0; i<eventLog.size(); i++){
					GrpRunners.get(i).setTime(eventLog.get(i));
				}
				saveToUSB();
				this.raceInProgress = false;
				return displayToServer;
			}
			for(int i=0; i<eventLog.size(); i++){
				runners.get(i).setTime(eventLog.get(i));
			}
			
			saveToUSB();
			this.raceInProgress = false;
			
			printResults();
		}
		return displayToServer;
	}
	
	public void printPower(){
		if (power)
		{
			if (printPower)
				writeln("Turning off printer...");
			else
				writeln("Turning on printer...");
			this.printPower = !this.printPower;
		}
	}
	
	public void printResults()
	{
		if (power)
		{
			String printString = "";
			if (!printPower)
			{
				writeln("Switch on printer to use.");
				return;
			}
			else if(mode == Modes.IND)
			{
				for(int i=0; i<eventLog.size(); i++){
					printString += (runners.get(i).getNumber() + " ");
					printString += (eventLog.get(i)) + "\n";
				}
			}
			else if(mode == Modes.PARIND)
			{
				for(int i=0; i<eventLog.size(); i++){
					printString += (finishedRunners.get(i).getNumber() + " ");
					printString += (eventLog.get(i)) + "\n";
				}
			}
			else if(mode == Modes.GRP)
			{
				for(int i=0; i<eventLog.size(); i++){
					printString += (GrpRunners.get(i).getNumber() + " ");
					printString += (eventLog.get(i)) + "\n";
				}
			}
			else if(mode == Modes.PARGRP)
			{
				for(int i=0; i<eventLog.size(); i++){
					printString += (ParGrpRunnersFinished.get(i).getNumber() + " ");//still need to add dnf's to ParGRPfinished
					printString += (eventLog.get(i)) + "\n";//need to add runners to event log for pargrp
				}
			}
			println(printString);
		}
	}
	
	public void saveToUSB()
	{
		//create json
		if(mode== Modes.GRP)
		{
			Gson g = new Gson();
			String json = g.toJson(GrpRunners);
			
			//create name
			String name = ""+runNumber;
			String pad = "000";
			name = pad.substring(name.length()) + name; // pad left 3 digits - eg. 001
			name = "RUN"+name+".txt";
			
			usb.save(json, name);
			return;
		}
		Gson g = new Gson();
		String json = g.toJson(runners);
		
		//create name
		String name = ""+runNumber;
		String pad = "000";
		name = pad.substring(name.length()) + name; // pad left 3 digits - eg. 001
		name = "RUN"+name+".txt";
		
		usb.save(json, name);
	}
	
	public int findNextRunner(Athlete x)
	{
		int y = -1;
		for(int i = 0; i < runners.size(); i++)
		{
			if(runners.get(i) == x)
			{
				
			}
			else if(runners.get(i).time.equals("") && !(currentlyRunning.contains(runners.get(i))))
			{
				y = i;
				break;
			}
		}
		return y;
	}
	
	public void swap(){
		if(this.mode!=mode.IND) return;
		Athlete newFirst, newSecond;
		LinkedList<Athlete> newRunners = new LinkedList<Athlete>();
		newSecond = this.runners.removeFirst();
		newFirst = this.runners.removeFirst();
		newRunners.add(newFirst);
		newRunners.add(newSecond);
		newRunners.addAll(this.runners);
		this.runners = newRunners;
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

	public void setScreen(int screen)
	{
		this.screen = screen;
		updateScreen();
	}
	
	public void enter(String s)
	{
		if (parGrpStarted || grpStarted || numStarted > 0)
		{
			dnf();			
		}
		else if(s != "")
		{
			addRacer(s);
		}
	}
	
	
	/*
	 * Helper Methods section
	 */
	
	// Updates the right display to the current standings.
	private void displayRace()
	{
		String standings = "";
		try{
			if (mode == Modes.IND)
			{
				standings += "Runners Queued:\n";
				
				for(int i = numStarted; (i < displayRunners.size() && i < numStarted + 3) ;i++)
				{
					standings += runners.get(i);
				}
				standings += "\nRacing:\n";
				for(int i = numFinished; i < numStarted; i++)
				{
					standings += runners.get(i) + "\n";
				}
				
				standings += "Finished:\n";
				if (numFinished > 0)
				{
					for(int i = 0; i < numFinished; i++)
					{
						standings += runners.get(i) + "\n";
					}
				}
			}
			else if (mode == Modes.PARIND)
			{
				standings += "Runners Queued:\n";
				
				for(int i = 0; i < displayRunners.size();i++)
				{
					standings += displayRunners.get(i) + "\n";
				}
				
				standings += "Racing:\nChannel 1:\n";
				for(int i = 0; i < currentlyRunning1.size();i++)
				{
					standings += currentlyRunning1.get(i);
				}
				standings += "Channel 3:\n";
				for(int i = 0; i < currentlyRunning2.size();i++)
				{
					standings += currentlyRunning2.get(i);
				}
				
				standings += "\nFinished:\n";
				if (finishedRunners.size() > 0)
				{
					for(int i = 0; i < numFinished; i++)
					{
						standings += finishedRunners.get(i) + "\n";
					}
				}
			}
			else if (mode == Modes.GRP)
			{
				standings += "Racing:\n";
				if(grpStarted == true)
				{
					for(int i = 0 ; i < displayRunners.size() ; i++)
					{
						standings += displayRunners.get(i);
					}
					
					standings += "\nFinished:\n";
					if(GrpRunners.size() > 0)
						standings += GrpRunners.get(GrpRunners.size()-1) + "\n\n";
				}
				
			}
			else if(mode == Modes.PARGRP)
			{
				standings += "Racing:\n";
				if(parGrpStarted == true)
				{
					for(int i = 0; i < displayRunners.size(); i++)
					{
						ParGrpDisplayRunners[i] = displayRunners.get(i).getNumber();
						if(displayRunners.get(i).getTime() == "")
						{
							standings += ParGrpDisplayRunners[i]+"\n";//need to add to displayRunners when start is triggered
						}
					}
					standings += "\nFinished:\n";
					for(int j = 0; j < ParGrpRunnersFinished.size(); j++)
					{
						standings += ParGrpRunnersFinished.get(j).getNumber() + "\n";
					}
				}
			}
			
			
			screens[1] = standings;
			if (screen == 1)
				updateScreen();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Writes a string to the event log
	public void write(String message)
	{
		if(power){
			screens[0] += message;
			updateScreen();
		}
		//frame.eventLog.setText(screens[0]);
	}
	
	// Writes a string to the event log and ends the line
	public void writeln(String message)
	{
		if(power){
			screens[0] += message + "\n";
			updateScreen();
		}
		//frame.eventLog.setText(screens[0]);
	}

	// Writes a line with the printer.
	private void println(String message)
	{
		frame.printer.setText(frame.printer.getText() + message + "\n");
	}

	private void updateScreen()
	{
		frame.eventLog.setText(screens[screen]);
	}
	
	// Cleans up unresolved data after switching modes.
	private void clean()
	{
		this.grpStarted = false;
		this.eventLog = new ArrayList<String>();
		this.indtimer = new Timer();
		this.parTimer1 = new Timer();
		this.parTimer2 = new Timer();
		this.GrpTimer = new Timer();
		this.runners = new LinkedList<Athlete>();
		this.displayRunners = new LinkedList<Athlete>();
		this.currentlyRunning = new LinkedList<Athlete>();
		this.currentlyRunning1 = new LinkedList<Athlete>();
		this.currentlyRunning2 = new LinkedList<Athlete>();
		this.finishedRunners = new LinkedList<Athlete>();
		this.GrpRunners = new LinkedList<Athlete>();
		this.channelNextToFinish = 0;
		this.runNumber = 0;
		this.grpRunnersFinished = 0;
		this.grpRunnerCounter = 1;
		this.displayRunnerCounter = 0;
		
		this.screen = 0;		
		numStarted = 0;
		numFinished = 0;
		numRunners = 0;
		runnerIndex = 0;
	}
	

	
	
}