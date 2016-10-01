/**
 * Created by juanso on 02/09/16.
 */
public class Main {
    public static void main(String[] args){
        GeneradorMatrices gen = new GeneradorMatrices();
        Matrix incidencia = gen.cargarIncidencia();
        Matrix marcado = gen.cargarMarcado();
        ProcesadorPetri proce = new ProcesadorPetri(incidencia,marcado);
        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());

    }
}
