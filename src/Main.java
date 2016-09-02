/**
 * Created by juanso on 02/09/16.
 */
public class Main {
    public static void main(String[] args){
        int [][] i = {{-1,1},{1,-1}};
        Matrix I = new Matrix(i);
        int [][] m = {{1,0}};
        Matrix M = new Matrix(m);
        ProcesadorPetri petri = new ProcesadorPetri(I,M);
        int [][] t1 = {{1,0}};
        int [][] t2 = {{0,1}};
        Matrix disparo1 = new Matrix(t1).transpose();
        Matrix disparo2 = new Matrix(t2).transpose();

    }
}
