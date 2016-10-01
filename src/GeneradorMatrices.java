/**
 * Created by fede on 02/09/16.
 */

import java.io.*;

public class GeneradorMatrices {

    private String linea;
    private BufferedReader lector = null;
    private File archivo = null;
    private int[][] mat;

    public GeneradorMatrices() {

    }

    public Matrix cargarDatos(File archivo) {
        mat = new int[100][100];
        int numeroLinea = -1;
        int numeroColumna = 0;
        Matrix matriziti = null;
        try {
            lector = new BufferedReader((new FileReader(archivo)));
            linea = null;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                String[] fila = linea.split(" ");
                for(numeroColumna=0; numeroColumna<fila.length; numeroColumna++){
                    int valor = Integer.parseInt(fila[numeroColumna]);
                    mat[numeroLinea][numeroColumna] = valor;
                }
                matriziti = new Matrix(numeroLinea+1, numeroColumna);
                for(int i = 0;i<=numeroLinea;i++)
                    for(int j = 0;j<numeroColumna;j++){
                        matriziti.setDato(i,j,mat[i][j]);
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matriziti;
    }

    public Matrix cargarIncidencia(){
        archivo = new File("incidencia.rdp");
        Matrix incidencia_aux = cargarDatos(archivo);
        return incidencia_aux;
    }

    public Matrix cargarMarcado(){
        archivo = new File("marcado.rdp");
        Matrix marcado_aux = cargarDatos(archivo);
        return marcado_aux;
    }

    public Matrix cargarPolitica(){
        archivo = new File("politica.rdp");
        Matrix politica_aux = cargarDatos(archivo);
        return politica_aux;
    }
}
