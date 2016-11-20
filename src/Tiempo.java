import java.util.ArrayList;

public class Tiempo {

    ArrayList<Intervalo> infoTiempo;

    public Tiempo(ArrayList<Intervalo> infoTiempo) {
        this.infoTiempo = infoTiempo;
    }

    void updateTimeStamps(Matrix sensibilizadas){
        for(int i = 0; i<infoTiempo.size(); i++){
            if(sensibilizadas.getVal(0,i)==1){
                infoTiempo.get(i).setT_stamp();
            }
            else{
                infoTiempo.get(i).setCorriendo(false);
            }
        }
    }

    boolean puedeDisparar(int transicion){
        return infoTiempo.get(transicion).puedeDisparar();

    }

    long getSleepTime(int transicion){
        return infoTiempo.get(transicion).getSleepTime();
    }


}
