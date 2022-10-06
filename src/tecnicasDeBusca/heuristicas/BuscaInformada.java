package tecnicasDeBusca.heuristicas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Estados.Estado;

/**
 * Esta classe modela os aspectos comuns as estrat�gias de busca informada,
 * que explorar o espa�o de estados a partir de um estado inicial e constr�i a
 * �rvore busca expandindo seus nodos at� que se alcance um estado objetivo.
 * 
 *
 */
public abstract class BuscaInformada {
	
	protected String nomeDaEstrategia;	// nome da estrat�gia de busca utilizada
	protected Estado<?> eInicial;		// representa o estado inicial da busca
	protected Estado<?> eObjetivo;		// representa um estado do problema que contenha os objetivos ou seja, em si mesmo, a meta para a busca.
	protected List<Estado<?>> caminho;	// cont�m o caminho da solu��o desde a raiz (inicio) 
	
	/**
	 * Construtor padr�o.
	 */
	public BuscaInformada() {
		this(null,null);
	}
	
	public BuscaInformada(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		this.nomeDaEstrategia = "Busca informada";
		eInicial = estadoInicial;
		eObjetivo = estadoMeta;
		caminho = new ArrayList<Estado<?>>();
	}

	/**
	 * Recupera o nome da estrat�gia de busca.
	 * @return nome da estrat�gia de busca
	 */
	public String getNomeDaEstrategia() {
		return nomeDaEstrategia;
	}
	
	/**
	 * Define o estado inicial da busca, isto �, o ponto de partida do processo
	 * @param estadoInicial um dos estados poss�veis do problema
	 */
	public void setInicio(Estado<?> estadoInicial) {
		this.eInicial = estadoInicial;
	}
	
	/**
	 * Define o estado objetivo para o processo de busca, sendo por dado por um
	 * estado que apresente os objetivos desejados ou que seja, em si mesmo, um
	 * estado meta. 
	 * @param estadoMeta um estado poss�vel do problema que atende os objetivos
	 */
	public void setObjetivo(Estado<?> estadoMeta) {
		this.eObjetivo = estadoMeta;
	}
	
	/**
	 * Recupera o estado considerado como meta para a busca, aquele que cont�m
	 * ou atende os objetivos estabelecidos para o processo de busca.
	 * @return um estado solu��o para o problema apresentado.
	 */
	public Estado<?> getObjetivo() {
		return eObjetivo;
	}
	
	/**
	 * M�todo que realiza a explora��o do espa�o de busca, tomando como partida
	 * o estado inicial e seguindo, estado ap�s estado, na constru��o do caminho
	 * que leva a um estado solu��o (objetivo).
	 */
	public abstract void buscar();
	
	/**
	 * Recupera o caminho correspondente a solu��o encontrada pela busca.
	 * @return
	 */
	public List<Estado<?>> getCaminhoSolucao() {
		return caminho;
	}
	
}

class OrdenarPorCusto implements Comparator<Estado<?>> {

	@Override
	public int compare(Estado<?> estado1, Estado<?> estado2) {
		return estado1.getCusto() - estado2.getCusto();
	}

}

class OrdenarPorAvaliacao implements Comparator<Estado<?>> {

	@Override
	public int compare(Estado<?> estado1, Estado<?> estado2) {
		return estado1.getAvaliacao() - estado2.getAvaliacao();
	}

}

class OrdenarPorCustoMaisAvaliacao implements Comparator<Estado<?>> {

	@Override
	public int compare(Estado<?> estado1, Estado<?> estado2) {
		int f = (estado1.getCusto() + estado1.getAvaliacao()) - (estado2.getCusto() + estado2.getAvaliacao());		
		return (f != 0) ? f : estado2.getNivel() - estado1.getNivel();
	}

}