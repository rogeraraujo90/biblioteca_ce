package ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ufrpe.ppgia.ce.base.OperadorSelecao;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;

public class SelecaoFPS implements OperadorSelecao<SolucaoInteira>{
	private boolean fitnessMenorGanha = true;

	@Override
	public List<SolucaoInteira> selecionar(List<SolucaoInteira> solutionSet) {
		// TODO Auto-generated method stub		
		solutionSet.sort(Comparator.comparingDouble(SolucaoInteira::getFitness));
		List<SolucaoInteira> pais = new ArrayList<>();
		
		if(fitnessMenorGanha) {
			pais.add(solutionSet.get(0));
			pais.add(solutionSet.get(1));
		} else {
			pais.add(solutionSet.get(solutionSet.size()-1));
			pais.add(solutionSet.get(solutionSet.size()-2));
		}
		
		return pais;
	}
	
	public static void main(String[] args) {
		
		SolucaoInteira p1 = new SolucaoInteira(); 
		SolucaoInteira p2 = new SolucaoInteira(); 
		SolucaoInteira p3 = new SolucaoInteira(); 
		SolucaoInteira p4 = new SolucaoInteira(); 
		
		p1.setFitness(9);
		p2.setFitness(4);
		p3.setFitness(3);
		p4.setFitness(1);
		
		ArrayList<SolucaoInteira> teste = new ArrayList<SolucaoInteira>();
		
		teste.add(p1);
		teste.add(p2);
		teste.add(p3);
		teste.add(p4);
		
		SelecaoFPS sfps = new SelecaoFPS();
		
		List<SolucaoInteira> resp = sfps.selecionar(teste);
		System.out.println(resp.size());
		System.out.println(resp.get(0).getFitness());
		System.out.println(resp.get(1).getFitness());
		
		
		
	}
	
}


