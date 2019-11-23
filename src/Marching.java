import java.util.Vector;

public class Marching {
	// two green one orange in parade
	// orange goes first and allows 2 greens?
	// Create enough convey objects for each group?
	// private Vector greenInParade = new Vector(); //make convey object too
	// private Vector redInParade = new Vector();
	private static Vector<Object> paradeGroups = new Vector<Object>();
	private static Vector<Object> puppetShow = new Vector<Object>();
	private static Vector<Object> puppetShowWait = new Vector<Object>();
	private static int seats = 0;
	private boolean seatsFilled = false;
	private int greenStudents = 0;// has two
	private int orangeStudents = -1; // has one
	private boolean hasOrange = false;
	private static boolean groupFormed = false;
	private static boolean paradeFilled;
	private static int paraders;
	private static int paradersEntered = 0;
	private static int paradeGroupsFormed = 0;// use this to tell where in vector to form next group Have mod to go back
	// in parade?
	private static Object ClockNotifier = new Object(); // No things can keep being added to vector!!!
	private static int greenParadeGroups = 0;

	public static long time = System.currentTimeMillis();

	public Marching() {

	}

	// creates convey parade objects to pass through later
	// Warning if can't create enough?
	public void startParade(int paradeNumber) {
		for (int i = 0; i < paradeNumber; i++) {
			Object convey = new Object();
			paradeGroups.add(convey);
			// paraders=paradeNumber;
		}

	}

	public void readyPuppetShow() {
		for (int i = 0; i < 6; i++) {
			Object convey = new Object();
			puppetShow.add(convey);
		}
	}

	public synchronized void Age() {
		long time = System.currentTimeMillis();
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + time);
	}

	// Let Thread in based by name? yes can get thread name
	// Creates then passes through?

	// Orange Enters To Keep Track of where orange should go -Increment as needed
	// Green Enters
	// Split into letOrangeIn/letGreenIn
	public void letInParade() {
		Object convey = new Object();
		convey = paradeGroups.get(paradersEntered);// needs convey before synch!!!!!
		paradersEntered++; // need to add one before for mod to work
		synchronized (convey) { // replace with

			if (paradersEntered % 3 == 0) {
				paradeGroupsFormed++;
			}
			paradersEntered++;// Use this to figure out which convey is passed
			// System.out.println(Thread.currentThread().getName().substring(0, 1));
			if (Thread.currentThread().getName().substring(0, 1).contentEquals("o")) {
				// hasOrange = true;
				// Critical Section!!
				// System.out.println("here");
				// letOrangeInParade(convey); //pass in or calculate from vector?
			}

			// For Greens Here
			// if (Thread.currentThread().getName().substring(0, 1).contentEquals("g"))
			// letGreenInParade(convey);
		}

	}

	// Similar for puppet theater?
	public synchronized void exitParade() {
		for (Object convey : paradeGroups) {
			for (int i = 0; i < 3; i++)
				convey.notify();
		}
	}

	public synchronized int letOrangeInParade() {

		// System.out.println(Thread.currentThread().getName());

		orangeStudents++;
		paradersEntered++;
		// System.out.println("f");
		// convey =
		if (Thread.currentThread().getName().substring(0, 1).contentEquals("g"))
			System.out.println("test");
		// System.out.println("hhhhhhhhhh");
		if (paradersEntered % 3 == 0) // no gurantee that 3 who entered are in the right order must count
										// green and orange!!!
		{
			groupFormed = true;
			// paradeGroupsFormed++;
			wakeClock();
		}
		return orangeStudents;

		// }
	}

	public synchronized int letGreenInParade() {

		// System.out.println(Thread.currentThread().getName());

		if (greenStudents % 2 == 0 && greenStudents != 0) {
			greenParadeGroups++;
		}
		greenStudents++;
		paradersEntered++;
		if (paradersEntered % 3 == 0) {
			groupFormed = true;
			// paradeGroupsFormed++;
			wakeClock();
		}
		// System.out.println(greenParadeGroups);
		return greenParadeGroups;

		// }
	}

	public void anotherParade() {

	}

	public static boolean isParadeFilled() {
		return paradeFilled;
	}

	public static void setParadeFilled(boolean paradeFilled) {
		Marching.paradeFilled = paradeFilled;
	}

	public static Object getClockNotifier() {
		return ClockNotifier;
	}

	public static void setClockNotifier(Object clockNotifier) {
		ClockNotifier = clockNotifier;
	}

	public boolean isGroupFormed() {
		return groupFormed;
	}

	public void setGroupFormed(boolean groupFormed) {
		this.groupFormed = groupFormed;
	}

	public static Vector<Object> getParadeGroups() {
		return paradeGroups;
	}

	public void wakeClock() {
		Object clockNotifier = ClockNotifier;
		synchronized (clockNotifier) {
			clockNotifier.notify();
		}
	}

	public static void releaseParadeGroups(int groupNumber) {
		Object convey = paradeGroups.get(groupNumber);
		synchronized (convey) {
			msg("A group is entering in the parade");
			convey.notifyAll();
		}
	}

	public void paradeWaiting(int paradeWaitNumber) {
		Object convey = paradeGroups.get(paradeWaitNumber);
		synchronized (convey) {
			while (true) {
				try {
					msg("I lined up for the parade");
					convey.wait();
					msg("I have entered the parade");
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void waiting(Object Notifier) {
		Object convey = Notifier;
		synchronized (convey) {
			while (true) {
				try {
					convey.wait();
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void releasing(Object Notifier) {
		Object convey = Notifier;
		synchronized (convey) {
			convey.notify();
		}
	}

	public void letInPuppetShow() {
		Object convey = new Object();
		synchronized (convey) {
			if (cantEnter(convey)) {
				while (true) {
					try {
						msg("I am waiting to get to see the puppet show");
						convey.wait();
						break;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

	}

	public synchronized boolean cantEnter(Object convey) {
		Boolean status;

		if (seats >= 6 && seatsFilled) {
			puppetShowWait.add(convey);
			status = true;
		} else
			status = false;
		seatsFilled = false;

		return status;

	}

	public synchronized void resetSeats() {
		seats = 0;
	}

	public synchronized int addSeats() {
		seats++;
		return seats;

	}

	public void sitDown(int numSeat) {// reached when can go in
		Object convey = new Object();
		synchronized (convey) {
			try {
				puppetShow.add(seats, convey);
				seats++;
				if (seats == numSeat) {
					seatsFilled = true;

				}
				msg("I am watching the puppet show");
				convey.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized int getGreenpadeGroup() {
		return greenParadeGroups;
	}

	public void puppetRelease(int seatNumber) {
		Object convey = puppetShow.get(seatNumber);
		synchronized (convey) {
			convey.notify();
		}
	}

	public static void releasingFromPuppetWaiting(int waitNumber) {
		Object convey = puppetShowWait.get(waitNumber);
		synchronized (convey) {
			convey.notify();
		}
	}

	public synchronized static void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public synchronized void walking() {
		msg("I am Marching in the Parade");
	}

}
