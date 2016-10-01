import java.util.ArrayList;

/**
 * Created by juanso on 01/10/16.
 */
public class Colas {
    ArrayList<ColaSimple> colas = new ArrayList<ColaSimple>();
    Matrix colasOcupadas;
    int cantidadDeColas;

    public Colas(int cantidadDeTransiciones){
        if(cantidadDeTransiciones<0){
            System.out.println("La cantidad de transiciones no puede ser negativa");
        }
        else{
            this.cantidadDeColas = cantidadDeTransiciones;
            this.colasOcupadas = new Matrix(new int[1][cantidadDeTransiciones]);
            for(int i = 0; i<cantidadDeTransiciones;i++){
                this.colas.add(new ColaSimple());
            }
        }
    }

    public void dormir(int index){
        colas.get(index).encolar();
    }
    public void despertar(int index){
        colas.get(index).liberar();
    }

    public ArrayList<ColaSimple> getColas(){
        return colas;
    }

    public Matrix getDormidos(){
        Matrix dormidos = new Matrix(1,this.cantidadDeColas);
        for(int i = 0; i< cantidadDeColas;i++){
            if(colas.get(i).tieneDormidos()) {
                dormidos.setVal(0, i, 1);
            }
        }
        return dormidos;
    }

    public void print(){
        System.out.println(cantidadDeColas);
        for(int i = 0; i<this.cantidadDeColas;i++){
            System.out.println(colas.get(i).toString());
        }
        System.out.println(colasOcupadas.toString());

    }
}
