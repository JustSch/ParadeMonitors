import java.util.Vector;

public class Marching {

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
	private static boolean paradeOngoing;

	public static long time = System.currentTimeMillis();

	public Marching() {

	}

	
	public synchronized void startParade(int paradeNumber) {
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

	
	public synchronized void exitParade() {
		for (Object convey : paradeGroups) {
			for (int i = 0; i < 3; i++)
				convey.notify();
		}
	}

	public synchronized int letOrangeInParade() {


		orangeStudents++;
		paradersEntered++;
		
		
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

	public static void releaseParadeGroups() {
		Object convey = paradeGroups.get(0);
		synchronized (convey) {
			msg("A group is entering in the parade");
			convey.notifyAll();
			paradeGroups.remove(0);
			
		}
	}

	public void paradeWaiting(int paradeWaitNumber) {
		if(paradeGroups.isEmpty()) {
			msg("yes");
		}
		msg(String.valueOf(paradeWaitNumber));
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
					msg("Cant wait for parade");
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
		Object convey = puppetShowWait.remove(0);
		synchronized (convey) {
			convey.notify();
		}

	}
	
	public void watchingPuppetShow() {
		Object convey = new Object();
		synchronized(convey){
			while (true) {
				try {
					msg("I am watching the puppet show");
					puppetShow.add(convey);
					convey.wait();
					msg("I left the puppet show");
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}

		}
		}
	}

	public synchronized boolean cantEnter(Object convey) {
		Boolean status;
		msg(String.valueOf(seats) + 'm');
		if (seats >= 6 || seatsFilled) {
			puppetShowWait.add(convey);
			status = true;
		} else
			status = false;
		// seatsFilled = false;
		msg(String.valueOf(status));
		return status;

	}

	public synchronized void resetSeats() {
		seats = 0;
	}

	public synchronized int addSeats() {
		seats++;
		return seats;

	}

	public void releaseStuff() {
		// Object o = new Objec t();
		//resetSeats();
		for (Object o : puppetShow) {
			synchronized (o) {
				o.notify();
			}
		}
		for (Object j : puppetShowWait) {
			synchronized (j) {
				j.notify();
			}
		}
	}

	public void puppetShowWait() {
		Object convey = new Object();
		synchronized (convey) {
			try {
				while (true) {
					puppetShowWait.add(convey);
					msg("I am standing in line for the puppet show");
					msg("I will wander while I wait for the next show to begin");
					convey.wait();
					break;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized int getGreenpadeGroup() {
		return greenParadeGroups;
	}

	public void puppetRelease() {
		Object convey = puppetShow.remove(0);
		synchronized (convey) {
			convey.notify();
		}
	}

	public static void releasingFromPuppetWaiting() {
		Object convey = puppetShowWait.remove(0);
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
	
	public synchronized void resetNum() {
		orangeStudents=-1;
		greenStudents =0;
		paraders =0;
	}

	public  boolean isParadeOngoing() {
		return paradeOngoing;
	}

	public void setParadeIsOngoing() {
		paradeOngoing = true;
	}
	
	public void setParadeOver() {
		paradeOngoing = false;
	}

}
