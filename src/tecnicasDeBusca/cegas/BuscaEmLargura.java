package tecnicasDeBusca.cegas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Estados.Estado;


public class BuscaEmLargura extends BuscaCega {
	
	private Queue<Estado<?>> eAbertos;

	
	public BuscaEmLargura() {
		this(null,null);
	}
	
	
	public BuscaEmLargura(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		super(estadoInicial,estadoMeta);
		nomeDaEstrategia = "Busca em Largura";
		eAbertos = new LinkedList<Estado<?>>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		while ((eCorrente != null) && (!eCorrente.equals(eObjetivo))) {
			for (Estado<?> estado : (List<Estado<?>>) eCorrente.getSucessores())
				eAbertos.add(estado);
			eCorrente = eAbertos.poll();
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
