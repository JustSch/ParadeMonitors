
public class GreenStudent implements Runnable {

	private Marching march = null;
	public String name;
	public Object Convey;

	public GreenStudent(Marching march, String name) {
		this.march = march;
		this.name = name;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		// System.out.println(Thread.currentThread().getName());
		int waiting = march.letGreenInParade(); //add then get number to wait on.//have it return number needed after add!!
		march.paradeWaiting(waiting);
		System.out.println("well");
		march.letInPuppetShow();
		//march.sitDown();
		
		//each has own convey for not in puppet show.
		//ones that get through wait for end 
		//ones that dont wait on convey!!!!
		//let in puppetshow
		//wait on monitor if cant
		//other wise go in monitor in vector and wait
		// march.exitParade();
	}

	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

}
