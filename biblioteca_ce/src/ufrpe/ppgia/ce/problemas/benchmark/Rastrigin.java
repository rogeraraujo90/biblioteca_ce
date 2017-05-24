package ufrpe.ppgia.ce.problemas.benchmark;

import java.lang.Math;
import ufrpe.ppgia.ce.algoritmoGenetico.GAReal;
import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal; 
 
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
	
	public static void main(String[] args) {
		Rastrigin test = new Rastrigin();
		for(int i = 0; i < 1; i++) {
			long timeIn = System.nanoTime();
			test.executar();
			double dt = (System.nanoTime() - timeIn) * (1e6);
			System.out.println("Total Time: " + dt);
		}
	}
}