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

        int cantidad_transiciones = incidencia.getCol();

        Politica politica = new Politica(politicas);
        ProcesadorPetriTiempo proce = new ProcesadorPetriTiempo(incidencia,marcado, inhibidores, lectores, tiempo);
        Colas cola = new Colas(cantidad_transiciones);

        System.out.println("Incidencia = \n"+incidencia.toString());
        System.out.println("Marcado = \n"+marcado.toString());
        System.out.println("Politicas = \n"+politicas.toString());
        System.out.println("Inhibidores = \n"+inhibidores.toString());
        System.out.println("Lectores = \n"+lectores.toString());
        System.out.println("Transiciones automaticas = \n"+transaut.toString());
        tiempo.imprimir();


        Monitor monitor = new Monitor(proce, politica, cola, transaut);


        int cantidad_de_transiciones = 42;

        /*Agua agua = new Agua();
        Thread aguaThread = new Thread(agua);
        Thread prendidoDeBomba = new Thread(new EjecutorDeTransiciones(monitor,new int[]{19,20},cantidad_de_transiciones));
        */Thread TimeOutCH4 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{2,6},cantidad_de_transiciones));
        /*Thread TimeOutCO = new Thread(new EjecutorDeTransiciones(monitor,new int[]{36,40},cantidad_de_transiciones));
        */Thread generadorSensorCH4 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{0},cantidad_de_transiciones));
        /*Thread generadorSensorCO = new Thread(new EjecutorDeTransiciones(monitor,new int[]{34},cantidad_de_transiciones));
        Thread paradaPorGas = new Thread(new EjecutorDeTransiciones(monitor,new int[]{21,23},cantidad_de_transiciones));
        Thread paradaPorApagar = new Thread(new EjecutorDeTransiciones(monitor,new int[]{22,24},cantidad_de_transiciones));
        Thread carrito1 = new Thread(new EjecutorDeTransiciones(monitor,new int[]{27,28,29,30,31,32},cantidad_de_transiciones));
        Thread carrito2= new Thread(new EjecutorDeTransiciones(monitor,new int[]{30,31,32,27,28,29},cantidad_de_transiciones));
        Thread BombaDeAgua = new Thread(new BombaDeAgua(monitor,cantidad_de_transiciones,agua));
        Thread SensorDeAgua =  new Thread(new SensorDeAgua(monitor,agua,cantidad_de_transiciones));
        */Thread SensorCH4 = new Thread(new SensorCH4(monitor,cantidad_de_transiciones));
        //Thread SensorCO = new Thread(new SensorCO(monitor,cantidad_de_transiciones));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*aguaThread.start();
        prendidoDeBomba.start();
        */TimeOutCH4.start();
        /*TimeOutCO.start();
        */generadorSensorCH4.start();
        /*generadorSensorCO.start();
        paradaPorGas.start();
        paradaPorApagar.start();
        carrito1.start();
        carrito2.start();
        BombaDeAgua.start();
        SensorDeAgua.start();
        */SensorCH4.start();
        //SensorCO.start();

        /*
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
        */
    }
}
