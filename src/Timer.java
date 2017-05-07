
import java.lang.reflect.Array;
import java.util.*;
public class Timer {
	//create arrays for channels 
	public LinkedList runTimes;
	public LinkedList grpRunTimes;
	public LinkedList parGrpRunTimes;
	private long grpStartTime;
	
	public Timer()
	{
		runTimes = new LinkedList();
		runTimes.Start = new Node();//dummy node
		runTimes.LastFinishTime = null;
		runTimes.currentStart = runTimes.Start;
		runTimes.currentFinish = runTimes.Start;
		runTimes.trailer = runTimes.currentStart;
		
		grpRunTimes = new LinkedList();
		grpRunTimes.Start = new Node();//dummy node
		this.grpStartTime = -1;
		
		parGrpRunTimes = new LinkedList();
		parGrpRunTimes.Start = new Node();//dummy node
		parGrpRunTimes.currentStart = parGrpRunTimes.Start;
		parGrpRunTimes.currentFinish = parGrpRunTimes.currentStart;
		parGrpRunTimes.findIndex =  parGrpRunTimes.currentStart;
	}
	
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
	
	public String ParGRPFinish(int index)
	{
		Node finish = parGrpRunTimes.getRunner(parGrpRunTimes, index);
		finish.EndTime = System.currentTimeMillis();
		return convert(finish);
	}
	
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
	
	public void start()
	{
		Node start = new Node();
		runTimes.currentStart.nextLink = start; 
		runTimes.currentStart = start;
		start.StartTime = System.currentTimeMillis();
		runTimes.size++;
	}
	
	public String finish()
	{
		runTimes.currentFinish.nextLink.EndTime = System.currentTimeMillis();
		runTimes.currentFinish = runTimes.currentFinish.nextLink;
		runTimes.LastFinishTime = runTimes.currentFinish;
		runTimes.trailer = runTimes.currentFinish;
		return convert(runTimes.currentFinish);
	}
	
	public void DNF()
	{
		runTimes.currentFinish = runTimes.currentFinish.nextLink;
		runTimes.LastFinishTime = runTimes.currentFinish;
		runTimes.trailer = runTimes.currentFinish;
	}
	
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
	
	      class Node
	      {
	            public long StartTime;
	            public long EndTime;
	            private Node nextLink;
	           
	            //construct
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
	     
	      class LinkedList{
	            Node Start;
	            Node LastFinishTime;
	            Node currentStart;
	            Node currentFinish;
	            Node trailer;
	            Node findIndex;
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
