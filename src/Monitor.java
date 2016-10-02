import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Politica prioridad;
    private ProcesadorPetri pro_petri;
    private Colas colas;
    private Matrix automaticas = new Matrix(new int[][]{{0,0,0,0}}); //TEMPORAL


    private Mutex mutex = new Mutex();

    public Monitor(ProcesadorPetri pro_petri, Politica prioridad, Colas colas){

        this.pro_petri = pro_petri;
        this.prioridad = prioridad;
        this.colas = colas;

    }

    public void dispararTransicion(Matrix transicion){

        boolean ejecute = false;
        boolean ejecute_independientes = false;
        boolean desperto = false;
        Matrix sensibilizadas, dormidos,resultadoAnd, proxima ;



        mutex.lock();

        try{
            while(!ejecute) {   //hasta que termine de ejecutar
                if (pro_petri.disparar(transicion)) {       //trato de disparar mi transision
                    while(!ejecute_independientes) {       //
                        sensibilizadas = pro_petri.getSensibilizadas(); //veo las sensibilizadas
                        dormidos = colas.getDormidos();                 //veo las dormidas
                        resultadoAnd = sensibilizadas.and(dormidos);    //hago el and para saber cual despertar
                        if(!resultadoAnd.isNull()){                     //si tengo alguien que despertar
                            proxima = prioridad.getMaxPrioridad(resultadoAnd);  //me fijo segun prioridades cual
                            colas.despertar(proxima.matrixToIndex());    //despierto ese hilo
                            desperto = true;
                            ejecute_independientes = true;                      // no ejecuto mas independientes
                        }else{      //si no tengo nadie que desperatar
                            resultadoAnd = sensibilizadas.and(automaticas);     //veo si hay transiciones automaticas
                            if(!resultadoAnd.isNull()){                         //si tengo automaticas
                                proxima = prioridad.getMaxPrioridad(resultadoAnd);  //veo cual es la siguiente
                                pro_petri.disparar(proxima);                    //la disparo
                            }else{ejecute_independientes = true;}               //si no hay mas automaticas salgo
                        }
                    }
                    ejecute = true;

                } else {    //sino pudde mi transicion
                    mutex.unlock(); //devuelvo lock
                    colas.dormir(transicion.matrixToIndex());//me duermo en la cola
                }
            }
        }finally {
            if(!desperto) mutex.unlock();
        }
    }




}
