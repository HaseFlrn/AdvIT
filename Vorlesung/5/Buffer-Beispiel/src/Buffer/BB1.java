package Buffer;
import java.util.concurrent.Semaphore;

public class BB1 {
    private int size;
    private String[] buffer;
    private int ctr = 0;
    private int nextfree = 0;
    private int nextfull = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore full = new Semaphore(0, true);
    private Semaphore empty;

    public BB1(int size){
        this.size = size;
        buffer = new String[size];
        empty = new Semaphore(size, true);
    }//constructor

    public void append(String data){
        try {
            System.out.println("Prod arriving");
            empty.acquire();
            mutex.acquire();
            System.out.println("Prod active with " + data);
            buffer[nextfree] = data;
            nextfree = (nextfree + 1) % size;
            ctr++;
            mutex.release();
            full.release();
            System.out.println("Prod gone");
        } catch (InterruptedException e) {e.printStackTrace();}
    }//append

    public String remove(){
        String data = "";
        try {
            System.out.println("<========Cons arriving");
            full.acquire();
            mutex.acquire();
            System.out.println("<========Cons active");
            data = buffer[nextfull];
            nextfull = (nextfull + 1) % size;
            ctr--;
            mutex.release();
            empty.release();
            System.out.println("<========Cons gone with " + data);
        } catch (InterruptedException e) {e.printStackTrace();}
        return data;
    }//remove


    public static void main(String[] args) {
        BB1 bb = new BB1(5);
        new Thread( new Cons(bb) ).start();
        new Thread( new Prod(bb) ).start();
    }//main

}//class
