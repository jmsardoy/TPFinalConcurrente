
public class Carrito implements Runnable {

    Monitor monitor;
    int disparoActual, indiceActual;
    Matrix disparo;
    String name;

    private static final int CANTIDAD_DE_TRANSICIONES = 9;

    int transiciones[] = new int[]{3, 4 , 5 , 7, 8, 9};

    public Carrito(Monitor monitor, int comienzo, String name){

        this.monitor = monitor;
        disparoActual = comienzo;
        indiceActual = transicionAindice(comienzo);
        this.name = name;
    }

    @Override
    public void run() {
        while(true){

            System.out.println(name+" trata de disparar transicion "+disparoActual);
            int dispaux = disparoActual;
            disparo = disparoSiguiente();
            monitor.dispararTransicion(disparo);
            System.out.println(name+" dispara transicion "+dispaux);
            dispaux = disparoActual;



        }
    }

    private int indiceATransicion(int indice){return transiciones[indice];}
    private int transicionAindice(int transicion) {
        for(int i = 0;i<6;i++){
            if (transiciones[i] == transicion) {
                return i;
            }
        }
        return -1;
    }
    private void updateDisparo(){
        indiceActual++;
        disparoActual = transiciones[indiceActual];
    }

    private Matrix disparoSiguiente(){
        int vector[][] = new int[1][CANTIDAD_DE_TRANSICIONES];

        for(int i = 0; i<CANTIDAD_DE_TRANSICIONES; i++){
            vector[0][i] = 0;
        }
        vector[0][disparoActual] = 1;
        updateDisparo();
        Matrix disparo = new Matrix(vector);
        return disparo;
    }
}

