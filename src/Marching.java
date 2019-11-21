import java.util.Vector;

public class Marching {
	//two green one orange in parade
	//orange goes first and allows 2 greens?
	//Create enough convey objects for each group?
	//private Vector greenInParade = new Vector(); //make convey object too
	//private Vector redInParade = new Vector();
	private static Vector<Object> paradeGroups = new Vector<Object>();
	private int greenStudents = 0;//has two
	private int orangeStudents=0; //has one
	private boolean hasOrange=false;
	private static boolean paradeFilled;
	private static int paraders;
	private static int paradersEntered =0;
	private static int paradeGroupsFormed=0;// use this to tell where in vector to form next group Have mod to go back in parade?
	private static Object ClockNotifier = new Object();   //No things can keep being added to vector!!!
	
	
	public Marching() {
		
	}
	
	//creates convey parade objects to pass through later
	//Warning if can't create enough?
	public void startParade(int paradeNumber) {
		for (int i =0;i<paradeNumber;i++) {
			Object convey = new Object();
			paradeGroups.add(convey);
			//paraders=paradeNumber;
		}
		
	}

	public synchronized void Age() {
		long time = System.currentTimeMillis();
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+time);
	}
	
	//Let Thread in based by name? yes can get thread name
	//Creates then passes through?
	
	//Orange Enters To Keep Track of where orange should go -Increment as needed
	//Green Enters
	//Split into letOrangeIn/letGreenIn
	public void letInParade() {
		Object convey = new Object();
		convey=paradeGroups.get(paradersEntered);//needs convey before synch!!!!!
		paradersEntered++;     //need to add one before for mod to work
		synchronized(convey) { //replace with 
			//synchronized(this)  { ?
			//Does this need to be passed in or it just chooses from vector?
			
			/*
			 * if (!isParadeFilled() && paradersEntered!=paraders) {
			 * 
			 * paraders++; } else setParadeFilled(true)
			 */;
			 //Have start at 1?
			 if (paradersEntered%3==0) {
				 paradeGroupsFormed++;
			 }
			 paradersEntered++;//Use this to figure out which convey is passed
			System.out.println(Thread.currentThread().getName().substring(0,1));
			if (Thread.currentThread().getName().substring(0,1).contentEquals("o")) {
				//hasOrange = true;
				//Critical Section!!
				//System.out.println("here");
				letOrangeInParade(convey);	//pass in or calculate from vector?				
			}
					
			
			//For Greens Here
			if (Thread.currentThread().getName().substring(0,1).contentEquals("g"))
				letGreenInParade(convey);	
		}
		
	}
	//Similar for puppet theater?
	public synchronized void exitParade() {
		for(Object convey : paradeGroups) {
			for(int i =0;i<3;i++)
				convey.notify();
		}
	}
	
	public synchronized void letOrangeInParade(Object convey) {
		while(true) {
			try {
				System.out.println(Thread.currentThread().getName());
				orangeStudents++;
				paradersEntered++;
				//System.out.println("f");
				//convey = 
				if (Thread.currentThread().getName().substring(0,1).contentEquals("g"))System.out.println("test");
				convey.wait(); //paradeGroups.get(paradersEntered).wait(); can work too?
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public synchronized void letGreenInParade(Object convey) {
		if (greenStudents !=2) {
			greenStudents++;
			System.out.println("there");
			while (true) {
				try {
					greenStudents++;
					convey.wait();
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}
	
	public void anotherParade() {
		
		
	}
	public static boolean isParadeFilled() {
		return paradeFilled;
	}

	public static void setParadeFilled(boolean paradeFilled) {
		Marching.paradeFilled = paradeFilled;
	}

	public static Object getClockNotifier() {
		return ClockNotifier;
	}

	public static void setClockNotifier(Object clockNotifier) {
		ClockNotifier = clockNotifier;
	}
	
}
