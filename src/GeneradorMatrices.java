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
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/incidencia.rdp";
        File archivo2 = new File(path);
        Matrix incidencia_aux = cargarDatos(archivo2);
        return incidencia_aux;
    }

    public Matrix cargarMarcado(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/marcado.rdp";
        File archivo2 = new File(path);
        Matrix marcado_aux = cargarDatos(archivo2);
        return marcado_aux;
    }

    public Matrix cargarPolitica(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/politica.rdp";
        File archivo2 = new File(path);
        Matrix politica_aux = cargarDatos(archivo2);
        return politica_aux;
    }

    public Matrix cargarInhibidores(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/inhibidores.rdp";
        File archivo2 = new File(path);
        Matrix inhibidores_aux = cargarDatos(archivo2);
        return inhibidores_aux;
    }
    public Matrix cargarLectores(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/lectores.rdp";
        File archivo2 = new File(path);
        Matrix lectores_aux = cargarDatos(archivo2);
        return lectores_aux;
    }

    public Matrix cargarTransicionesAutomaticas(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+"/transaut.rdp";
        File archivo2 = new File(path);
        Matrix transaut_aux = cargarDatos(archivo2);
        return transaut_aux;
    }
}
