import java.util.ArrayList;

public class Tiempo {

    ArrayList<Intervalo> infoTiempo;

    public Tiempo(ArrayList<Intervalo> infoTiempo) {
        this.infoTiempo = infoTiempo;
    }

    int getTimeIni(int transicion){
        //si no anda buscar por transicion dentro de la clase intervalo
        return infoTiempo.get(transicion).getT_ini();
    }

    int getTimeFin(int transicion){
        return infoTiempo.get(transicion).getT_fin();
    }

    long getTimeStamp(int transicion){
        return infoTiempo.get(transicion).getT_stamp();
    }

    void setTimeStamp(int transicion){
        infoTiempo.get(transicion).setT_stamp();
    }

    void updateTimeStamps(Matrix sensibilizadas){
        for(int i = 0; i<infoTiempo.size(); i++){
            if(sensibilizadas.getVal(0,i)){

            }
        }
    }


}
