package ufrpe.ppgia.ce.problemas.coloracao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;
import ufrpe.ppgia.ce.operadores.mutacao.MutacaoInicializacaoAleatoria;
import ufrpe.ppgia.ce.operadores.recombinacao.CrossoverUmPontoSolucaoInteira;
import ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes.SelecaoFPS;

public class ColoracaoComPenalidade extends AE<SolucaoInteira> implements Problema<SolucaoInteira> {
	private Random random = new Random();
	private static Integer COR_AZUL = 1;
	private static Integer COR_VERDE = 2;
	private static Integer COR_VERMELHO = 3;

	public static Integer[][] MAPA = {
			{2, 4, 6}, 
			{1, 3, 10}, 
			{2, 5, 8}, 
			{1, 7, 8}, 
			{3, 6, 7}, 
			{1, 5, 9}, 
			{4, 5, 10}, 
			{3, 4, 9}, 
			{6, 8, 10}, 
			{2, 7, 9} 
			};
	
	@Override
	public void executar() {
		List<SolucaoInteira> pop = inicializar();
		
		for (SolucaoInteira individuo : pop) {
			avaliar(individuo);
		}
		
		int iteracao = 1;
		
		while (!parar(pop)) {
			System.out.println("Iteração: " + iteracao);
			iteracao++;
			
			SolucaoInteira[] pais = selecionarPais(pop);
			SolucaoInteira[] descendentes = recombinar(pais);
			
			for(int i = 0; i < descendentes.length; i++) {
//				validar(descendentes[i]);
				descendentes[i] = executarMutacao(descendentes[i]);
//				validar(descendentes[i]);
				avaliar(descendentes[i]);
			}
			
			pop = selecionarSovreviventes(pop, descendentes);
		}
		
		System.out.println("Parou");
	}
	
	
	@Override
	public void avaliar(SolucaoInteira solucao) {
		int countVermelhos = 0;
		for (int i = 0 ; i < solucao.getN(); i ++) {
			if(solucao.getValor(i) == COR_VERMELHO) {
				countVermelhos += 1;
			}
		}
		
		solucao.setFitness(countVermelhos);
		
		penalizar(solucao);
	}

	@Override
	public List<SolucaoInteira> inicializar() {
		List<SolucaoInteira> pais = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			SolucaoInteira pai = new SolucaoInteira(10);
			
			for (int j = 0; j < pai.getN(); j++) {
				pai.setLimiteInferior(j, 1);
				pai.setLimiteSuperior(j, 3);
				pai.setValor(j, random.nextInt(3) + 1);
			}
			
//			validar(pai);
			pais.add(pai);
		}
		
		return pais;
	}

	@Override
	public boolean parar(List<SolucaoInteira> pop) {
		pop.sort(Comparator.comparingDouble(SolucaoInteira::getFitness));
		System.out.println("Melhor Fitness: " + pop.get(99).getFitness());
		return pop.get(99).getFitness() == 4;
	}

	@Override
	public SolucaoInteira[] selecionarPais(List<SolucaoInteira> pop) {
		SelecaoFPS operadorSelecao = new SelecaoFPS();
		SolucaoInteira[] popIntermediaria = new SolucaoInteira[pop.size()];
		
		for(int i = 0; i < pop.size(); i += 2) {
			List<SolucaoInteira> paisSelecionados =  operadorSelecao.selecionar(pop);
			popIntermediaria[i] = paisSelecionados.get(0);
			popIntermediaria[i + 1] = paisSelecionados.get(1);
		}
		
		return popIntermediaria;
	}


	@Override
	public List<SolucaoInteira> selecionarSovreviventes(List<SolucaoInteira> pop, SolucaoInteira[] descendentes) {
		return new ArrayList<SolucaoInteira>(Arrays.asList(descendentes));
	}


	@Override
	public SolucaoInteira[] recombinar(SolucaoInteira[] pais) {
		//Embaralhando o array de pais
		List<SolucaoInteira> paisAux= new ArrayList<>(Arrays.asList(pais));
		long seed = System.nanoTime();
		Collections.shuffle(paisAux, new Random(seed));
		SolucaoInteira[] paisEmbaralhados = new SolucaoInteira[pais.length];
		paisEmbaralhados = paisAux.toArray(paisEmbaralhados);
		
		CrossoverUmPontoSolucaoInteira cup = new CrossoverUmPontoSolucaoInteira();
		cup.setPr(0.7);
		SolucaoInteira[] filhos = new SolucaoInteira[pais.length];
		
		for(int i = 0; i < paisEmbaralhados.length; i += 2) {
			SolucaoInteira[] filhosDaVez = cup.recombinar(paisEmbaralhados[i], paisEmbaralhados[i + 1]);
			filhos[i] = filhosDaVez[0];
			filhos[i + 1] = filhosDaVez[1];
		}
		
		return filhos;
	}


	@Override
	public SolucaoInteira executarMutacao(SolucaoInteira pai) {
		MutacaoInicializacaoAleatoria operadorMutacao = new MutacaoInicializacaoAleatoria();
		return operadorMutacao.executarMutacao(pai);
	}
	
	private void validar(SolucaoInteira pai) {
//		for (int i = 0; i < pai.getN(); i++) {
//			Integer corNodo = pai.getValor(i);
//			Integer[] vizinhos = MAPA[i];
//			
//			for(int j = 0; j < vizinhos.length; j ++) {
//				if(corNodo == pai.getValor(vizinhos[j] - 1)) {
//					pai.setValor(vizinhos[j] - 1, mudarCor(corNodo));
//				}
//			}
//		}
		
	}
	
	private void penalizar(SolucaoInteira individuo) {
		int w = 1, penalidadeTotal = 0;
		
		for (int i = 0; i < individuo.getN(); i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (individuo.getValor(i) == individuo.getValor( MAPA[i][j] - 1 )) {
					penalidadeTotal += w;
					
				}
				
			}
		}

		individuo.setFitness(individuo.getFitness() + penalidadeTotal);
		
	}

	private Integer mudarCor(Integer corNodo) {
		switch(corNodo) {
			case 1:
				return COR_VERDE;
			case 2:
				return COR_VERMELHO;
			default:
				return COR_AZUL;
		}
	}
	
	public static void main(String[] args) {
		ColoracaoComPenalidade test = new ColoracaoComPenalidade();
		test.executar();
	}

}
