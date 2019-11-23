
public class OrangeStudent implements Runnable {
	private Marching march = null;
	public String name;
	public static long time = System.currentTimeMillis();
	public int numSeat;

	public OrangeStudent(Marching march, String name, int numSeat) {
		// TODO Auto-generated constructor stub
		this.march = march;
		this.name = name;
		this.numSeat = numSeat;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		// System.out.println(Thread.currentThread().getName());
		
		int waiting = march.letOrangeInParade();
		
		march.paradeWaiting(waiting);
		msg("I have entered the parade");
		msg("I have exited the parade");

		//System.out.println("ddddd");
		// march.exitParade();

		// enterPuppetShow/More parades

	}

	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}
	public void msg(String m) {
		 System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+m);
		 }

}
