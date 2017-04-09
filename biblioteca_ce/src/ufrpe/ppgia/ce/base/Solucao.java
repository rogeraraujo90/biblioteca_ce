package ufrpe.ppgia.ce.base;

public interface Solucao <T> {
	public void setValor(int indice, T valor);
	
	public T getValor(int indice);
	
	public double getFitness();
	
	public void setFitness(double valor);
	
	public T getLimiteInferior(int indice);
	
	public T getLimiteSuperior(int indice);
	
	public void setLimiteInferior(int indice, T valor);
	
	public void setLimiteSuperior(int indice, T valor);
	
	public int getN();
}
