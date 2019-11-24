
public class StaffMember implements Runnable {

	private Marching march = null;
	public String name;
	public Object StaffNotifier;
	public static long time = System.currentTimeMillis();
	public int numSeat;

	public StaffMember(Marching march, String name, Object StaffNotifier, int numSeat) {
		this.march = march;
		this.name = name;
		this.StaffNotifier = StaffNotifier;
		this.numSeat = numSeat;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub //While loop for paradesinSession and ShowsInSession!!!
		setName(name);
		msg("The Puppet Show Will Start Soon: Opening Tent");
		march.waiting(StaffNotifier);
		//march.
		try {
			openingCurtain();
		}
		catch(Exception e) {
			msg("The Students Were too slow to see the parade at this time");
		}
		msg("The Puppet Show Is Starting: Closing Tent");
		
		
		msg("The Puppet Show Has Ended: 6 More Students May Enter");
		

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
	
	public void openingCurtain() {
		for (int i=0; i<numSeat;i++) {
			march.letInPuppetShow();
		}
	}
	
	public void guideToExit() {
		for (int j=0;j<numSeat;j++) {
			march.puppetRelease();
		}
	}

}
