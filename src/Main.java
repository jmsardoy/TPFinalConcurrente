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
        Colas cola = new Colas(cantidad_transiciones)

        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());

        Monitor monitor = new Monitor(ProcesadorPetri, politica, cola);

        

    }
}
