package ufrpe.ppgia.ce.base;

import java.util.List;

public abstract class AE <S>{
	
	
	public void executar() {
		List<S> pop = inicializar();
		for (S s : pop) {
			avaliar(s);
		}
		while (!parar(pop)) {
			S[] pais = selecionarPais(pop);
			S[] descendentes = recombinar(pais);
			descendentes[0] = executarMutacao(descendentes[0]);
			descendentes[1] = executarMutacao(descendentes[1]);
			avaliar(descendentes[0]);
			avaliar(descendentes[1]);
			pop = selecionarSovreviventes(pop, descendentes);
		}
	}
	
	public abstract List<S> inicializar();
	
	public abstract void avaliar(S s);
	
	public abstract boolean parar(List<S> pop);
	
	public abstract S[] selecionarPais(List<S> pop);
	
	public abstract List<S> selecionarSovreviventes(List<S> pop, S[] descendentes);
	
	public abstract S[] recombinar(S[] pais);
	
	public abstract S executarMutacao(S pai);
	
}
