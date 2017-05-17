package ufrpe.ppgia.ce.operadores.mutacao;

import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class MutacaoNaoUniforme implements OperadorMutacao<SolucaoReal> {
	Random r = new Random();

	public static double probabilidade = 0.8;
	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		for (int i =0; i<pai.getN();i++){
			float aux = r.nextFloat();
			float aux2 = (float) r.nextGaussian();
			if (aux<probabilidade){
				double newGene = pai.getValor(i) + aux2;
				pai.setValor(i, newGene);
			} 
		}
		return pai;	
	}
}

