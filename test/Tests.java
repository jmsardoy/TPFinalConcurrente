import org.junit.Test;

public class Tests {
    GeneradorMatrices gen = new GeneradorMatrices();

    Matrix incidencia = gen.cargarIncidencia();
    Matrix marcado = gen.cargarMarcado();
    Matrix politicas = gen.cargarPolitica();
    Matrix inhibidores = gen.cargarInhibidores();
    Matrix lectores = gen.cargarLectores();
    Matrix transaut = gen.cargarTransicionesAutomaticas();
    Tiempo tiempo = gen.cargarTiempo();


    int cantidad_de_transiciones = incidencia.getCol();;
    Politica politica = new Politica(politicas);

    Colas cola = new Colas(cantidad_de_transiciones);

    VariablesExternas variables = new VariablesExternas();

    MineControlView view = MineControlView.get_instance(variables);

    Logger logger = new Logger("log.txt");


    ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado, inhibidores, lectores, tiempo,logger);

    @Test
    public void testPrendidoBomba(){
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);

        
    }
}

