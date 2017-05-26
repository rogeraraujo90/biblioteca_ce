package ufrpe.ppgia.ce.problemas.benchmark;

import java.util.Comparator;
import java.util.List;

import ufrpe.ppgia.ce.base.Problema;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;
import ufrpe.ppgia.ce.programacaoEvolucionaria.PE;

public class Griewank extends PE implements Problema<SolucaoReal> {
	private static final double[] x = new double[401];

	@Override
	public void avaliar(SolucaoReal solucao) {
		int cont = -200;
		
		for (int i = 0; i < x.length; i++) {
			x [i] = cont;
			cont++;
		}
		
		int indice = 0;
		double valor = Math.ceil(solucao.getValor(0));
		
		for (int i = 0; i < x.length; i++) {
			if (valor == x[i]) {
				indice  = i+1;
				break;
			}
		}
		
		double p1 = (1+(1/4000) * Math.pow(valor, 2) - Math.cos(valor/Math.sqrt(indice)));
		solucao.setFitness(p1);
	}
	
	
	
	@Override
	public boolean parar(List<SolucaoReal> pop) {
		/*
		 * Organizar a população em ordem crescente
		 */
		pop.sort(Comparator.comparingDouble(SolucaoReal::getFitness));
		
		System.out.println("Melhor Fitnessssss: " + pop.get(0).getFitness());
		
		return pop.get(0).getFitness() == 0;
	}

	public static void main(String[] args) {
		
		Griewank test = new Griewank();
		
		for(int i = 0; i < 1; i++) {
			long timeIn = System.nanoTime();
			
			test.executar();
			
			double dt = (System.nanoTime() - timeIn) * (1e6);
			
			System.out.println("Total Time: " + dt);
		}
	}
	
	
}
