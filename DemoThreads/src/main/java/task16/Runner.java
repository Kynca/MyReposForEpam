package task16;


public class Runner {
    public static void main(String[] args) {

        CommonResource resource = new CommonResource();

        for (int i = 0; i < 10; i++){
            Thread taker = new Thread(new Taker(resource),"" + i);
            taker.start();
            if(i%2==0){
                Thread putter = new Thread(new Putter(resource),"" + i/2);
                putter.start();
            }
        }
    }

}
