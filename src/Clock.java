
public class Clock implements Runnable{
	
	
	public static long time = System.currentTimeMillis();
	public String clockName;
	
	public Clock() {

	}
	public Clock(String clockName) { // Constructor Used To Set Thread Name

		setName(clockName);
		this.clockName = clockName;
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
			Thread.sleep(1500);
			msg("It is 12:15PM. The First Show haas Started");
			
		} catch (InterruptedException e) {
			System.out.println("Error: The Clock is Broken. Please Call The Technictian!!");
		}
		while(true) {
			if (Marching.isParadeFilled())break;//Replace with wait in its own object then is signalled when filled? yes use static one
		}
		
		
		
	}

}
