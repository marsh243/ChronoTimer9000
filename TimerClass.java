import java.lang.reflect.Array;
import java.util.*;
public class Timer {
	//create arrays for channels 
	LinkedList runTimes;   
	
	public Timer()
	{
		runTimes = new LinkedList();
		runTimes.Start = new Node();//dummy node
		runTimes.LastFinishTime = null;
		runTimes.current = runTimes.Start;
		runTimes.trailer = runTimes.current;
	}
	
	public String[] getRunTimes()
	{
		Node currentRacer = new Node();
		runTimes.Start.nextLink = currentRacer;
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
		runTimes.current.nextLink = start; 
		runTimes.current = start;
		start.StartTime = System.currentTimeMillis();
		runTimes.size++;
	}
	
	public void finish()
	{
		runTimes.current.EndTime = System.currentTimeMillis();
		runTimes.LastFinishTime = runTimes.current;
		runTimes.trailer = runTimes.current;
	}
	
	public void DNF()
	{
		runTimes.current.EndTime = -1;
		runTimes.trailer = runTimes.current;
		runTimes.size++;
	}
	
	public void cancel()
	{
		runTimes.current = runTimes.trailer;
		runTimes.current.nextLink = null;
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
	            Node current;
	            Node trailer;
	            int size;
	           
	            public LinkedList(){
	                  Start=null;
	                  LastFinishTime=null;
	                  current = null;
	                  size = 0;
	            }
	           
	            public boolean isEmpty(){
	                  return Start==null;
	            }
	           
	      }//end LinkedList class
	}//end Timer Class
