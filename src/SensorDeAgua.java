
public class SensorDeAgua implements Runnable {
    int nivelAlto = 8;
    int nivelBajo = 9;
    int prender = 10;
    int apagar = 11;
    Monitor monitor;
    int cantidad_de_transiciones;
    VariablesExternas variables;

    public SensorDeAgua(Monitor monitor, VariablesExternas variables, int cantidad_de_transiciones) {
        this.monitor = monitor;
        this.variables = variables;
        this.cantidad_de_transiciones = cantidad_de_transiciones;
    }

    @Override
    public void run() {
        while(true) {
            if (variables.getAgua()) {
                monitor.dispararTransicionConTiempo(Matrix. indexToMatrix(nivelAlto, cantidad_de_transiciones));
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(prender, cantidad_de_transiciones));
            } else {
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(nivelBajo, cantidad_de_transiciones));
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(apagar, cantidad_de_transiciones));
            }
        }

    }
}
