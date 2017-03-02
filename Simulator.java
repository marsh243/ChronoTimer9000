package lab05;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
public class Simulator {
	
	private CashDispenser CD;
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
						
					}
					else if(input.equals("EXIT"))
					{
						
					}
					else if(input.matches("^RESET$"))
					{

					}
					else if(input.matches("^TIME$"))
					{

					}
					else if(input.matches("^DNF$"))
					{

					}
					else if(input.equals("CANCEL"))
					{

					}
					else if(input.equals("TOG \\d{1}"))
					{
						ct.toggle(Integer.parseInt(input.replace("TOG ", "")));
					}
					else if(input.equals("TRIG \\d{1}"))
					{
						ct.trigger(Integer.parseInt(input.replace("TRIG ", "")));
					}
					else if(input.equals("START"))
					{

					}
					else if(input.equals("FINISH"))
					{

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
				String command = "";
				while(reader.ready()){
					command = reader.readLine();
					System.out.println(command);
					System.out.print(">> ");
					input = p.nextLine();
					if(input.matches("^POWER$")) 
					{
						
					}
					else if(input.equals("EXIT"))
					{
						
					}
					else if(input.matches("^RESET$"))
					{

					}
					else if(input.matches("^TIME$"))
					{

					}
					else if(input.matches("^DNF$"))
					{

					}
					else if(input.equals("CANCEL"))
					{

					}
					else if(input.equals("TOG \\d{1}"))
					{
						ct.toggle(Integer.parseInt(input.replace("TOG ", "")));
					}
					else if(input.equals("TRIG \\d{1}"))
					{
						ct.trigger(Integer.parseInt(input.replace("TRIG ", "")));
					}
					else if(input.equals("START"))
					{

					}
					else if(input.equals("FINISH"))
					{

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
