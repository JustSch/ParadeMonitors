
public class OrangeStudent implements Runnable {
	private Marching march = null;
	public String name;
	
	
	public OrangeStudent(Marching march, String name) {
		// TODO Auto-generated constructor stub
		this.march = march;
		this.name = name;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setName(name);
		System.out.println(Thread.currentThread().getName());
		march.letOrangeInParade();
		
		
		march.exitParade();
		
		//enterPuppetShow/More parades
		
	}
	
	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

}
