package ufrpe.ppgia.ce.problemas.benchmark;

import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Comparator;
import java.util.List;

import ufrpe.ppgia.ce.algoritmoGenetico.GAReal;
import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;
import ufrpe.ppgia.ce.util.ManipuladorArquivo; 
 
/**
 * Calculates the fitness of Rastrigin's function. 
 */ 
public class Rastrigin extends GAReal implements Problema<SolucaoReal> {
	@Override
	public void avaliar(SolucaoReal solucao) {
		Double fitness = 10.0 * solucao.getN(); 
		   
		  /* Compute the fitness function for Rastrigin's Function:
		   *  
		   * f(X) = 10n + sigma (Xi^2 - 10cos(2*pi*Xi)) 
		   *  
		   */ 
		  for (int i = 0; i < solucao.getN(); i++) { 
			  /* Calculate the fitness */ 
			  double parameter = solucao.getValor(i);
			  fitness += Math.pow(parameter, 2.0) - 10.0 * Math.cos(2 * Math.PI * parameter); 
		  }
		  
		  solucao.setFitness(fitness);
	}
	
	private String executar(int tamanhoPop, double pm, double pc) {
		long timeIn = System.nanoTime();
		
		this.getOperadorMutacao().setPm(pm);
		this.getOperadorCruzamento().setPr(pc);
		this.setTamanhoPop(tamanhoPop);
		
		List<SolucaoReal> pop = inicializar();
		for (SolucaoReal individuo : pop) {
			avaliar(individuo);
		}
		
		int iteracao = 0;
		while (!parar(pop) && iteracao < 10000) {
			iteracao++;
			SolucaoReal[] pais = selecionarPais(pop);
			SolucaoReal[] descendentes = recombinar(pais);
			
			for(int i = 0; i < descendentes.length; i++) {
				descendentes[i] = executarMutacao(descendentes[i]);
				avaliar(descendentes[i]);
			}
			
			pop = selecionarSovreviventes(pop, descendentes);
		}
		
		double dt = (System.nanoTime() - timeIn) * (1e6);
		System.out.println("Total Time: " + dt);
		
		System.out.println("Iteracao: " + iteracao);
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		System.out.println("Melhor Fitness " +  pop.get(0).getFitness());
		
		return pop.get(0).getFitness() + "," + dt + "," + iteracao + "\n";
	}
	
	public static void main(String[] args) {
		Rastrigin test = new Rastrigin();
		
		int populacao1 = 50; 
		int populacao2 = 100; 
		int populacao3 = 30;
		
		double pm1 = 0.1d;
		double pm2 = 0.2d;
		double pm3 = 0.05d;
		
		double pc1 = 0.9d;
		double pc2 = 0.7d;
		double pc3 = 1d; 
		
		String resultados = "";

		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm1, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm1, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm1, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm2, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm2, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm2, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm3, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm3, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao1, pm3, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm1, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm1, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm1, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm2, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm2, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm2, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm3, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm3, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao2, pm3, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm1, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm1, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm1, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm2, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm2, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm2, pc3);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm3, pc1);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm3, pc2);
		}
		
		for(int i = 0; i < 30; i++) {
			resultados += test.executar(populacao3, pm3, pc3);
		}
		
		System.out.println("\n####### resultados #####\n" + resultados);
		
		try {
			ManipuladorArquivo.escritor(System.getProperty("user.home") + File.separator + "ResultadosGA.csv", resultados);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}