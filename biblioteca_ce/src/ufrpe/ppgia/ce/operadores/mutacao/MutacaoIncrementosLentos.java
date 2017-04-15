package ufrpe.ppgia.ce.operadores.mutacao;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;

public class MutacaoIncrementosLentos implements OperadorMutacao<SolucaoInteira> {
	private double pm = 0.1;

	@Override
	public SolucaoInteira executarMutacao(SolucaoInteira pai) {
		SolucaoInteira mutacao = new SolucaoInteira(pai.getN());
		
		for(int i = 0; i < pai.getN(); i++) {
			if(Math.random() < pm) {
				int incremento = (int) ((pai.getLimiteSuperior(i) - pai.getLimiteInferior(i)) * 0.1);
				mutacao.setValor(i, pai.getValor(i) + incremento);
			} else {
				mutacao.setValor(i, pai.getValor(i));
			}
		}
		
		return mutacao;
	}

}
