package TeilB;

public class App {

  public static void main(String[] args) {
    Weiche w = new Weiche(2);
    Thread t[] = { new Lok(0, w, 950), new Lok(1, w, 1000) };
    for (Thread thread : t) {
      thread.start();
    }
  }
}
