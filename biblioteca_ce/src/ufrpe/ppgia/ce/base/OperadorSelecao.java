package ufrpe.ppgia.ce.base;

import java.util.List;

public interface OperadorSelecao <S> {
	public List<S> selecionar(List<S> solutionSet);
}
