import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Parade extends Object {

	public static void main(String[] args) {

		int numGreen = 14;
		int numOrange = 7;
		int numSeat = 6; // tent capacity
		//used if values given via command line
		try {
			numGreen = Integer.valueOf(args[0]);
			numOrange = Integer.valueOf(args[1]);
			numSeat = Integer.valueOf(args[2]);
		}

		catch (Exception e) {

		}
		ArrayList<Thread> orangeList = new ArrayList<Thread>();
		ArrayList<Thread> greenList = new ArrayList<Thread>();
		// setNumOrange();
		Marching march = new Marching();
		march.startParade(numOrange);
		Random random = new Random();
		int walkingTime = 2000 + random.nextInt(500);// for walking around 20 min

		Thread clockThread = new Thread(new Clock("Clock", Marching.getClockNotifier(), numOrange, march, numSeat)); // Makes

		clockThread.start();

		for (int i = 0; i < numOrange; i++) { // Creates Each Thread With Array Used
			Thread orangeStudent = new Thread(new OrangeStudent(march, "orange " + i, numSeat, numOrange));
			orangeList.add(orangeStudent);
		}

		for (Thread orange : orangeList)
			orange.start(); // starts each thread in the
		// visitor Array using For Each Loop

		for (int j = 0; j < numGreen; j++) { // Creates Each Thread With Array Used
			Thread greenStudent = new Thread(new GreenStudent(march, "green " + j, numSeat, numOrange));
			greenList.add(greenStudent);
		}

		for (Thread green : greenList)
			green.start(); // starts each thread in the

	}

	public static long time = System.currentTimeMillis();

	public void Age(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

}
