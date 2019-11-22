
public class StaffMember implements Runnable {

	private Marching march = null;
	public String name;
	public Object StaffNotifier;
	public static long time = System.currentTimeMillis();

	public StaffMember(Marching march, String name, Object StaffNotifier) {
		this.march = march;
		this.name = name;
		this.StaffNotifier = StaffNotifier;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		msg("The Puppet Show Will Start Soon: Opening Tent");
		march.waiting(StaffNotifier);
		
		msg("The Puppet Show Is Starting: Closing Tent");
	}
	
	
	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}
	public void msg(String m) {
		 System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+m);
		 }
	
	public void waiting() {
		synchronized(StaffNotifier) {
			try {
				StaffNotifier.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
