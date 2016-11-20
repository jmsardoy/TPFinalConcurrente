/**
 * Created by juanso on 02/09/16.
 */
public class Main {
    public static void main(String[] args){
        GeneradorMatrices gen = new GeneradorMatrices();

        Matrix incidencia = gen.cargarIncidencia();
        Matrix marcado = gen.cargarMarcado();
        Matrix politicas = gen.cargarPolitica();
        Matrix inhibidores = gen.cargarInhibidores();
        Matrix lectores = gen.cargarLectores();
        //Matrix transaut = gen.cargarTransicionesAutomaticas();
        Tiempo tiempo = gen.cargarTiempo();

        int cantidad_transiciones = incidencia.getCol();

        Politica politica = new Politica(politicas);
        ProcesadorPetri proce = new ProcesadorPetri(incidencia,marcado, inhibidores, lectores);
        Colas cola = new Colas(cantidad_transiciones);

        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());
        System.out.println("Politicas = \n"+politicas.toString());
        System.out.println("Inhibidores = \n"+inhibidores.toString());
        System.out.println("Lectores = \n"+lectores.toString());
        //System.out.println("Tiempo= \n"+tiempo.toString());
        //System.out.println("Transiciones automaticas = \n"+transaut.toString());

        Monitor monitor = new Monitor(proce, politica, cola);

        Thread tre1 = new Thread(new Carrito(monitor, 3, "Carrito 1"),"Carrito 1");
        Thread tre2 = new Thread(new Carrito(monitor, 7,"Carrito 2"),"Carrito 2");
        Thread tre3 = new Thread((new BombaDeAgua(monitor)));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tre1.start();
        tre2.start();
        tre3.start();

    }
}
