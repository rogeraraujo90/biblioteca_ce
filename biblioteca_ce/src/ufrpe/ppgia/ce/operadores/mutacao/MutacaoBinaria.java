package ufrpe.ppgia.ce.operadores.mutacao;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;

public class MutacaoBinaria implements OperadorMutacao<SolucaoBinaria> {
	private double pm = 0.1;

	@Override
	public SolucaoBinaria executarMutacao(SolucaoBinaria pai) {
		for (int i = 0; i < pai.getN(); i++) {
			if(Math.random() <= pm) {
				if(pai.getValor(i) == 0) {
					pai.setValor(i, 1);
				} else {
					pai.setValor(i, 0);
				}
			}
		}
		
		return pai;
	}
	
	public void setPm(double pm) {
		this.pm = pm;
	}

}
