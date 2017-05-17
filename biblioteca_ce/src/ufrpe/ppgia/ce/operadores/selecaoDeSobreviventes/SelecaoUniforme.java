package biblioteca_ce;

import java.util.ArrayList;
import java.util.Random;


public class SelecaoUniforme {
	
	Random r = new Random();
	ArrayList<String> pais = new ArrayList<String>();

	public ArrayList<String> pais (ArrayList<String> populacao, int numeroPais) {
		for (int i = 0; i<numeroPais;i++){
			int aux = r.nextInt(populacao.size());
			pais.add(populacao.get(aux));
		}
		return pais;
	}
}
