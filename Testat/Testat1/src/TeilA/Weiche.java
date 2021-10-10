package TeilA;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Weiche {

	private int size;
	private int[] lok;
	private int nextfree = 0;
	private int nextfull = 0;
	private Semaphore full = new Semaphore(0, true);
	private Semaphore empty;

	public Weiche(int size) {
		this.size = size;
		lok = new int[size];
		empty = new Semaphore(size, true);
	} //constructor

	public void enterLok0() {
		try {
			System.out.println("Lok0 möchte in die Mitte.");
			empty.acquire();
			lok[nextfree]++;
			nextfree = (nextfree + 1) % size;
			System.out.println("Lok0 ist in der Mitte.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} // enterLok0

	public void exitLok0() {
		System.out.println("Lok0 fährt aus der Mitte.");
		full.release();
	} //exitLok0

	public void enterLok1() {
		try {
			System.out.println("        Lok1 möchte in die Mitte.");
			full.acquire();
			lok[nextfull]--;
			nextfull = (nextfull + 1) % size;
			System.out.println("        Lok1 ist in der Mitte.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} //enterLok1

	public void exitLok1() {
		System.out.println("        Lok1 fährt aus der Mitte.");
		empty.release();
	} //exitLok1

	public static void main(String[] args) {
		Weiche w = new Weiche(1);
		Random random = new Random();
		Thread thread[] = {
			new Lok0(w, random.nextInt(1000)),
			new Lok1(w, random.nextInt(1000)),
		};
		for (Thread t : thread) {
			t.start();
		}
	} //main
} //class
