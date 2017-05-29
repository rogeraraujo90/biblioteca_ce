package ufrpe.ppgia.ce.problemas.benchmark;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;
import ufrpe.ppgia.ce.programacaoEvolucionaria.PE;
import ufrpe.ppgia.ce.util.GriewankFunction;
import ufrpe.ppgia.ce.util.ManipuladorArquivo;

public class Griewank extends PE implements Problema<SolucaoReal> {
	private int cont = 1, contAvaliacoes = 0;
	
	private double melhorFitness = 200;

	@Override
	public void avaliar(SolucaoReal solucao) {
		
		GriewankFunction griewankFunction = new GriewankFunction();
		
		solucao.setFitness(griewankFunction.apply(solucao.getCromossomo()));
		
		this.contAvaliacoes++;
	}
	
	@Override
	public boolean parar(List<SolucaoReal> pop) {
		/*
		 * Organizar a população em ordem crescente
		 */
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		
		double melhorFitnessAtual = pop.get(0).getFitness();
		
		if (melhorFitnessAtual < this.melhorFitness)
			this.melhorFitness = melhorFitnessAtual;
		
		System.out.println(" Fitness: " + melhorFitnessAtual);
		
		return Math.abs(melhorFitnessAtual - 0) < 0.009;
	}
	
	private String executar(int quantidadeVezes, int tamanhoPop, double pm, double pc) {
		
		this.getOperadorMutacao().setPm(pm);
//		this.getOperadorCruzamento().setPr(pc);
		this.setTamanhoPop(tamanhoPop);
		
		double melhorTempo = 10000.0; // Unidade em milisegundos (10000 = 10 segundos)
		this.melhorFitness = 200; // Resetando o melhor fitness
		
		for(int i = 0; i < quantidadeVezes; i++) {
			long timeIn = System.nanoTime();
			
			this.executar();
			
			double deltaTime = ( System.nanoTime() - timeIn ) / (1e6); // Convertendo de nanosegundo para milesegundo
			
			if (deltaTime < melhorTempo) {
				melhorTempo = deltaTime;
			}
			
		}// Fim for
		
		System.out.println("\n#########################");
		System.out.println("Execucao: " + cont++);
		System.out.println("Melhor fitness geral: " + this.melhorFitness);
		System.out.println("Melhor tempo geral: " + melhorTempo + " ms");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.melhorFitness + "," + melhorTempo + "," + contAvaliacoes + "\n";
		
	}

	public static void main(String[] args) {
		
		Griewank griewankObject = new Griewank();
		
		int quantidadeExecucao = 30, 
				populacao1 = 50, 
				populacao2 = 100, 
				populacao3 = 150;
		
		double pm1 = 0.03, 
				pm2 = 0.06, 
				pm3 = 0.1;
		
		double pc1 = 0.8,
				pc2 = 0.9, 
				pc3 = 0.95; 
		
		String resultados = "";
		
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm1, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm1, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm1, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm2, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm2, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm2, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm3, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm3, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao1, pm3, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm1, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm1, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm1, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm2, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm2, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm2, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm3, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm3, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao2, pm3, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm1, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm1, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm1, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm2, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm2, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm2, pc3);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm3, pc1);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm3, pc2);
		resultados += griewankObject.executar(quantidadeExecucao, populacao3, pm3, pc3);
		
		System.out.println("\n####### resultados #####\n" + resultados);
		
		try {
			ManipuladorArquivo.escritor(System.getProperty("user.home") + File.separator + "ResultadosGA.csv", resultados);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// Fim main
	
}// Fim classe
