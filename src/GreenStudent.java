
public class GreenStudent implements Runnable {

	private Marching march = null;
	public String name;
	public Object Convey;
	public static long time = System.currentTimeMillis();
	public int numSeat;

	public GreenStudent(Marching march, String name, int numSeat) {
		this.march = march;
		this.name = name;
		this.numSeat = numSeat;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		// System.out.println(Thread.currentThread().getName());
		int waiting = march.letGreenInParade(); // add then get number to wait on.//have it return number needed after
												// add!!
		// ;
		march.paradeWaiting(waiting);
		// msg("I have entered the parade");
		march.walking();
		msg("I have exited the parade");
		
		//march.puppetShowWait();
		//march.watchingPuppetShow();
	}

	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

	public void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

}
