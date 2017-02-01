
public class Monitor {
    private Politica prioridad;
    private ProcesadorPetriTiempo pro_petri;
    private Colas colas;
    private Matrix automaticas;


    private Mutex mutex = new Mutex();

    public Monitor(ProcesadorPetriTiempo pro_petri, Politica prioridad, Colas colas, Matrix automaticas){

        this.pro_petri = pro_petri;
        this.prioridad = prioridad;
        this.colas = colas;
        this.automaticas = automaticas;

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
                    pro_petri.imprimirMarcado();
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

    public void dispararTransicionConTiempo(Matrix transicion) {

        boolean ejecute = false;
        boolean ejecute_independientes = false;
        boolean desperto = false;
        boolean ejecuto_tiempo = false;
        Matrix sensibilizadas, dormidos, resultadoAnd, proxima;
        System.out.println("quiere entrar al monitor: " + transicion.matrixToIndex());
        while (!ejecuto_tiempo) {
            mutex.lock();
            try {
                while (!ejecute) {   //hasta que termine de ejecutar
                    long resultado_disparo = pro_petri.dispararConTiempo(transicion); //trato de disparar
                    System.out.println("intento disparar: " + transicion.matrixToIndex() + " -- resultado disparo: " +resultado_disparo);
                    if (resultado_disparo == 0) { //disparo exitoso
                        pro_petri.imprimirMarcado();
                        while (!ejecute_independientes) {
                            sensibilizadas = pro_petri.getSensibilizadas(); //veo las sensibilizadas
                            dormidos = colas.getDormidos();                 //veo las dormidas
                            resultadoAnd = sensibilizadas.and(dormidos);    //hago el and para saber cual despertar
                            if (!resultadoAnd.isNull()) {                     //si tengo alguien que despertar
                                proxima = prioridad.getMaxPrioridad(resultadoAnd);  //me fijo segun prioridades cual
                                colas.despertar(proxima.matrixToIndex());    //despierto ese hilo
                                System.out.println("despierto transicion: "+proxima.matrixToIndex());
                                desperto = true;
                                ejecute_independientes = true;                      // no ejecuto mas independientes
                            } else {      //si no tengo nadie que desperatar
                                resultadoAnd = sensibilizadas.and(automaticas);     //veo si hay transiciones automaticas
                                if (!resultadoAnd.isNull()) {                         //si tengo automaticas
                                    proxima = prioridad.getMaxPrioridad(resultadoAnd);  //veo cual es la siguiente

                                    //fijarse si las automaticas pueden tener tiempo y si hace falta chequear
                                    // el resultado de este disparo.
                                    pro_petri.dispararConTiempo(proxima);           //la disparo
                                    System.out.println("disparo automatica: "+ proxima.matrixToIndex());
                                } else {
                                    ejecute_independientes = true;
                                }               //si no hay mas automaticas salgo
                            }
                        }
                        ejecuto_tiempo = true;
                        ejecute = true;
                    } else if (resultado_disparo == -1) { //disparo no posible por sensibilizacion
                        mutex.unlock(); //devuelvo lock
                        colas.dormir(transicion.matrixToIndex());//me duermo en la cola
                    } else { //disparo no posible por los tiempos
                        mutex.unlock();
                        Thread.sleep(resultado_disparo);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!desperto) mutex.unlock();
            }
        }
    }
}
