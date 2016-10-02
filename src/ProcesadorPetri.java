
public class ProcesadorPetri {

	
	// Matriz de incidencia y marcado
	Matrix I, marcado;
	
	public ProcesadorPetri(Matrix incidencia, Matrix marcaInicial)
	{
		I = incidencia;
		marcado = marcaInicial;
	}
	
	synchronized public boolean disparar (Matrix disparo){
		
		Matrix multiplicacion = I.mult(disparo.transpose());		//resultado de I*d
		
		Matrix nuevoMarcado = marcado.plus(multiplicacion.transpose());		//resultado de m + I*d

		//se chequea si el marcado resultante es positivo.
		//en caso de ser positivo se actualiza el marcado y devuelve true
		//en caso de tener algun elemento negativo devuelve false y no actualiza
		//el marcado.
		if(nuevoMarcado.isPos()){
			marcado = nuevoMarcado;
			System.out.println("disparo exitoso");
			return true;
		}
		else{
			System.out.println("disparo no posible");
			return false;
		}
	}
	
	synchronized public void imprimirMarcado(){
		System.out.println("Marcado: "+ marcado.toString() );	
	}

	synchronized public Matrix getSensibilizadas(){
		Matrix sensibilizadas = new Matrix(1,I.getCol());
		for(int j = 0; j<I.getCol();j++){
			Matrix nuevoMarcado = marcado.copy();
			for(int i =0; i<I.getFil();i++){
				if(I.getVal(i,j)< 0){
					nuevoMarcado.setVal(0,j,nuevoMarcado.getVal(0,i)+I.getVal(i,j));
				}
			}
			if(nuevoMarcado.isPos()){
				sensibilizadas.setVal(0,j,1);
			}
			else{
				sensibilizadas.setVal(0,j,0);
			}
		}
		return sensibilizadas;
	}
	
}
