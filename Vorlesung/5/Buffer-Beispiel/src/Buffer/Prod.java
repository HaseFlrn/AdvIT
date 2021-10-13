package Buffer;
public class Prod implements Runnable{
    BB1 bb;
    public Prod(BB1 bb){
        this.bb = bb;
    }//constructor

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            bb.append("Data" + i);
        }
        
    }//run
    
}//class
