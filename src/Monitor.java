import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Politica prioridad;
    private ProcesadorPetri pro_petri;
    private Colas colas;
    private Matrix automaticas = new Matrix(new int[][]{{0,1,0,0,0,1,0,0,0}}); //TEMPORAL


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
                        System.out.println("veo si hay dormidas");
                        sensibilizadas = pro_petri.getSensibilizadas(); //veo las sensibilizadas
                        dormidos = colas.getDormidos();                 //veo las dormidas
                        resultadoAnd = sensibilizadas.and(dormidos);    //hago el and para saber cual despertar
                        System.out.println("dormidos: " + dormidos.toString());
                        System.out.println("sensibilizadas: " + sensibilizadas.toString());
                        System.out.println("resultadoAND: " + resultadoAnd.toString());
                        colas.print();
                        if(!resultadoAnd.isNull()){                     //si tengo alguien que despertar
                            System.out.println("Despierto alguien.");//TEMPORAL
                            proxima = prioridad.getMaxPrioridad(resultadoAnd);  //me fijo segun prioridades cual
                            colas.despertar(proxima.matrixToIndex());    //despierto ese hilo
                            desperto = true;
                            ejecute_independientes = true;                      // no ejecuto mas independientes
                        }else{      //si no tengo nadie que desperatar
                            System.out.println("intento automaticas");//TEMPORAL
                            pro_petri.imprimirMarcado();
                            resultadoAnd = sensibilizadas.and(automaticas);     //veo si hay transiciones automaticas
                            if(!resultadoAnd.isNull()){                         //si tengo automaticas
                                proxima = prioridad.getMaxPrioridad(resultadoAnd);  //veo cual es la siguiente
                                pro_petri.disparar(proxima);                    //la disparo
                                System.out.println("Disparo automatico.");//TEMPORAL
                                System.out.println("ejecute independientes: " + ejecute_independientes);
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
