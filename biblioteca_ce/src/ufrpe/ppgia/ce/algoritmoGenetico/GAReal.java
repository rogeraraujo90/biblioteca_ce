package ufrpe.ppgia.ce.algoritmoGenetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;
import ufrpe.ppgia.ce.operadores.mutacao.MutacaoUniforme;
import ufrpe.ppgia.ce.operadores.recombinacao.RecombinacaoAritmetica;
import ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes.SelecaoFPSReal;

public class GAReal extends AE<SolucaoReal>{
	
	@Override
	public void executar() {
		List<SolucaoReal> pop = inicializar();
		for (SolucaoReal individuo : pop) {
			avaliar(individuo);
		}
		
		int iteracao = 1;
		while (!parar(pop) && iteracao < 100000) {
			iteracao++;
			SolucaoReal[] pais = selecionarPais(pop);
			SolucaoReal[] descendentes = recombinar(pais);
			
			for(int i = 0; i < descendentes.length; i++) {
				descendentes[i] = executarMutacao(descendentes[i]);
				avaliar(descendentes[i]);
			}
			
			pop = selecionarSovreviventes(pop, descendentes);
		}
		
		System.out.println("Número de Iterações: " + iteracao);
		
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		System.out.println("Melhor Fitness " +  pop.get(0).getFitness());
	}

	@Override
	public List<SolucaoReal> inicializar() {
		List<SolucaoReal> populacao = new ArrayList<>();
		for (int i = 0; i < 50; i++){
			SolucaoReal individuo = new SolucaoReal(10);
			for(int j = 0; j < individuo.getN(); j++) {
				individuo.setLimiteInferior(j, -5.12);
				individuo.setLimiteSuperior(j, 5.12);
				individuo.setValor(j, (new Random().nextDouble() * 10.24) - 5.12);
			}
			populacao.add(individuo);
		}
		
		return populacao;
	}
	
	@Override
	public void avaliar(SolucaoReal s) {
		double countZeros = 0;
		for (int i = 0; i < s.getN(); i++) {
			if(s.getValor(i) == 0) {
				countZeros += 1;
			}
		}
		
		s.setFitness(countZeros);
	}

	@Override
	public boolean parar(List<SolucaoReal> pop) {
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		return pop.get(0).getFitness() <= 0.0000001d;
	}

	@Override
	public SolucaoReal[] selecionarPais(List<SolucaoReal> pop) {
		SelecaoFPSReal solucaoFPS = new SelecaoFPSReal();
		SolucaoReal[] popIntermediaria = new SolucaoReal[pop.size()];
		
		for(int i = 0; i < pop.size(); i += 2) {
			List<SolucaoReal> paisSelecionados =  solucaoFPS.selecionar(pop);
			popIntermediaria[i] = paisSelecionados.get(0);
			popIntermediaria[i + 1] = paisSelecionados.get(1);
		}
		
		return popIntermediaria;
	}

	@Override
	public List<SolucaoReal> selecionarSovreviventes(List<SolucaoReal> pop, SolucaoReal[] descendentes) {
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		SolucaoReal pai = pop.get(0);
		List<SolucaoReal> sobreviventes = new ArrayList<>();
		sobreviventes.add(pai);
		for(int i = 1; i < descendentes.length; i++) {
			sobreviventes.add(descendentes[i]);
		}
		
		return sobreviventes;
	}

	@Override
	public SolucaoReal[] recombinar(SolucaoReal[] pais) {
		//Embaralhando o array de pais
		List<SolucaoReal> paisAux= new ArrayList<>(Arrays.asList(pais));
		long seed = System.nanoTime();
		Collections.shuffle(paisAux, new Random(seed));
		SolucaoReal[] paisEmbaralhados = new SolucaoReal[pais.length];
		paisEmbaralhados = paisAux.toArray(paisEmbaralhados);
		
		RecombinacaoAritmetica cup = new RecombinacaoAritmetica();
		cup.setPr(0.9d);
		SolucaoReal[] filhos = new SolucaoReal[pais.length];
		
		for(int i = 0; i < paisEmbaralhados.length; i += 2) {
			SolucaoReal[] filhosDaVez = cup.recombinar(paisEmbaralhados[i], paisEmbaralhados[i + 1]);
			filhos[i] = filhosDaVez[0];
			filhos[i + 1] = filhosDaVez[1];
		}
		
		return filhos;
	}

	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		MutacaoUniforme operadorMutacao = new MutacaoUniforme();
		operadorMutacao.setPM(1d/(double)pai.getN());
		return operadorMutacao.executarMutacao(pai);
	}
	
	public static void main(String[] args) {
		GAReal ga = new GAReal();
		ga.executar();
	}
}
