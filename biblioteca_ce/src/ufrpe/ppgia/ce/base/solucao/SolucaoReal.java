package ufrpe.ppgia.ce.base.solucao;

import ufrpe.ppgia.ce.base.Solucao;

public class SolucaoReal implements Solucao<Double> {
	
	/* 
	 * Vetor que representa o conjunto de genes do cromossomo
	 * */
	protected Double[] cromossomo;
	
	/*
	 * Vetor que representa o menor valor que cada gene pode assumir
	 * */
	protected Double[] limiteInferior;

	/*
	 * Vetor que representa o maior valor que cada gene pode assumir
	 * */
	protected Double[] limiteSuperior;
	
	/*
	 * Inteiro que representa o tamanho do cromossomo (a quantidade de genes)
	 * */
	protected int n;
	
	/*
	 * Valor que representa a aptidão atual do indivíduo
	 * */
	protected double fitness = Double.MAX_VALUE;
	
	public SolucaoReal(int n) {
		/*
		 * Faz o preenchimento do cromossomo com genes aleatórios a partir da quantidade 'n' recebida por parametro
		 * */
		
		this.n = n;
		this.cromossomo = new Double[n];
		this.limiteInferior = new Double[n];
		this.limiteSuperior = new Double[n];
		
		for (int i = 0; i < n; i++) {
//			this.limiteInferior[i] = Double.MIN_VALUE;
//			this.limiteSuperior[i] = Double.MAX_VALUE;
			
			this.limiteInferior[i] = 1.0;
			this.limiteSuperior[i] = 10.0;
			
		}
		
		for (int i = 0; i < cromossomo.length; i++) {
			this.cromossomo[i] = (Math.random() * (limiteSuperior[i] - limiteInferior[i])) + limiteInferior[i];
		}
		
	}

	@Override
	public void setValor(int indice, Double valor) {
		this.cromossomo[indice] = valor;
	}

	@Override
	public Double getValor(int indice) {
		return this.cromossomo[indice];
	}

	@Override
	public double getFitness() {
		return this.fitness;
	}

	@Override
	public void setFitness(double valor) {
		this.fitness = valor;
	}

	@Override
	public Double getLimiteInferior(int indice) {
		return this.limiteInferior[indice];
	}

	@Override
	public Double getLimiteSuperior(int indice) {
		return this.limiteSuperior[indice];
	}

	@Override
	public void setLimiteInferior(int indice, Double valor) {
		this.limiteInferior[indice] = valor;
	}

	@Override
	public void setLimiteSuperior(int indice, Double valor) {
		this.limiteSuperior[indice] = valor;
	}

	@Override
	public int getN() {
		return this.n;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		SolucaoReal solucaoReal = new SolucaoReal(this.n);
		solucaoReal.cromossomo = this.cromossomo;
		solucaoReal.fitness = this.fitness;
		solucaoReal.limiteInferior = this.limiteInferior;
		solucaoReal.limiteSuperior = this.limiteSuperior;
		
		return solucaoReal;
	}
	
	

}