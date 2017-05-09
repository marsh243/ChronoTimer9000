import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class USBdevice 
{
	private String saveLocation = "ChronoTimer9000/"; // Defualt save location
	private String failSafe; // Contains the last attempted save
	
	// Save JSON
	public void save(String json, String name)
	{
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter(name) );
			bw.write(json);
			bw.flush();
			bw.close();
			System.out.println("Successfully saved to " + saveLocation + name);
		} catch (IOException e) {
			failSafe = (name+json);
			System.out.println("Unable to save " + name + "!\nData saved to failSafe.");
		}
	}
}
