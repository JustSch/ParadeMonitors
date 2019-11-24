import java.util.Vector;

public class Marching {

	private static Vector<Object> paradeGroups = new Vector<Object>();
	private static Vector<Object> puppetShow = new Vector<Object>();
	private static Vector<Object> puppetShowWait = new Vector<Object>();


	private int greenStudents = 0;// has two
	private int orangeStudents = -1; // has one


	private static Object ClockNotifier = new Object(); // No things can keep being added to vector!!!
	private static int greenParadeGroups = 0;
	private static boolean paradeOngoing;

	public static long time = System.currentTimeMillis();

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
		return orangeStudents;

	}

	public synchronized int letGreenInParade() {

		// System.out.println(Thread.currentThread().getName());

		if (greenStudents % 2 == 0 && greenStudents != 0) {
			greenParadeGroups++;
		}
		greenStudents++;
		return greenParadeGroups;

		
	}



	public static Object getClockNotifier() {
		return ClockNotifier;
	}

	public static void setClockNotifier(Object clockNotifier) {
		ClockNotifier = clockNotifier;
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
			

		}
		}
	}


	public void releasePuppetVectors() {
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



	public void puppetRelease() {
		Object convey = puppetShow.remove(0);
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
