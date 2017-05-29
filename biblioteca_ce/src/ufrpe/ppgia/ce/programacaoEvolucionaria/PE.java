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
	 * Por padrão a probalidade de mutação é 0.1 
	 */
	private MutacaoNaoUniforme operadorMutacao = new MutacaoNaoUniforme();
	
	/**
	 * Por padrão o tamanho da população é 100 
	 */
	private int tamanhoPop = 100;
	
	/**
	 * Método executar.
	 * Realiza toda a execução do algoritmo.
	 */
	public void executar() {
		/*
		 * Criar população de indivíduos
		 * */
		List<SolucaoReal> pop = inicializar();
		
		/*
		 * Avaliar cada indivíduo da pupulação
		 * */
		for (SolucaoReal solucaoReal : pop) {
			avaliar(solucaoReal);
		}
		
		int iteracao = 0;
		System.out.print("Iteracao: " + iteracao);
		
		/*
		 * Executar a Porgramação Evolutica (PE) 
		 * enquanto a condição de parada não seja satisfeita
		 * */
		while (!parar(pop) && iteracao < 1000) {
			
			iteracao++;
			
			/*
			 * Criar um array de descendentes do tamanho da população de pais
			 * */
			SolucaoReal[] descendentes = new SolucaoReal[pop.size()];
			
			/*
			 * Criar um descendente para cada pai da população
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
			 * A população recebe seleção de sobreviventes dos pais 
			 * e seu descendentes
			 * */
			pop = selecionarSovreviventes(pop, descendentes);
			
			System.out.print("Iteracao: " + iteracao);
			
		}
		System.out.println("Parou");
	}
	
	/**
	 * Método inicializar.
	 * Cria uma população com 100 indivíduos do tipo SolucaoReal, 
	 * onde cada indivíduo tem um cromossomo com 10 genes.
	 * @return populacao, lista gerada aleatoriamente com indivíduos do tipo SolucaoReal.
	 */
	@Override
	public List<SolucaoReal> inicializar() {
		List<SolucaoReal> populacao = new ArrayList<SolucaoReal>();
		
		/*
		 * Adiciona cada indivíduo ao array da população.
		 * */
		for (int i = 0; i < this.tamanhoPop; i++) {
			populacao.add(new SolucaoReal(10));
			
		}
		
		return populacao;
	}

	/**
	 * Método avaliar.
	 * Método que avalia um indivíduo e determina um valor o seu valor de fitness.
	 * Para exemplificar o funcionamento do algoritmo, considerou-se que o 
	 * valor de fitness é baseado na quantidade de genes de valor maior que 5 
	 * presentes no seu cromossomo.
	 * @param s indivíduo do tipo SolucaoReal, que será avaliado.
	 */
	@Override
	public void avaliar(SolucaoReal s) {
		/*
		 * Contador de genes com valor maior que 5
		 */
		double count = 0;
		
		for (int i = 0; i < s.getN(); i++) {
			/*
			 * Se o gene tiver valor maior que 5 o contador é incrementado
			 */
			if(s.getValor(i) > 5)
				count += 1;
				
		}
		
		s.setFitness(count);
	}

	/**
	 * Método parar.
	 * Método que verifica se o indivíduo com melhor fitness atualmente 
	 * já repesenta a melhor solução para o problema, ou seja, 
	 * se todos os genes tem valor valor maior que 5, seu fitness egual a 10.
	 */
	@Override
	public boolean parar(List<SolucaoReal> pop) {
		
		/*
		 * Organizar a população em ordem crescente
		 */
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		
		System.out.println("Melhor Fitness: " + pop.get(pop.size() - 1).getFitness());
		
		return pop.get(pop.size() - 1).getFitness() == 10;
		
	}

	/**
	 * Método selecionarPais.
	 * Método não utilizado no algoritmo de Programação Evolutiva
	 */
	@Override
	public SolucaoReal[] selecionarPais(List<SolucaoReal> pop) {
		return null;
	}

	/**
	 * Método selecionarSovreviventes.
	 * Seleciona uma nova população de sobreviventes a partir da população 
	 * atual e seus descendentes.
	 * @param pop população atual de indivíduos.
	 * @param descendentes população de descentes gerados por mutação nos idivíduos 
	 * da população atual.
	 * @return novaPopulacao nova população de indivíduos sobreviventes selecionados 
	 * por torneio
	 */
	@Override
	public List<SolucaoReal> selecionarSovreviventes(List<SolucaoReal> pop, SolucaoReal[] descendentes) {
		
		/*
		 * Número de indivíduos para cada torneio
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
		 * PopSobreviventes será a nova população
		 */
		List<SolucaoReal> popSobreviventes = new ArrayList<>();
		
		Random random = new Random();
		
		/*
		 * Executar a seleção de 100 indivídous por torneio
		 */
		for (int i = 0; i < descendentes.length; i++) {
			
			/*
			 * Lista de indivíduos para o torneio
			 */
			List<SolucaoReal> individuosTorneio = new ArrayList<>();
			
			/*
			 * Sorteia 10 índices de indivíduos para o torneio
			 */
			for (int j = 0; j < tamanhoTorneio; j++) {
				int rand = random.nextInt(popTotal.size());
				
				/*
				 * Não permite a inserção de índices duplicados
				 */
				while ( indicesTorneio.contains(rand) )
					rand = random.nextInt(popTotal.size());
					
				/*
				 * Insere o índice sorteado
				 */
				indicesTorneio.add(rand);
			}
			
			/*
			 * Insere os indivíduos sorteados ao array
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
			 * Insere o indivíduo ganhador do torneio na população de sobreviventes
			 */
			popSobreviventes.add(individuoGanhador);
			
			/*
			 * Remove da população total o indivíduo ganhador do torneio 
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
	 * Método recombinar.
	 * Método não utilizado no algoritmo de Programação Evolutiva
	 */
	@Override
	public SolucaoReal[] recombinar(SolucaoReal[] pais) {
		return null;
	}

	/**
	 * Método executarMutacao.
	 * Executa a mutação em indivíduo da população.
	 * @param pai, pai do tipo SolucaoReal que será mutado.
	 * @return descendente, descendente do tipo SolucaoReal gerado a partir do genes do pai.
	 */
	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		
		SolucaoReal descendente = new SolucaoReal(10);
		
		/*
		 * Executar a mutaçao
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
