import java.util.concurrent.Semaphore;

/**
 * Created by juanso on 01/10/16.
 */
public class ColaSimple {
    Semaphore semaphore;
    int numeroDeBloqueados = 0;

    public ColaSimple(){
        this.semaphore = new Semaphore(0,true);
    }

    public void encolar(){
        try {
            if(this.semaphore.availablePermits() <= 0){
                numeroDeBloqueados++;
            }
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void liberar(){
        if(this.numeroDeBloqueados>0){
            this.numeroDeBloqueados--;
            this.semaphore.release();
        }
    }

    public int getNumeroDeBloqueados() {
        return numeroDeBloqueados;
    }

    public boolean tieneDormidos(){
        if(numeroDeBloqueados>0) return true;
        else return false;
    }
}
