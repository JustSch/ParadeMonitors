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
		setName(name);
		Random random = new Random();
		int walkingTime = 2000 + random.nextInt(500);// for walking around 20 min
		while (march.isParadeOngoing()) {
			int waiting = march.letGreenInParade();  
			march.paradeWaiting(waiting%totalParadeGroups); //used to place itself in proper place in parade vector
			if(!march.isParadeOngoing()) break;
		
			try {
				Thread.sleep(walkingTime);
				if(!march.isParadeOngoing()) break;
				march.walking();
			} catch (InterruptedException e1) {
				msg("I didn't like the parade so I went home");
			}
			if(!march.isParadeOngoing()) break;
			msg("I have exited the parade");
			if(!march.isParadeOngoing()) break;
			try {
				msg("I Have Taken a Snack Break");
				Thread.sleep(walkingTime);
				if(!march.isParadeOngoing()) break;
			} catch (InterruptedException e) {
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

	public void msg(String m) {  //Generic Message Method
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

}
