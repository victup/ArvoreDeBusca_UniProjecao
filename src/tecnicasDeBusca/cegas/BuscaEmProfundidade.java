package tecnicasDeBusca.cegas;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import Estados.Estado;

public class BuscaEmProfundidade extends BuscaCega {
	
	protected Stack<Estado<?>> eAbertos;

	public BuscaEmProfundidade() {
		this(null,null);
	}
	
	public BuscaEmProfundidade(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		super(estadoInicial,estadoMeta);
		super.nomeDaEstrategia = "Busca em Profundidade";
		eAbertos = new Stack<Estado<?>>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		while ((eCorrente != null) && (!eCorrente.equals(eObjetivo))) {
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
