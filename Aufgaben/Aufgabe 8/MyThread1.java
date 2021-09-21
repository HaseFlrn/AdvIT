public class MyThread1 extends Thread{
    private int id;
    public MyThread1 (int id){
        this.id = id;
    }

    public void run(){
        try {
            Thread.sleep((int) Math.random() * 1000);
        } catch (Exception e) {}
        System.out.println("Hello World (ID=" + id +")");
    }

    public static void main(String[] args) {
        Thread[] t = new Thread[10]; 
        for(int i=1; i<10; i++){
            t[i] = new MyThread1(i);
            t[i].start();
        }
        for(int i=1; i<10; i++){
            try {
                t[i].join();
            } catch (Exception e) {}    
        }   
        System.out.println("Alle fertig");
    }
    
}