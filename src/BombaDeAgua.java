
public class BombaDeAgua implements Runnable {

    Monitor monitor;
    int disparo = 25;
    int cantidad_de_transiciones;
    Agua agua;

    public BombaDeAgua(Monitor monitor, int cantidad_de_transiciones, Agua agua){
        this.monitor = monitor;
        this.cantidad_de_transiciones = cantidad_de_transiciones;
        this.agua = agua;
    }

    @Override
    public void run() {
        while(true){

            //System.out.println("Trato cargar aguita.");
            monitor.dispararTransicion(Matrix.indexToMatrix(25,cantidad_de_transiciones));
            //System.out.println("Cargue aguita.");
            agua.bombear();

        }
    }


}

