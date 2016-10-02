
public class Consumidor implements Runnable {

    Monitor monitor;
    Matrix disp1 = new Matrix(new int[][]{{0,0,1,0}});
    Matrix disp2 = new Matrix(new int[][]{{0,0,0,1}});

    public Consumidor(Monitor monitor){this.monitor = monitor;}

    @Override
    public void run() {

        while(true){
            System.out.println("intento sacar de buffer");
            monitor.dispararTransicion(disp1);
            System.out.println("Saco buffer");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("intento consumir");
            monitor.dispararTransicion(disp2);
            System.out.println("Consumo.");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
