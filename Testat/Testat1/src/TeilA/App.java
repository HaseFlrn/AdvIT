package TeilA;

import java.util.Random;

public class App {

  public static void main(String[] args) {
    Weiche w = new Weiche();
    Random random = new Random();
    long speedLok0 = random.nextInt(1000); //Lok0 sleep in millis
    long speedLok1 = random.nextInt(1000); //Lok1 sleep in millis
    Thread thread[] = { new Lok0(w, speedLok0), new Lok1(w, speedLok1) };
    for (Thread t : thread) {
      t.start();
    }
  } //main
}
