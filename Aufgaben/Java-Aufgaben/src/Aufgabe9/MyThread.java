package Aufgabe9;

public class MyThread extends Thread {

  private int begin;
  private int end;
  private int[] zahlen;
  public int summe;

  public MyThread(int[] zahlen, int begin, int end) {
    this.zahlen = zahlen;
    this.begin = begin;
    this.end = end;
  }

  public void run() {
    int summe = 0;
    for (int i = begin; i < end; i++) {
      summe += zahlen[i];
    }
    this.summe = summe;
  }

  public static void main(String[] args) {
    int l = 20997152;
    int[] zahlen = new int[l];
    for (int i = 0; i < zahlen.length; i++) {
      zahlen[i] = i + 1;
    }
    int n = 32;
    Thread[] t = new Thread[n];
    for (int i = 1; i <= n; i++) {
      int begin = (i - 1) * (l / n);
      int end = i * (l / n) - 1;
      t[i - 1] = new MyThread(zahlen, begin, end);
    }
    long timeSum = 0;
    int sum = 0;
    for (Thread th : t) {
      th.start();
      try {
        long time = System.currentTimeMillis();
        th.join();
        time = System.currentTimeMillis() - time;
        timeSum += time;
        System.out.println("Zeit: " + time);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      MyThread mt = (MyThread) th;
      sum += mt.summe;
    }
    System.out.println("Summe: " + sum);
    System.out.println("ZeitSumme: " + timeSum);
  }
}
