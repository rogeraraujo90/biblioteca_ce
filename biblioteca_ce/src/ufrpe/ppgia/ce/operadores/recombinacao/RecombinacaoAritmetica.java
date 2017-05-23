package ufrpe.ppgia.ce.operadores.recombinacao;

import java.util.ArrayList;
import java.util.Random;

import ufrpe.ppgia.ce.base.OperadorRecombinacao;
import ufrpe.ppgia.ce.base.solucao.SolucaoReal;


public class RecombinacaoAritmetica implements OperadorRecombinacao<SolucaoReal> {
	private Random random = new Random();
	private double pr = 0.7; 
	
	private void setPr(double pr) {
		this.pr = pr;
	}
	
	public SolucaoReal[] recombinar(SolucaoReal pai1, SolucaoReal pai2) {
		// TODO Auto-generated method stub
		int k =  random.nextInt(pai1.getN()-1)+1;
		
				
		SolucaoReal f1 = pai1.clone();
		SolucaoReal f2 = pai2.clone();
		
		
		if(Math.random() <= pr) {
					
			
		for(int i=0; i<k; i++){
			f1.setValor(i, pai1.getValor(i));
			f2.setValor(i, pai2.getValor(i));
		}
		
		for(int i=k; i<pai1.getN(); i++){
			double media = (pai1.getValor(i)+pai2.getValor(i))/2;
			f1.setValor(i, media);
			f2.setValor(i, media);
		}
		
	}
		SolucaoReal[] resposta = {f1,f2};
	
		return resposta;		
		
}
	
	public static void main(String[] args) {
		
		SolucaoReal pai1 = new SolucaoReal(6);
		SolucaoReal pai2 = new SolucaoReal(6);
		
		for (int i=0; i<6; i++) {
			pai1.setValor(i, (double)1+i);
			pai2.setValor(i, (double)2+i);
		}
		
		RecombinacaoAritmetica ra = new RecombinacaoAritmetica();
		SolucaoReal[] filhos = ra.recombinar(pai1, pai2);
		
		ArrayList<Double> filho1 = new ArrayList<>();
		ArrayList<Double> filho2 = new ArrayList<>();
		for (int i=0; i<6; i++){
			filho1.add(filhos[0].getValor(i));
			filho2.add(filhos[1].getValor(i));
		}
		

		System.out.println(filho1);
		System.out.println(filho2);
		
		
	}

}