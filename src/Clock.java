
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
	
	public void run(){
		
		while(true) {
			if (Marching.isParadeFilled())break;//Replace with wait in its own object then is signalled when filled?
		}
		
		
	}

}
