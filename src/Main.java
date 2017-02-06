public class Main {
    public static void main(String[] args){
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

        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());
        System.out.println("Politicas = \n"+politicas.toString());
        System.out.println("Inhibidores = \n"+inhibidores.toString());
        System.out.println("Lectores = \n"+lectores.toString());
        System.out.println("Transiciones automaticas = \n"+transaut.toString());
        tiempo.imprimir();
        System.out.println("Marcado = \n"+marcado.toString());

        VariablesExternas variables = new VariablesExternas();
        MineControlView view = MineControlView.get_instance(variables);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger logger = new Logger("log.txt");
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado, inhibidores, lectores, tiempo,logger);
        Monitor monitor = new Monitor(proce, politica, cola, transaut,view);





        Thread TimeOutCH4 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{2,6},cantidad_de_transiciones));
        Thread generadorSensorCH4 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{0},cantidad_de_transiciones));
        Thread SensorCH4 = new Thread(new SensorCH4(monitor,variables,cantidad_de_transiciones));
        Thread SensorDeAgua =  new Thread(new SensorDeAgua(monitor,variables,cantidad_de_transiciones));
        Thread BombaDeAgua = new Thread(new EjecutorDeTransiciones(monitor,new int[]{25},cantidad_de_transiciones));
        Thread TimeOutCO = new Thread(new EjecutorDeTransiciones(monitor,new int[]{36,40},cantidad_de_transiciones));
        Thread generadorSensorCO = new Thread(new EjecutorDeTransiciones(monitor,new int[]{34},cantidad_de_transiciones));
        Thread SensorCO = new Thread(new SensorCO(monitor,variables,cantidad_de_transiciones));
        Thread prendidoDeBomba = new Thread(new EjecutorDeTransiciones(monitor,new int[]{20},cantidad_de_transiciones));
        Thread paradaPorApagar = new Thread(new EjecutorDeTransiciones(monitor,new int[]{22,24},cantidad_de_transiciones));
        Thread paradaPorGas = new Thread(new EjecutorDeTransiciones(monitor,new int[]{21,23},cantidad_de_transiciones));
        Thread carrito1 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{27,28,29,30,31,32},cantidad_de_transiciones));
        Thread carrito2= new Thread(new EjecutorDeTransiciones(monitor,new int[]{30,31,32,27,28,29},cantidad_de_transiciones));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TimeOutCH4.start();
        generadorSensorCH4.start();
        SensorCH4.start();

        SensorDeAgua.start();
        BombaDeAgua.start();
        paradaPorApagar.start();
        prendidoDeBomba.start();
        paradaPorGas.start();



        carrito1.start();
        carrito2.start();

        TimeOutCO.start();
        generadorSensorCO.start();
        SensorCO.start();

    }
}
