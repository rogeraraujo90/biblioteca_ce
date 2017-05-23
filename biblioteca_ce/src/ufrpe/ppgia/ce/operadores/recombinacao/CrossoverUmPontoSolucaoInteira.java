package ufrpe.ppgia.ce.operadores.recombinacao;

import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorRecombinacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;

public class CrossoverUmPontoSolucaoInteira implements OperadorRecombinacao<SolucaoInteira>{
	private double pr = 1;

	@Override
	public SolucaoInteira[] recombinar(SolucaoInteira pai1, SolucaoInteira pai2) {
		if(Math.random() <= pr) {
			int pontoDeCrossover = new Random().nextInt(pai1.getN() - 1) + 1;
			SolucaoInteira filho1 = pai1.clone();
			SolucaoInteira filho2 = pai1.clone();
			
			for(int i = 0; i < pontoDeCrossover; i++) {
				filho1.setValor(i, pai1.getValor(i));
				filho2.setValor(i, pai2.getValor(i));
			}
			
			for(int i = pontoDeCrossover; i < pai1.getN(); i++) {
				filho1.setValor(i, pai2.getValor(i));
				filho2.setValor(i, pai1.getValor(i));
			}
			
			return new SolucaoInteira[]{filho1, filho2};
		}
		
		return new SolucaoInteira[]{pai1, pai2};
	}
	
	public void setPr(double pr) {
		this.pr = pr;
	}
}
