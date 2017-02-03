import com.github.rjeschke.txtmark.Run;

public class SensorCH4 implements Runnable{
    int leerNivel = 3;
    int nivelAlto = 4;
    int nivelBajo = 5;
    int alarmaNivelAlto = 7;
    Monitor monitor;
    int cantidad_de_transiciones;
    VariablesExternas variables;

    public SensorCH4(Monitor monitor, VariablesExternas variables, int cantidad_de_transiciones) {
        this.monitor = monitor;
        this.variables = variables;
        this.cantidad_de_transiciones = cantidad_de_transiciones;
    }

    @Override
    public void run() {
        while(true) {
            monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(leerNivel, cantidad_de_transiciones));
            if (variables.getCH4()) {
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(nivelAlto, cantidad_de_transiciones));
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(alarmaNivelAlto, cantidad_de_transiciones));
            } else {
                monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(nivelBajo, cantidad_de_transiciones));
            }
        }
    }
}
