public class WorkerMonitor {

  private boolean activeWriter = false;
  private boolean activeReader = false;
  private int writectr = 0;
  private int readctr = 0;

  public synchronized void startReading() {
    while (activeWriter || writectr > 0) {
      try {
        wait();
      } catch (InterruptedException e) {}
    }
    activeReader = true;
    readctr++;
  }

  public synchronized void stopReading() {
    readctr--;
    if (readctr == 0) {
      activeReader = false;
    }
    notifyAll();
  }

  public synchronized void startWriting() {
    writectr++;
    while (activeWriter || activeReader) {
      try {
        wait();
      } catch (InterruptedException e) {}
    }
    activeWriter = true;
  }

  public synchronized void stopWriting() {
    writectr--;
    if (writectr == 0) {
      activeWriter = false;
    }
    notifyAll();
  }
}
