package Estados;

import java.util.List;

public abstract class Estado<T> {
	
	private int id = 0;					// identificador do estado
	private int nivel;					// n�vel em que o estado se encontra na �rvore de busca
	private Estado<?> ancestral = null;	// referencia o n� pai na �rvore de busca
	
	protected int avaliacao = 0;		// valor de avalia��o do estado em rela��o a um objetivo
	protected int custo = 0;			// esfor�o despendido para alcan�ar o estado
	protected int f = 0;				// valor representativo do esfor�o combinado a avalia��o
		
	/**
	 * Define o n�mero identificador do estado.
	 * @param identificador do estado
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Recupera o identificador do estado.
	 * @return identificador do estado
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o n�vel que o estado (nodo) ocupa na �rvore de busca.
	 * @param n�vel do estado na �rvore de busca
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * M�todo de acesso que permite recuperar qual o n�vel (profundidade em
	 * rela��o a raiz) que este estado ocupa na �rvore de busca.
	 * @return n�vel do nodo na �rvore de busca
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Define qual o estado que originou o estado corrente dentro do espa�o
	 * de busca, ou seja, qual � o seu estado predecessor.
	 * @param refer�ncia ao estado ancestral
	 */
	public void setAncestral(Estado<?> estado) {
		ancestral = estado;
	}
	
	/**
	 * Recupera o estado predecessor deste estado no espa�o de busca.
	 * @return refer�ncia ao estado anterior, sob o qual a a��o resultou no estado atual
	 */
	public Estado<?> getAncestral() {
		return ancestral;
	}	
	
	/**
	 * Define um valor que expresse a avalia��o deste estado.
	 * @param avaliacao
	 */
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	/**
	 * Retorna a avalia��o do estado, uma quantifica��o para sua condi��o.
	 * @return valor de avalia��o do estado.
	 */
	public int getAvaliacao() {
		return avaliacao;
	}

	/**
	 * Define um valor que represente o custo acumulado para se alcan�ar este
	 * estado dentro do espa�o.
	 * @param custo valor acumulado do esfor�o realizado.
	 */
	public void setCusto(int custo) {
		this.custo = custo;
	}	
	
	/**
	 * Recupera o custo despendido para se alcan�ar este estado dentro do espa�o.
	 * @return valor acumulado do esfor�o realizado.
	 */
	public int getCusto() {
		return custo;
	}
	
	/**
	 * Define o valor da fun��o F para o estado corrente.
	 * @param valor de F(e).
	 */
	public void setF(int f) {
		this.f = f;
	}	
	
	/**
	 * Recupera o valor da fun��o F para o estado.
	 * @return valor da fun��o F(e).
	 */
	public int getF() {
		return f;
	}

	public abstract int heuristica(T cfg);
	
	/**
	 * Status, descri��o ou configura��o representativa para o estado.
	 * @param informa��es que caracterizam este estado 
	 */
	public abstract void setEstado(T cfg);
	
	/**
	 * Retorna a configura��o do estado 
	 * @return todas as informa��es que caracterizam este estado
	 */
	public abstract T getEstado();
		
	/**
	 * Fun��o que gera os estados sucessores de acordo com o problema
	 * @return lista de estados sucessores (adjacentes) a partir deste estado
	 */
	public abstract List<?> getSucessores();

	/**
	 * Permite verificar se este estado � igual a outro.
	 * @param o estado qual se deseja comparar com este
	 * @return true ou false
	 */
	public abstract boolean equals(Object estado);
	
	/**
	 * Retorna uma representa��o do estado numa forma textual e que possa ser
	 * apresentada na console, por exemplo.
	 * @return uma String representativa contendo as informa��es descritivas do estado
	 */
	public abstract String toString();
	
}