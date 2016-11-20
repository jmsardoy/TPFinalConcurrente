
public class BombaDeAgua implements Runnable {

    Monitor monitor;
    Matrix disp = new Matrix(new int[][]{{1,0,0,0,0,0,0,0,0}});

    public BombaDeAgua(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){


            System.out.println("Trato cargar aguita.");
            monitor.dispararTransicionConTiempo(disp);
            System.out.println("Cargue aguita.");


        }
    }


}

