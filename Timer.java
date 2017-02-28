import java.util.*;

public class Timer {

	LinkedList runTimes;

	public Timer()
	{
		runTimes = new LinkedList();
		runTimes.Start = new Node();//dummy
		runTimes.LastFinishTime= null;
		runTimes.Current=runTimes.Start;
		runTimes.Trailer=runTimes.Current;
	}
	
	public long[] getRunTimes()
	{
		Node currentRacer = new Node();
		runTimes.Start.nextLink=currentRacer;
		long[] runners= new long[runTimes.Size];
		for(int i = 0; i <runTimes.Size; i++){
			if(currentRacer.EndTime!=-1)
			{
				runners[i]=(currentRacer.EndTime - currentRacer.StartTime);
			}
			else
			{
				runners[i]=-1;
			}
			currentRacer=currentRacer.nextLink;
		}
		return runners;
		
	}
	
	public long entireRaceTime()
	{
		return (runTimes.LastFinishTime.EndTime - runTimes.Start.nextLink.StartTime);
	}
	
	public void start()
	{
		Node start = new Node();
		runTimes.Current.nextLink = start;
		runTimes.Current = start;
		start.StartTime = (long)System.currentTimeMillis();
		runTimes.Size++;
		
	}
	public void finish(Node Start)
	{
		runTimes.Current.EndTime=(long)System.currentTimeMillis();
		runTimes.LastFinishTime=runTimes.Current;
		runTimes.Trailer = runTimes.Current;
	}
	
	public void DNF()
	{
		runTimes.Current.EndTime=-1;
		runTimes.Trailer = runTimes.Current;
		runTimes.Size++;
	}
	
	public void cancel()
	{
		runTimes.Current =runTimes.Trailer;
		runTimes.Current.nextLink=null;
		runTimes.Size--;
	}
	class Node{
		public long StartTime;
		public long EndTime;
		public Node nextLink;
		
		//constructor
		public Node(){
			nextLink = null;
			StartTime=0;
			EndTime=-1;
		}
		
		public Node(long s, long e, Node l)
		{
			StartTime=s;
			EndTime = e;
			nextLink=null;
		}
		
		public void setLink(Node n){
			nextLink=n;
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
		Node Current;
		Node Start;
		Node LastFinishTime;
		Node Trailer;
		int Size;
		
		public LinkedList(){
			Start = null;
			LastFinishTime = null;
			Current = null;
			Size = 0; 
		}
		
		public boolean isEmpty(){
			return Start==null;
		}
		
	}//end LinkedList class
}//end Timer Class
