public class MyThread2 implements Runnable{
    private int id;
    public MyThread2 (int id){
        this.id = id;
    }

    public void run(){
        try {
            Thread.sleep((int) Math.random() * 1000);
        } catch (Exception e) {}
        System.out.println("Hello World (ID=" + id +")");
    }

    public static void main(String[] args) {
        for(int i=1; i<10; i++){
            MyThread2 runnable = new MyThread2(i);
            Thread t = new Thread(runnable);
            t.start();
        }   
    }
}