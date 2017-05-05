import java.util.ArrayList;

public class Directory {
    private ArrayList<Athlete> racers;
    DirectoryProxy dp;

    public Directory()
    {
        racers = new ArrayList<Athlete>();
        dp = new DirectoryProxy();
    }

    public void add(Athlete r)
    {
        racers.add(r);
        dp.add(racers);
    }
}