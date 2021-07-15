package task7;

public class WritingThread extends Thread{
    CommonResource resource;

    public WritingThread(CommonResource resource, String name){
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i = 0; i<5 ; i++){
            resource.writing(getName(),i);
        }
    }
}
