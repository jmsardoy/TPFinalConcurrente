public class Matrix {

	int [][]dato;

	public Matrix(int fil,int col){
		dato=new int[fil][col];
	}
	
	public Matrix(int[][]dato){
		this.dato=dato;
	}

	public Matrix(int size) {
		dato=new int[size][size];
		
	}

	public int[][] getDato() {
		return dato;
	}
	public int getVal(int fil,int col){

		return dato[fil][col];

	}

	public void setVal(int fil, int col, int val){
		this.dato[fil][col] = val;
	}

	public void setMatrix(int[][] dato) {
		this.dato = dato;
	}

	public void setDato(int fil,int col,int dato) {

		this.dato[fil][col]=dato;
	}

	public int getFil() {
		return dato.length;

	}

	public int getCol() {
		return dato[0].length;
	}

	public Matrix copy(){
		int [][] datoNuevo = new int[dato.length][dato[0].length];
		for(int i = 0; i<dato.length;i++){
			for(int j = 0; j<dato[0].length;j++){
				datoNuevo[i][j] = this.dato[i][j];
			}
		}
		return new Matrix(datoNuevo);
	}
	public Matrix getCol(int col) {

		Matrix columna=new Matrix(this.getFil(),1);

		for(int i=0;i<this.getFil();i++)
		{
			columna.setDato(i,0, this.dato[i][col]);


		}
		return columna;
	}

	public void Clear(){

		for (int i=0;i<this.getFil();i++){
			for (int j=0;j<this.getCol();j++){
				this.setDato(i, j, 0);
			}
		}

	}
	public boolean isPos(){

		for (int i=0;i<this.getFil();i++){
			for (int j=0;j<this.getCol();j++){
				if(this.dato[i][j]<0)return false;
			}
		}

		return true;
	}
	// create and return the transpose of the invoking matrix
	public Matrix transpose() {
		Matrix A = new Matrix(this.getCol(), this.getFil());
		for (int i = 0; i < this.getFil(); i++)
			for (int j = 0; j < this.getCol(); j++)
				A.setDato(j, i,this.getDato()[i][j]);
		return A;
	}
	public String toString(){
		String texto="\n";
		for(int i=0; i<this.getFil(); i++){
			for(int j=0; j<this.getCol(); j++){
				texto+="\t "+this.dato[i][j];
			}
			texto+="\n";
		}
		texto+="\n";
		return texto;
	}
	// return C = A + B
	public Matrix plus(Matrix B) {
		Matrix A = this;
		if (B.getFil() != A.getFil() || B.getCol() != A.getCol()) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix C = new Matrix(getFil(), getCol());
		for (int i = 0; i < getFil(); i++)
			for (int j = 0; j < getCol(); j++)
				C.setDato(i,j,A.getDato()[i][j] + B.getDato()[i][j]);
		return C;
	}

	// return C = A - B
	public Matrix minus(Matrix B) {
		Matrix A = this;
		if (B.getFil() != A.getFil() || B.getCol() != A.getCol()) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix C = new Matrix(getFil(), getCol());
		for (int i = 0; i < getFil(); i++)
			for (int j = 0; j < getCol(); j++)
				C.setDato(i,j,A.getDato()[i][j] - B.getDato()[i][j]);
		return C;
	}
	// return C = A * B
	public Matrix mult(Matrix B) {
		Matrix A = this;
		if (A.getCol() != B.getFil()) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix C = new Matrix(A.getFil(), B.getCol());
		for (int i = 0; i < C.getFil(); i++)
			for (int j = 0; j < C.getCol(); j++)
				for (int k = 0; k < A.getCol(); k++)
					C.setDato(i,j,C.getDato()[i][j]+(A.getDato()[i][k] * B.getDato()[k][j]));
		return C;
	}

	public void setIdentity() {
		for (int i = 0; i < dato.length; i++) {
			dato[i][i]=1;
		}
	}

	public boolean isBinary(){
		for(int i = 0; i< this.getFil();i++){
			for(int j=0;j<this.getCol();j++){
				if(this.getVal(i,j)<0 || this.getVal(i,j)>1){
					return false;
				}
			}
		}
		return true;
	}
	public Matrix and(Matrix m){
		if(this.getFil()!=m.getFil() || this.getCol()!=m.getCol()){
			return null;
		}
		if(!this.isBinary() && !m.isBinary()){
			return null;
		}
		Matrix resultado = new Matrix(this.getFil(),this.getCol());
		for(int i = 0; i< this.getFil();i++){
			for(int j=0;j<this.getCol();j++){
				resultado.setVal(i,j, this.getVal(i,j)*m.getVal(i,j));
			}
		}
		return resultado;


	}

	public int matrixToIndex(){
		if(this.getFil()==1){
			for(int i = 0; i<this.getCol();i++){
				if(this.getVal(0,i) > 0){
					return i;
				}
			}
		}
		return -1;
	}

	public boolean isNull(){
		for(int i = 0; i< this.getFil();i++){
			for(int j = 0; j<this.getCol();j++){
				if(this.getVal(i,j) != 0) return false;
			}
		}
		return true;
	}

	public Matrix not(){
		if(!this.isBinary()) throw new RuntimeException("Matrix Not Binary.");

		Matrix nuevaMatrix = new Matrix(this.getFil(), this.getCol());
		for (int i = 0; i < this.getFil(); i++) {
			for (int j = 0; j < this.getCol(); j++) {
				if (this.getVal(i, j) == 1){
					nuevaMatrix.setVal(i,j,0);
				}
				else{
					nuevaMatrix.setVal(i,j,1);
				}
			}
		}
		return nuevaMatrix;

	}
}