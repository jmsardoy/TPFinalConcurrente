/**
 * Created by juanso on 02/09/16.
 */
public class Politica {
    Matrix prioridades;

    public Politica(Matrix p){
        if(p.getFil()>1){
            System.out.println("El vector debe tener 1 sola dimension");
        }else{
            this.prioridades = p;
        }
    }

    public Matrix getPrioridades(){
        return prioridades;
    }

    public void setPrioridades(Matrix p){
        if(p.getFil()>1){
            System.out.println("El vector debe tener 1 sola dimension");
        }else{
            this.prioridades = p;
        }
    }

    public Matrix getMaxPrioridad(Matrix transiciones){
        Matrix prioridadesAux = this.prioridades.and(transiciones);
        int indiceMax = -1;
        int valorMax = 0;
        for(int i=0; i<prioridadesAux.getCol();i++){
            if(prioridadesAux.getVal(0,i)>valorMax){
                indiceMax = i;
                valorMax = prioridadesAux.getVal(0,i);
            }
        }
        if(indiceMax<0){
            return null;
        }
        else{
            Matrix transicion = new Matrix(1,prioridadesAux.getCol());
            transicion.setVal(0,indiceMax,1);
            return transicion;
        }
    }
}


