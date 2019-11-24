
public class Clock implements Runnable {

	public static long time = System.currentTimeMillis();
	public String clockName;
	public Object ClockNotifier = new Object();
	public int total;
	public Marching march;
	public static Object StaffNotifier = new Object();
	public int numSeat;

	public Clock() {

	}

	public Clock(String clockName, Object ClockNotifier, int total, Marching march, int numSeat) { // Constructor Used
																									// To Set Thread
																									// Name
		this.clockName = clockName;
		this.ClockNotifier = ClockNotifier;
		this.total = total;
		this.march = march;
		this.numSeat = numSeat;
	}

	public final void setName(String clockNameToSet) {

		Thread.currentThread().setName(clockNameToSet); // Sets name of Clock

	}

	public void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public void run() {
		setName(clockName);
		march.readyPuppetShow();
		Thread staffMember = new Thread(new StaffMember(march, "Staff Member", StaffNotifier,numSeat));
		staffMember.start();
		msg("It is 11:00AM The Parade Has Started");

		try {
			//waiting();
			//setParadeOngoing!!!!!!
			Thread.sleep(1500);//"sleep" while they line up for parade
			releaseGroups();

			msg("It is 11:15AM. The First Show has Started");
			march.releasing(StaffNotifier);
			
			Thread.sleep(4500);
			msg("It is 12:00PM. The Second Parade has Started");
			
			msg("It is 1:00PM. The Third Parade has Started");
			
			msg("It is 2:00PM. The Fourth Parade has Started");
			
			msg("It is 3:00PM. The Fifth Parade has Started");
			
			msg("It is 3:00PM. The Final Parade has Started");
			// ClockNotifier.wait();
		} catch (InterruptedException e) {
			System.out.println("Error: The Clock is Broken. Please Call The Technictian!!");
		}
		/*
		 * while(true) { if (Marching.isParadeFilled())break;//Replace with wait in its
		 * own object then is signalled when filled? yes use static one }
		 */
		
		

	}

	public void waiting() {
		synchronized (ClockNotifier) {
			try {
				ClockNotifier.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void releaseGroups() {
		for (int i = 0; i < total; i++) {
			Marching.releaseParadeGroups();
		}
	}

}
