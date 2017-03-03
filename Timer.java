
import java.lang.reflect.Array;
import java.util.*;
public class Timer {
	//create arrays for channels 
	LinkedList runTimes;   //
	
	public Timer()
	{
		runTimes = new LinkedList();
		runTimes.Start = new Node();//dummy node
		runTimes.LastFinishTime = null;
		runTimes.currentStart = runTimes.Start;
		runTimes.currentFinish = runTimes.Start;
		runTimes.trailer = runTimes.currentStart;
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
	
	public void start()
	{
		Node start = new Node();
		runTimes.currentStart.nextLink = start; 
		runTimes.currentStart = start;
		start.StartTime = System.currentTimeMillis();
		runTimes.size++;
	}
	
	public void finish()
	{
		runTimes.currentFinish.nextLink.EndTime = System.currentTimeMillis();
		runTimes.currentFinish = runTimes.currentFinish.nextLink;
		runTimes.LastFinishTime = runTimes.currentFinish;
		runTimes.trailer = runTimes.currentFinish;
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
	}//end Timer Class
