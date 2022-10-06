package tecnicasDeBusca.heuristicas;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import Estados.Estado;

public class AStar extends BuscaInformada {

	private Stack<Estado<?>> eAbertos;

	/**
	 * Construtor padr�o.
	 */
	public AStar() {
		this(null,null);
	}
	
	/**
	 * Cria uma nova inst�ncia da Busca �tima (A*) e define os estados inicial
	 * e objetivo para o processo.
	 * @param estadoInicial estado inicial de busca
	 * @param estadoMeta estado que cont�m os objetivos da busca
	 */
	public AStar(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		super(estadoInicial,estadoMeta);
		nomeDaEstrategia = "Busca �tima - A* (A-Star)";
		eAbertos = new Stack<Estado<?>>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void buscar() {
		Estado<?> eCorrente = eInicial;
		while ((eCorrente != null) && (!eCorrente.equals(eObjetivo))) {
			List<?> eSucessores = eCorrente.getSucessores();			
			for (Estado<?> estado : (List<Estado<?>>) eSucessores)
				eAbertos.push(estado);
			Collections.sort((List<Estado<?>>) eAbertos, new OrdenarPorCustoMaisAvaliacao());
			Collections.reverse((List<Estado<?>>) eAbertos);
			eCorrente = eAbertos.pop();
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
