package ufrpe.ppgia.ce.operadores.mutacao;

import ufrpe.ppgia.ce.base.OperadorMutacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;

public class MutacaoUniforme implements OperadorMutacao<SolucaoReal> {
	
	private double PM = 0.1d;

	@Override
	public SolucaoReal executarMutacao(SolucaoReal pai) {
		/*
		 * Recebe um pai e retorna um novo indivíduo mutado a partir dos gentes do pai
		 * */
		
		/*
		 * O objeto mutacao receberá parte dos genes do pai e alguns genes mutados
		 * */
		SolucaoReal mutacao = pai.clone();
		
		for (int i = 0; i < pai.getN(); i++) {
			if (Math.random() <= PM) {
				/*
				 * Um novo valor para um gene é gerado caso ele tenha passado pelo teste do PM
				 * */
				double novoValor = Math.random() * ( pai.getLimiteSuperior(i) - pai.getLimiteInferior(i) ) + pai.getLimiteInferior(i);
				
				mutacao.setValor(i, novoValor);
				
			} else {
				/*
				 * Caso o gene não tenha passado pelo teste do PM ele recebe o gene diretamente do pai
				 * */
				mutacao.setValor(i, pai.getValor(i));

			}
		}
		
		return mutacao;
	}
	
	public void setPm(double pm) {
		this.PM = pm;
	}

//	public static void main(String[] args) {
//		
//		/*
//		 * Metodo main para executar testes
//		 * */
//		
//		Solucao<Double> solucaoReal = new SolucaoReal(10);
//		
//		MutacaoUniforme mutacaoUniforme = new MutacaoUniforme();
//		
//		System.out.println("Antes da mutacao: ");
//		for (int i = 0; i < 10; i++) {
//			System.out.println(solucaoReal.getValor(i));
//			
//		}
//		
//		Solucao<Double> mutado = mutacaoUniforme.executarMutacao( (SolucaoReal) solucaoReal);
//		
//		System.out.println("Depois da mutacao: ");
//		for (int i = 0; i < 10; i++) {
//			System.out.println(mutado.getValor(i));
//			
//		}
//		
//	}

}