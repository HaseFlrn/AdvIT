package TeilB;

public class Lok extends Thread {

  private int lokId;
  private Weiche w;
  private long speed;

  public Lok(int lokId, Weiche w, long speed) {
    this.lokId = lokId;
    this.w = w;
    this.speed = speed;
  }

  private void fahren() {
    try {
      System.out.println("Lok" + lokId + " fährt");
      Thread.sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void mitteFahren() {
    try {
      System.out.println("Lok" + lokId + " fährt in der Mitte");
      Thread.sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      fahren();
      System.out.println("Lok" + lokId + " möchte in die Mitte");
      w.enter(lokId);
      mitteFahren();
      System.out.println("Lok" + lokId + " verlässt die Mitte");
      w.exit(lokId);
    }
  }
}
