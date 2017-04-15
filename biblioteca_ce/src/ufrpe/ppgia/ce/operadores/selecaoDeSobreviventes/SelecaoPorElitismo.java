package ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ufrpe.ppgia.ce.base.OperadorSelecao;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;

public class SelecaoPorElitismo implements OperadorSelecao<SolucaoInteira>{
	private double tamanhoDaElite = 0.1;
	private boolean fitnessMenorGanha = true;

	@Override
	public List<SolucaoInteira> selecionar(List<SolucaoInteira> solutionSet) {
		List<SolucaoInteira> selecionados = new ArrayList<>();
		solutionSet.sort(Comparator.comparingDouble(SolucaoInteira::getFitness));
		int pontoDeSalvamento = (int) (solutionSet.size() * tamanhoDaElite);
		
		if(fitnessMenorGanha) {
			for(int i = 0; i < pontoDeSalvamento; i++) {
				selecionados.add(solutionSet.get(i));
			}
		} else {
			for(int i = solutionSet.size() - 1; i > solutionSet.size() - 1 - pontoDeSalvamento; i--) {
				selecionados.add(solutionSet.get(i));
			}
		}
		
		return selecionados;
	}
	
	public boolean isFitnessMenorGanha() {
		return fitnessMenorGanha;
	}

	public void setFitnessMenorGanha(boolean fitnessMenorGanha) {
		this.fitnessMenorGanha = fitnessMenorGanha;
	}
}
