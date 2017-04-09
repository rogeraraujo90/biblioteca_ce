package ufrpe.ppgia.ce.base.solucao;

import ufrpe.ppgia.ce.base.Solucao;

public class SolucaoInteira implements Solucao<Integer> {
	protected Integer[] cromossomo;

	protected Integer[] limiteInferior;

	protected Integer[] limiteSuperior;

	protected int n;
	
	protected double fitness;
	
	public SolucaoInteira() {
	}

	public SolucaoInteira(int n) {
		this.n = n;
		cromossomo = new Integer[n];
		limiteInferior = new Integer[n];
		limiteSuperior = new Integer[n];

		for (int i = 0; i < n; i++) {
			limiteInferior[i] = Integer.MIN_VALUE;
			limiteSuperior[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < n; i++) {
			cromossomo[i] = (int) (Math.random() * (limiteSuperior[i] - limiteInferior[i])) + limiteInferior[i];
		}
	}

	@Override
	public void setValor(int indice, Integer valor) {
		cromossomo[indice] = valor;
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
		return limiteInferior[indice];
	}

	@Override
	public Integer getLimiteSuperior(int indice) {
		return limiteSuperior[indice];
	}

	@Override
	public void setLimiteInferior(int indice, Integer valor) {
		this.limiteInferior[indice] = valor;
	}

	@Override
	public void setLimiteSuperior(int indice, Integer valor) {
		this.limiteSuperior[indice] = valor;
	}

	@Override
	public int getN() {
		return n;
	}

}
