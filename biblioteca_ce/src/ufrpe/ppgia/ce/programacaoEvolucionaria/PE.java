package ufrpe.ppgia.ce.programacaoEvolucionaria;

import java.util.List;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class PE extends AE<SolucaoReal>{
	
	public void executar() {
		List<SolucaoReal> pop = inicializar();
		for (SolucaoReal solucaoReal : pop) {
			avaliar(solucaoReal);
		}
		while (!parar(pop)) {
			SolucaoReal[] descendentes = new SolucaoReal[pop.size()];
			
			for (SolucaoReal solucaoReal : pop) {
				
				SolucaoReal descendente = executarMutacao(solucaoReal);
				avaliar(descendente);
				
				descendentes[descendentes.length] = descendente;
				
			}
			
			pop = selecionarSovreviventes(pop, descendentes);
			
			
		}
	}

	@Override
	public List<SolucaoReal> inicializar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void avaliar(SolucaoReal s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean parar(List<SolucaoReal> pop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SolucaoReal[] selecionarPais(List<SolucaoReal> pop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SolucaoReal> selecionarSovreviventes(List<SolucaoReal> pop, SolucaoReal[] descendentes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolucaoReal[] recombinar(SolucaoReal[] pais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("teste");
	}

}
