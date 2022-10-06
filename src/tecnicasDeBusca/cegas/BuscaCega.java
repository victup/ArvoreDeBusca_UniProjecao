package tecnicasDeBusca.cegas;

import java.util.ArrayList;
import java.util.List;

import Estados.Estado;

public abstract class BuscaCega {
	
	protected String nomeDaEstrategia;	// nome da estrat�gia de busca utilizada
	protected Estado<?> eInicial;		// representa o estado inicial da busca
	protected Estado<?> eObjetivo;		// representa um estado do problema que contenha os objetivos ou seja, em si mesmo, a meta para a busca.
	protected List<Estado<?>> caminho; // cont�m o caminho da solu��o desde a raiz (inicio) 
	
	
	public BuscaCega() {
		this(null,null);
	}
	
	public BuscaCega(Estado<?> estadoInicial, Estado<?> estadoMeta) {
		this.nomeDaEstrategia = "Busca cega";
		eInicial = estadoInicial;
		eObjetivo = estadoMeta;
		caminho = new ArrayList<Estado<?>>();
	}

	public String getNomeDaEstrategia() {
		return nomeDaEstrategia;
	}

	public void setInicio(Estado<?> estadoInicial) {
		this.eInicial = estadoInicial;
	}
	
	
	public void setObjetivo(Estado<?> estadoMeta) {
		this.eObjetivo = estadoMeta;
	}
	
	
	public Estado<?> getObjetivo() {
		return eObjetivo;
	}
	
	
	public abstract void buscar();
	
	
	public List<Estado<?>> getCaminhoSolucao() {
		return caminho;
	}
	
}