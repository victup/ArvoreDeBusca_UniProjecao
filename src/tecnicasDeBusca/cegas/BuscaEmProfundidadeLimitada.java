package tecnicasDeBusca.cegas;

import java.util.Collections;
import java.util.List;

import Estados.Estado;

public class BuscaEmProfundidadeLimitada extends BuscaEmProfundidade {
	
	private int limite;

	
	public BuscaEmProfundidadeLimitada() {
		this(null,null,10);
	}
	
	
	public BuscaEmProfundidadeLimitada(Estado<?> estadoInicial, Estado<?> estadoMeta, int nivelLimite) {
		super(estadoInicial,estadoMeta);
		super.nomeDaEstrategia = "Busca em Profundidade Limitada (at� " + limite + " n�veis)";
		this.limite = nivelLimite;
	}
	
	
	public int getLimite() {
		return limite;
	}

	
	public void setLimite(int limite) {
		this.limite = limite;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		while ((eCorrente != null) && (!eCorrente.equals(eObjetivo))) {
			if (eCorrente.getNivel() < limite)
				for (Estado<?> estado : (List<Estado<?>>) eCorrente.getSucessores())
					eAbertos.push(estado);
			eCorrente = eAbertos.pop();
		}
		
		if (eCorrente != null) {
			
			caminho.add(eCorrente);
			while (eCorrente.getAncestral() != null) {
				eCorrente = eCorrente.getAncestral();
				caminho.add(eCorrente);
			}
			Collections.reverse(caminho);
		}
	}
	
}
