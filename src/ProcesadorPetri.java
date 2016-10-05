
public class ProcesadorPetri {

	
	// Matriz de incidencia y marcado
	Matrix I, marcado, inhibidores, lectores;
	
	public ProcesadorPetri(Matrix incidencia, Matrix marcaInicial, Matrix inhibidores, Matrix lectores)
	{
		this.I = incidencia;
		this.marcado = marcaInicial;
		this.inhibidores = inhibidores;
        this.lectores = lectores;
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

	}
	
	synchronized public void imprimirMarcado(){
		System.out.println("Marcado: "+ marcado.toString() );	
	}

	synchronized public Matrix getSensibilizadas(){
		Matrix sensibilizadas = new Matrix(1, I.getCol());
        for(int i = 0; i<I.getCol();i++){
            Matrix disparoAux = new Matrix(1,I.getCol());
            disparoAux.setVal(0,i,1);
            Matrix multiplicacion = I.mult(disparoAux.transpose());		//resultado de I*d
            Matrix nuevoMarcado = marcado.plus(multiplicacion.transpose());
            if(nuevoMarcado.isPos()){
                sensibilizadas.setVal(0,i,1);
            }
            else{
                sensibilizadas.setVal(0,i,0);
            }
        }

		// deshabilitadas = inhibidores * sensibilizadas
		Matrix deshabilitadas = inhibidores.transpose().mult(marcado.transpose()).transpose();
		sensibilizadas = sensibilizadas.and(deshabilitadas.not());
        //sensibilizadas = this.deshabilitar(sensibilizadas.transpose(),deshabilitadas).transpose();

        Matrix lectoresHabilitados = lectores.transpose().mult(marcado.transpose()).transpose();
        sensibilizadas = sensibilizadas.transpose().and(lectoresHabilitados.transpose());

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
		    if(sensibilizadas.getVal(i,0) == 1 && deshabilitadas.getVal(i,0) == 0){
		        nuevoSensibilizado.setVal(i,0,1);
            }
            else{
                nuevoSensibilizado.setVal(i,0,0);
            }
		}
		return nuevoSensibilizado;
	}
	
}
