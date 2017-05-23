package ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;


public class SelecaoMelhores {
	
	ArrayList<SolucaoReal> pais = new ArrayList<SolucaoReal>();

	public ArrayList<SolucaoReal> pais (List<SolucaoReal> populacao, int numeroPais) {
		populacao.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		
		for (int i = 0; i<numeroPais; i++){
			pais.add(populacao.get(i));
		}
		return pais;
	}
}
