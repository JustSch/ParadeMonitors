
public class GreenStudent implements Runnable {

	private Marching march = null;
	public String name;
	public GreenStudent(Marching march, String name) {
		this.march = march;
		this.name = name;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		System.out.println(Thread.currentThread().getName());
		march.letGreenInParade();
		
		
		march.exitParade();
	}
	
	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

}
