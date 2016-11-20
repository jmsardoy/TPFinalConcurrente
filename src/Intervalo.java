
public class Intervalo {

    int transicion;
    int t_ini;
    int t_fin;
    long t_stamp;
    boolean corriendo;

    public Intervalo(int transicion, int t_ini, int t_fin) {
        this.transicion = transicion;
        this.t_ini = t_ini;
        this.t_fin = t_fin;
    }

    public void setT_stamp() {
        if(!this.corriendo) {
            this.t_stamp = System.currentTimeMillis();
            this.corriendo = true;
        }
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }

    public boolean puedeDisparar(){
        if(this.corriendo){
            long current_time = System.currentTimeMillis() - this.t_stamp;
            if(this.t_ini <= current_time && current_time <= this.t_fin){
                return true;
            }
        }
        return false;
    }

    public long getSleepTime(){
        long current_time = System.currentTimeMillis() - this.t_stamp;
        if(current_time < t_ini){
            return (t_ini - current_time);
        }
        return 0;
    }

}
