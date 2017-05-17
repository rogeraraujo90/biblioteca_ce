package ufrpe.ppgia.ce.operadores.recombinacao;

import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorRecombinacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;

public class CrossoverUmPonto implements OperadorRecombinacao<SolucaoBinaria>{
	private double pr = 1;

	@Override
	public SolucaoBinaria[] recombinar(SolucaoBinaria pai1, SolucaoBinaria pai2) {
		if(Math.random() <= pr) {
			int pontoDeCrossover = new Random().nextInt(pai1.getN() - 1) + 1;
			SolucaoBinaria filho1 = new SolucaoBinaria(pai1.getN());
			SolucaoBinaria filho2 = new SolucaoBinaria(pai1.getN());
			
			for(int i = 0; i < pontoDeCrossover; i++) {
				filho1.setValor(i, pai1.getValor(i));
				filho2.setValor(i, pai2.getValor(i));
			}
			
			for(int i = pontoDeCrossover; i < pai1.getN(); i++) {
				filho1.setValor(i, pai2.getValor(i));
				filho2.setValor(i, pai1.getValor(i));
			}
			
			return new SolucaoBinaria[]{filho1, filho2};
		}
		
		return new SolucaoBinaria[]{pai1, pai2};
	}
	
	public void setPr(double pr) {
		this.pr = pr;
	}
}
