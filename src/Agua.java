import java.util.concurrent.Semaphore;

public class Agua implements Runnable{
    int agua = 0;
    Semaphore mutex = new Semaphore(1,true);

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agua = agua + 1;
            mutex.release();
        }
    }

    public void bombear(){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agua = agua -1;
        mutex.release();
    }

    public int getAgua(){
        System.out.println("agua: " + agua);
        return agua;
    }
}
