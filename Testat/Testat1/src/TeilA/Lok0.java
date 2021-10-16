package TeilA;

public class Lok0 extends Thread {

  Weiche w;
  long speed; //Durchfahrdauer in Millisekunden

  public Lok0(Weiche w, long speed) {
    this.w = w;
    System.out.println("Geschwindigkeit Lok0: " + speed);
    this.speed = speed;
  } //constructor

  public void fahren() {
    try {
      System.out.println("Lok0 fährt.");
      Thread.sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } //fahren

  @Override
  public void run() {
    while (true) {
      fahren();
      w.enterLok0();
      fahren();
      w.exitLok0();
    }
  } //run
} //class
