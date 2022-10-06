package tecnicasDeBusca.heuristicas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Estados.Estado;

public class BestFirst extends BuscaInformada {
	
	private Queue<Estado<?>> eAbertos;

	/**
	 * Construtor padr�o.
	 */
	public BestFirst() {
		this(null,null);
	}
	
	/**
	 * Cria uma nova inst�ncia de Busca em Largura e define os estados inicial
	 * e objetivo para o processo.
	 * @param estadoInicial estado inicial de busca
	 * @param estadoMeta estado que cont�m os objetivos da busca
	 */
	public BestFirst(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		super(estadoInicial,estadoMeta);
		nomeDaEstrategia = "Best-First Search (Busca pelo Melhor-Primeiro)";
		eAbertos = new LinkedList<Estado<?>>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		while ((eCorrente != null) && (!eCorrente.equals(eObjetivo))) {
			List<?> eSucessores = eCorrente.getSucessores();
			Collections.sort((List<Estado<?>>) eSucessores, new OrdenarPorAvaliacao());
			for (Estado<?> estado : (List<Estado<?>>) eSucessores)
				eAbertos.add(estado);
			eCorrente = eAbertos.poll();
		}
		// Se o la�o foi encerrado por um estado v�lido ...
		if (eCorrente != null) {
			// ent�o constru�mos o caminho da solu��o (da folha at� a raiz)
			caminho.add(eCorrente);
			while (eCorrente.getAncestral() != null) {
				eCorrente = eCorrente.getAncestral();
				caminho.add(eCorrente);
			}
			Collections.reverse(caminho);
		}		
	}

}
