
import java.lang.reflect.Array;
import java.util.*;
public class Timer {
	//create arrays for channels 
	public LinkedList runTimes;//Linked list used for storing the run times of the IND and PARIND modes
	public LinkedList grpRunTimes;//Linked list used for storing the run times of the GRP mode
	public LinkedList parGrpRunTimes;//Linked list used for storing the run times of the PARGRP mode
	private long grpStartTime;//Used to retrieve the starting time for GRP mode 
	
	public Timer()
	{
		runTimes = new LinkedList();
		runTimes.Start = new Node();//dummy node
		runTimes.LastFinishTime = null;
		runTimes.currentStart = runTimes.Start;
		runTimes.currentFinish = runTimes.Start;
		runTimes.trailer = runTimes.currentStart;//trailer is used to keep track of the racer that is next to finish
		
		grpRunTimes = new LinkedList();
		grpRunTimes.Start = new Node();//dummy node
		this.grpStartTime = -1;
		
		parGrpRunTimes = new LinkedList();
		parGrpRunTimes.Start = new Node();//dummy node
		parGrpRunTimes.currentStart = parGrpRunTimes.Start;
		parGrpRunTimes.currentFinish = parGrpRunTimes.currentStart;
		parGrpRunTimes.findIndex =  parGrpRunTimes.currentStart;//findIndex finds the position in the linked list based on which channel that was selected to finish
	}
	
	/*
	 * Returns the run times if every racer in order for IND and PARIND modes
	 */
	public String[] getRunTimes()
	{
		Node currentRacer = new Node();
		currentRacer = runTimes.Start.nextLink;
		long runTime = 0;
		int hours;
		int min;
		int sec;
		int hundreths;
		String[] runners = new String[runTimes.size];
		for(int i = 0; i < runTimes.size; i++)
		{
			if(currentRacer.EndTime != -1)
			{
				runTime = currentRacer.EndTime - currentRacer.StartTime;
				hours = (int)runTime/3600000;
				min = (int)(runTime%3600000)/60000;
				sec = (int)((runTime%60000)/1000);
				hundreths = (int)(runTime%1000)/10;
				runners[i] = (hours + ":" + min + ":" + sec + "." + hundreths);
			}
			else
			{
				runners[i] = "DNF";
			}
			currentRacer = currentRacer.nextLink;
		}
		return runners;
	}
	
	
	/*
	 * Returns the length of time for the entire race
	 */
	public String entireRaceTime()
	{
		long runTime = runTimes.LastFinishTime.EndTime - runTimes.Start.nextLink.StartTime;
		int hours;
		int min;
		int sec;
		int hundreths;
		hours = (int)runTime/3600000;
		min = (int)(runTime%3600000)/60000;
		sec = (int)((runTime%60000)/1000);
		hundreths = (int)(runTime%1000)/10;
		return (hours + ":" + min + ":" + sec + "." + hundreths);
	}
	
	
	/*
	 * Sets the start time for runners in the PARGRP mode
	 */
	public void ParGRPStartTime(int numRunners)
	{
		long startingTime = System.currentTimeMillis();//set start time of each runner to current system time
		for(int i = 0; i < numRunners; i++)
		{
			Node par = new Node();
			parGrpRunTimes.currentStart.nextLink = par;
			parGrpRunTimes.currentStart = parGrpRunTimes.currentStart.nextLink;
			parGrpRunTimes.currentStart.StartTime = startingTime;
		}
	}
	
	
	/*
	 * Sets the finish time for runners in the PARGRP mode
	 */
	public String ParGRPFinish(int index)
	{
		Node finish = parGrpRunTimes.getRunner(parGrpRunTimes, index);
		finish.EndTime = System.currentTimeMillis();
		return convert(finish);
	}
	
	
	/*
	 * Sets the start time for runners in the GRP mode
	 */
	public void GRPStartTime()
	{
		if(grpRunTimes.currentStart == null)
		{
			grpRunTimes.currentStart = grpRunTimes.Start;
			Node grpStart = new Node();
			grpRunTimes.currentStart.nextLink = grpStart;
			grpRunTimes.currentStart = grpStart;
			grpStartTime = System.currentTimeMillis();
			grpStart.StartTime = grpStartTime;
			grpRunTimes.size++;
		}
	}
	
	
	/*
	 * Sets the finish time for runners in the GRP mode
	 */
	public String GRPFinishTime()
	{
		if(grpRunTimes.size == 1)
		{
			grpRunTimes.currentStart.EndTime = System.currentTimeMillis();
			grpRunTimes.currentFinish = grpRunTimes.currentStart;
			Node grp = new Node();
			grpRunTimes.currentStart.nextLink = grp;
			grpRunTimes.currentStart = grp;
			grpRunTimes.size++;
			return convert(grpRunTimes.currentFinish);
		}
		else if(grpRunTimes.size > 1)
		{
			grpRunTimes.currentStart.StartTime = grpStartTime;
			grpRunTimes.currentStart.EndTime = System.currentTimeMillis();
			grpRunTimes.currentFinish = grpRunTimes.currentStart;
			Node grp = new Node();
			grpRunTimes.currentStart.nextLink = grp;
			grpRunTimes.currentStart = grp;
			grpRunTimes.size++;
			return convert(grpRunTimes.currentFinish);
		}
		return "";
	}
	
	
	/*
	 * Sets the start time for runners in the IND and PARIND modes
	 */
	public void start()
	{
		Node start = new Node();
		runTimes.currentStart.nextLink = start; 
		runTimes.currentStart = start;
		start.StartTime = System.currentTimeMillis();
		runTimes.size++;
	}
	
	
	/*
	 * Sets the finish time for runners in the IND and PARIND modes
	 */
	public String finish()
	{
		runTimes.currentFinish.nextLink.EndTime = System.currentTimeMillis();
		runTimes.currentFinish = runTimes.currentFinish.nextLink;
		runTimes.LastFinishTime = runTimes.currentFinish;
		runTimes.trailer = runTimes.currentFinish;
		return convert(runTimes.currentFinish);
	}
	
	
	/*
	 * Skips over the racer next to finish so that a time won't get set
	 * for that racer. When a time is not set for a racer, they will DNF
	 */
	public void DNF()
	{
		runTimes.currentFinish = runTimes.currentFinish.nextLink;
		runTimes.LastFinishTime = runTimes.currentFinish;
		runTimes.trailer = runTimes.currentFinish;
	}
	
	
	/*
	 * Cancels the racer next to finish start time and puts them 
	 * back in the queue to race for the IND and PARIND modes
	 */
	public void cancel()
	{
		if(runTimes.trailer.nextLink.nextLink != null)
		{
			runTimes.trailer.nextLink = runTimes.trailer.nextLink.nextLink;
		}
		else
		{
			runTimes.trailer.nextLink = null;
			runTimes.currentStart = runTimes.trailer;
		}
		runTimes.size--;
	}
	
	
	/*
	 * Used for converting the System time that is given in milliseconds
	 * into a string that will have hours, minutes, seconds as the runners
	 * time
	 */
	public String convert(Node currentRacer)
	{
		long runTime = 0;
		int hours;
		int min;
		int sec;
		int hundreths;
		runTime = currentRacer.EndTime - currentRacer.StartTime;
		hours = (int)runTime/3600000;
		min = (int)(runTime%3600000)/60000;
		sec = (int)((runTime%60000)/1000);
		hundreths = (int)(runTime%1000)/10;
		return (hours + ":" + min + ":" + sec + "." + hundreths);
	}
	
	/*
	 * Defined fields for our nodes start and finish times in the linked list, and an edge nextLink
	 * to move from the first runner to start, to the last runner to start
	 */
	      class Node
	      {
	            public long StartTime;
	            public long EndTime;
	            private Node nextLink;
	           
	            //constructor
	            public Node(){
	                  nextLink = null;
	                  StartTime=0;
	                  EndTime=-1;
	            }
	           
	            public Node(long s, long e, Node l){
	                  StartTime=s;
	                  EndTime=e;
	                  nextLink=l;
	            }
	           
	            public void setLink(Node N){
	                  nextLink=N;
	            }
	           
	            public Node getLink(){
	                  return nextLink;
	            }
	           
	            public long getStartTime(){
	                  return StartTime;
	            }
	           
	            public long getEndTime(){
	                  return EndTime;
	            }
	      }//end Node class
	     
	      
	     /*
	  	 * Defined this linked list to help with keeping track of runners. 
	  	 */
	      class LinkedList{
	            Node Start;//start of the linked list
	            Node LastFinishTime;//pointer to the last runner that finished
	            Node currentStart;//pointer to the last runner that started racing
	            Node currentFinish;//pointer to the next runner that will finish
	            Node trailer;//used to keep track of the runners that started before all currently racing runners in IND and PARIND
	            Node findIndex;//used for PARGRP mode to tell which node should be given an end time based on which channel was pushed
	            int size;
	           
	            public LinkedList(){
	                  Start=null;
	                  LastFinishTime=null;
	                  currentStart = null;
	                  currentFinish = null;
	                  size = 0;
	            }
	           
	            public boolean isEmpty(){
	                  return Start==null;
	            }
	            
	            
	            /*
	        	 * Used for finding the node in the linked list to set a finish time for a runner in
	        	 * PARGRP mode based on which channel was selected to finish
	        	 */
	            public Node getRunner(LinkedList r,int i)
	            {
	            	r.findIndex = r.Start;
	            	for(int j = 0; j < i; j++)
	            	{
	            		r.findIndex = r.findIndex.nextLink;
	            	}
	            	return r.findIndex;
	            }
	           
	      }//end LinkedList class	      
	}//end Timer Class
