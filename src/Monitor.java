/**
 * Created by fede on 01/10/16.
 */
public class Monitor {
    private Politica prioridad;
    private ProcesadorPetri pro_petri;
    private Colas colas;


    public Monitor(ProcesadorPetri pro_petri, Politica prioridad, Colas colas){

        this.pro_petri = pro_petri;
        this.prioridad = prioridad;
        this.colas = colas;


    }






}
