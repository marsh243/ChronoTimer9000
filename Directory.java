import java.util.ArrayList;
import java.util.LinkedList;

public class Directory {
    //private ArrayList<Athlete> racers;
    DirectoryProxy dp;

    public Directory()
    {
        //racers = new ArrayList<Athlete>();
        dp = new DirectoryProxy();
    }

    public void add(LinkedList<Athlete> r)
    {
        //racers.add(r);
        dp.add(r);
    }
}