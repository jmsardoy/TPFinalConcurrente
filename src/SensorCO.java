/**
 * Created by juanso on 06/12/16.
 */
public class SensorCO implements Runnable{
    int leerNivel = 37;
    int nivelAlto = 38;
    int nivelBajo = 39;
    int alarmaNivelAlto = 41;
    Monitor monitor;
    int cantidad_de_transiciones;

    public SensorCO(Monitor monitor, int cantidad_de_transiciones) {
        this.monitor = monitor;
        this.cantidad_de_transiciones = cantidad_de_transiciones;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("tratando de leer CO");
            monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(leerNivel, cantidad_de_transiciones));
            System.out.println("tratando de leer CO");
            if (Math.random() > 0.7) {
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(nivelAlto, cantidad_de_transiciones));
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(alarmaNivelAlto, cantidad_de_transiciones));
            } else {
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(nivelBajo, cantidad_de_transiciones));
            }
        }
    }
}
