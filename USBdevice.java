import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class USBdevice 
{
	private String saveLocation = "ChronoTimer9000/";
	private String failSafe;
	
	public void save(String json, String name)
	{
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter(saveLocation + name) );
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
