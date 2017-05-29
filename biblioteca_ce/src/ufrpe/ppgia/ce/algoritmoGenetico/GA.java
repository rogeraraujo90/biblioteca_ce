package ufrpe.ppgia.ce.algoritmoGenetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;
import ufrpe.ppgia.ce.operadores.mutacao.MutacaoBinaria;
import ufrpe.ppgia.ce.operadores.recombinacao.CrossoverUmPonto;
import ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes.SelecaoFPSBinaria;

public class GA extends AE<SolucaoBinaria>{
	
	/**
	 * Por padrão a probalidade de mutação é 0.1 
	 */
	private MutacaoBinaria operadorMutacao = new MutacaoBinaria();
	
	/**
	 * Por padrão a probalidade de cruzamento é 1 
	 */
	private CrossoverUmPonto operadorCruzamento = new CrossoverUmPonto();
	
	/**
	 * Por padrão o tamanho da população é 100 
	 */
	private int tamanhoPop = 100;
	
	/**
	 * @return the tamanhoPop
	 */
	public int getTamanhoPop() {
		return tamanhoPop;
	}

	/**
	 * @param tamanhoPop the tamanhoPop to set
	 */
	public void setTamanhoPop(int tamanhoPop) {
		this.tamanhoPop = tamanhoPop;
	}

	/**
	 * @return the operadorMutacao
	 */
	public MutacaoBinaria getOperadorMutacao() {
		return operadorMutacao;
	}

	/**
	 * @return the operadorCruzamento
	 */
	public CrossoverUmPonto getOperadorCruzamento() {
		return operadorCruzamento;
	}

	@Override
	public void executar() {
		List<SolucaoBinaria> pop = inicializar();
		for (SolucaoBinaria individuo : pop) {
			avaliar(individuo);
		}
		
		int iteracao = 1;
		while (!parar(pop)) {
			System.out.println("Iteração: " + iteracao);
			iteracao++;
			SolucaoBinaria[] pais = selecionarPais(pop);
			SolucaoBinaria[] descendentes = recombinar(pais);
			
			for(int i = 0; i < descendentes.length; i++) {
				descendentes[i] = executarMutacao(descendentes[i]);
				avaliar(descendentes[i]);
			}
			
			pop = selecionarSovreviventes(pop, descendentes);
		}
		
		System.out.println("Parou");
	}

	@Override
	public List<SolucaoBinaria> inicializar() {
		List<SolucaoBinaria> populacao = new ArrayList<>();
		for (int i = 0; i < this.tamanhoPop; i++){
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
		double countZeros = 0;
		for (int i = 0; i < s.getN(); i++) {
			if(s.getValor(i) == 0) {
				countZeros += 1;
			}
		}
		
		s.setFitness(countZeros);
	}

	@Override
	public boolean parar(List<SolucaoBinaria> pop) {
		pop.sort(Comparator.comparingDouble(SolucaoBinaria::getFitness));
		System.out.println("Melhor Fitness: " + pop.get(0).getFitness());
		return pop.get(0).getFitness() == 0;
	}

	@Override
	public SolucaoBinaria[] selecionarPais(List<SolucaoBinaria> pop) {
		SelecaoFPSBinaria solucaoFPS = new SelecaoFPSBinaria();
		SolucaoBinaria[] popIntermediaria = new SolucaoBinaria[pop.size()];
		
		for(int i = 0; i < pop.size(); i += 2) {
			List<SolucaoBinaria> paisSelecionados =  solucaoFPS.selecionar(pop);
			popIntermediaria[i] = paisSelecionados.get(0);
			popIntermediaria[i + 1] = paisSelecionados.get(1);
		}
		
		return popIntermediaria;
	}

	@Override
	public List<SolucaoBinaria> selecionarSovreviventes(List<SolucaoBinaria> pop, SolucaoBinaria[] descendentes) {
		return new ArrayList<SolucaoBinaria>(Arrays.asList(descendentes));
	}

	@Override
	public SolucaoBinaria[] recombinar(SolucaoBinaria[] pais) {
		//Embaralhando o array de pais
		List<SolucaoBinaria> paisAux= new ArrayList<>(Arrays.asList(pais));
		long seed = System.nanoTime();
		Collections.shuffle(paisAux, new Random(seed));
		SolucaoBinaria[] paisEmbaralhados = new SolucaoBinaria[pais.length];
		paisEmbaralhados = paisAux.toArray(paisEmbaralhados);
		
		operadorCruzamento = new CrossoverUmPonto();
//		operadorCruzamento.setPr(0.7);
		SolucaoBinaria[] filhos = new SolucaoBinaria[pais.length];
		
		for(int i = 0; i < paisEmbaralhados.length; i += 2) {
			SolucaoBinaria[] filhosDaVez = operadorCruzamento.recombinar(paisEmbaralhados[i], paisEmbaralhados[i + 1]);
			filhos[i] = filhosDaVez[0];
			filhos[i + 1] = filhosDaVez[1];
		}
		
		return filhos;
	}

	@Override
	public SolucaoBinaria executarMutacao(SolucaoBinaria pai) {
//		operadorMutacao = new MutacaoBinaria();
//		operadorMutacao.setPm(1d/(double)pai.getN());
		return operadorMutacao.executarMutacao(pai);
	}
	
	public static void main(String[] args) {
		GA ga = new GA();
		ga.executar();
	}
}
