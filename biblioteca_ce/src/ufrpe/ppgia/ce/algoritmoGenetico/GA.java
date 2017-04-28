package ufrpe.ppgia.ce.algoritmoGenetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;
import ufrpe.ppgia.ce.operadores.mutacao.MutacaoBinaria;
import ufrpe.ppgia.ce.operadores.recombinacao.CrossoverUmPonto;
import ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes.SelecaoFPSBinaria;

public class GA extends AE<SolucaoBinaria>{

	@Override
	public List<SolucaoBinaria> inicializar() {
		List<SolucaoBinaria> populacao = new ArrayList<>();
		for (int i = 0; i < 50; i++){
			SolucaoBinaria individuo = new SolucaoBinaria(10);
			for(int j = 0; j < individuo.getN(); j++) {
				individuo.setValor(j, new Random().nextInt(2));
			}
			populacao.add(individuo);
		}
		
		return populacao;
	}
	
	@Override
	public void avaliar(SolucaoBinaria s) {
		int countZeros = 0;
		int countUns = 0;
		for (int i = 0; i < s.getN(); i++) {
			if(s.getValor(i) == 0) {
				countZeros += 1;
			} else {
				countUns += 1;
			}
		}
		
		s.setFitness(countZeros/countUns);
	}

	@Override
	public boolean parar(List<SolucaoBinaria> pop) {
		pop.sort(Comparator.comparingDouble(SolucaoBinaria::getFitness));
		return pop.get(0).getFitness() == 0;
	}

	@Override
	public SolucaoBinaria[] selecionarPais(List<SolucaoBinaria> pop) {
		SelecaoFPSBinaria solucaoFPS = new SelecaoFPSBinaria();
		return (SolucaoBinaria[]) solucaoFPS.selecionar(pop).toArray();
	}

	@Override
	public List<SolucaoBinaria> selecionarSovreviventes(List<SolucaoBinaria> pop, SolucaoBinaria[] descendentes) {
		return new ArrayList<SolucaoBinaria>(Arrays.asList(descendentes));
	}

	@Override
	public SolucaoBinaria[] recombinar(SolucaoBinaria[] pais) {
		CrossoverUmPonto cup = new CrossoverUmPonto();
		cup.setPr(0.7);
		return cup.recombinar(pais[0], pais[1]);
	}

	@Override
	public SolucaoBinaria executarMutacao(SolucaoBinaria pai) {
		MutacaoBinaria mb = new MutacaoBinaria();
		mb.setPm(1/50);
		return mb.executarMutacao(pai);
	}
}
