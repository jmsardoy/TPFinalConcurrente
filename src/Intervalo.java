
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

    public int getTransicion() {
        return transicion;
    }

    public int getT_ini() {
        return t_ini;
    }

    public int getT_fin() {
        return t_fin;
    }

    public long getT_stamp() {
        return t_stamp;
    }

    public boolean estaCorriendo() {
        return corriendo;
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

}
