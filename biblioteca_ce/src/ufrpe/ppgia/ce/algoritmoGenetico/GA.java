package ufrpe.ppgia.ce.algoritmoGenetico;

import java.util.List;

import ufrpe.ppgia.ce.base.AE;
import ufrpe.ppgia.ce.base.solucao.SolucaoBinaria;

public class GA extends AE<SolucaoBinaria>{

	@Override
	public List<SolucaoBinaria> inicializar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void avaliar(SolucaoBinaria s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean parar(List<SolucaoBinaria> pop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SolucaoBinaria[] selecionarPais(List<SolucaoBinaria> pop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SolucaoBinaria> selecionarSovreviventes(List<SolucaoBinaria> pop, SolucaoBinaria[] descendentes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolucaoBinaria[] recombinar(SolucaoBinaria[] pais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolucaoBinaria executarMutacao(SolucaoBinaria pai) {
		// TODO Auto-generated method stub
		return null;
	}

}
