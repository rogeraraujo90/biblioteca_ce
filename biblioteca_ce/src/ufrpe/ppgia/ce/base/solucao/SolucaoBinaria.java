package ufrpe.ppgia.ce.base.solucao;

import ufrpe.ppgia.ce.base.Solucao;

public class SolucaoBinaria implements Solucao<Integer> {
	protected Integer[] cromossomo;

	protected Integer limiteInferior = 0;

	protected Integer limiteSuperior = 1;

	protected int n;
	
	protected double fitness = Integer.MAX_VALUE;
	
	public SolucaoBinaria(int n) {
		this.n = n;
		cromossomo = new Integer[n];
	}

	@Override
	public void setValor(int indice, Integer valor) {
		if(valor == limiteInferior || valor == limiteSuperior) {
			cromossomo[indice] = valor;
		}
	}

	@Override
	public Integer getValor(int indice) {
		return cromossomo[indice];
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void setFitness(double valor) {
		this.fitness = valor;
	}

	@Override
	public Integer getLimiteInferior(int indice) {
		return limiteInferior;
	}

	@Override
	public Integer getLimiteSuperior(int indice) {
		return limiteSuperior;
	}

	@Override
	public void setLimiteInferior(int indice, Integer valor) {}

	@Override
	public void setLimiteSuperior(int indice, Integer valor) {}

	@Override
	public int getN() {
		return n;
	}

}
