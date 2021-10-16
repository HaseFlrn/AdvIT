package TeilB;

import java.util.concurrent.Semaphore;

public class Weiche {

  private boolean weicheFrei = true;
  private int turn = 0;
  private int lokAnzahl = 2;
  private Semaphore mutex = new Semaphore(1, true);
  private boolean[] waiting = new boolean[lokAnzahl]; //Verwaltung, welche Loks warten
  private Semaphore[] privesem = new Semaphore[lokAnzahl]; // private Semaphore
  private static final int NO_ID = -1; //undefinierte Thread-ID

  public Weiche() {
    for (int i = 0; i < lokAnzahl; i++) {
      waiting[i] = false;
      privesem[i] = new Semaphore(0, true);
    }
  } //constructor

  //enter und exit entsprechend der Aufgabenstellung auseinandergezogen
  public void enter(int lokId) {
    try {
      mutex.acquire();
      if (lokId == turn && weicheFrei) { //Weiche reservieren
        weicheFrei = false;
        privesem[lokId].release(); // später kein warten notwendig
      } else {
        waiting[lokId] = true;
      }
      mutex.release();
      privesem[lokId].acquire(); //ggf warten
      turn = (turn + 1) % lokAnzahl;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } //enter

  public void exit(int lokId) {
    try {
      mutex.acquire();
      weicheFrei = true;
      int selectedLok = NO_ID;
      for (int i = 0; i < lokAnzahl; i++) {
        if (waiting[i] && i == turn) {
          selectedLok = i;
        }
      }
      if (selectedLok != NO_ID) {
        waiting[selectedLok] = false;
        weicheFrei = false;
        privesem[selectedLok].release();
      }
      mutex.release();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } //exit
} //class
