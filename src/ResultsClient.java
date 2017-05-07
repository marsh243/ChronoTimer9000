import java.util.ArrayList;
import java.util.LinkedList;

public class ResultsClient {
    //private ArrayList<Athlete> racers;
    ResultsServerProxy dp;

    public ResultsClient()
    {
        //racers = new ArrayList<Athlete>();
        dp = new ResultsServerProxy();
    }

    public void add(LinkedList<Athlete> r)
    {
        //racers.add(r);
    	ArrayList<Athlete> ra = new ArrayList<Athlete>();
    	for (Athlete a : r)
    		ra.add(a);
        dp.add(ra);
    }
}