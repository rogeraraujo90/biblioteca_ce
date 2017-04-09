package ufrpe.ppgia.ce.problemas.benchmark;

import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class Rosenbrock implements Problema<SolucaoReal> {
	private static final double A = 1;
	private static final double B = 100;

	@Override
	public void avaliar(SolucaoReal solucao) {
		double p1 = (A - solucao.getValor(0));
		double p2 = (solucao.getValor(1) - solucao.getValor(0) * solucao.getValor(0));
		solucao.setFitness((p1 * p1 + B * p2 * p2));
	}

}
