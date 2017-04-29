package ufrpe.ppgia.ce.operadores.mutacao;

import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class MutacaoNaoUniforme implements OperadorMutacao<SolucaoReal> {
	public static int tamanhoFita = 8;
	Random r = new Random();
	
	public float[] fita (float[]fita, float probabilidade){
		for (int i =0; i<fita.length;i++){
			float aux = r.nextFloat();
			float aux2 = (float) r.nextGaussian();
			if (aux<probabilidade){
				fita[i] = (float) (fita[i] + aux2);
			}
		}
		return fita;	
	}
}
