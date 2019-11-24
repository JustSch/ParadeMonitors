
public class OrangeStudent implements Runnable {
	private Marching march = null;
	public String name;
	public static long time = System.currentTimeMillis();
	public int numSeat;
	public int mySeat;
	public int totalParadeGroups;

	public OrangeStudent(Marching march, String name, int numSeat, int totalParadeGroups) {
		// TODO Auto-generated constructor stub
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
		while (march.isParadeOngoing()) {
			int waiting = march.letOrangeInParade();
			/*
			 * try { march.paradeWaiting(waiting); } catch (Exception e) {
			 * msg("Im lining up for the parade"); }
			 */
			march.paradeWaiting(waiting%totalParadeGroups);
			msg(String.valueOf(march.isParadeOngoing()));
			if(!march.isParadeOngoing()) break;
			march.walking();
			if(!march.isParadeOngoing()) break;
			
			msg("I have exited the parade");

			try {
				msg("I Have Taken a Snack Break");
				Thread.sleep(1000);
				if(!march.isParadeOngoing()) break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				msg("I didn't like the parade so I went home");
			}

			

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
