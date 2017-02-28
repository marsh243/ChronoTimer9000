import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class TimerTest {

	Timer timer = new Timer();
	public void setUp()
	{
		
	}
	@Test
	public void testMultipleStarts() {
		System.out.println("\n\ntestMultipleStarts\n\n");
		timer.start();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.start();
		timer.finish();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.finish();
		String[] runtime = timer.getRunTimes();
		System.out.println(runtime[0] + "\n" + runtime[1]);
	}
	
	@Test
	public void testMultipleStartsWithNoFinish() {
		System.out.println("\n\ntestMultipleStartsWithNoFinish\n\n");
		timer.start();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.start();
		timer.finish();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.finish();
		timer.start();
		timer.start();
		String[] runtime = timer.getRunTimes();
		System.out.println(runtime[0] + "\n" + runtime[1] + "\n" + runtime[2] + "\n" + runtime[3]);
	}

	@Test
	public void testDNF() {
		System.out.println("\n\ntestDNF\n\n");
		timer.start();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.start();
		timer.DNF();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.finish();
		String[] runtime = timer.getRunTimes();
		System.out.println(runtime[0] + "\n" + runtime[1]);
	}
	
	@Test
	public void testCancel() {
		System.out.println("\n\ntestCancel\n\n");
		timer.start();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.finish();
		timer.start();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.start();
		try {
			Thread.sleep(2200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		timer.finish();
		String[] runtime = timer.getRunTimes();
		System.out.println(runtime[0] + "\n" + runtime[1]);
	}
}
