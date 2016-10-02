import java.util.concurrent.Semaphore;

public class Mutex {
    Semaphore semaphore;
    int numeroDeBloqueados = 0;

    public Mutex(){
        this.semaphore = new Semaphore(1,true);
    }

    public void lock(){
        try {
            if(this.semaphore.availablePermits() <= 0){
                numeroDeBloqueados++;
            }
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock(){
        if(this.numeroDeBloqueados>0){
            this.numeroDeBloqueados--;
        }
        this.semaphore.release();
    }

    public int getNumeroDeBloqueados() {
        return numeroDeBloqueados;
    }

    public boolean tieneDormidos(){
        if(numeroDeBloqueados>0) return true;
        else return false;
    }
}