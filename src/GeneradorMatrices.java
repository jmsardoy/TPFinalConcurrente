/**
 * Created by fede on 02/09/16.
 */

import java.io.*;
import java.util.ArrayList;

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
                for (numeroColumna = 0; numeroColumna < fila.length; numeroColumna++) {
                    int valor = Integer.parseInt(fila[numeroColumna]);
                    mat[numeroLinea][numeroColumna] = valor;
                }
                matriziti = new Matrix(numeroLinea + 1, numeroColumna);
                for (int i = 0; i <= numeroLinea; i++)
                    for (int j = 0; j < numeroColumna; j++) {
                        matriziti.setDato(i, j, mat[i][j]);
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matriziti;
    }

    Tiempo cargarTiempo() {

        /*Abro el archivo*/
        int t_ini = 0, t_fin = 0, transicion = 0;
        Intervalo intv;
        ArrayList<Intervalo> infoTiempo = new ArrayList<Intervalo>();
        Tiempo tiempo;

        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\") + 1, path.length()) + "/tiempo.rdp";
        File archivo2 = new File(path);

        /*Lo leo y cargo en el array de intervalos*/
        try {
            lector = new BufferedReader((new FileReader(archivo2)));
            linea = null;
            while ((linea = lector.readLine()) != null) {
                t_ini = 0;
                t_fin = 0;
                String[] fila = linea.split("-");
                t_ini = Integer.parseInt(fila[0]);
                t_fin = Integer.parseInt(fila[1]);

                intv = new Intervalo(transicion, t_ini, t_fin);
                infoTiempo.add(transicion, intv);
                transicion++;
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        /*creo tiempo y lo devuelvo*/
        tiempo = new Tiempo(infoTiempo);
        return tiempo;
    }





    public Matrix cargarArchivo(String String_archivo){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File archivo = new File(path);
        path = archivo.getParent();
        path = path.substring(path.lastIndexOf("\\")+1,path.length())+String_archivo;
        File archivo2 = new File(path);
        Matrix matrix = cargarDatos(archivo2);
        return matrix;
    }

    public Matrix cargarIncidencia(){
       return cargarArchivo("/incidencia.rdp");
    }

    public Matrix cargarMarcado(){
        return cargarArchivo("/marcado.rdp");
            }

    public Matrix cargarPolitica(){
        return cargarArchivo("/politica.rdp");
    }

    public Matrix cargarInhibidores() {
        return cargarArchivo("/inhibidores.rdp");
    }

    public Matrix cargarLectores(){
        return cargarArchivo("/lectores.rdp");
    }

    public Matrix cargarTransicionesAutomaticas(){
        return cargarArchivo("/transaut.rdp");
    }



}
