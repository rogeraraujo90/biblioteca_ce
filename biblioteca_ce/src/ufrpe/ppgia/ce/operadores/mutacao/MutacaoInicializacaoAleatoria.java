package ufrpe.ppgia.ce.operadores.mutacao;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;
import java.util.concurrent.ThreadLocalRandom;

public class MutacaoInicializacaoAleatoria implements OperadorMutacao<SolucaoInteira> {
	private double pm = 0.1d;

	@Override
	public SolucaoInteira executarMutacao(SolucaoInteira pai) {
		SolucaoInteira mutacao = new SolucaoInteira(pai.getN());
		
		for(int i = 0; i < pai.getN(); i++) {
			if(Math.random() < pm) {
				int novoValor = ThreadLocalRandom.current().nextInt(pai.getLimiteInferior(i), pai.getLimiteSuperior(i) + 1);
				mutacao.setValor(i, novoValor);
			} else {
				mutacao.setValor(i, pai.getValor(i));
			}
		}
		
		return mutacao;
	}
}
