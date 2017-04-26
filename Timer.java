
import java.lang.reflect.Array;
import java.util.*;
public class Timer {
	//create arrays for channels 
	public LinkedList runTimes;
	public LinkedList grpRunTimes;
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
		//grpRunTimes.currentStart = grpRunTimes.Start;
		this.grpStartTime = -1;
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
		//runTimes.trailer = runTimes.currentFinish;
		//runTimes.currentStart.nextLink = null;
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
	           
	      }//end LinkedList class
	      
//	      public class ParIndTimer extends Timer
//	      {
//	    	  public ParIndTimer()
//	    	  {
//	    		  Timer runTimes = new Timer();
//	    	  }
//	    	  
//	    	  
//	      }
	      
	}//end Timer Class
