
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
public class Simulator {
	

	private static BufferedReader reader;
	
	public static void main(String[] args)
	{
		ChronoTimer ct = new ChronoTimer();
	
		while(true){
			System.out.println("Would you like to read from Console? Y/N: ");
			Scanner p = new Scanner(System.in);
			String input = p.nextLine();
			
			while((!(input.equalsIgnoreCase("y"))) && (!(input.equalsIgnoreCase("n"))))
			{
				System.out.println("Would you like to read from Console? Y/N: ");
				input = p.nextLine();
			}
			if((input.equalsIgnoreCase("y")))
			{
				System.out.print("Enter Command\n");
				while(true)
				{
					System.out.print(">> ");
					input = p.nextLine();
					if(input.matches("^POWER$")) 
					{
						ct.power();
					}
					else if(input.matches("EXIT"))
					{
						ct.exit();
					}
					else if(input.matches("^RESET$"))
					{
						ct.reset();
					}
					else if(input.matches("^TIME \\d{0,1}:\\d{0,1}:\\d{0,1}$"))
					{
						String[] t = input.replace("TIME ", "").split(":");
						ct.time( Integer.parseInt(t[0]) , Integer.parseInt(t[1]) , Integer.parseInt(t[2]) );
					}
					else if(input.matches("^DNF$"))
					{
						ct.dnf();
					}
					else if(input.matches("CANCEL"))
					{
						ct.cancel();
					}
					else if(input.matches("TOG \\d{1}"))
					{
						ct.toggle(Integer.parseInt(input.replace("TOG ", "")));
					}
					else if(input.matches("TRIG \\d{1}"))
					{
						ct.trigger(Integer.parseInt(input.replace("TRIG ", "")));
					}
					else if(input.matches("START"))
					{
						ct.trigger(1);
					}
					else if(input.matches("FINISH"))
					{
						ct.trigger(2);
					}
					else if(input.matches("EVENT \\w{3,6}"))
					{
						if (input.matches("EVENT IND"))
						{
							ct.setMode(Modes.IND);
						}
					}
					else if(input.matches("NEWRUN"))
					{
						ct.newRun();
					}
					else if(input.matches("ENDRUN"))
					{
						ct.endRun();
					}
					else if(input.matches("NUM \\d+"))
					{
						ct.addRacer(input.replace("NUM ", ""));
					}
					else if (input.matches("PRINT"))
					{
						ct.print();
					}
					else
					{
						System.out.println("Command not recognized");
					}
				}
		}
		else
		{
			try {
				reader = new BufferedReader(new FileReader("transactions.txt"));
				String intput = "";
				while(reader.ready()){
					input = reader.readLine();
					System.out.println(">> " + input);
					input = p.nextLine();
					System.out.print(">> ");
					input = p.nextLine();
					if(input.matches("^POWER$")) 
					{
						ct.power();
					}
					else if(input.matches("EXIT"))
					{
						ct.exit();
					}
					else if(input.matches("^RESET$"))
					{
						ct.reset();
					}
					else if(input.matches("^TIME \\d{0,1}:\\d{0,1}:\\d{0,1}$"))
					{
						String[] t = input.replace("TIME ", "").split(":");
						ct.time( Integer.parseInt(t[0]) , Integer.parseInt(t[1]) , Integer.parseInt(t[2]) );
					}
					else if(input.matches("^DNF$"))
					{
						ct.dnf();
					}
					else if(input.matches("CANCEL"))
					{
						ct.cancel();
					}
					else if(input.matches("TOG \\d{1}"))
					{
						ct.toggle(Integer.parseInt(input.replace("TOG ", "")));
					}
					else if(input.matches("TRIG \\d{1}"))
					{
						ct.trigger(Integer.parseInt(input.replace("TRIG ", "")));
					}
					else if(input.matches("START"))
					{
						ct.trigger(1);
					}
					else if(input.matches("FINISH"))
					{
						ct.trigger(2);
					}
					else if(input.matches("EVENT \\w{3,6}"))
					{
						if (input.matches("EVENT IND"))
						{
							ct.setMode(Modes.IND);
						}
					}
					else if(input.matches("NEWRUN"))
					{
						ct.newRun();
					}
					else if(input.matches("ENDRUN"))
					{
						ct.endRun();
					}
					else if(input.matches("NUM \\d+"))
					{
						ct.addRacer(input.replace("NUM ", ""));
					}
					else if (input.matches("PRINT"))
					{
						ct.print();
					}
					else
					{
						System.out.println("Command not recognized\nAborting...");
						break;
					}
				}
			} catch(Exception ex){
				System.out.println("Error, file not found exception.");
				return;
			}
		}
	}
	}
	
	
}
