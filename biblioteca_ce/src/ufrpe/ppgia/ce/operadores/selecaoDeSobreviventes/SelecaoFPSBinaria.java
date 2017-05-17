package ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorSelecao;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;

public class SelecaoFPSBinaria implements OperadorSelecao<SolucaoBinaria>{
	@Override
	public List<SolucaoBinaria> selecionar(List<SolucaoBinaria> solutionSet) {
		solutionSet.sort(Comparator.comparingDouble(SolucaoBinaria::getFitness));
		List<SolucaoBinaria> pais = new ArrayList<>();
		
		double totalFitness = 0;
		Double[] fitnessNormalizado = new Double[solutionSet.size()];
		
		for(SolucaoBinaria solucao : solutionSet) {
			totalFitness += solucao.getFitness();
		}
		
		for(int i = 0; i < solutionSet.size(); i++) {
			fitnessNormalizado[i] = solutionSet.get(i).getFitness() / totalFitness;
		}
		
		List<SolucaoBinaria> roletaDePais = new ArrayList<>();
		for(int i = 0; i < solutionSet.size(); i++) {
			double fitnessParcial = fitnessNormalizado[0];
			while(fitnessParcial <= fitnessNormalizado[i]) {
				roletaDePais.add(solutionSet.get(i));
				fitnessParcial += fitnessParcial;
			}
		}
		
		// Escolhendo o primeiro pai
		int seletorDePai = new Random().nextInt(roletaDePais.size());
		pais.add(roletaDePais.get(seletorDePai));
		
		//Removendo todas as ocorrências do primeiro pai da roleta
		while(roletaDePais.contains(pais.get(0))) {
			roletaDePais.remove(pais.get(0));
		}
		
		// Escolhendo o segundo pai
		seletorDePai = new Random().nextInt(roletaDePais.size());
		pais.add(roletaDePais.get(seletorDePai));
		
		return pais;
	}
	
	public static void main(String[] args) {
		
		SolucaoBinaria p1 = new SolucaoBinaria(1); 
		SolucaoBinaria p2 = new SolucaoBinaria(1); 
		SolucaoBinaria p3 = new SolucaoBinaria(1); 
		SolucaoBinaria p4 = new SolucaoBinaria(1);
		SolucaoBinaria p5 = new SolucaoBinaria(1); 
		SolucaoBinaria p6 = new SolucaoBinaria(1); 
		SolucaoBinaria p7 = new SolucaoBinaria(1); 
		SolucaoBinaria p8 = new SolucaoBinaria(1);
		
		p1.setFitness(9);
		p2.setFitness(4);
		p3.setFitness(3);
		p4.setFitness(1);
		p5.setFitness(5);
		p6.setFitness(7);
		p7.setFitness(2);
		p8.setFitness(6);
		
		ArrayList<SolucaoBinaria> teste = new ArrayList<SolucaoBinaria>();
		
		teste.add(p1);
		teste.add(p2);
		teste.add(p3);
		teste.add(p4);
		teste.add(p5);
		teste.add(p6);
		teste.add(p7);
		teste.add(p8);
		
		SelecaoFPSBinaria sfps = new SelecaoFPSBinaria();
		
		List<SolucaoBinaria> resp = sfps.selecionar(teste);
		System.out.println(resp.size());
		System.out.println(resp.get(0).getFitness());
		System.out.println(resp.get(1).getFitness());
	}	
}