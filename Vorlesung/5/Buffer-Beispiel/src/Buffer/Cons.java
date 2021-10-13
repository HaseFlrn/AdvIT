package Buffer;
public class Cons implements Runnable{
    BB1 bb;
    public Cons(BB1 bb){
        this.bb = bb;
    }//constructor

    @Override
    public void run() {
        String data;
        while (true){
            data = bb.remove();
        }
        
    }//run
    
}//class
