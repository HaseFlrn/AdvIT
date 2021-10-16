package TeilB;

public class Lok extends Thread {

  private int lokId;
  private Weiche w; //gemeinsames Weichenobjekt
  private long speed; //Durchfahrdauer in Millisekunden
  private String spaces = "";

  public Lok(int lokId, Weiche w, long speed) {
    this.lokId = lokId;
    if (lokId == 1) spaces = "        "; //zur Veranschaulichung in der Ausgabe
    this.w = w;
    this.speed = speed;
    System.out.println("Geschwindigkeit von Lok" + lokId + ": " + speed);
  } //constructor

  private void fahren() {
    try {
      System.out.println(spaces + "Lok" + lokId + " fährt");
      Thread.sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } //fahren

  private void mitteFahren() {
    try {
      System.out.println(spaces + "Lok" + lokId + " fährt in der Mitte");
      Thread.sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  } //mitteFahren

  @Override
  public void run() {
    while (true) {
      fahren();
      System.out.println(spaces + "Lok" + lokId + " möchte in die Mitte");
      w.enter(lokId);
      mitteFahren();
      System.out.println(spaces + "Lok" + lokId + " verlässt die Mitte");
      w.exit(lokId);
    }
  } //run
} //class
