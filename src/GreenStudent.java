import java.util.Random;

public class GreenStudent implements Runnable {

	private Marching march = null;
	public String name;
	public Object Convey;
	public static long time = System.currentTimeMillis();
	public int numSeat;
	public int totalParadeGroups;

	public GreenStudent(Marching march, String name, int numSeat, int totalParadeGroups) {
		this.march = march;
		this.name = name;
		this.numSeat = numSeat;
		this.totalParadeGroups = totalParadeGroups;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		// System.out.println(Thread.currentThread().getName());
		Random random = new Random();
		int walkingTime = 2000 + random.nextInt(500);// for walking around 20 min
		while (march.isParadeOngoing()) {
			int waiting = march.letGreenInParade(); // add then get number to wait on.//have it return number needed
													// after
													// add!!
			// ;
			/*
			 * try { march.paradeWaiting(waiting); } catch (Exception e) {
			 * msg("Im lining up for the parade"); }
			 */
			march.paradeWaiting(waiting%totalParadeGroups);
			if(!march.isParadeOngoing()) break;
			// msg("I have entered the parade");
			march.walking();
			try {
				Thread.sleep(walkingTime);
			} catch (InterruptedException e1) {
				msg("I didn't like the parade so I went home");
			}
			if(!march.isParadeOngoing()) break;
			msg("I have exited the parade");
			try {
				msg("I Have Taken a Snack Break");
				Thread.sleep(walkingTime);
				if(!march.isParadeOngoing()) break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				msg("I didn't like the parade so I went home");
			}

			// enterPuppetShow/More parades

			march.puppetShowWait();
			if(!march.isParadeOngoing()) break;
			march.watchingPuppetShow();
			if(!march.isParadeOngoing()) break;
		}
		msg("i am going home now");
	}

	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

	public void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

}
