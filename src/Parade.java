import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

//use two types of convey objects one for parade and one for show
public class Parade extends Object {
	private Vector shows = new Vector();  //make convey object too
	//Vectors keep track of who cant go in. the rest wait on object
	//Vectors are entry section?
	// with these defaults can create 7 groups
	//Orange creates convey object then two greens enter
	// or create enough for orange?
	//Convey objects in queue then have clock or staff pop off?
	//need to sync signals (only 3 needed per object)
	//check of they can enter parade numGreen/numOrange ===2?
	//Parade is critical section
	
	//multiple threads CAN wait on same object. Execute notify or notifyall!!!!!!!!!!!
	//.notify for order?
	
	//Look at how Readers Know they are done with CS!!!!!!
	//Set These in Marching using get/set!!!!!!!!
	private static int numGreen = 14;
	private static int numOrange = 7;
	private int numSeat = 6; // tent capacity
	//private int ParadeNumber;
	
	
	//Don't forget Age() method!!!
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Thread> orangeList = new ArrayList<Thread>();
		ArrayList<Thread> greenList = new ArrayList<Thread>();
		//setNumOrange();
		Marching march = new Marching();
		march.startParade(getNumOrange());
		Random random = new Random();
		int walkingTime = 2000+ random.nextInt(500);// for walking around 20 min
		
		Thread clockThread = new Thread(new Clock("Clock")); // Makes Clock Thread

		clockThread.start();

		/*
		 * for (int i = 0; i < numOrange; i++) { //Creates Each Thread With Array Used
		 * Earlier Thread orangeStudent = new Thread(new
		 * OrangeStudent(march,"orange "+i)); orangeList.add(orangeStudent); }
		 * 
		 * for (Thread orange :orangeList) orange.start(); // starts each thread in the
		 * visitor Array using For Each Loop
		 * 
		 * for (int i = 0; i < numGreen; i++) { //Creates Each Thread With Array Used
		 * Earlier Thread greenStudent = new Thread(new GreenStudent(march,"green "+i));
		 * orangeList.add(greenStudent); }
		 * 
		 * for (Thread green :greenList) green.start(); // starts each thread in the
		 * visitor Array using For Each Loop
		 */		
		Thread orangeStudent = new Thread(new OrangeStudent(march,"orange"));
		orangeStudent.start();
		Thread greenStudent = new Thread(new GreenStudent(march,"green"));
		greenStudent.start();
		
		Thread staffMember = new Thread(new StaffMember());

	}
	
	 public static long time = System.currentTimeMillis();
	 public void Age(String m) {
	 System.out.println("["+(System.currentTimeMillis()-time)+"] "+Thread.currentThread().getName()+": "+m);
	 }
	
	public int getNumGreen() {
		return numGreen;
	}
	public void setNumGreen(int numGreen) {
		this.numGreen = numGreen;
	}
	public int getNumSeat() {
		return numSeat;
	}
	public void setNumSeat(int numSeat) {
		this.numSeat = numSeat;
	}
	/*
	 * public int getParadeNumber() { return ParadeNumber; } public void
	 * setParadeNumber(int paradeNumber) { ParadeNumber = paradeNumber; }
	 */

	public static int getNumOrange() {
		return numOrange;
	}

	public static void setNumOrange(int numOrange) {
		Parade.numOrange = numOrange;
	}

}
