package TeilA;

import java.util.concurrent.Semaphore;

public class Weiche {

  int size = 1; //steht für die Anzahl gemeinsamer Weichen
  private int[] lok = new int[size];
  private int nextfree = 0;
  private int nextfull = 0;
  private Semaphore full = new Semaphore(0, true); //Semaphore für Lok1 (Verbraucher)
  private Semaphore empty = new Semaphore(size, true); //Semaphore für Lok0 (Erzeuger)

  public Weiche() {} //constructor

  //enter und exit für die Aufgabenstellung auseinandergezogen
  public void enterLok0() { //Mitte wird erzeugt
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

  public void exitLok0() { //und freigegeben
    System.out.println("Lok0 fährt aus der Mitte.");
    full.release();
  } //exitLok0

  public void enterLok1() { //Mitte wird verbraucht
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

  public void exitLok1() { // und freigegeben
    System.out.println("        Lok1 fährt aus der Mitte.");
    empty.release();
  } //exitLok1
} //class
