/**
 * Created by juanso on 02/09/16.
 */
public class Main {
    public static void main(String[] args){
        GeneradorMatrices gen = new GeneradorMatrices();

        Matrix incidencia = gen.cargarIncidencia();
        Matrix marcado = gen.cargarMarcado();
        Matrix politicas = gen.cargarPolitica();

        int cantidad_transiciones = incidencia.getCol();

        Politica politica = new Politica(politicas);
        ProcesadorPetri proce = new ProcesadorPetri(incidencia,marcado);
        Colas cola = new Colas(cantidad_transiciones);

        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());
        System.out.println("Politicas = \n"+politicas.toString());

        Monitor monitor = new Monitor(proce, politica, cola);

        Thread tre1 = new Thread(new Productor(monitor));
        Thread tre2 = new Thread(new Consumidor(monitor));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tre1.start();
        tre2.start();

    }
}
