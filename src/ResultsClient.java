import java.util.ArrayList;
import java.util.LinkedList;

public class ResultsClient {
    ResultsServerProxy dp;

    public ResultsClient()
    {
        dp = new ResultsServerProxy();
    }

    /*Using the results proxy, posts an entire list of racers to the server*/
    public void add(LinkedList<Athlete> r)
    {
        //Convert to Arraylist
    	ArrayList<Athlete> ra = new ArrayList<Athlete>();
    	for (Athlete a : r)
    		ra.add(a);
        dp.add(ra);
    }
}