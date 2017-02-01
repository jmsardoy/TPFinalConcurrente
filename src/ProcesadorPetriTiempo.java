
public class ProcesadorPetriTiempo extends ProcesadorPetri {

    Tiempo tiempo;

    public ProcesadorPetriTiempo(Matrix incidencia, Matrix marcaInicial, Matrix inhibidores,
                                 Matrix lectores, Tiempo tiempo) {
        super(incidencia, marcaInicial, inhibidores, lectores);
        this.tiempo = tiempo;
        tiempo.updateTimeStamps(this.getSensibilizadas());

    }

    public synchronized long dispararConTiempo(Matrix disparo) {

        Matrix sensibilizadas = this.getSensibilizadas();
        if(!disparo.and(sensibilizadas).isNull()){
            //esta sensibilizada
            if(tiempo.puedeDisparar(disparo.matrixToIndex())){

                //puede disparar
                Matrix multiplicacion = I.mult(disparo.transpose());
                this.marcado = this.marcado.plus(multiplicacion.transpose());
                //System.out.println("disparo exitoso");
                tiempo.setCorriendo(disparo.matrixToIndex(), false);
                tiempo.updateTimeStamps(this.getSensibilizadas());
                return 0;
            }
            else{
                //no puede disparar por el tiempo
                //System.out.println("disparo no posible por tiempos");
                return tiempo.getSleepTime(disparo.matrixToIndex());
            }
        }
        else{
            //no puede disparar porque no esta sensibilizada
            //System.out.println("disparo no posiblie por sensibilizacion");
            return -1;
        }
    }
}
