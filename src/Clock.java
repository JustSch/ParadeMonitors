
public class Clock implements Runnable{
	
	
	public static long time = System.currentTimeMillis();
	public String clockName;
	public Object ClockNotifier = new Object();
	
	public Clock() {

	}
	public Clock(String clockName,Object ClockNotifier) { // Constructor Used To Set Thread Name

		setName(clockName);
		this.clockName = clockName;
		this.ClockNotifier=ClockNotifier;
	}

	public final void setName(String clockNameToSet) {

		Thread.currentThread().setName(clockNameToSet); // Sets name of Clock
		
	}
	
	public synchronized void Age() {
		long time = System.currentTimeMillis();
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+time);
	}
	 public void msg(String m) {
	 System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+m);
	 }
	public void run(){
		
		msg("It is 12:00PM The Parade Has Started");
		
		try {
			waiting();
			
			Thread.sleep(1500);
			Marching.releaseParadeGroups(0);
			msg("It is 12:15PM. The First Show has Started");
			//ClockNotifier.wait();
		} catch (InterruptedException e) {
			System.out.println("Error: The Clock is Broken. Please Call The Technictian!!");
		}
		/*
		 * while(true) { if (Marching.isParadeFilled())break;//Replace with wait in its
		 * own object then is signalled when filled? yes use static one }
		 */
		
		
		
	}
	
	public void waiting() {
		synchronized(ClockNotifier) {
			try {
				ClockNotifier.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
