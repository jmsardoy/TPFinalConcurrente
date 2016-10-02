/**
 * Created by fede on 01/10/16.
 */
public class Productor implements Runnable {

    Monitor monitor;
    Matrix disp1 = new Matrix(new int[][]{{1,0,0,0}});
    Matrix disp2 = new Matrix(new int[][]{{0,1,0,0}});

    public Productor(Monitor monitor){this.monitor = monitor;}

    @Override
    public void run() {

        while(true){
            System.out.println("intento producir");
            monitor.dispararTransicion(disp1);
            System.out.println("Produzco.");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("intento meter en buffer");
            monitor.dispararTransicion(disp2);
            System.out.println("Meto buffer.");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
