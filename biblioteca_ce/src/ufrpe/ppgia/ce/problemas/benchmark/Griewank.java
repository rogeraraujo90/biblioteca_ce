package ufrpe.ppgia.ce.problemas.benchmark;

import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class Griewank implements Problema<SolucaoReal> {
	private static final double[] x = new double[401];

	@Override
	public void avaliar(SolucaoReal solucao) {
		int cont = -200;
		
		for (int i = 0; i < x.length; i++) {
			x [i] = cont;
			cont++;
		}
		
		int ind = 0;
		double valor = Math.ceil(solucao.getValor(0));
		
		for (int i = 0; i < x.length; i++) {
			if (valor == x[i]) {
				ind  = i+1;
				break;
			}
		}
		
		double p1 = (1+(1/4000) * Math.pow(valor, 2) - Math.cos(valor/Math.sqrt(ind)));
		solucao.setFitness(p1);
	}
	
}
