package ufrpe.ppgia.ce.programacaoEvolucionaria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;
import ufrpe.ppgia.ce.operadores.mutacao.MutacaoNaoUniforme;

public class PE extends AE<SolucaoReal>{
	
	/**
	 * Por padr�o a probalidade de muta��o � 0.1 
	 */
	private MutacaoNaoUniforme operadorMutacao = new MutacaoNaoUniforme();
	
	/**
	 * Por padr�o o tamanho da popula��o � 100 
	 */
	private int tamanhoPop = 100;
	
	/**
	 * M�todo executar.
	 * Realiza toda a execu��o do algoritmo.
	 */
	public void executar() {
		/*
		 * Criar popula��o de indiv�duos
		 * */
		List<SolucaoReal> pop = inicializar();
		
		/*
		 * Avaliar cada indiv�duo da pupula��o
		 * */
		for (SolucaoReal solucaoReal : pop) {
			avaliar(solucaoReal);
		}
		
		int iteracao = 0;
		System.out.print("Iteracao: " + iteracao);
		
		/*
		 * Executar a Porgrama��o Evolutica (PE) 
		 * enquanto a condi��o de parada n�o seja satisfeita
		 * */
		while (!parar(pop) && iteracao < 1000) {
			
			iteracao++;
			
			/*
			 * Criar um array de descendentes do tamanho da popula��o de pais
			 * */
			SolucaoReal[] descendentes = new SolucaoReal[pop.size()];
			
			/*
			 * Criar um descendente para cada pai da popula��o
			 * */
			for (int i = 0; i < descendentes.length; i++) {
				
				/*
				 * Criar um descendente a partir de um pai
				 * */
				SolucaoReal descendente = executarMutacao(pop.get(i));
				
				/*
				 * Avaliar o descendente criado anteriormente
				 * */
				avaliar(descendente);
				
				/*
				 * Adicionar o novo descendente ao array des descendentes
				 * */
				descendentes[i] = descendente;
				
			}
			
			/*
			 * A popula��o recebe sele��o de sobreviventes dos pais 
			 * e seu descendentes
			 * */
			pop = selecionarSovreviventes(pop, descendentes);
			
			System.out.print("Iteracao: " + iteracao);
			
		}
		System.out.println("Parou");
	}
	
	/**
	 * M�todo inicializar.
	 * Cria uma popula��o com 100 indiv�duos do tipo SolucaoReal, 
	 * onde cada indiv�duo tem um cromossomo com 10 genes.
	 * @return populacao, lista gerada aleatoriamente com indiv�duos do tipo SolucaoReal.
	 */
	@Override
	public List<SolucaoReal> inicializar() {
		List<SolucaoReal> populacao = new ArrayList<SolucaoReal>();
		
		/*
		 * Adiciona cada indiv�duo ao array da popula��o.
		 * */
		for (int i = 0; i < this.tamanhoPop; i++) {
			populacao.add(new SolucaoReal(10));
			
		}
		
		return populacao;
	}

	/**
	 * M�todo avaliar.
	 * M�todo que avalia um indiv�duo e determina um valor o seu valor de fitness.
	 * Para exemplificar o funcionamento do algoritmo, considerou-se que o 
	 * valor de fitness � baseado na quantidade de genes de valor maior que 5 
	 * presentes no seu cromossomo.
	 * @param s indiv�duo do tipo SolucaoReal, que ser� avaliado.
	 */
	@Override
	public void avaliar(SolucaoReal s) {
		/*
		 * Contador de genes com valor maior que 5
		 */
		double count = 0;
		
		for (int i = 0; i < s.getN(); i++) {
			/*
			 * Se o gene tiver valor maior que 5 o contador � incrementado
			 */
			if(s.getValor(i) > 5)
				count += 1;
				
		}
		
		s.setFitness(count);
	}

	/**
	 * M�todo parar.
	 * M�todo que verifica se o indiv�duo com melhor fitness atualmente 
	 * j� repesenta a melhor solu��o para o problema, ou seja, 
	 * se todos os genes tem valor valor maior que 5, seu fitness egual a 10.
	 */
	@Override
	public boolean parar(List<SolucaoReal> pop) {
		
		/*
		 * Organizar a popula��o em ordem crescente
		 */
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		
		System.out.println("Melhor Fitness: " + pop.get(pop.size() - 1).getFitness());
		
		return pop.get(pop.size() - 1).getFitness() == 10;
		
	}

	/**
	 * M�todo selecionarPais.
	 * M�todo n�o utilizado no algoritmo de Programa��o Evolutiva
	 */
	@Override
	public SolucaoReal[] selecionarPais(List<SolucaoReal> pop) {
		return null;
	}

	/**
	 * M�todo selecionarSovreviventes.
	 * Seleciona uma nova popula��o de sobreviventes a partir da popula��o 
	 * atual e seus descendentes.
	 * @param pop popula��o atual de indiv�duos.
	 * @param descendentes popula��o de descentes gerados por muta��o nos idiv�duos 
	 * da popula��o atual.
	 * @return novaPopulacao nova popula��o de indiv�duos sobreviventes selecionados 
	 * por torneio
	 */
	@Override
	public List<SolucaoReal> selecionarSovreviventes(List<SolucaoReal> pop, SolucaoReal[] descendentes) {
		
		/*
		 * N�mero de indiv�duos para cada torneio
		 */
		int tamanhoTorneio = 10;
		
		List<Integer> indicesTorneio = new ArrayList<>();
		
		/*
		 * popTotal = pop + descendentes
		 */
		List<SolucaoReal> popTotal = new ArrayList<>();
		
		/*
		 * Adicionar pais para popTotal
		 */
		for (SolucaoReal solucaoReal : pop) {
			popTotal.add(solucaoReal);
		}
		
		/*
		 * Adicionar descendentes para popTotal
		 */
		for (int i = 0; i < descendentes.length; i++)
			popTotal.add(descendentes[i]);
		
		/*
		 * PopSobreviventes ser� a nova popula��o
		 */
		List<SolucaoReal> popSobreviventes = new ArrayList<>();
		
		Random random = new Random();
		
		/*
		 * Executar a sele��o de 100 indiv�dous por torneio
		 */
		for (int i = 0; i < descendentes.length; i++) {
			
			/*
			 * Lista de indiv�duos para o torneio
			 */
			List<SolucaoReal> individuosTorneio = new ArrayList<>();
			
			/*
			 * Sorteia 10 �ndices de indiv�duos para o torneio
			 */
			for (int j = 0; j < tamanhoTorneio; j++) {
				int rand = random.nextInt(popTotal.size());
				
				/*
				 * N�o permite a inser��o de �ndices duplicados
				 */
				while ( indicesTorneio.contains(rand) )
					rand = random.nextInt(popTotal.size());
					
				/*
				 * Insere o �ndice sorteado
				 */
				indicesTorneio.add(rand);
			}
			
			/*
			 * Insere os indiv�duos sorteados ao array
			 */
			for (Integer indice : indicesTorneio)
				individuosTorneio.add(popTotal.get(indice));
			
			/*
			 * Organizar os individuos do torneio pelo valor do 
			 * fitness em ordem crescente
			 */
			individuosTorneio.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
			
			/*
			 * Define o ganhador do torneio
			 */
			SolucaoReal individuoGanhador = individuosTorneio.get(tamanhoTorneio - 1);
			
			/*
			 * Insere o indiv�duo ganhador do torneio na popula��o de sobreviventes
			 */
			popSobreviventes.add(individuoGanhador);
			
			/*
			 * Remove da popula��o total o indiv�duo ganhador do torneio 
			 */
			popTotal.remove(individuoGanhador);
			
			/*
			 * Limpa as listas para o novo torneio
			 */
			individuosTorneio.clear();
			indicesTorneio.clear();
			
		} // fim for de torneios
		
		return popSobreviventes;
	}

	/**
	 * M�todo recombinar.
	 * M�todo n�o utilizado no algoritmo de Programa��o Evolutiva
	 */
	@Override
	public SolucaoReal[] recombinar(SolucaoReal[] pais) {
		return null;
	}

	/**
	 * M�todo executarMutacao.
	 * Executa a muta��o em indiv�duo da popula��o.
	 * @param pai, pai do tipo SolucaoReal que ser� mutado.
	 * @return descendente, descendente do tipo SolucaoReal gerado a partir do genes do pai.
	 */
	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		
		SolucaoReal descendente = new SolucaoReal(10);
		
		/*
		 * Executar a muta�ao
		 */
		descendente = this.operadorMutacao.executarMutacao(pai);
		
		return descendente;
	}
	
	/**
	 * @return the operadorMutacao
	 */
	public MutacaoNaoUniforme getOperadorMutacao() {
		return operadorMutacao;
	}

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
	 * Testando o algoritimo
	 */
	public static void main(String[] args) {
		new PE().executar();
	}
	
}
