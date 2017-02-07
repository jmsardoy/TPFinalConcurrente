import org.junit.Test;
import static org.junit.Assert.assertTrue;


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
        System.out.println("\nTest Prendido de Bomba");
        Matrix marcado_inicial = new Matrix(new int[][]{
            {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        System.out.println("Marcado: "+marcado_inicial.toString());

        Matrix marcado_esperado = new Matrix(new int[][]{
            {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado_inicial, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);

        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(8,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(10,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(20,cantidad_de_transiciones));

        assertTrue(marcado_esperado.isEquals(proce.getMarcado()));

    }

    @Test
    public void testApagadoBomba(){
        System.out.println("\nTest Apagado de Bomba");
        Matrix marcado_inicial = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        System.out.println("Marcado: "+marcado_inicial.toString());

        Matrix marcado_esperado = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado_inicial, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);

        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(9,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(11,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(22,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(24,cantidad_de_transiciones));

        assertTrue(marcado_esperado.isEquals(proce.getMarcado()));

    }

    @Test
    public void testParadoPorGas(){
        System.out.println("\nTest Parado por Gas");
        Matrix marcado_inicial = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        System.out.println("Marcado: "+marcado_inicial.toString());

        Matrix marcado_esperado = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,1}});
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado_inicial, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);

        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(0,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(3,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(4,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(7,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(21,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(23,cantidad_de_transiciones));

        assertTrue(marcado_esperado.isEquals(proce.getMarcado()));

    }

    @Test
    public void testNoArrancarPorGas(){
        System.out.println("\nTest No Arrancar por Gas");
        Matrix marcado_inicial = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0}});
        System.out.println("Marcado: "+marcado_inicial.toString());

        Matrix marcado_esperado = new Matrix(new int[][]{
                {1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,1}});
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado_inicial, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);

        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(8,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(10,cantidad_de_transiciones));
        monitor.dispararTransicionConTiempo(Matrix.indexToMatrix(20,cantidad_de_transiciones));

        assertTrue(marcado_esperado.isEquals(proce.getMarcado()));

    }

}

