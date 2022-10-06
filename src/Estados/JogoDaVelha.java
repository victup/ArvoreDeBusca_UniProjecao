package Estados;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe modela o jogo de tabuleiro conhecido como "Jogo da Velha" ou
 * Tic-Tac-Toe na forma de um estado, de modo que seja poss�vel representar as
 * partidas como um conjunto de possibilidades (espa�o de estados).
 * Nele temos um tabuleiro de tr�s linhas e tr�s colunas, onde dois jogadores
 * se enfrentam marcando um ap�s o outro as posi��es livres com um s�mbolo que
 * os representa.
 * O jogo termina quando um dos jogadores consegue formar uma trinca alinhada ou
 * quando n�o h� mais uma posi��o vaga que possa ser preenchida.
 *  
 * Ex:
 *     |   |          |   |          |   |          |   |          |   |          |   |          |   |
 *   X | O |        X | O |        X | O |        X | O |        X | O |        X | O |        X | O |    
 * ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----
 *     |   |          |   |          |   |        O |   |        O |   |        O | O |        O | O |
 * ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----  ----+---+----
 *     |   |          |   |        X |   |        X |   |        X |   | X      X |   | X    --X-+-X-+-X--
 *     |   |          |   |          |   |          |   |          |   |          |   |          |   |
 *     
 *
 */
public class JogoDaVelha extends Estado<char[]> {
	
	private char jogador;
	private static int contGlobal = 0;	// contador global representando a qde de inst�ncias
	private char[] tabuleiro;			// vetor que descreve a posi��o das pe�as do quebra-cabe�as
	
	/**
	 * Cria uma inst�ncia para representar uma configura��o para o tabuleiro do quebra-cabe�as
	 */
	public JogoDaVelha() {
		this(new char[9], null, 0);
	}
	
	/**
	 * Permite criar um novo estado, j� definindo a configura��o para o tabuleiro 
	 * @param cfgTabuleiro vetor de caracteres que representa a disposi��o das pe�as
	 */
	public JogoDaVelha(char[] cfgTabuleiro) {
		this(cfgTabuleiro, null, 0);
	}
	
	/**
	 * Permite criar um novo estado, j� definindo a configura��o para o tabuleiro 
	 * @param cfgTabuleiro vetor de caracteres que representa a disposi��o das pe�as
	 * @param ancestral referencia ao nodo pai do estado corrente
	 * @param nivel altura do nodo em rela��o a raiz da �rvore
	 */
	public JogoDaVelha(char[] cfgTabuleiro, JogoDaVelha ancestral, int nivel) {
		setId(contGlobal++);
		setNivel(nivel);
		setAncestral(ancestral);
		tabuleiro = cfgTabuleiro.clone();
	}
	
	/**
	 * Define o s�mbolo do pr�ximo jogador (jogador 'X' ou jogador 'O').
	 */
	public void setJogador(char simbolo) {
		jogador = simbolo;
	}
	
	/**
	 * Recupera o s�mbolo do pr�ximo jogador.
	 * @return s�mbolo 'X' ou 'O'
	 */
	public char getJogador() {
		return jogador;
	}
	
	/**
	 * Define o posicionamento das pe�as do quebra-cabe�as, determinando a configura��o
	 * que ser� assumida pelo tabuleiro. 
	 * @param cfgTabuleiro vetor representando a posi��o dos elementos no tabuleiro 
	 */
	@Override
	public void setEstado(char[] cfgTabuleiro) {
		for (int i = 0; i < cfgTabuleiro.length; i++) {
			this.tabuleiro[i] = cfgTabuleiro[i];	
		}
	}
	
	/**
	 * Retorna a configura��o das pe�as no tabuleiro do quebra-cabe�as 
	 * @return vetor representando a configura��o do tabuleiro
	 */
	@Override
	public char[] getEstado() {
		char[] cfgTabuleiro = new char[9];
		for (int i = 0; i < tabuleiro.length; i++) {
			cfgTabuleiro[i] = tabuleiro[i];
		}
		return cfgTabuleiro;
	}

	/**
	 * Fun��o que gera os estados sucessores de acordo com os diferentes movimentos poss�veis
	 * a partir da configura��o do tabuleiro.
	 * @return lista de estados sucessores
	 */
	@Override
	public List<JogoDaVelha> getSucessores() {
		List<JogoDaVelha> estadosSucessores = new ArrayList<JogoDaVelha>();
		
		char proximoJogador = (getJogador() == 'O') ? 'X' : 'O';
		
		char[] cfg = tabuleiro.clone();
		for (int k = 0; k < 9; k++)
			if (tabuleiro[k] == ' ') {
				for (int i = 0; i < cfg.length; i++)
					cfg[i] = (i != k) ? tabuleiro[i] : jogador;
				JogoDaVelha sucessor = new JogoDaVelha(cfg,this,this.getNivel()+1);
				sucessor.setJogador(proximoJogador);
				estadosSucessores.add(sucessor);
			}
		
		return estadosSucessores;
	}
	
	public int heuristica(char[] cfgEstadoMeta) {
		return 0;
	}

	/**
	 * Retorna uma String correspondente a posi��o das pe�as no tabuleiro do quebra-cabe�as 
	 */
	public String toString() {
		String msg = "";
		msg += " " + tabuleiro[0] + " | " + tabuleiro[1] + " | " + tabuleiro[2] + "\n";
		msg += "---+---+---\n";
		msg += " " + tabuleiro[3] + " | " + tabuleiro[4] + " | " + tabuleiro[5] + "   Nodo #" + getId() + "  N�vel: " + getNivel() + "\n";
		msg += "---+---+---\n";
		msg += " " + tabuleiro[6] + " | " + tabuleiro[7] + " | " + tabuleiro[8] + "\n";
		return msg;
	}

	/**
	 * Implementa a compara��o entre dois estados, sendo baseada na disposi��o das pe�as no
	 * tabuleiro do quebra-cabe�as
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogoDaVelha other = (JogoDaVelha) obj;
		for (int i = 0; i < tabuleiro.length; i++)
			if ((tabuleiro[i] != other.tabuleiro[i]) && (other.tabuleiro[i] != '?'))
				return false;
		
		return true;
	}
	
}