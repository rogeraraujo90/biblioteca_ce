package ufrpe.ppgia.ce.operadores.selecaoDeSobreviventes;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ufrpe.ppgia.ce.base.solucao.SolucaoInteira;

public class TesteSelecaoDeSobreviventes {
	@Test
	public void elitismoComFitnessMenorGanhando() {
		SolucaoInteira solucao1 = new SolucaoInteira(1);
		solucao1.setFitness(2);
		
		SolucaoInteira solucao2 = new SolucaoInteira(1);
		solucao2.setFitness(1.54);
		
		SolucaoInteira solucao3 = new SolucaoInteira(1);
		solucao3.setFitness(4.45);
		
		SolucaoInteira solucao4 = new SolucaoInteira(1);
		solucao4.setFitness(0.4);
		
		SolucaoInteira solucao5 = new SolucaoInteira(1);
		solucao5.setFitness(10);
		
		SolucaoInteira solucao6 = new SolucaoInteira(1);
		solucao6.setFitness(2);
		
		SolucaoInteira solucao7 = new SolucaoInteira(1);
		solucao7.setFitness(1.54);
		
		SolucaoInteira solucao8 = new SolucaoInteira(1);
		solucao8.setFitness(4.45);
		
		SolucaoInteira solucao9 = new SolucaoInteira(1);
		solucao9.setFitness(0.4);
		
		SolucaoInteira solucao10 = new SolucaoInteira(1);
		solucao10.setFitness(11);
		
		ArrayList<SolucaoInteira> solucaoList = new ArrayList<>();
		solucaoList.add(solucao10);
		solucaoList.add(solucao9);
		solucaoList.add(solucao8);
		solucaoList.add(solucao7);
		solucaoList.add(solucao6);
		solucaoList.add(solucao5);
		solucaoList.add(solucao4);
		solucaoList.add(solucao3);
		solucaoList.add(solucao2);
		solucaoList.add(solucao1);
		
		SelecaoPorElitismo test = new SelecaoPorElitismo();
		test.setFitnessMenorGanha(true);
		List<SolucaoInteira> result = test.selecionar(solucaoList);
		
		if(! (result.size() == 1 && result.get(0).getFitness() == 0.4)) {
			fail("Selection does not working");
		}
	}
	
	@Test
	public void elitismoComFitnessMairGanhando() {
		SolucaoInteira solucao1 = new SolucaoInteira(1);
		solucao1.setFitness(2);
		
		SolucaoInteira solucao2 = new SolucaoInteira(1);
		solucao2.setFitness(1.54);
		
		SolucaoInteira solucao3 = new SolucaoInteira(1);
		solucao3.setFitness(4.45);
		
		SolucaoInteira solucao4 = new SolucaoInteira(1);
		solucao4.setFitness(0.4);
		
		SolucaoInteira solucao5 = new SolucaoInteira(1);
		solucao5.setFitness(10);
		
		SolucaoInteira solucao6 = new SolucaoInteira(1);
		solucao6.setFitness(2);
		
		SolucaoInteira solucao7 = new SolucaoInteira(1);
		solucao7.setFitness(1.54);
		
		SolucaoInteira solucao8 = new SolucaoInteira(1);
		solucao8.setFitness(4.45);
		
		SolucaoInteira solucao9 = new SolucaoInteira(1);
		solucao9.setFitness(0.4);
		
		SolucaoInteira solucao10 = new SolucaoInteira(1);
		solucao10.setFitness(11);
		
		ArrayList<SolucaoInteira> solucaoList = new ArrayList<>();
		solucaoList.add(solucao10);
		solucaoList.add(solucao9);
		solucaoList.add(solucao8);
		solucaoList.add(solucao7);
		solucaoList.add(solucao6);
		solucaoList.add(solucao5);
		solucaoList.add(solucao4);
		solucaoList.add(solucao3);
		solucaoList.add(solucao2);
		solucaoList.add(solucao1);
		
		SelecaoPorElitismo test = new SelecaoPorElitismo();
		test.setFitnessMenorGanha(false);
		List<SolucaoInteira> result = test.selecionar(solucaoList);
		
		if(! (result.size() == 1 && result.get(0).getFitness() == 11)) {
			fail("Selection does not working");
		}
	}

}
