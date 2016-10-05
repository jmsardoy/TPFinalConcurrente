
public class ProcesadorPetri {

	
	// Matriz de incidencia y marcado
	Matrix I, marcado, inhibidores;
	
	public ProcesadorPetri(Matrix incidencia, Matrix marcaInicial, Matrix inhibidores)
	{
		this.I = incidencia;
		this.marcado = marcaInicial;
		this.inhibidores = inhibidores;
	}
	
	synchronized public boolean disparar (Matrix disparo){
		
		Matrix sensibilizadas = this.getSensibilizadas();
        if(!disparo.and(sensibilizadas).isNull()){
            Matrix multiplicacion = I.mult(disparo.transpose());
            this.marcado = this.marcado.plus(multiplicacion.transpose());
            System.out.println("disparo exitoso");
            return true;
        }
        else{
            System.out.println("disparo no posible");
            return false;
        }
	    /*
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
		}*/
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

		// deshabilitadas = inhibidores * sensibilizadas
		Matrix deshabilitadas = inhibidores.transpose().mult(sensibilizadas.transpose());
		sensibilizadas = this.deshabilitar(sensibilizadas,deshabilitadas).transpose();

		return sensibilizadas;
	}

	private Matrix deshabilitar(Matrix sensibilizadas, Matrix deshabilitadas){
		if ( (deshabilitadas.getFil() != sensibilizadas.getFil()) ||
                (deshabilitadas.getCol() != 1 )||
                (sensibilizadas.getCol() != 1) ) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }

		Matrix nuevoSensibilizado = new Matrix(sensibilizadas.getFil(),1);
		for(int i = 0; i< sensibilizadas.getFil(); i++){
		    if(sensibilizadas.getVal(i,1) == 1 && deshabilitadas.getVal(i,1) == 0){
		        nuevoSensibilizado.setVal(i,1,1);
            }
            else{
                nuevoSensibilizado.setVal(i,1,0);
            }
		}
		return nuevoSensibilizado;
	}
	
}
