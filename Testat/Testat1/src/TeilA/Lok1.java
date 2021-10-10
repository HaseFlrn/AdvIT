package TeilA;

public class Lok1 extends Thread {

	Weiche w;
	long speed;

	public Lok1(Weiche w, long speed) {
		this.w = w;
		System.out.println("Geschwindigkeit Lok1: " + speed);
		this.speed = speed;
	} //constructor

	public void fahren() {
		try {
			System.out.println("        Lok1 fährt.");
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} //fahren

	@Override
	public void run() {
		while (true) {
			fahren();
			w.enterLok1();
			fahren();
			w.exitLok1();
		}
	} //run
} //class
