
class EjecutorDeTransiciones implements Runnable {

    Monitor monitor;
    int[] transiciones;
    int cantidad_de_transiciones;

    EjecutorDeTransiciones(Monitor monitor, int[] transiciones, int cantidad_de_transiciones) {
        this.monitor = monitor;
        this.transiciones = transiciones;
        this.cantidad_de_transiciones = cantidad_de_transiciones;
    }

    @Override
    public void run() {
        while(true){
            for(int i = 0; i<transiciones.length;i++){
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(transiciones[i],cantidad_de_transiciones));
            }
        }

    }
}
