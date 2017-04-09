package ufrpe.ppgia.ce.base.solucao;

import java.util.List;
import java.util.Vector;

public class SolucaoPermutacao extends SolucaoInteira {
	public SolucaoPermutacao(int n) {
		this.n = n;
		cromossomo = new Integer[n];
		limiteInferior = new Integer[n];
		limiteSuperior = new Integer[n];

		for (int i = 0; i < n; i++) {
			limiteInferior[i] = 0;
			limiteSuperior[i] = n-1;
		}

		List<Integer> linhasNaoSorteadas = new Vector<>();

		for (int i = 0; i < n; i++) {
			linhasNaoSorteadas.add(i);
		}

		cromossomo = new Integer[n];
		for (int i = 0; i < n; i++) {
			int linha = (int) (Math.random() * linhasNaoSorteadas.size());
			cromossomo[i] = linhasNaoSorteadas.get(linha);
			linhasNaoSorteadas.remove(linha);
		}
	}
}
